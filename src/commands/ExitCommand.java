package commands;

import exceptions.WrongElementsCountException;
import util.Console;

/**
 * Command which exits the program no matter what.
 */
public class ExitCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param) {
        try {
            if (!param.isEmpty()) {
                throw new WrongElementsCountException();
            }
            System.exit(0);
            return 0;
        }
        catch (WrongElementsCountException e){
            Console.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
