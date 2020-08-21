class Basketball extends Product {

    Basketball() {
        stock = 100;
        description = "Professional ball used in the NBA league";
        price = 25;
        name = "Basketball";
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
