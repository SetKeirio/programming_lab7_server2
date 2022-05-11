package commands;

import core.LabWork;
import exceptions.ScriptWrongInputException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;
import util.LabWorkAsker;

import java.time.ZonedDateTime;

/**
 * Command which inserts elements into collection.
 */
public class InsertCommand extends AbstractCommand {
    /**
     * Each command should be determined only once.
     */
    private LabWorkAsker asker;
    private CollectionManager cmanager;
    public InsertCommand(CollectionManager c, LabWorkAsker a) {
        super("insert {element}", "добавить новый элемент с заданным ключом");
        cmanager = c;
        asker = a;
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
            if (!param.isEmpty()){
                throw new WrongElementsCountException();
            }
            Integer id = cmanager.generateNextId();
            cmanager.addToCollection(new LabWork(
                    id,
                    asker.askName(),
                    asker.askCoordinates(),
                    ZonedDateTime.now(),
                    asker.askMinimalPoint(),
                    asker.askPersonalQualitiesMaximum(),
                    asker.askDifficulty(),
                    asker.askAuthor()
            ), id);
            Console.println("LabWork добавлена.");
            return (byte) 0;
        } catch (WrongElementsCountException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (ScriptWrongInputException exception) {}
        return (byte) 1;
    }
}
