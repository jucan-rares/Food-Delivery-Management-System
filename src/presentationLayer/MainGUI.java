package presentationLayer;


import businessLayer.DeliveryServiceProcessing;
import businessLayer.model.Role;
import businessLayer.model.User;
import controllers.AdministratorController;
import controllers.ClientController;
import tools.Validator;


import javax.swing.*;
import java.awt.*;


public class MainGUI extends JFrame {

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();

    private JButton adminRegister = new JButton("admin register");
    private JButton adminLogIn = new JButton("admin log in");
    private JButton clientRegister = new JButton("client register");
    private JButton clientLogIn = new JButton("client log in");
    private JButton employeeRegister = new JButton("employee register");
    private JButton employeeLogIn = new JButton("employee log in");
    private DeliveryServiceProcessing deliveryServiceProcessing;


    public MainGUI() {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.deliveryServiceProcessing = new DeliveryServiceProcessing();

        content.add(new JLabel("username:"));
        content.add(username);

        content.add(new JLabel("password:"));
        content.add(password);

        content.add(adminRegister);
        content.add(adminLogIn);

        content.add(clientRegister);
        content.add(clientLogIn);

        content.add(employeeRegister);
        content.add(employeeLogIn);

        listenerConfig();
    }

    public void listenerConfig(){

        this.getRegisterAdmin().addActionListener(e -> {
            if ((Validator.stringValidate(this.getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this.getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!deliveryServiceProcessing.registerUser(new User(Role.ADMIN, this.getUsernameTextField().getText(),
                    this.getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Cannot register new admin...", "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "New admin was registered...", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        this .getRegisterEmployee().addActionListener(e -> {
            if ((Validator.stringValidate(this .getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deliveryServiceProcessing.registerUser(new User(Role.EMPLOYEE, this .getUsernameTextField().getText(),
                    this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Cannot register new employee...", "Error", JOptionPane.ERROR_MESSAGE);

            }
            JOptionPane.showMessageDialog(null, "New employee was registered...", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        this .getRegisterClient().addActionListener(e -> {
            if ((Validator.stringValidate(this .getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deliveryServiceProcessing.registerUser(new User(Role.CLIENT, this .getUsernameTextField().getText(),
                    this .getPasswordTextField().getText()))){
                JOptionPane.showMessageDialog(null, "Cannot register new client...", "Error", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "New client was registered...", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        this .getLogInAdmin().addActionListener(e -> {
            if ((Validator.stringValidate(this .getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deliveryServiceProcessing.logInUser(this .getUsernameTextField().getText(), this .getPasswordTextField().getText(), Role.ADMIN)){
                JOptionPane.showMessageDialog(null, "Log in failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(null, "Logged in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            new AdministratorController(deliveryServiceProcessing);
        });

        this .getLogInEmployee().addActionListener(e -> {
            if ((Validator.stringValidate(this .getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!deliveryServiceProcessing.logInUser(this .getUsernameTextField().getText(), this .getPasswordTextField().getText(), Role.EMPLOYEE)){
                JOptionPane.showMessageDialog(null, "Log in failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(null, "Logged in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            new EmployeeGUI(deliveryServiceProcessing);
        });

        this .getLogInClient().addActionListener(e -> {

            if ((Validator.stringValidate(this .getUsernameTextField().getText())) ||
                    (Validator.stringValidate(this .getPasswordTextField().getText()))) {
                JOptionPane.showMessageDialog(null, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!deliveryServiceProcessing.logInUser(this.getUsernameTextField().getText(), this.getPasswordTextField().getText(), Role.CLIENT)){
                JOptionPane.showMessageDialog(null, "Log in failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(null, "Logged in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            new ClientController(deliveryServiceProcessing);
        });
    }


    public JTextField getUsernameTextField() {
        return username;
    }

    public JPasswordField getPasswordTextField() {
        return password;
    }

    public JButton getRegisterAdmin() {
        return adminRegister;
    }

    public JButton getLogInAdmin() {
        return adminLogIn;
    }

    public JButton getRegisterEmployee() {
        return employeeRegister;
    }

    public JButton getLogInEmployee() {
        return employeeLogIn;
    }


    public JButton getRegisterClient() {
        return clientRegister;
    }

    public JButton getLogInClient() {
        return clientLogIn;
    }
}