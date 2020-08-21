package utility;

public class ProductsInBasket {

    private String product;
    private int quantity;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductsInBasket))  {
            return false;
        }
        ProductsInBasket other = (ProductsInBasket)obj;
        return product.equals(other.product);
    }
    @Override
    public int hashCode() {
        return 31*product.hashCode();
    }

}
