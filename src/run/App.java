package run;

import commands.*;
import util.CollectionKeeper;
import util.CollectionManager;
import util.CommandManager;
import util.RequestHandler;

/**
 * Main app class. Starts the program.
 */
public class App {
    /**
     * Setups all managers and console.
     * @param args
     */
    public static void main(String[] args) {
        final int port = 1984;
        final String envVar = "source";
        final int timeout = 10000; // milliseconds
        CollectionKeeper keeper = new CollectionKeeper(envVar);
        CollectionManager colmanager = new CollectionManager(keeper);
        CommandManager commanager = new CommandManager(new ClearCommand(colmanager),
                new ExecuteScriptCommand(), new ExitCommand(), new GroupCountingByPersonalQualitiesMaximumCommand(colmanager),
                new HelpCommand(), new InfoCommand(colmanager), new InsertCommand(colmanager), new PrintDescendingCommand(colmanager),
                new RemoveAnyByPersonalQualitiesMaximumCommand(colmanager), new RemoveGreaterKeyCommand(colmanager), new RemoveKeyCommand(colmanager),
                new ReplaceIfGreaterCommand(colmanager), new ReplaceIfLowerCommand(colmanager), new SaveCommand(colmanager), new ShowCommand(colmanager),
                new UpdateCommand(colmanager), new ExitServerCommand());
        RequestHandler handler = new RequestHandler(commanager);
        Server server = new Server(port, timeout, handler, colmanager);
        server.start();
    }

}
