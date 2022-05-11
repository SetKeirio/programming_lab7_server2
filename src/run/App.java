package run;

import commands.*;
import util.*;

import java.util.Scanner;

/**
 * Main app class. Starts the program.
 */
public class App {
    /**
     * Setups all managers and console.
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in))
        {
            final String envVar = "source";
            FileManager fmanager = new FileManager(envVar);
            CollectionManager colmanager = new CollectionManager(fmanager);
            LabWorkAsker lwasker = new LabWorkAsker(scanner);
            CommandManager commanager = new CommandManager(new ClearCommand(colmanager),
                    new ExecuteScriptCommand(), new ExitCommand(), new GroupCountingByPersonalQualitiesMaximumCommand(colmanager),
                    new HelpCommand(), new InfoCommand(colmanager), new InsertCommand(colmanager, lwasker), new PrintDescendingCommand(colmanager),
                    new RemoveAnyByPersonalQualitiesMaximumCommand(colmanager), new RemoveGreaterKeyCommand(colmanager), new RemoveKeyCommand(colmanager),
                    new ReplaceIfGreaterCommand(colmanager, lwasker), new ReplaceIfLowerCommand(colmanager, lwasker), new SaveCommand(colmanager), new ShowCommand(colmanager),
                    new UpdateCommand(colmanager, lwasker));
            Console console = new Console(scanner, commanager, lwasker);
            console.userMode();
        }
    }

}
