package by.epam.shop.entity;

public class Souvenir extends BasicEntity {

    private int size;
    private String color;
    private int price;
    private User author;
    private String image;

    public Souvenir(int size, String color, String image, int price, User author) {
        super(0);
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
        this.author = author;
    }

    public Souvenir(Integer identity, int size, String color, String image, int price, User author) {
        super(identity);
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
        this.author = author;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public User getAuthor() {
        return author;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
