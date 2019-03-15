package by.epam.shop.entity;

public class Balance extends BasicEntity {

    private User user;
    private double currentBalance;
    private double overdraft;

    public Balance(User user) {
        super(0);
        this.user = user;
        this.currentBalance = 0;
        this.overdraft = 100;
    }

    public Balance(Integer identity, User user, double currentBalance, double overdraft) {
        super(identity);
        this.user = user;
        this.currentBalance = currentBalance;
        this.overdraft = overdraft;
    }

    public User getUser() {
        return user;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
