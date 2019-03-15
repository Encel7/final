package by.epam.shop.command.factory;

import by.epam.shop.command.Command;
import by.epam.shop.command.CommandType;
import by.epam.shop.command.implementation.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandType, Command> commandMap = new HashMap<>();

    public CommandFactory(){
        commandMap.put(CommandType.LOG_IN, new LogIn());
        commandMap.put(CommandType.REGISTRATION, new Registration());
        commandMap.put(CommandType.MAIN_PAGE, new MainPage());
        commandMap.put(CommandType.LOG_OUT, new LogOut());
        commandMap.put(CommandType.PROFILE, new Profile());
        commandMap.put(CommandType.VISIT_LOG_IN, new VisitLogIn());
        commandMap.put(CommandType.ADD_SOUVENIR, new AddSouvenir());
        commandMap.put(CommandType.REPLENISH_BALANCE, new ReplenishBalance());
        commandMap.put(CommandType.USER_LIST, new UsersList());
        commandMap.put(CommandType.EDIT_USER, new EditUser());
        commandMap.put(CommandType.ADD_TO_ORDER, new AddToOrder());
        commandMap.put(CommandType.CHECKOUT, new Checkout());
        commandMap.put(CommandType.ORDER_LIST, new OrderList());
        commandMap.put(CommandType.DELETE, new Delete());
        commandMap.put(CommandType.TRANSLATE, new Translate());
        commandMap.put(CommandType.SEARCH, new Search());
    }

    public Command createCommand(String commandName) {
        return commandMap.get(CommandType.valueOf(commandName.toUpperCase()));
    }
}
