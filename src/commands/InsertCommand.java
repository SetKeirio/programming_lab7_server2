package commands;

import core.LabWork;
import exceptions.ScriptWrongInputException;
import exceptions.WrongElementsCountException;
import messages.MessageLabWork;
import util.CollectionManager;
import util.ClientOutputBuilder;

import java.time.ZonedDateTime;

/**
 * Command which inserts elements into collection.
 */
public class InsertCommand extends AbstractCommand {
    /**
     * Each command should be determined only once.
     */
    //private LabWorkAsker asker;
    private CollectionManager cmanager;
    public InsertCommand(CollectionManager c) {
        super("insert {element}", "добавить новый элемент с заданным ключом");
        cmanager = c;
    }

    /**
     * Executes the command.
     *
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object) {
        try {
            if (!param.isEmpty() || object == null){
                throw new WrongElementsCountException();
            }
            MessageLabWork lw = (MessageLabWork) object;
            Integer id = cmanager.generateNextId();
            cmanager.addToCollection(new LabWork(
                    id,
                    lw.getName(),
                    lw.getCoordinates(),
                    ZonedDateTime.now(),
                    lw.getMinimalPoint(),
                    lw.getPersonalQualitiesMaximum(),
                    lw.getDifficulty(),
                    lw.getAuthor()
            ), id);
            ClientOutputBuilder.println("LabWork добавлена.");
            return (byte) 0;
        } catch (WrongElementsCountException exception) {
            ClientOutputBuilder.println("Использование: '" + getName() + "'");
        } catch (ScriptWrongInputException exception) {}
        return (byte) 1;
    }
}
