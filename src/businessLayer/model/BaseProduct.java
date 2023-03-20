package businessLayer.model;

/**
 * The type Base product.
 * @author Ariana Horvath
 */
public class BaseProduct extends MenuItem {

    private static final long serialVersionUID = -7678494477769756030L;

    /**
     * Instantiates a new Base product.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param protein  the protein
     * @param fat      the fat
     * @param sodium   the sodium
     * @param price    the price
     */
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public int compuntePrice() {
        return getPrice();
    }

}
