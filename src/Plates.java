class Plates extends Product {

    Plates() {
        stock = 100;
        description = "Ceramic Dinner Plates";
        price = 5;
        name = "Plates";
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
