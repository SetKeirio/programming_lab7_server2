package commands;

import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;

public class ExitServerCommand extends AbstractCommand{

    public ExitServerCommand(){
        super("exit_server", "завершить работу сервера (без сохранения в файл)");
    }

    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (!param.isEmpty() || object != null) {
                throw new WrongElementsCountException();
            }
            ClientOutputBuilder.println("Сервер завершает свою работу.");
            return 0;
        } catch (WrongElementsCountException e) {
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
