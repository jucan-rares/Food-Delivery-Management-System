package presentationLayer;

import javax.swing.*;
import java.awt.*;


public class ClientGUI extends JFrame {

    private JTextField title = new JTextField();
    private JTextField rating = new JTextField();
    private JTextField calories = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField fat = new JTextField();
    private JTextField sodium = new JTextField();
    private JTextField price = new JTextField();
    private JTextField client = new JTextField();

    private JButton viewProducts = new JButton("view products");
    private JButton showCompositeProduct = new JButton("show composite products");
    private JButton search = new JButton("search");
    private JButton makeOrder = new JButton("make order");
    private JButton exit = new JButton("exit");

    private JTextArea compositeProductTitle;
    private JTextArea products;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;


    public ClientGUI() {
        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);

        content.add(new JLabel("client:"));
        content.add(client);

        content.add(new JLabel("products:"));
        products = new JTextArea();
        products.setEditable(true);
        scrollPane2 = new JScrollPane(products);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(660, 80, 300, 150);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.add(scrollPane2);

        content.add(new JLabel("title:"));
        content.add(title);

        content.add(new JLabel("rating:"));
        content.add(rating);

        content.add(new JLabel("calories:"));
        content.add(calories);

        content.add(new JLabel("protein:"));
        content.add(protein);

        content.add(new JLabel("fat:"));
        content.add(fat);

        content.add(new JLabel("sodium:"));
        content.add(sodium);

        content.add(new JLabel("price:"));
        content.add(price);

        content.add(new JLabel(""));
        content.add(new JLabel(""));

        content.add(viewProducts);
        content.add(search);

        content.add(showCompositeProduct);
        content.add(makeOrder);

        content.add(new JLabel("composite products:"));
        compositeProductTitle = new JTextArea();
        compositeProductTitle.setEditable(false);
        scrollPane2 = new JScrollPane(compositeProductTitle);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(660, 80, 300, 150);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.add(scrollPane2);

        scrollPane = new JScrollPane();
        content.add(exit);
    }

    public void addTable(JTable productsTable) {
        scrollPane.setBounds(30, 330, 930, 310);
        scrollPane.setBackground(new Color(56,215,109));

        productsTable.setVisible(true);
        productsTable.setEnabled(true);
        scrollPane.setViewportView(productsTable);
        this.getContentPane().add(scrollPane);
    }

    public JButton getMakeOrderButton() {
        return makeOrder;
    }

    public JTextArea getProductsTextArea() {
        return products;
    }

    public JTextField getClientTextField() {
        return client;
    }

    public JButton getExitButton() {
        return exit;
    }

    public JButton getViewProductsButton() {
        return viewProducts;
    }

    public JButton getShowCProductButton() {
        return showCompositeProduct;
    }

    public JButton getSearchButton() {
        return search;
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

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextArea getCompositeProductTextArea() {
        return compositeProductTitle;
    }
}