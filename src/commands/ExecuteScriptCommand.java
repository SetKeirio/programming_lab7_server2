package commands;

import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;

/**
 * Command which executes script from some file.
 */
public class ExecuteScriptCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    public ExecuteScriptCommand() {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (param.isEmpty() || object != null) throw new WrongElementsCountException();
            ClientOutputBuilder.println("Выполняю скрипт '" + param + "'...");
            return 0;
        } catch (WrongElementsCountException exception) {
            ClientOutputBuilder.println("Использование: '" + getName() + "'");
        }
        return 1;
}
}
