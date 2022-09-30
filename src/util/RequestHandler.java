package util;

import messages.CommandMessage;
import messages.CommandResponse;
import messages.ResponseCodeEnum;


//Изменить команды на два аргумента
public class RequestHandler {
    private CommandManager commandManager;
    public RequestHandler(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    public CommandResponse handle(CommandMessage request){
        ResponseCodeEnum responceCode = execute(request.getCommandName(), request.getCommandArgument(), request.getObjectArgument());
        return new CommandResponse(ClientOutputBuilder.refresh(), responceCode);
    }

    private ResponseCodeEnum execute(String commandName, String commandArgument, Object objectArgument){
        switch (commandName){
            case "clear":
                if (commandManager.clear(commandArgument, objectArgument) != 0) {
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "execute_script":
                if (commandManager.executeScript(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "exit":
                if (commandManager.exit(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "exit_server":
                if (commandManager.exitServer(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "group_counting_by_personal_qualities_maximum":
                if (commandManager.groupCountingByPersonalQualitiesMaximum(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "help":
                if (commandManager.help(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "info":
                if (commandManager.info(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "insert":
                if (commandManager.insert(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "print_descending":
                if (commandManager.printDescending(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_any_by_personal_qualities_maximum":
                if (commandManager.removeAnyByPersonalQualitiesMaximum(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_greater_key":
                if (commandManager.removeGreaterKey(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "remove_key":
                if (commandManager.removeKey(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "replace_if_lower":
                if (commandManager.replaceIfLower(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "replace_if_greater":
                if (commandManager.replaceIfGreater(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "save":
                return ResponseCodeEnum.ERROR;
            case "show":
                if (commandManager.show(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            case "update":
                if (commandManager.update(commandArgument, objectArgument) != 0){
                    return ResponseCodeEnum.ERROR;
                }
                break;
            default:
                Console.println("Такой команды нет");
                return ResponseCodeEnum.ERROR;
        }
        return ResponseCodeEnum.OK;
    }
}
