package commands;

import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;

/**
 * Command which prints all existed commands.
 */
public class HelpCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (!param.isEmpty() || object != null) {
                throw new WrongElementsCountException();
            }
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
