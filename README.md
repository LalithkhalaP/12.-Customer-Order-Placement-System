# 12.-Customer-Order-Placement-System
Objective:
To create a servlet + JDBC application where customers place an order, and the servlet stores it in the database.
Description:
Customer enters name, item, and quantity through an HTML form.
Servlet stores the order in the database and prints an order summary.
Uses init() for DB connection and destroy() for cleanup.
Requirements:
HTML:
Form for entering name, item, quantity
Servlet:
init() → establish DB connection
service() →
oRead parameters
oInsert into orders table
oDisplay confirmation
destroy() → close connection
connect jar and server then run it.
