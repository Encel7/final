package by.epam.shop.entity;

import java.sql.Date;

public class Order extends BasicEntity{

    private boolean isPaid;
    private double totalPrice;
    private Date date;
    private User user;

    public Order(boolean isPaid, double totalPrice, Date date, User user) {
        super(0);
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
    }

    public Order(Integer identity, boolean isPaid, double totalPrice, Date date, User user) {
        super(identity);
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
