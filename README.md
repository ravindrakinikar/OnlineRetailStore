# OnlineRetailStore Problem Statement
Build the domain model only for a checkout counter in an online retail store that scans products and
generates an itemized bill.
The bill should also total the cost of all the products and the applicable sales tax for each product.
The total cost and total sales tax should be printed
Sales tax varies based on the type of products
- Category A products carry a levy of 10%
- Category B products carry a levy of 20%
- Category C products carry no levy

# Introduction: 
This project consists of RESTful services that implement a checkout counter for an online retail store that scans products and generates itemized bill.

It provides services for managing products and orders.  Products can be configured with rate and category (A,B or C). Sales tax is applied based on the category of the product:
* Category A - 10%
* Category B - 20%
* Category C- 0%

Bill details the products, quantity, total cost,sales tax and the total value of the bill.Data for 10 Products and 1 bill are added during startup to browse.

# REST endpoints
Client can add/update/modify products and orders using the REST endpoints.Below is overview of REST end points:

## Products
*  GET /products - fetches list of all product data
*  GET /products/{id} - fetch a specific product
*  POST /products - Creates a new product based on request JSON
*  PUT /products/{id} - Updates product data based on request JSON
*  DELETE /products/{id} - Delete an existing product if it is not associated with a bill.


## Bills
*  GET /bills - fetches all bill data
*  GET /bills/{id} - fetches bill of a particular id
*  POST /bills - creates a bill Id. Client has to use this bill Id while adding and removing products
*  PUT /bills/{id} - Updates bill data. Client can add or remove products to bill sending a JSON request.
*  DELETE /bills/{id} - Delete bill from the system.

 These REST end points are secured using basic authentication mechanism. Code uses in-memory repository with 'bob' as single user.

# About Implementation

This application has been using SpringBoot as it provides a wide variety of features that aid development and maintainence. Some features that were utilised were: Spring Security, Spring Data/JPA and starters.


This application uses H2 database and does not persist data on application restarts. 

