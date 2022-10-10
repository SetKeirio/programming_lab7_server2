package commands;

import exceptions.UserDoesNotExist;
import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;
import util.Console;
import util.UserManager;

import java.sql.SQLException;

public class LoginCommand extends AbstractCommand{
    private UserManager userManager;

    public LoginCommand(UserManager userManager) {
        super("login","команда для логина пользователя (ТОЛЬКО СЕРВЕР)");
        this.userManager = userManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (!param.isEmpty() || object != null) {
                throw new WrongElementsCountException();
            }
            if (userManager.loginAndPasswordCheck(user)) {
                ClientOutputBuilder.println("Пользователь " + user.getUsername() + " входит в систему.");
            }
            else{
                throw new UserDoesNotExist();
            }
            return 0;
        } catch (SQLException throwables) {
            Console.printerr("Произошла sql ошибка!");
            return 1;
        }
        catch (UserDoesNotExist e){
            Console.printerr("Такого пользователя не существует!");
            return 2;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 3;
        }
    }
}
