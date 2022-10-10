package run;

import commands.*;
import exceptions.LimitIgnoreException;
import exceptions.WrongElementsCountException;
import util.*;

/**
 * Main app class. Starts the program.
 */
public class App {
    private static String dbName = "keirio";
    private static int port;
    private static String dbHost;
    private static String dbPassword;
    private static String db;
    private static DatabaseManager databaseManager;
    private static UserManager userManager;
    private static DatabaseChanger databaseChanger;

    private static byte setupConnection(String[] args){
        try {
            if (args.length != 3){
                throw new WrongElementsCountException();
            }
            dbHost = args[0];
            port = Integer.parseInt(args[1]);
            if (port <= 0) {
                throw new LimitIgnoreException();
            }
            dbPassword = args[2];
            db = "jdbc:postgresql://" + dbHost + ":5432/studs";
            return 0;
        }
        catch (WrongElementsCountException e){
            Console.printerr("Не удалось соединиться.");
            Console.println("Верное соединение: java -jar <имя jar>" + dbHost + " " + port + " " + dbPassword);
            return 1;
        }
        catch (LimitIgnoreException e){
            Console.printerr("Порт должен быть положительным!");
            return 2;
        }
        catch (NumberFormatException e){
            Console.printerr("Порт должен быть числом!");
            return 3;
        }
        //return -1;
    }
    /**
     * Setups all managers and console.
     * @param args
     */
    public static void main(String[] args) {
        final int port = 1984;
        final String envVar = "source";
        final int timeout = 10000; // milliseconds
        if (setupConnection(args) != 0){
            System.exit(0);
        }
        databaseManager = new DatabaseManager(dbHost, dbName, dbPassword);
        userManager = new UserManager(databaseManager);
        databaseChanger = new DatabaseChanger(databaseManager, userManager);
        CollectionManager colmanager = new CollectionManager(databaseChanger);
        CommandManager commanager = new CommandManager(new ClearCommand(colmanager),
                new ExecuteScriptCommand(), new ExitCommand(), new GroupCountingByPersonalQualitiesMaximumCommand(colmanager),
                new HelpCommand(), new InfoCommand(colmanager), new InsertCommand(colmanager), new PrintDescendingCommand(colmanager),
                new RemoveAnyByPersonalQualitiesMaximumCommand(colmanager), new RemoveGreaterKeyCommand(colmanager), new RemoveKeyCommand(colmanager),
                new ReplaceIfGreaterCommand(colmanager), new ReplaceIfLowerCommand(colmanager), new ShowCommand(colmanager),
                new UpdateCommand(colmanager), new ExitServerCommand(), new LoginCommand(userManager), new RegisterCommand(userManager));
        Server server = new Server(port, commanager);
        server.start();
        databaseManager.closeConnection();
    }

}
