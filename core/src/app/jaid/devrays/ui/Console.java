package app.jaid.devrays.ui;

import app.jaid.devrays.debug.*;
import app.jaid.devrays.io.Media;

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
		contexts.addAll(LogContext.INFO, LogContext.CHAT, LogContext.WARNING, LogContext.GUILD, LogContext.TEAM, LogContext.ERROR);

		if (DebugFlags.on)
			contexts.addAll(LogContext.DEBUG);
	}

	public void focus()
	{

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
								CommandExecutor.run(CommandProcessor.process(getText()));
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
								CommandDescriptor resolvedCommand = Shell.getCommandDescriptorByAlias(getText().trim().substring(1));
								if (resolvedCommand != null)
								{
									setText("/" + resolvedCommand.getName() + " ");
									setCursorPosition(getText().length());
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
						String trimmedText = getText().trim();
						Color fontColor = Color.WHITE;

						if (trimmedText.startsWith("/"))
							fontColor = Color.GREEN;

						getStyle().fontColor = fontColor;
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
		lines.setText(lines.getText() + "\n[#" + LogContext.getColor(context) + "]" + message);
		lines.layout();
		lines.layout();
		linesWrapper.layout();
		linesWrapper.setScrollPercentY(100);
	}
}
