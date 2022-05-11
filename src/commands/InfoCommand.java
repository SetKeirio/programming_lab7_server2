package commands;

import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

import java.time.ZonedDateTime;

/**
 * Command which shows main info about collection.
 */
public class InfoCommand extends AbstractCommand{
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */
    public InfoCommand(CollectionManager c) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param) {
        try{
            if (!param.isEmpty()){
                throw new WrongElementsCountException();
            }
            String init;
            String save;
            ZonedDateTime saveConvert = cmanager.getSaveTime();
            if (saveConvert == null){
                save = "сохранения пока нет.";
            }
            else{
                save = saveConvert.toLocalDate().toString() + " " + saveConvert.toLocalTime().toString();
            }
            ZonedDateTime initConvert = cmanager.getCreationTime();
            if (initConvert == null){
                init = "инициализации пока нет.";
            }
            else{
                init = initConvert.toLocalDate().toString() + " " + initConvert.toLocalTime().toString();
            }
            Console.println("Коллекция:");
            Console.println("\tТип: " + cmanager.getType());
            Console.println("\tДата инициализации: " + init);
            Console.println("\tДата сохранения: " + save);
            Console.println("\tКоличество элементов: " + cmanager.getSize());
            return 0;
        }
        catch (WrongElementsCountException e){
            Console.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
    }
}
