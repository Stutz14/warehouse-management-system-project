# warehouse-management-system-project
The Warehouse Management System (WMS) is a software application that simulates the operations of a company's warehouse, enabling the sale of goods to clients and restocking from suppliers. The system comprises the following key components and functionalities:

Information Model:
A database tracks product details, including ID, name, stock levels, unit price, restock schedule, and discount strategies.
Products have predefined minimum and maximum stock levels to ensure availability and prevent overstocking.
Order Processing:
Clients place orders through a user interface, selecting products and quantities.
Orders exceeding available stock trigger a restocking process. Orders surpassing the maximum stock limit are rejected.
Pricing strategies, such as volume-based discounts, are applied dynamically.
Restocking and Notifications:
Restocking schedules are predefined for each product, specifying how many units are added per operation.
Notifications are triggered for low stock or pending orders, ensuring smooth warehouse operations.
System Structure:
Server: Manages backend functionality, including authentication, request handling, business logic, and database updates.
Client: Allows users to place orders and receive responses via a simple interface.
Database: Stores product and admin credentials information.
Use Cases:
UC1: Server startup with admin authentication.
UC2: Client connection to the server.
UC3: Order placement and response handling.
UC4: Order processing and warehouse updates.
UC5: Price calculation using discount strategies.
UC6: Automated restocking based on predefined schedules.
Design Patterns:
The project employs various design patterns, including Singleton, Proxy, Factory Method, and Observer, to ensure a modular and maintainable system architecture.
This project demonstrates end-to-end warehouse operations, including order management, dynamic pricing, and inventory control, through a scalable and user-friendly system.
