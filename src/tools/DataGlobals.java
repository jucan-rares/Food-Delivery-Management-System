package tools;

import businessLayer.model.MenuItem;
import businessLayer.model.Order;
import businessLayer.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGlobals {

    public static Map<String, User> globalUsers = new HashMap<>();
    public static List<MenuItem> globalMenuItems = new ArrayList<>();
    public static Map<Order, ArrayList<MenuItem>> globalOrders = new HashMap<>();

}

