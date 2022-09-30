package commands;

import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.ClientOutputBuilder;

/**
 * Command which saves the collection into file.
 */
public class SaveCommand extends AbstractCommand{
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */
    public SaveCommand(CollectionManager c) {
        super("save", "сохранить коллекцию в файл (НЕДОСТУПНО НА КЛИЕНТЕ)");
        cmanager = c;
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
            cmanager.saveCollection();
            return 0;
        }
        catch (WrongElementsCountException e){
            ClientOutputBuilder.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
