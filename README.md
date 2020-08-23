# eCommerceCart
#Library Usage
1) I used the JUnit 5 because that was the latest one available and the documentation was good to get started with some tests
2) I wanted to use an API for calculating tax so I looked at TaxJar and Avalara but could not succefully obtain an API key to use their services
3) I was succesfully able to integrate Google's Geocoding API and get results based on an input address but decided to not use the API because I could not get the status code. The ide behind it was that if I get a status 'OK' from users input shipping address, then it is a valid address otherwise I would ask them to enter a valid address again. I had issues getting the status code from the response, as the response was just returning the Geocode results. So in the end I removed the library and the code.

Application Usage:
First it shows the user a catologue of items that can be bought.
Then prompts user to enter a product and its quantity. It keeps prompting the user until they finish shopping by pressing a specified key.
Then it shows them the current cart with the total and ask them if they want to proceed to check out or update the cart.
If user selects to update the cart, it asks them to enter the product which they want to update the quantity for (enter 0 to remove it completely)
Finally it lets the user choose the shipping and payment methods (does not ask user for shipping or payment information, just says we will use the information on file for you)


