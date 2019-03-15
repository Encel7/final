package by.epam.shop.entity;

public class User extends BasicEntity {

    private Role role;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private double discount;

    public User(Role role,
                String firstName,
                String lastName,
                String login,
                String password,
                 double discount) {
        super(0);
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.discount = discount;
    }

    public User(Integer identity,
                Role newRole,
                String newFirstName,
                String newLastName,
                String newLogin,
                String newPassword,
                double newDiscount) {
        super(identity);
        this.role = newRole;
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.login = newLogin;
        this.password = newPassword;
        this.discount = newDiscount;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getDiscount() {
        return discount;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
