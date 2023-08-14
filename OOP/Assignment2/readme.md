My approach,

My approach to this assignment was to make most things revolve around the customer,
to make the ordering process easy.

I decided that every customer will have their own shopping cart (not every shopping cart having a customer),
Every time a new customer instance is created we provide it with its own shopping cart,
items can be added and removed directly through the customer class to help make the code
easier to understand and easier to read

I wanted to make the Item class super basic as I didn't see a need to complicate it
It is a simple class that takes in a name and a price in its constructor and has 2 getter methods for them
But I also wanted it to be impossible for an item to be free or a negative value so I fixed that using a ternary operator

For the address class I understood there was a requirement for the ability to add both the shipping and billing addresses,
however most people (atleast me anyway) don't use a different billing address, I added 2 constructors, one where it
only takes in a shipping address and sets the billing address as the same and then the other constructor that takes
in another address. There is also a very basic address checker that just checks the length of the address to ensure it
is atleast somewhat valid.

Then there was the payment class. In here I take in the card type, card number and the expiry date.
I check if each of them is valid. The card types accepted are mastercard and visa, it will not accept any other ones,
the card number must be 16 digits, and the expiry date must be in the future. I implemented that using the LocalDate library.

Then we have the most "important" class. The order class. In here I ensure that all the data we collected is valid,
I print out the customers items and its total price. I then use the email class to send of an email to the customer
depending on their scenario. Such as all good and the order went through or whether something went wrong and their
order couldn't process for whichever reason as well as mentioning said reasons in the "email".

I also tested a few extra scenarios outside of the scope of the assignment to provide extra detail on some of the functionality
of the program.