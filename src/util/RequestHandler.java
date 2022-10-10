package util;

import messages.CommandMessage;
import messages.CommandResponse;
import messages.ResponseCodeEnum;
import messages.User;

import java.util.concurrent.RecursiveTask;


public class RequestHandler extends RecursiveTask<CommandResponse> {
    private CommandManager commandManager;
    private CommandMessage request;
    public RequestHandler(CommandManager commandManager, CommandMessage request){
        this.commandManager = commandManager;
        this.request = request;
    }

    /**public CommandResponse handle(CommandMessage request){
        ResponseCodeEnum responceCode = execute(request.getCommandName(), request.getCommandArgument(), request.getObjectArgument());
        return new CommandResponse(ClientOutputBuilder.refresh(), responceCode);
    }**/

    private synchronized ResponseCodeEnum execute(String commandName, String commandArgument, Object objectArgument, User user){
        switch (commandName){
            case "clear":
                if (commandManager.clear(commandArgument, objectArgument, user) != 0) {
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "execute_script":
                if (commandManager.executeScript(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "exit":
                if (commandManager.exit(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "exit_server":
                if (commandManager.exitServer(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "group_counting_by_personal_qualities_maximum":
                if (commandManager.groupCountingByPersonalQualitiesMaximum(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "help":
                if (commandManager.help(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "info":
                if (commandManager.info(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "insert":
                if (commandManager.insert(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "print_descending":
                if (commandManager.printDescending(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_any_by_personal_qualities_maximum":
                if (commandManager.removeAnyByPersonalQualitiesMaximum(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_greater_key":
                if (commandManager.removeGreaterKey(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_key":
                if (commandManager.removeKey(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "replace_if_lower":
                if (commandManager.replaceIfLower(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "replace_if_greater":
                if (commandManager.replaceIfGreater(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "save":
                return ResponseCodeEnum.ERROR;
            case "show":
                if (commandManager.show(commandArgument, objectArgument, user) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "update":
                if (commandManager.update(commandArgument, objectArgument, user ) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            default:
                Console.println("Такой команды нет");
                return ResponseCodeEnum.ERROR;
        }
        return ResponseCodeEnum.OK;
    }

    @Override
    protected CommandResponse compute() {
        User hashed = new User(request.getUser().getUsername(), Hasher.hashPassword(request.getUser().getPassword()));
        ResponseCodeEnum responseCode = execute(request.getCommandName(), request.getCommandArgument(), request.getObjectArgument(), hashed);
        return new CommandResponse(ClientOutputBuilder.refresh(), responseCode);
    }
}
