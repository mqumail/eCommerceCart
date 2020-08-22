public abstract class Product {

    protected String name;
    String description;
    double price;
    protected int stock;

    String getName() {
        return name;
    }
    String getDescription(){
        return description;
    }
    double getPrice(){
        return price;
    }
    int getStock(){
        return stock;
    }

    void setStock(int newStock){
        stock = newStock;
    }

}
