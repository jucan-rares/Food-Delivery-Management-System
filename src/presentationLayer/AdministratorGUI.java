package presentationLayer;
import javax.swing.*;
import java.awt.*;

public class AdministratorGUI extends JFrame {

    private JTextField title = new JTextField();
    private JTextField rating = new JTextField();
    private JTextField calories = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField fat = new JTextField();
    private JTextField sodium = new JTextField();
    private JTextField price = new JTextField();
    private JTextField compositeTitle = new JTextField();
    private JTextField products = new JTextField();
    private JTextField start = new JTextField();
    private JTextField end = new JTextField();
    private JTextField noTimes = new JTextField();
    private JTextField noTimes2 = new JTextField();
    private JTextField amount = new JTextField();
    private JTextField day = new JTextField();

    private JButton importProducts = new JButton("import products");
    private JButton viewProducts = new JButton("view products");
    private JButton addBaseProduct = new JButton("add base product");
    private JButton updateBaseProduct = new JButton("update base product");
    private JButton deleteBaseProduct = new JButton("delete base product");
    private JButton addCompositeProduct = new JButton("add composite product");
    private JButton showCompositeProduct = new JButton("show composite product");
    private JButton report1 = new JButton("report 1");
    private JButton report2 = new JButton("report 2");
    private JButton report3 = new JButton("report 3");
    private JButton report4 = new JButton("report 4");
    private JButton exit = new JButton("exit");

    private JTextArea compositeProduct;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;

    /**
     * Instantiates a new Administrator gui.
     */
    public AdministratorGUI() {
        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 3));
        setVisible(true);

        content.add(new JLabel(""));
        content.add(new JLabel("Base product information"));
        content.add(new JLabel(""));

        content.add(new JLabel("title:"));
        content.add(title);
        content.add(new JLabel(""));

        content.add(new JLabel("rating:"));
        content.add(rating);
        content.add(new JLabel(""));

        content.add(new JLabel("calories:"));
        content.add(calories);
        content.add(new JLabel(""));

        content.add(new JLabel("protein:"));
        content.add(protein);
        content.add(new JLabel(""));

        content.add(new JLabel("fat:"));
        content.add(fat);
        content.add(new JLabel(""));

        content.add(new JLabel("sodium:"));
        content.add(sodium);
        content.add(new JLabel(""));

        content.add(new JLabel("price:"));
        content.add(price);
        content.add(new JLabel(""));

        content.add(new JLabel(""));
        content.add(new JLabel("Composite product information"));
        content.add(new JLabel(""));

        content.add(new JLabel("title:"));
        content.add(compositeTitle);
        content.add(new JLabel(""));

        content.add(new JLabel("products:"));
        content.add(products);
        content.add(new JLabel(""));

        content.add(new JLabel(""));
        content.add(new JLabel("Manipulate base products"));
        content.add(new JLabel(""));

        content.add(addBaseProduct);
        content.add(updateBaseProduct);
        content.add(deleteBaseProduct);

        content.add(new JLabel(""));
        content.add(new JLabel("Manipulate composite products"));
        content.add(new JLabel(""));

        content.add(addCompositeProduct);
        content.add(new JLabel(""));
        content.add(showCompositeProduct);

        content.add(new JLabel(""));
        content.add(new JLabel("Create reports"));
        compositeProduct = new JTextArea();
        compositeProduct.setEditable(false);
        scrollPane2 = new JScrollPane(compositeProduct);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(590, 350, 350, 70);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.add(scrollPane2);

        content.add(new JLabel("starting hour"));
        content.add(new JLabel("ending hour"));
        content.add(new JLabel("orders placed between the given hours"));

        content.add(start);
        content.add(end);
        content.add(report1);

        content.add(new JLabel("number of times"));
        content.add(new JLabel(""));
        content.add(new JLabel("products ordered more than the given number of times"));

        content.add(noTimes);
        content.add(new JLabel(""));
        content.add(report2);

        content.add(new JLabel("number of times"));
        content.add(new JLabel("amount"));
        content.add(new JLabel("products ordered more than the given number of times and their price is higher than the given amount"));

        content.add(noTimes2);
        content.add(amount);
        content.add(report3);

        content.add(new JLabel("date(DD/MM/YYYY)"));
        content.add(new JLabel(""));
        content.add(new JLabel("products ordered in the specified date"));

        content.add(day);
        content.add(new JLabel(""));
        content.add(report4);

        content.add(new JLabel(""));
        content.add(new JLabel("More options"));
        content.add(new JLabel(""));

        content.add(viewProducts);
        content.add(new JLabel(""));
        content.add(importProducts);

        content.add(exit);

        scrollPane = new JScrollPane();

    }

    /**
     * Add table.
     *
     * @param productsTable the products table
     */
    public void addTable(JTable productsTable) {
        scrollPane.setBounds(40, 440, 900, 200);
        scrollPane.setBackground(new Color(56,215,109));
        productsTable.setVisible(true);
        productsTable.setEnabled(true);
        scrollPane.setViewportView(productsTable);
        this.getContentPane().add(scrollPane);
    }

    public JButton getReport1Button() {
        return report1;
    }
    public JButton getReport2Button() {
        return report2;
    }
    public JButton getReport3Button() {
        return report3;
    }
    public JButton getReport4Button() {
        return report4;
    }
    public JTextField getStartHourTextField() {
        return start;
    }
    public JTextField getEndHourTextField() {
        return end;
    }
    public JTextField getNoTimesTextField() {
        return noTimes;
    }
    public JTextField getNoTimes2TextField() {
        return noTimes2;
    }
    public JTextField getAmountTextField() {
        return amount;
    }
    public JTextField getDayTextField() {
        return day;
    }
    public JTextArea getCompositeProductTextArea() {
        return compositeProduct;
    }
    public JButton getAddCompositeProductButton() {
        return addCompositeProduct;
    }
    public JButton getShowCProductButton() {
        return showCompositeProduct;
    }
    public JButton getDeleteBaseProductButton() {
        return deleteBaseProduct;
    }
    public JButton getUpdateBaseProductButton() {
        return updateBaseProduct;
    }
    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public JButton getAddBaseProductButton() {
        return addBaseProduct;
    }
    public JButton getViewProductsButton() {
        return viewProducts;
    }
    public JTextField getTitleTextField() {
        return title;
    }
    public JTextField getRatingTextField() {
        return rating;
    }
    public JTextField getCaloriesTextField() {
        return calories;
    }
    public JTextField getProteinTextField() {
        return protein;
    }
    public JTextField getFatTextField() {
        return fat;
    }
    public JTextField getSodiumTextField() {
        return sodium;
    }
    public JTextField getPriceTextField() {
        return price;
    }
    public JTextField getCompositeTitleTextField() {
        return compositeTitle;
    }
    public JTextField getProductTextField() {
        return products;
    }
    public JButton getImportProducts() {
        return importProducts;
    }
    public JButton getExitButton() {
        return exit;
    }
}

