package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongElementsCountException;
import messages.User;
import util.CollectionManager;
import util.ClientOutputBuilder;

/**
 * Command which prints all elements of the collection.
 */
public class ShowCommand extends AbstractCommand{
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */
    public ShowCommand(CollectionManager c) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try{
            if (!param.isEmpty() || object != null){
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0){
                throw new EmptyCollectionException();
            }
            ClientOutputBuilder.println(cmanager.showAll());
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
        catch (EmptyCollectionException e){
            ClientOutputBuilder.printerr("Коллекция пуста.");
            return 1;
        }
    }
}
