package utility;

import java.util.ArrayList;

public class Helpers {

    //reference: https://www.tutorialspoint.com/How-to-create-a-string-from-a-Java-ArrayList#:~:text=To%20convert%20the%20contents%20of,using%20the%20toString()%20method.
    public static String productsListToString(ArrayList<ProductsInBasket> products){
        StringBuilder buffer = new StringBuilder();
        buffer.append("Current Cart:\n");
        for (ProductsInBasket product : products){
            if (product.getQuantity() != 0){
                buffer.append("Name: ").append(product.getProduct()).append(" Quantity: ").append(product.getQuantity()).append("\n");
            }
        }
        return buffer.toString();
    }
}
