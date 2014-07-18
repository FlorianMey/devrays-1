package app.jaid.devrays.debug;

import com.badlogic.gdx.utils.Array;

/**
 * Interface that gets implemented by every class that wants to be able to listen to incoming log entries. Needs to be
 * registered at {@link Log#registerPrinter}. Printings can decide what log contexts (see {@link LogContext}) they want
 * to listen to.
 *
 * @author jaid
 */
public interface Printing {

	public abstract Array<LogContext> getContexts();

	public abstract boolean isDisplayingContextInfo();

	public abstract void print(String message, LogContext context);

}
