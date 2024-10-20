
# Product Leasing Platform 
A microservices-based platform for leasing products like mobiles, laptops, and other electronic devices. The system is built using **Spring Boot** and follows best practices for building scalable microservices. It includes features like image handling for products.

<a href="https://app.getpostman.com/run-collection/17506310-b931b750-ff0b-4aad-9a97-e001f8d1a936?action=collection%2Ffork&collection-url=entityId%3D17506310-b931b750-ff0b-4aad-9a97-e001f8d1a936%26entityType%3Dcollection%26workspaceId%3D53ff0ab4-0b9e-49cc-8c9c-11eb62d5e9b6" target="_blank">
<img src="https://run.pstmn.io/button.svg" alt="Run in Postman">
</a>


## Project Structure

The platform consists of the following core services:

### 1. **Product Service**
- **Purpose**: Manages the product catalog, including adding, updating, retrieving, and deleting products. Each product can have an associated image, which is uploaded and stored in the server's file system.
- **Key Features**:
    - Add, update, delete, and retrieve products.
    - Upload and store product images.
    - Manage product availability status.

### 2. **Lease Service**
- **Purpose**: Manages product leases, allowing users to lease available products for a specific duration. Leases have various statuses (e.g., **PENDING**, **ACTIVE**, **COMPLETED**), and once a product is leased, its availability is updated automatically.
- **Key Features**:
    - Create and update leases.
    - Track the lease status (e.g., **PENDING**, **ACTIVE**, **COMPLETED**).
    - Automatically update product availability when a lease is created.

## Technologies & Tools

- **Java 11+**
- **Spring Boot** (Microservice framework)
- **Spring Data JPA** (Database interaction)
- **Lombok** (To reduce boilerplate code)
- **Multipart File Handling** (For product image uploads)
- **Maven** (Dependency management and build tool)
- **Postman** (For API testing)

## Project Setup and Running

### Prerequisites

- **Java 11+**
- **Maven**
- **Postman** or any API testing tool for testing APIs.

### Steps to Run Each Service

1. **Product Service**:
    - Navigate to the `product-service` directory.
    - Run `mvn spring-boot:run`.

2. **Lease Service**:
    - Navigate to the `lease-service` directory.
    - Run `mvn spring-boot:run`.

## Features

- **Product Service**:
    - Manage product data (e.g., name, category, price per month, availability).
    - Upload and associate images with products, stored on the server’s filesystem.

- **Lease Service**:
    - Create and manage product leases, including tracking lease status.
    - Automatically update the product’s availability when a lease is created.

## Future Enhancements

- **Kafka Integration**: Add event-driven communication between services using Kafka.
- **Cloud Storage Integration**: Move image storage to cloud providers like AWS S3.
- **Authentication & Authorization**: Implement user authentication using JWT to secure the APIs.
- **Dockerization**: Containerize each service using Docker for easier deployment.
