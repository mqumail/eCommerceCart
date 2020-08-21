class Jeans extends Product {

    Jeans() {
        stock = 100;
        description = "Blue slim fit jeans";
        price = 100;
        name = "Jeans";
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
