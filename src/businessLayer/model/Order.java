package businessLayer.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Order.
 * @author Ariana Horvath
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 5243160052947837473L;
    private int orderID;
    private String clientID;
    private LocalDateTime orderDate;
    private String products;
    private int price;


    public Order(int orderID, String clientID, LocalDateTime orderDate, String products, int price) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.products = products;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID &&
                price == order.price &&
                Objects.equals(clientID, order.clientID) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return orderID*13 + orderDate.getDayOfMonth()*7 + orderDate.getHour()*11;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Sets order id.
     *
     * @param orderID the order id
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public String getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(String products) {
        this.products = products;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}

