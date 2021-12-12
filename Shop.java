public class Shop {
    private int id;
    private String name;
    private String price;
    private String stock;
    private int user_id;
    public Shop() {

    }

    public Shop(int id, String name, String price, String stock, int user_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    public String getStock() {
        return stock;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }
}
