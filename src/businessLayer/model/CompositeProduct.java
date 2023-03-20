package businessLayer.model;

import java.util.ArrayList;

/**
 * The type Composite product.
 * @author Ariana Horvath
 */
public class CompositeProduct extends MenuItem {

    private static final long serialVersionUID = -4764928940278378853L;
    private ArrayList<MenuItem> compositeProduct;

    /**
     * Instantiates a new Composite product.
     *
     * @param title            the title
     * @param rating           the rating
     * @param calories         the calories
     * @param protein          the protein
     * @param fat              the fat
     * @param sodium           the sodium
     * @param price            the price
     * @param compositeProduct the composite product
     */
    public CompositeProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price, ArrayList<MenuItem> compositeProduct) {
        super(title, rating, calories, protein, fat, sodium, price);
        this.compositeProduct = compositeProduct;
    }

    @Override
    public int compuntePrice() {
        int price = 0;
        for(MenuItem item : compositeProduct) {
            price += item.getPrice();
        }
        return price;
    }

    /**
     * Gets composite product.
     *
     * @return the composite product
     */
    public ArrayList<MenuItem> getCompositeProduct() {
        return compositeProduct;
    }

    /**
     * Sets composite product.
     *
     * @param compositeProduct the composite product
     */
    public void setCompositeProduct(ArrayList<MenuItem> compositeProduct) {
        this.compositeProduct = compositeProduct;
    }
}
