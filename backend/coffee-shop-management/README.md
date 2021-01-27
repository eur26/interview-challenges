## General instructions

We have developed a small software to run a Coffee Shop. You can place an order and print the receipt.
We have some bug reports from users and some tasks to evolve the application.

Your task is to understand the current code and implement the topics that we have listed at the next section


## Coffee Shop Management

##### 1) There is a promotion: if you order 2 lattes, you will receive a free espresso.
* This feature is already available, but our users are complaining that it does not work everytime. We need to fix it.

##### 2) Print the Menu:

List of all available products and their prices:

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

##### 5) We need to add a new promotion:
  * When the order has food and drinks with total value over $ 50, which Latte will cost $3.
  * Note: The final price after the promotion is applied can be under $ 50.

##### 6) New Feature: The software does not have a way to know the amount of clients we had on a specific period.
   * Add the client concept: Consider that a client place an order which can have one or more products.
   * The order can have different amounts and types of products.
   
##### 7) Create a sales daily report
   * List all the products that were sold with the amount (2 lattes, 3 slices of cake...)
   * Calculate the average client expense.   

## Additional comments

Feel free to use any dependency that is not currently on the project.

If you are planning to use git to manage the code, it would be nice to have a branch for each requirement.
In this scenario, you should:

1) create a branch from the original code
2) work on the first topic
3) create a second branch from the first branch
4) work on the second topic
5) create a third branch from the second branch 
....

That way we can see the evolution of your test.

Feel free to contact us if you have any questions or comments.
