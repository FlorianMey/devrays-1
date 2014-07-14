package app.jaid.devrays.debug;

import com.badlogic.gdx.utils.Array;

public interface Printing {

	public abstract Array<LogContext> getContexts();

	public abstract boolean isDisplayingContextInfo();

	public abstract void print(String message, LogContext context);

}
