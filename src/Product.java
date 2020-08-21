public abstract class Product {

    protected String name;
    String description;
    double price;
    protected int stock;

    abstract String getName();
    abstract String getDescription();
    abstract double getPrice();
    abstract int getStock();

    abstract void setStock(int newStock);

}
