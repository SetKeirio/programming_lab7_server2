package commands;

import core.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import messages.User;
import util.CollectionManager;
import util.ClientOutputBuilder;
import util.DatabaseChanger;

/**
 * Command which removes element from the collection by key.
 */
public class RemoveKeyCommand extends AbstractCommand {
    private CollectionManager cmanager;
    private DatabaseChanger changer;
    /**
     * Each command should be determined only once.
     */
    public RemoveKeyCommand(CollectionManager c, DatabaseChanger changer) {
        super("remove_key {element}", "удалить элемент по ключу");
        cmanager = c;
        this.changer = changer;
    }

    /**
     * Executes the command.
     *
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (param.isEmpty() || object != null){
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
            ClientOutputBuilder.println("LabWork удален.");
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.printerr("Использование команды: " + getName());
        }
        catch (EmptyCollectionException e){
            ClientOutputBuilder.printerr("Коллекция пуста.");
        }
        catch (NumberFormatException e){
            ClientOutputBuilder.printerr("Ключ должен быть числом.");
        }
        catch (LabWorkSearchException e){
            ClientOutputBuilder.printerr("LabWork найти не удалось.");
        }
        return 1;
    }

}
