package businessLayer;

import businessLayer.model.MenuItem;
import businessLayer.model.Order;
import businessLayer.model.Role;
import businessLayer.model.User;

import java.util.List;

/**
 * The interface Delivery service processing.
 * @author Ariana Horvath
 */
public interface IDeliveryServiceProcessing {


    boolean registerUser(User user) throws Exception;

    boolean logInUser(String username, String password, Role role) throws Exception;

    boolean importProducts() throws Exception;

    /**
     * Add base product.
     * @pre menuItem != null
     * @pre !menuItem.getTitle().isBlank()
     * @pre menuItem.getRating() >= 0 && menuItem.getRating() <= 5
     * @pre menuItem.getCalories() >= 0
     * @pre menuItem.getFat() >= 0
     * @pre menuItem.getSodium() >= 0
     * @pre menuItem.getProtein() >= 0
     * @pre menuItem.getPrice() >= 0
     * @post menuItems.size() == menuItems.size()@pre+1
     * @param menuItem the menu item
     */
    boolean addBaseProduct(MenuItem menuItem) throws Exception;

    /**
     * Update base product.
     * @param menuItem the menu item
     * @pre menuItem != null
     * @pre !menuItem.getTitle().isBlank()
     * @pre menuItem.getRating() >= 0 && menuItem.getRating() <= 5
     * @pre menuItem.getCalories() >= 0
     * @pre menuItem.getFat() >= 0
     * @pre menuItem.getSodium() >= 0
     * @pre menuItem.getProtein() >= 0
     * @pre menuItem.getPrice() >= 0
     * @post menuItems.size() == menuItems.size()@pre
     */
    void updateBaseProduct(MenuItem menuItem) throws Exception;

    /**
     * Delete base product.
     * @param menuItemTitle the menu item title
     * @pre menuItemTitle != null && !menuItemTitle.isBlank()
     * @post menuItems.size() == menuItems.size()@pre - 1
     */
    boolean deleteBaseProduct(String menuItemTitle) throws Exception;

    /**
     * Add composite product.
     * @param title    the title
     * @param products the products
     * @pre title != null && !title.isBlank()
     * @pre products != null && !products.isBlank()
     * @post menuItems.size() == menuItems.size()@pre+1
     */
    void addCompositeProduct(String title, String products) throws Exception;

    /**
     * Search products list.
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param protein  the protein
     * @param fat      the fat
     * @param sodium   the sodium
     * @param price    the price
     * @return the list
     * @pre true
     * @post @nochange
     */
    List<MenuItem> searchProducts(String title, String rating, String calories, String protein, String fat, String sodium, String price) throws Exception;

    /**
     * Create order.
     * @param client   the client
     * @param products the products
     * @pre client != null && !client.isBlank()
     * @pre products != null && !products.isBlank()
     * @post orders.size() == orders.size()@pre + 1
     */
    void createOrder(String client, String products) throws Exception;

    /**
     * Create bill.
     * @param order the order
     * @pre order != null
     * @post @nochange
     */
    void createBill(Order order);

    /**
     * Generate report 1.
     * @param startHourMinutes the start hour in minutes
     * @param endHourMinutes   the end hour in minutes
     * @pre startHourMinutes >= 0 && endHourMinutes >= 0
     * @post @nochange
     */
    void generateReport1(int startHourMinutes, int endHourMinutes);

    /**
     * Generate report 2.
     * @param nbOfTimes the nb of times
     * @pre nbOfTimes >= 0
     * @post @nochange
     */
    void generateReport2(int nbOfTimes);

    /**
     * Generate report 3.
     * @param nbOfTimes the nb of times
     * @param amount    the amount
     * @pre nbOfTimes >= 0
     * @pre amount >= 0
     * @post @nochange
     */
    void generateReport3(int nbOfTimes, int amount);

    /**
     * Generate report 4.
     * @param day   the day
     * @param month the month
     * @param year  the year
     * @pre day >= 0 && day <= 31
     * @pre month >= 0 && month <= 12
     * @pre year >= 0 && year <= currentYear
     * @post @nochange
     */
    void generateReport4(int day, int month, int year);

}

