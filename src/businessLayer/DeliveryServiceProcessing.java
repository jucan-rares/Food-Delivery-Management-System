package businessLayer;
import businessLayer.model.*;
import businessLayer.model.MenuItem;
import tools.DataGlobals;
import tools.Validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DeliveryServiceProcessing extends Observable implements IDeliveryServiceProcessing, Serializable {

    private static final long serialVersionUID = 1L;
    private String compositeProductString;
    private int orderId;

    public DeliveryServiceProcessing() {}

    @Override
    public boolean registerUser(User user){
        assert isWellFormed();

        if (DataGlobals.globalUsers.containsKey(user.getUsername())){
            return false;
        }
        DataGlobals.globalUsers.put(user.getUsername(), user);

        assert isWellFormed();
        return true;
    }

    @Override
    public boolean logInUser(String username, String password, Role role){
        assert !Validator.stringValidate(password);
        assert !Validator.stringValidate(username);
        assert role != null;
        assert isWellFormed();

        User user = DataGlobals.globalUsers.get(username);

        if ( user == null )
            return false;

        if (user.getRole() != role )
            return false;

        if (user.getPassword().compareTo(password) != 0)
            return false;

        assert isWellFormed();
        return true;
    }

    @Override
    public boolean importProducts() {
        assert isWellFormed();
        List<businessLayer.model.MenuItem> inputList;
        try { File inputFile = new File("products.csv");
            InputStream inputStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            inputList = bufferedReader.lines()
                    .skip(1)
                    .map((line) -> {
                        String[] lineString = line.split(",");
                        return new BaseProduct(lineString[0], Double.parseDouble(lineString[1]), Integer.parseInt(lineString[2]),
                                Integer.parseInt(lineString[3]), Integer.parseInt(lineString[4]), Integer.parseInt(lineString[5]),
                                Integer.parseInt(lineString[6]));
                    })
                    .collect(Collectors.toList());
            bufferedReader.close();

            Map<String, businessLayer.model.MenuItem> itemMap = new HashMap<>();
            for (MenuItem menuItem : inputList)
                itemMap.put(menuItem.getTitle(), menuItem);

            for (String key : itemMap.keySet())
                DataGlobals.globalMenuItems.add(itemMap.get(key));

            DataGlobals.globalMenuItems = DataGlobals.globalMenuItems.stream().sorted(Comparator.comparing(businessLayer.model.MenuItem::getTitle)).collect(Collectors.toList());
            assert DataGlobals.globalMenuItems.size() > 0;
            assert isWellFormed();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addBaseProduct(businessLayer.model.MenuItem menuItem){
        assert menuItem != null;
        assert !menuItem.getTitle().isBlank();
        assert menuItem.getRating() >= 0 && menuItem.getRating() <= 5;
        assert menuItem.getCalories() >= 0;
        assert menuItem.getFat() >= 0;
        assert menuItem.getSodium() >= 0;
        assert menuItem.getProtein() >= 0;
        assert menuItem.getPrice() >= 0;
        assert isWellFormed();
        int preSize = DataGlobals.globalMenuItems.size();

        for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
            if (menuItem.getTitle().compareTo(item.getTitle()) == 0)
                return false;
        }
        DataGlobals.globalMenuItems.add(menuItem);

        int postSize = DataGlobals.globalMenuItems.size();
        assert postSize == preSize + 1;
        assert isWellFormed();
        return true;
    }

    @Override
    public void updateBaseProduct(businessLayer.model.MenuItem menuItem) throws Exception {
        assert menuItem != null;
        assert (!menuItem.getTitle().isBlank() || (menuItem.getRating() >= 0 && menuItem.getRating() <= 5)
                || (menuItem.getCalories() >= 0) || ( menuItem.getFat() >= 0 ) || ( menuItem.getSodium() >= 0 ) ||
                ( menuItem.getProtein() >= 0 ) || ( menuItem.getPrice() >= 0 ) || isWellFormed() );

        int preSize = DataGlobals.globalMenuItems.size();

        boolean found = false;
        for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
            if (menuItem.getTitle().compareTo(item.getTitle()) == 0) {
                found = true;
                item.setTitle(menuItem.getTitle());
                item.setRating(menuItem.getRating());
                item.setCalories(menuItem.getCalories());
                item.setProtein(menuItem.getProtein());
                item.setFat(menuItem.getFat());
                item.setSodium(menuItem.getSodium());
                item.setPrice(menuItem.getPrice());
            }
        }
        if (!found)
            throw new Exception("Product to update is not existent!");

        int postSize = DataGlobals.globalMenuItems.size();
        assert postSize == preSize;
        assert isWellFormed();
    }

    @Override
    public boolean deleteBaseProduct(String menuItemTitle){
        assert menuItemTitle != null && !menuItemTitle.isBlank();
        assert isWellFormed();
        int preSize = DataGlobals.globalMenuItems.size();

        int index = -1;
        for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
            if (menuItemTitle.compareTo(item.getTitle()) == 0) {
                index = DataGlobals.globalMenuItems.indexOf(item);
            }
        }
        if (index == -1)
            return false;
        DataGlobals.globalMenuItems.remove(index);

        int postSize = DataGlobals.globalMenuItems.size();
        assert postSize == preSize - 1;
        assert isWellFormed();
        return true;
    }

    @Override
    public void addCompositeProduct(String title, String products) throws Exception {
        assert title != null && !title.isBlank();
        assert products != null && !products.isBlank();
        assert isWellFormed();
        int preSize = DataGlobals.globalMenuItems.size();

        for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
            if (item.getTitle().compareTo(title) == 0)
                throw new Exception("Composite Product title already existent!");
        }
        double rating = 0;
        int calories = 0, protein = 0, fat = 0, sodium = 0, price = 0;
        String[] product = products.split(",");
        ArrayList<businessLayer.model.MenuItem> composite = new ArrayList<>();
        for (String prod : product) {
            prod = prod.replaceAll("\\s+", "");
            boolean found = false;
            for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
                String titleS = item.getTitle().replaceAll("\\s+", "");
                if (titleS.compareTo(prod) == 0) {
                    found = true;
                    composite.add(item);
                    rating += item.getRating();
                    calories += item.getCalories();
                    protein += item.getProtein();
                    fat += item.getFat();
                    sodium += item.getSodium();
                    price += item.getPrice();
                }
            }
            if (!found) throw new Exception(prod + " is not existent in the menu!");
        }
        rating = rating / composite.size();
        DataGlobals.globalMenuItems.add(new CompositeProduct(title, rating, calories, protein, fat, sodium, price, composite));

        int postSize = DataGlobals.globalMenuItems.size();
        assert postSize == preSize + 1;
        assert isWellFormed();
    }

    @Override
    public List<businessLayer.model.MenuItem> searchProducts(String title, String rating, String calories, String protein, String fat, String sodium, String price) throws Exception {
        assert isWellFormed();

        List<businessLayer.model.MenuItem> filteredItems = new ArrayList<>(DataGlobals.globalMenuItems);
        Stream<businessLayer.model.MenuItem> stream = filteredItems.stream();
        try {
            if (!title.isEmpty())
                stream = stream.filter(product -> product.getTitle().contains(title));
            if (rating.compareTo("") != 0)
                stream = stream.filter(product -> product.getRating() == Double.parseDouble(rating));
            if (calories.compareTo("") != 0)
                stream = stream.filter(product -> product.getCalories() == Integer.parseInt(calories));
            if (protein.compareTo("") != 0)
                stream = stream.filter(product -> product.getProtein() == Integer.parseInt(protein));
            if (fat.compareTo("") != 0)
                stream = stream.filter(product -> product.getFat() == Integer.parseInt(fat));
            if (sodium.compareTo("") != 0)
                stream = stream.filter(product -> product.getSodium() == Integer.parseInt(sodium));
            if (price.compareTo("") != 0)
                stream = stream.filter(product -> product.getPrice() == Integer.parseInt(price));

            assert isWellFormed();
            return stream.collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new Exception("Input is not a number!");
        }
    }

    @Override
    public void createOrder(String client, String products) throws Exception {
        assert client != null && !client.isBlank();
        assert products != null && !products.isBlank();
        assert isWellFormed();
        int preSize = DataGlobals.globalOrders.size();

        compositeProductString = "";
        ArrayList<businessLayer.model.MenuItem> orderedItems = new ArrayList<>();
        User user = DataGlobals.globalUsers.get(client);
        if (user == null || user.getRole() != Role.CLIENT)
            throw new Exception("Invalid client username.");

        int price = 0;
        String[] product = products.split("\n");
        for(String prod : product) {
            prod = prod.replaceAll("\\s+", "");
            boolean found = false;
            for(businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
                String title = item.getTitle().replaceAll("\\s+", "");
                if(title.compareTo(prod) == 0){
                    found = true;
                    price += item.getPrice();
                    if (item instanceof BaseProduct)
                        compositeProductString = compositeProductString + item.getTitle() + "\n";
                    else extractProducts((CompositeProduct) item);
                    orderedItems.add(item);
                }
            }
            if (!found) throw new Exception(prod + " is not existent in the menu!");
        }
        orderId = idGenerator() + 1;
        Order order = new Order(orderId, client, LocalDateTime.now(), compositeProductString, price);
        DataGlobals.globalOrders.put(order, orderedItems);
        createBill(order);
        String stringForEmployee = "\nOrder id: " + orderId + "\nClient: " + client + "\n" + compositeProductString;
        setChanged();
        notifyObservers(stringForEmployee);

        int postSize = DataGlobals.globalOrders.size();
        assert postSize == preSize + 1;
        assert isWellFormed();
    }

    public ArrayList<MenuItem> fetchProductsByOrder( Order order ){
        ArrayList<businessLayer.model.MenuItem> foundItems = null;
        String[] product = order.getProducts().split("\n");
        for(String prod : product) {
            prod = prod.replaceAll("\\s+", "");
            boolean found = false;
            for(businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
                String title = item.getTitle().replaceAll("\\s+", "");
                if(title.compareTo(prod) == 0){
                    found = true;
                    if (item instanceof BaseProduct)
                        compositeProductString = compositeProductString + item.getTitle() + "\n";
                    else extractProducts((CompositeProduct) item);
                    foundItems.add(item);
                }
            }
        }

        return foundItems;
    }

    @Override
    public void createBill(Order order) {
        assert order != null;
        assert isWellFormed();

        FileWriter writer;
        try {

            writer = new FileWriter("Bill" + order.getOrderID()+".txt");
            writer.append("Bill \nOrder ID: ").append(String.valueOf(order.getOrderID()))
                    .append("\nClient: ").append(order.getClientID()).append("\n\nPlaced at: ")
                    .append(String.valueOf(order.getOrderDate())).append("\n\nOrdered products:\n")
                    .append(order.getProducts()).append("\nTotal: ").append(String.valueOf(order.getPrice()));

            writer.close();
            assert isWellFormed();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport1(int startHourMinutes, int endHourMinutes) {
        assert startHourMinutes >= 0 && endHourMinutes >= 0;
        assert isWellFormed();

        List<Order> ordersR1 = DataGlobals.globalOrders.keySet()
                .stream()
                .filter(order -> {
                    int hour = order.getOrderDate().getHour() * 60 + order.getOrderDate().getMinute();
                    return hour >= startHourMinutes && hour <= endHourMinutes; })
                .collect(Collectors.toList());
        FileWriter writer;
        try {
            writer = new FileWriter("Report1.txt");
            writer.append("Orders placed between " + startHourMinutes/60 + ":" + startHourMinutes%60 + " and " + endHourMinutes/60 + ":"+ endHourMinutes%60);
            for(Order order : ordersR1) {
                writer.append("\n\nOrder ID: " + order.getOrderID() + "\nClient: " + order.getClientID()+"Placed at: "+ order.getOrderDate()
                        + "\nOrdered products:\n" + order.getProducts() + "Total: " + order.getPrice());
            }
            writer.close();
            assert isWellFormed();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport2(int nbOfTimes) {
        assert nbOfTimes >= 0;
        assert isWellFormed();

        Stream<businessLayer.model.MenuItem> stream = Stream.of();
        for(Order order : DataGlobals.globalOrders.keySet()) {
            stream = Stream.concat(stream, DataGlobals.globalOrders.get(order).stream());
        }
        List<businessLayer.model.MenuItem> products = stream.collect(Collectors.toList());
        List<businessLayer.model.MenuItem> products2 = products.stream()
                .filter(item -> Collections.frequency(products, item) > nbOfTimes)
                .distinct()
                .collect(Collectors.toList());
        FileWriter writer;
        try {
            writer = new FileWriter("Report2.txt");
            writer.append("Products ordered more then " + nbOfTimes + " times: \n\n");
            for(businessLayer.model.MenuItem item : products2) {
                writer.append(item.getTitle() + "\n");
            }
            writer.close();
            assert isWellFormed();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport3(int nbOfTimes, int amount) {
        assert nbOfTimes >= 0;
        assert amount >= 0;
        assert isWellFormed();

        List<String> clients = DataGlobals.globalOrders.keySet()
                .stream()
                .filter(order -> order.getPrice() > amount)
                .map(Order::getClientID)
                .collect(Collectors.toList());
        List<String> clients2 = clients.stream()
                .filter(c -> Collections.frequency(clients, c) > nbOfTimes)
                .distinct()
                .collect(Collectors.toList());
        FileWriter writer;
        try {
            writer = new FileWriter("Report3.txt");
            writer.append("The clients that have ordered more than "+ nbOfTimes +" times and the value of the order was higher than " +
                    amount + "\n\n");
            for (String client : clients2) {
                writer.append(client + "\n");
            }
            writer.close();
            assert isWellFormed();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport4(int day, int month, int year) {
        assert day >= 0 && day <= 31;
        assert month >= 0 && month <= 12;
        assert year >= 0 && year <= LocalDateTime.now().getYear();
        assert isWellFormed();

        Stream<businessLayer.model.MenuItem> stream = Stream.of();
        for(Order order : DataGlobals.globalOrders.keySet()) {
            if (order.getOrderDate().getDayOfMonth() == day && order.getOrderDate().getMonthValue() == month &&
                    order.getOrderDate().getYear() == year)
                stream = Stream.concat(stream, DataGlobals.globalOrders.get(order).stream());
        }
        List<businessLayer.model.MenuItem> products = stream.collect(Collectors.toList());
        Map<businessLayer.model.MenuItem, Long> myProducts = products.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        FileWriter writer;
        try {
            writer = new FileWriter("Report4.txt");
            writer.append("Products ordered on "+day+"/"+month+"/"+year+"\n\n");
            for(businessLayer.model.MenuItem product : myProducts.keySet()) {
                writer.append(product.getTitle() + " " + myProducts.get(product) + "\n");
            }
            writer.close();
            assert isWellFormed();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean isWellFormed() {
        if (DataGlobals.globalUsers == null || DataGlobals.globalMenuItems == null || DataGlobals.globalOrders == null)

            if (DataGlobals.globalUsers.size() > 0)
                for (String username : DataGlobals.globalUsers.keySet()) {
                    if (DataGlobals.globalUsers.get(username) == null ) return false;
                    if (DataGlobals.globalUsers.get(username).getUsername() == null || DataGlobals.globalUsers.get(username).getUsername().isBlank()) return false;
                    if (DataGlobals.globalUsers.get(username).getPassword() == null || DataGlobals.globalUsers.get(username).getPassword().isBlank()) return false;
                    if (DataGlobals.globalUsers.get(username).getRole() == null) return false;
                }

        if (DataGlobals.globalMenuItems.size() > 0)
            for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
                if (item == null ) return false;
                if (item.getTitle() == null || item.getTitle().isBlank()) return false;
                if (item.getRating() < 0 || item.getRating() > 5) return false;
                if (item.getCalories() < 0) return false;
                if (item.getProtein() < 0) return false;
                if (item.getFat() < 0) return false;
                if (item.getSodium() < 0) return false;
                if (item.getPrice() < 0) return false;
            }
        else return DataGlobals.globalOrders.size() <= 0;

        if (DataGlobals.globalOrders.size() > 0)
            for (Order order : DataGlobals.globalOrders.keySet()) {
                if (order == null) return false;
                if (DataGlobals.globalOrders.get(order) == null || DataGlobals.globalOrders.get(order).size() == 0) return false;
                if (order.getOrderID() == 0) return false;
                if (order.getOrderDate() == null) return false;
                if (order.getOrderDate().isAfter(LocalDateTime.now())) return false;
                if (order.getClientID() == null || order.getClientID().isBlank()) return false;
                if (order.getPrice() < 0) return false;
            }
        return true;
    }

    /**
     * Create products JTable.
     * @param list the list of menu items
     * @return the JTable
     */
    public JTable createProductsTable(List<businessLayer.model.MenuItem> list) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Title");
        columns.add("Rating");
        columns.add("Calories");
        columns.add("Protein");
        columns.add("Fat");
        columns.add("Sodium");
        columns.add("Price");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns.toArray());
        for (businessLayer.model.MenuItem item : list) {
            ArrayList<String> menuItems = new ArrayList<>();
            menuItems.add(item.getTitle());
            menuItems.add(Double.toString(item.getRating()));
            menuItems.add(Integer.toString(item.getCalories()));
            menuItems.add(Integer.toString(item.getProtein()));
            menuItems.add(Integer.toString(item.getFat()));
            menuItems.add(Integer.toString(item.getSodium()));
            menuItems.add(Integer.toString(item.getPrice()));

            model.addRow(menuItems.toArray());
        }
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(500);
        table.getTableHeader().setBackground(new Color(234, 144, 173, 255));
        table.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 14));
        table.getTableHeader().setForeground(new Color(146, 8, 51, 255));
        table.setRowHeight(20);
        table.setBackground(new Color(246, 218, 227, 255));
        table.setForeground(new Color(146, 8, 51, 255));
        table.setGridColor(new Color(146, 8, 51, 255));
        table.setFont(new Font("Calibri", Font.PLAIN, 14));
        return table;
    }


    public String createCProductString(String menuItemS) throws Exception {
        compositeProductString = "";
        boolean found = false;
        for (businessLayer.model.MenuItem item : DataGlobals.globalMenuItems) {
            if (item.getTitle().compareTo(menuItemS) == 0) {
                found = true;
                if (item instanceof BaseProduct)
                    throw new Exception("Product is not composite!");
                extractProducts((CompositeProduct) item);
            }
        }
        if (!found) throw new Exception("Product is not existent!");
        return compositeProductString;
    }


    public void extractProducts(CompositeProduct product) {
        for(businessLayer.model.MenuItem item : product.getCompositeProduct()) {
            if (item instanceof BaseProduct)
                compositeProductString = compositeProductString + item.getTitle() + "\n";
            else extractProducts((CompositeProduct) item);
        }
    }


    public int idGenerator() {
        int max = 0;
        for(Order order : DataGlobals.globalOrders.keySet()) {
            if (order.getOrderID() > max)
                max = order.getOrderID();
        }
        return max;
    }

}
