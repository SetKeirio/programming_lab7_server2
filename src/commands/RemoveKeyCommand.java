package commands;

import core.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

/**
 * Command which removes element from the collection by key.
 */
public class RemoveKeyCommand extends AbstractCommand {
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */
    public RemoveKeyCommand(CollectionManager c) {
        super("remove_key {element}", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    /**
     * Executes the command.
     *
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param) {
        try {
            if (param.isEmpty()){
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0){
                throw new EmptyCollectionException();
            }
            Integer id = Integer.parseInt(param);
            LabWork lw = cmanager.getByKey(id);
            if (lw == null){
                throw new LabWorkSearchException();
            }
            cmanager.removeFromCollectionByKey(id);
            Console.println("LabWork удален.");
            return 0;
        }
        catch (WrongElementsCountException e){
            Console.printerr("Использование команды: " + getName());
        }
        catch (EmptyCollectionException e){
            Console.printerr("Коллекция пуста.");
        }
        catch (NumberFormatException e){
            Console.printerr("Ключ должен быть числом.");
        }
        catch (LabWorkSearchException e){
            Console.printerr("LabWork найти не удалось.");
        }
        return 1;
    }

}
