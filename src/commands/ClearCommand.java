package commands;

import exceptions.WrongElementsCountException;
import util.ClientOutputBuilder;
import util.CollectionManager;


/**
 * Command which fully clears the collection.
 */
public class ClearCommand extends AbstractCommand{
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */

    public ClearCommand(CollectionManager c) {
        super("clear", "очистить коллекцию");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - WrongElementsCountException error (byte)
     */
    @Override
    public byte exec(String param, Object object) {
        try{
            if (!(param.isEmpty()) || object != null){
                throw new WrongElementsCountException();
            }
            cmanager.clear();
            ClientOutputBuilder.println("Коллекция теперь очищена.");
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
