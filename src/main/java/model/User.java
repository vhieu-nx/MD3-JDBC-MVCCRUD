package model;

public class User {
    private String username;
    private String password;
    private String lastname;

    private boolean roles;

    public User() {
    }

    public User(String username, String password, String lastname, boolean roles) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isRoles() {
        return roles;
    }

    public void setRoles(boolean roles) {
        this.roles = roles;
    }
}
