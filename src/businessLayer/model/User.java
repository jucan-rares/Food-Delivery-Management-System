package businessLayer.model;

import businessLayer.model.Role;

import java.io.Serializable;

/**
 * The type User.
 * @author Ariana Horvath
 */
public class User implements Serializable {

    private static final long serialVersionUID = -352059126420006602L;
    private Role role;
    private String username;
    private String password;


    public User(){};

    public User(Role role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role (enum)
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password string
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

