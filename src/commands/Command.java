package commands;

import messages.User;

/**
 * The highest abstraction for commands.
 */
public interface Command {
    /**
     * @return Command name
     */
    String getName();

    /**
     * @return Command explanation
     */
    String getExplanation();

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    byte exec(String param, Object object, User user);
}
