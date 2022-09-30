package commands;

import exceptions.WrongElementsCountException;
import util.ClientOutputBuilder;

/**
 * Command which exits the program no matter what.
 */
public class ExitCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    public ExitCommand() {
        super("exit", "завершить работу клиента (без сохранения в файл)");
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object) {
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
