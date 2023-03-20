package presentationLayer;

import businessLayer.DeliveryServiceProcessing;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class EmployeeGUI extends JFrame implements Observer {

    JPanel content;
    private JTextArea orders;
    private JScrollPane scrollPane;
    private JButton exit = new JButton("exit");
    public DeliveryServiceProcessing deliveryServiceProcessing;

    public EmployeeGUI(DeliveryServiceProcessing deliveryServiceProcessing) {
        content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 3));
        setVisible(true);

        this.deliveryServiceProcessing = deliveryServiceProcessing;
        this.deliveryServiceProcessing.addObserver( this );
        this.getExitButton().addActionListener(e->{
            this.setVisible(false);

        content.add(new JLabel("orders:"));
        orders = new JTextArea();
        orders.setEditable(false);
        scrollPane = new JScrollPane(orders);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(20, 50, 450, 355);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.add(scrollPane);

        content.add(exit);
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        orders.append((String) arg);
        content.updateUI();
    }

    /**
     * Gets exit button.
     *
     * @return the exit button
     */
    public JButton getExitButton() {
        return exit;
    }
}

