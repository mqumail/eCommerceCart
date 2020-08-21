import utility.StockCheck;

class Football extends Product {

    Football() {
        stock = 50;
        description = "Professional ball used in the World Cup";
        price = 15;
        name = "Football";
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
