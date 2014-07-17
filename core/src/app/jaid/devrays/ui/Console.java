package app.jaid.devrays.ui;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.*;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Console extends Table implements Printing {

	private Array<LogContext> contexts = new Array<LogContext>();
	private Label lines;
	private ScrollPane linesWrapper;
	private TextField textField;

	public Console() {
		contexts.addAll(LogContext.INFO, LogContext.HUMAN_ERROR, LogContext.CHAT, LogContext.GUILD, LogContext.TEAM);

		if (DebugFlags.debugMode)
			contexts.addAll(LogContext.DEBUG, LogContext.WARNING, LogContext.EXCEPTION);
	}

	public void focus()
	{
		focus(null);
	}

	public void focus(String text)
	{
		if (text != null)
			textField.setText(text);

		setVisible(true);
		textField.setVisible(true);
		Core.getHudStage().setKeyboardFocus(textField);
		setCursorToEnd();
		updateInputColor();
	}

	@Override
	public Array<LogContext> getContexts()
	{
		return contexts;
	}

	public Label getLinesLabel()
	{
		return lines;
	}

	public TextField getTextField()
	{
		return textField;
	}

	public void hide()
	{
		setVisible(false);
	}

	public void init()
	{
		textField = new TextField("", Hud.oldSkin) {
			{
				setSize(600, 30);
				setStyle(Hud.gdxSkin.get(TextFieldStyle.class));

				addListener(new InputListener() {

					@Override
					public boolean keyUp(InputEvent event, int keycode)
					{
						if (keycode == Keys.ENTER)
						{
							if (getText().isEmpty())
							{
								hide();
								return true;
							}

							if (getText().startsWith("/"))
							{
								print(">> " + getText(), LogContext.INFO);

								String error = CommandProcessor.checkCommand(getText());

								if (error != null)
								{
									Log.error(error);
									return true;
								}

								CommandExecutor.run(getText());
							}
							else
								Log.chat("You said: " + getText().trim());

							setText("");
							return true;
						}

						if (keycode == Keys.ESCAPE)
						{
							hide();
							return true;
						}

						if (keycode == Keys.SPACE)
						{
							if (getText().startsWith("/"))
							{
								ConsoleShortcut shortcut = Shell.getShortcut(getText().trim());
								if (shortcut != null)
								{
									setText(shortcut.getTo() + " ");
									setCursorToEnd();
								}
							}

							return true;
						}

						return false;
					};
				});
				setTextFieldListener(new TextFieldListener() {

					@Override
					public void keyTyped(TextField textField, char c)
					{
						updateInputColor();
					}

				});
			}
		};

		lines = new Label("- Chat Log -", Hud.oldSkin) {
			{
				setAlignment(Align.top, Align.left);
				align(Align.top);
				setWrap(true);
				setStyle(new LabelStyle(Hud.oldSkin.getFont("mono16"), Color.WHITE));
				getStyle().font.setMarkupEnabled(true);
			}
		};

		linesWrapper = new ScrollPane(lines, Hud.oldSkin) {
			{
				setBounds(0, textField.getHeight(), 600, 200);
			}
		};

		debug();
		addActor(linesWrapper);
		row(); // TODO Fix the overlapping of both rows (current fix: setY(textfield height))
		addActor(textField);
		row();
		hide();
		Log.registerPrinter(this);
	}

	@Override
	public boolean isDisplayingContextInfo()
	{
		return DebugFlags.displayContextInfoIngame;
	}

	@Override
	public void print(String message, LogContext context)
	{
		lines.setText(lines.getText() + "\n[#" + context.getColor() + "]" + message);
		lines.layout();
		lines.layout();
		linesWrapper.layout();
		linesWrapper.setScrollPercentY(100);
	}

	private void setCursorToEnd()
	{
		textField.setCursorPosition(textField.getText().length());
	}

	private void updateInputColor()
	{
		String trimmedText = textField.getText().trim();
		Color fontColor = Color.WHITE;

		if (trimmedText.startsWith("/"))
			fontColor = CommandProcessor.checkCommand(textField.getText()) == null ? Color.GREEN : Color.RED;

		textField.getStyle().fontColor = fontColor;
	}
}
