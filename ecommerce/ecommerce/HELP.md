## Spring Boot E-commerce REST API

This is a backend REST API for an e-commerce platform built with Java and Spring Boot. It features a robust, role-based authentication system using JWT, token caching for enhanced security, and foundational e-commerce functionalities.

## Features

###  Secure Authentication: Token-based authentication (JWT) for secure API access.

Role-Based Access Control (RBAC):

ADMIN: Full access to all system functionalities.

STAFF: Privileged access to manage products and orders.

CUSTOMER: Can browse products, view deals, and manage their own purchases.

Token Caching & Blacklisting: Uses Caffeine cache to invalidate JWTs upon logout, preventing token reuse.

Product & Deals Management:

Endpoints for general products.

Special sections for "Regular Deals" and time-sensitive "Midnight Deals".

In-Memory Database: Utilizes H2 for easy setup and development testing.

## Technologies Used

Java 17

Spring Boot 2.7.5

Spring Web

Spring Security

Spring Data JPA

Spring Cache

H2 Database: In-memory SQL database.

Hibernate: JPA provider.

JWT (Java Web Token): For generating and validating access tokens.

Caffeine: High-performance Java caching library.

Lombok: To reduce boilerplate code.

Maven: Dependency management.

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

JDK 17 or later
Apache Maven

### Installation & Running the Application

Clone the repository or set up the project with the provided files.

Navigate to the project's root directory.

Run the application using Maven:

``` mvn spring-boot:run```


The application will start on http://localhost:8080.

## API Endpoints

### Here are some of the main API endpoints available:

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| POST | `/api/auth/register` | Public | Register a new user (customer, staff, or admin). |
| POST | `/api/auth/login` | Public | Authenticate a user and receive a JWT. |
| POST | `/api/auth/logout` | Authenticated | Log out the user and invalidate the JWT. |
| GET | `/api/products` | Public | Get a list of all products. |
| GET | `/api/deals/regular` | Public | Get products currently on a regular deal. |
| GET | `/api/deals/midnight` | Public | Get products currently on a midnight deal. |
| POST | `/api/products/staff/**` | Staff, Admin | Endpoints for staff to manage products. |
| POST | `/api/products/admin/**` | Admin | Endpoints for admins to manage products. |
| GET | `/api/orders/my-orders` | Customer | View personal order history. |

## Configuration

Key configuration settings can be found in src/main/resources/application.properties.

* Database: H2 database connection details.
* JWT Secret: The secret key used for signing JWTs. It's crucial to change this to a strong, unique secret in a production environment.
* Cache: Caffeine cache settings, including expiration time and maximum size.

## H2 Database Console

The H2 in-memory database console is enabled for development.

* URL: http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:ecommercedb
* Username: sa
* Password: password

## How It Works

* Registration: A user registers by providing a username, email, password, and role. The password is encrypted using BCrypt before being stored.
* Login: The user logs in with their credentials. Upon success, the server generates a JWT and returns it to the client.
* API Requests: The client includes the JWT in the Authorization header (Bearer <token>) for all subsequent requests to protected endpoints.
* Authorization Filter: The JwtRequestFilter intercepts each request, validates the token, and checks that it hasn't been blacklisted in the cache. If valid, it sets the user's authentication context.
* Logout: When a user logs out, their JWT is added to a "blacklist" in the Caffeine cache. The token will be rejected if used again before it expires naturally.
