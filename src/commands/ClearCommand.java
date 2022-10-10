package commands;

import core.LabWork;
import exceptions.NoPermissionsException;
import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;
import util.CollectionManager;
import util.DatabaseChanger;


/**
 * Command which fully clears the collection.
 */
public class ClearCommand extends AbstractCommand{
    private CollectionManager cmanager;
    private DatabaseChanger changer;
    /**
     * Each command should be determined only once.
     */

    public ClearCommand(CollectionManager c, DatabaseChanger changer) {
        super("clear", "очистить коллекцию");
        cmanager = c;
        this.changer = changer;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - WrongElementsCountException error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try{
            if (!(param.isEmpty()) || object != null){
                throw new WrongElementsCountException();
            }
            for (LabWork lw: cmanager.getCollection().values()){
                if (!lw.getCreator().equals(user)){
                    throw new NoPermissionsException();
                }
            }
            changer.clearCollection();
            cmanager.clear();
            ClientOutputBuilder.println("Коллекция теперь очищена.");
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.printerr("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
        catch (NoPermissionsException e){
            ClientOutputBuilder.printerr("Не все объекты коллекции являеются собственностью пользователя!");
            return 2;
        }
    }
}
