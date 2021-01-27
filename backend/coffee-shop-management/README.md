## General instructions

We have developed a small software to run a Coffe Shop. You can place a order and get the receipt.
Now we have some bug reports from the users and some tasks to evolve the application.

Your task is to understand the current code and implement the topics that we have listed at the next section


## Coffee Shop Management

##### 1) There is a promotion, when you order 2 lattes, you will receive a free espresso.
* This feature is already available, but our users are complaining that it does not work everytime. We need to fix it.

##### 2) Print the Menu:

Expected output:

| Product Name | Price |
| :---: | :---: |
| Latte | $ 5.3 |
| Espresso | $ 4 |
| Sandwich | $10.10 |

##### 3) We need to add a new promotion: 
  * 5% discount on the total, if the order have more than 8 products. 
  * In cases that the order is suitable for more than one promotion, only one should be active (the cheapest for the client).
  
##### 4) Add the following product list:

| Product Name | Price |
| :---: | :---: |
| Capuccino | $ 8 |
| Tea | $ 6.1 |
| Cake Slice | $ 9 |
| Milk | $ 1 | 

##### 4) We need to add a new promotion:
  * When the order has food and drinks with total value over $ 50, which Latte will cost $3.
  * Note: As the total price of the order is going to be recaulculated, the final value can be under $ 50.

##### 5) Create a sales daily report
   * List all the products that were sold with the ammount (2 lattes, 3 slices of cake...)
   * How many orders we received on that day? 1 order XXXXXX
   


## Additional comments

Feel free to use any other dependency that is not current on the project, if you think it's necessary.

If you are planning to use git to manage the code, it would be nice to have a branch for each requirement. 
In this scenario, you should:

##### 1 - create a branch from the original code
##### 2 - work on the first topic
##### 3 - create a second branch from the first branch
##### 4 - work on the second topic
##### 5 - create a third branch from the second branch 
##### ....
