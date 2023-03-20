package controllers;

import businessLayer.DeliveryServiceProcessing;
import presentationLayer.ClientGUI;
import tools.DataGlobals;
import tools.Serializer;
import tools.Validator;

import javax.swing.*;

public class ClientController {

    private ClientGUI clientGUI;
    private DeliveryServiceProcessing deliveryServiceProcessing;

    public ClientController(DeliveryServiceProcessing deliveryServiceProcessing) {
        clientGUI = new ClientGUI();
        this.deliveryServiceProcessing = deliveryServiceProcessing;
        init();
    }

    public void init() {
        clientGUI.getExitButton().addActionListener(e->{
            Serializer.serializeProducts();
            Serializer.serializeClients();
            Serializer.serializeProducts();
            clientGUI.setVisible(false);
        });

        clientGUI.getViewProductsButton().addActionListener(e->{
            JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
            clientGUI.addTable(productsTable);
        });

        clientGUI.getShowCProductButton().addActionListener(e->{
            try {
                if ( Validator.stringValidate(clientGUI.getTitleTextField().getText()) ){
                    JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
                clientGUI.getCompositeProductTextArea().setText(deliveryServiceProcessing.createCProductString(clientGUI.getTitleTextField().getText()));
            } catch (Exception exp) {
            }
            JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        clientGUI.getSearchButton().addActionListener(e->{
            try {
                JTable productsTable = deliveryServiceProcessing.createProductsTable(
                        deliveryServiceProcessing.searchProducts (clientGUI.getTitleTextField().getText(), clientGUI.getRatingTextField().getText(),
                                clientGUI.getCaloriesTextField().getText(), clientGUI.getProteinTextField().getText(),
                                clientGUI.getFatTextField().getText(), clientGUI.getSodiumTextField().getText(),
                                clientGUI.getPriceTextField().getText()));
                clientGUI.addTable(productsTable);
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "Failure", "Fail", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

        });

        clientGUI.getMakeOrderButton().addActionListener(e->{
            System.out.println("Debug");
            try {
                if ( Validator.stringValidate(clientGUI.getClientTextField().getText()) ||
                        ( Validator.stringValidate(clientGUI.getProductsTextArea().getText()) ) ){
                    JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
                deliveryServiceProcessing.createOrder(clientGUI.getClientTextField().getText(), clientGUI.getProductsTextArea().getText());

            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "Fail", "Fail", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

        });
    }
}

