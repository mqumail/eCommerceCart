class Shirt extends Product {

    Shirt() {
        stock = 100;
        description = "100% cotton shirts";
        price = 25;
        name = "Shirt";
    }

    @Override
    double getPrice() {
        return price;
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
    String getDescription() {
        return description;
    }

    @Override
    void setStock(int newStock) {
        this.stock = newStock;
    }
}
