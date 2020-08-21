class Shoes extends Product {

    Shoes() {
        stock = 100;
        description = "Oxford Shoes in Brown";
        price = 110;
        name = "Shoes";
    }

    @Override
    double getPrice() {
        return price;
    }

    @Override
    String getDescription() {
        return description;
    }

    @Override
    int getStock() {
        return stock;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    void setStock(int newStock) {
        this.stock = newStock;
    }
}
