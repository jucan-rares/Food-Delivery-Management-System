package controllers;


import businessLayer.DeliveryServiceProcessing;
import businessLayer.model.BaseProduct;
import presentationLayer.AdministratorGUI;
import tools.DataGlobals;
import tools.Serializer;
import tools.Validator;


import javax.swing.*;


public class AdministratorController {

    private AdministratorGUI administratorGUI;
    private DeliveryServiceProcessing deliveryServiceProcessing;


    public AdministratorController(DeliveryServiceProcessing deliveryServiceProcessing) {
        administratorGUI = new AdministratorGUI();
        this.deliveryServiceProcessing = deliveryServiceProcessing;
        init();
    }


    public void init() {
        administratorGUI.getExitButton().addActionListener(e->{
            Serializer.serializeClients();
            Serializer.serializeOrders();
            Serializer.serializeProducts();
            administratorGUI.setVisible(false);
        });

        administratorGUI.getImportProducts().addActionListener(e->{
            if ( (deliveryServiceProcessing.importProducts() == false) ||
                    ( Serializer.serializeClients() == false ) ||
                    ( Serializer.serializeOrders() == false ) ||
                    (Serializer.serializeProducts() == false)) {
                JOptionPane.showMessageDialog(null, "Products already imported", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        administratorGUI.getViewProductsButton().addActionListener(e->{
            JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
            administratorGUI.addTable(productsTable);
        });

        administratorGUI.getAddBaseProductButton().addActionListener(e->{
            if ( Validator.stringValidate( administratorGUI.getTitleTextField().getText() ) ){
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Exception status = Validator.validateNumberTextFields(administratorGUI.getRatingTextField().getText(), administratorGUI.getCaloriesTextField().getText(),
                    administratorGUI.getProteinTextField().getText(), administratorGUI.getSodiumTextField().getText(),
                    administratorGUI.getFatTextField().getText(), administratorGUI.getPriceTextField().getText());

            if ( status != null ){
                JOptionPane.showMessageDialog(null, status.getMessage(), "Invalid product description", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deliveryServiceProcessing.addBaseProduct(new BaseProduct(administratorGUI.getTitleTextField().getText(),
                    Double.parseDouble(administratorGUI.getRatingTextField().getText()),
                    Integer.parseInt(administratorGUI.getCaloriesTextField().getText()),
                    Integer.parseInt(administratorGUI.getProteinTextField().getText()),
                    Integer.parseInt(administratorGUI.getSodiumTextField().getText()),
                    Integer.parseInt(administratorGUI.getFatTextField().getText()),
                    Integer.parseInt(administratorGUI.getPriceTextField().getText())))){
                JOptionPane.showMessageDialog(null, status.getMessage(), "Cannot add base products", JOptionPane.ERROR_MESSAGE);
                return;
            }
//                administratorGUI.displayInformationMessage("Product successfully added");
            JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
            administratorGUI.addTable(productsTable);
        });

        administratorGUI.getUpdateBaseProductButton().addActionListener(e->{
            try {
                if ( Validator.stringValidate( administratorGUI.getTitleTextField().getText() ) ){
                    JOptionPane.showMessageDialog(null, "Title field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Exception status = Validator.validateNumberTextFields(administratorGUI.getRatingTextField().getText(), administratorGUI.getCaloriesTextField().getText(),
                        administratorGUI.getProteinTextField().getText(), administratorGUI.getSodiumTextField().getText(),
                        administratorGUI.getFatTextField().getText(), administratorGUI.getPriceTextField().getText());

                if ( status != null ){
                    JOptionPane.showMessageDialog(null, status.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                deliveryServiceProcessing.updateBaseProduct(new BaseProduct(administratorGUI.getTitleTextField().getText(),
                        Double.parseDouble(administratorGUI.getRatingTextField().getText()),
                        Integer.parseInt(administratorGUI.getCaloriesTextField().getText()),
                        Integer.parseInt(administratorGUI.getProteinTextField().getText()),
                        Integer.parseInt(administratorGUI.getSodiumTextField().getText()),
                        Integer.parseInt(administratorGUI.getFatTextField().getText()),
                        Integer.parseInt(administratorGUI.getPriceTextField().getText())));
//                administratorGUI.displayInformationMessage("Product successfully updated");
                JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
                administratorGUI.addTable(productsTable);
//                Serializer.serialize(deliveryService);

            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getDeleteBaseProductButton().addActionListener(e->{
            if ( Validator.stringValidate( administratorGUI.getTitleTextField().getText() ) ){
                JOptionPane.showMessageDialog(null, "Title field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            deliveryServiceProcessing.deleteBaseProduct(administratorGUI.getTitleTextField().getText());
//                administratorGUI.displayInformationMessage("Product successfully deleted");
            JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
            administratorGUI.addTable(productsTable);

        });

        administratorGUI.getAddCompositeProductButton().addActionListener(e->{
            try {
                if ( (Validator.stringValidate(administratorGUI.getCompositeTitleTextField().getText())) ||
                        (Validator.stringValidate(administratorGUI.getProductTextField().getText())) ) {
                    JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
                deliveryServiceProcessing.addCompositeProduct(administratorGUI.getCompositeTitleTextField().getText(),
                        administratorGUI.getProductTextField().getText());
//                administratorGUI.displayInformationMessage("Composite Product successfully added");
                JTable productsTable = deliveryServiceProcessing.createProductsTable(DataGlobals.globalMenuItems);
                administratorGUI.addTable(productsTable);
//                Serializer.serialize(deliveryService);
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getShowCProductButton().addActionListener(e->{
            try {
                if ( (Validator.stringValidate(administratorGUI.getCompositeTitleTextField().getText())) ) {
                    JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
                administratorGUI.getCompositeProductTextArea().setText(deliveryServiceProcessing.createCProductString(administratorGUI.getCompositeTitleTextField().getText()));
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getReport1Button().addActionListener(e->{
            try {

                // Validate this later
//                validator.validateTextField(administratorGUI.getStartHourTextField().getText());
//                validator.validateTextField(administratorGUI.getEndHourTextField().getText());
//                int start = validator.validateHour(administratorGUI.getStartHourTextField().getText());
//                int end = validator.validateHour(administratorGUI.getEndHourTextField().getText());
//                validator.validateHoursInMinutes(start, end);
                int start = Validator.validateHour( administratorGUI.getStartHourTextField().getText() );
                int end = Validator.validateHour( administratorGUI.getEndHourTextField().getText() );
                deliveryServiceProcessing.generateReport1(start, end);
//                administratorGUI.displayInformationMessage("Report 1 successfully created.");
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getReport2Button().addActionListener(e->{
            try {


                if ( Validator.intValidate( administratorGUI.getNoTimesTextField().getText() ) ){
                    JOptionPane.showMessageDialog(null, "Report 2 param is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }

                deliveryServiceProcessing.generateReport2(Integer.parseInt(administratorGUI.getNoTimesTextField().getText()));
//                administratorGUI.displayInformationMessage("Report 2 successfully created.");
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getReport3Button().addActionListener(e->{
            try {
                if ( Validator.intValidate( administratorGUI.getNoTimes2TextField().getText() )
                        ||   Validator.intValidate( administratorGUI.getAmountTextField().getText() )){
                    JOptionPane.showMessageDialog(null, "Number fields are invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }

                deliveryServiceProcessing.generateReport3(Integer.parseInt(administratorGUI.getNoTimes2TextField().getText()),
                        Integer.parseInt(administratorGUI.getAmountTextField().getText()));
//                administratorGUI.displayInformationMessage("Report 3 successfully created.");
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });

        administratorGUI.getReport4Button().addActionListener(e->{
            try {
                int[] date = Validator.validateDate(administratorGUI.getDayTextField().getText());
                deliveryServiceProcessing.generateReport4(date[0], date[1], date[2]);
//                administratorGUI.displayInformationMessage("Report 4 successfully created.");
            } catch (Exception exp) {
//                administratorGUI.displayErrorMessage(exp);
            }
        });
    }
}

