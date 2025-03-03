# Order Processing System
This Order Processing System is a backend service that manages and processes e-commerce orders efficiently. It demonstrates modular, scalable, and maintainable system design while incorporating database storage, asynchronous processing, and metrics reporting.

## Overview

This Order Processing System is a backend service that manages and processes e-commerce orders efficiently. It demonstrates modular, scalable, and maintainable system design while incorporating database storage, asynchronous processing, and metrics reporting.

## Features

- **RESTful API** for order creation and management.
- **In-memory queue** for asynchronous order processing.
- **Order status tracking** (`Pending`, `Processing`, `Completed`).
- **Metrics API** to track order processing statistics.

## Technology Stack

- **Spring Boot** (Java)
- **H2 Database** (for development and testing)
- **In-memory queue** for async processing
- **Maven** for dependency management
- **JUnit** for testing

## Setup Instructions

### Prerequisites

Ensure you have the following installed:

- Java 17+
- Maven 3+

### Steps to Run

1. **Clone the repository**:

   ```sh
   git clone <updated-repository-url>
   cd orderProcessingUnit
   ```

2. **Configure Database**:

   - The application uses H2 by default. No additional setup is required.

3. **Run SQL Scripts to Populate H2 Database**:

   - Start the application:
     ```sh
     mvn clean install
     mvn spring-boot:run
     ```
   - Access the H2 Console at `http://localhost:8080/h2-console`.
   - Use the JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, and leave the password empty.
   - Run the SQL scripts manually in the console or set them to execute on startup by placing them in `src/main/resources/data.sql`.

4. **Verify the application is running**: Open `http://localhost:8080/actuator/health` in a browser or use:

   ```sh
   curl http://localhost:8080/actuator/health
   ```

## API Endpoints

### 1. Create Order

**Endpoint:** `POST /orders`

**Request:**

```json
{
  "user_id": 123,
  "order_id": "ORD001",
  "item_ids": ["item1", "item2"],
  "total_amount": 100.50
}
```

**Response:**

```json
{
  "order_id": "ORD001",
  "status": "Pending"
}
```

### 2. Check Order Status

**Endpoint:** `GET /orders/{order_id}`

**Response:**

```json
{
  "order_id": "ORD001",
  "status": "Processing"
}
```

### 3. Fetch Metrics

**Endpoint:** `GET /metrics`

**Response:**

```json
{
  "total_orders_processed": 5000,
  "average_processing_time": 2.5,
  "order_status_counts": {
    "Pending": 50,
    "Processing": 30,
    "Completed": 4920
  }
}
```

## Design Decisions & Trade-offs

- **In-Memory Queue:** Chosen for simplicity and speed, but a distributed queue (e.g., Kafka, RabbitMQ) may be needed for production scalability.
- **Database Choice:** Using H2 for development and testing; can be switched to PostgreSQL/MySQL for production.
- **Async Processing:** Ensures system responsiveness but may introduce slight processing delays.
- **Metrics Collection:** Simple in-memory tracking is used for metrics; a more robust monitoring solution like Prometheus/Grafana can be integrated.
- **Scalability:** The current setup can simulate handling 1,000 concurrent orders, but horizontal scaling and load balancing will be required for large-scale production environments.

## Assumptions

- Orders are uniquely identified by `order_id`.
- Order processing time is simulated and varies.
- The system scales up to 1,000 concurrent orders in the current setup.
- No authentication is implemented, assuming a trusted internal environment.
- The system assumes data consistency is managed by the application logic since an in-memory queue is used.
- Failures in processing are not currently retried automatically; future versions can implement a retry mechanism.

## Running Tests

To execute unit tests:

```sh
mvn test
```

## Future Considerations

This application is designed to be containerized and deployed to AWS. The following components are included for future scalability and deployment:

- **Dockerfile**: Enables containerization of the application.
- **Jenkinsfile**: Automates CI/CD pipeline.
- **Terraform Configuration**: Infrastructure as Code (IaC) for AWS deployment.

### AWS Deployment Architecture

```
                      +--------------------------+
                      |      Route 53 (DNS)      |
                      +-----------+--------------+
                                  |
                      +-----------v--------------+
                      |      ALB (Load Balancer)  |
                      +-----------+--------------+
                                  |
          +-----------------------+----------------------+
          |                                              |
+---------v----------+                      +-----------v----------+
| EC2 Instance 1    |                      | EC2 Instance 2        |
| Order Processing  |                      | Order Processing      |
+---------+---------+                      +-----------+----------+
          |                                             |
          +----------------------+----------------------+
                                 |
                        +--------v--------+
                        |    SQS Queue    |
                        +--------+--------+
                                 |
               +----------------v-----------------+
               |       RDS (PostgreSQL/MySQL)     |
               +----------------------------------+
                                 |
               +----------------v-----------------+
               |      S3 (Logs/Backups)           |
               +----------------------------------+
                                 |
               +----------------v-----------------+
               |      CloudWatch (Monitoring)     |
               +----------------------------------+
```

### Steps for Containerization & Deployment

1. **Build the Docker Image**
   ```sh
   docker build -t order-processing-app .
   ```
2. **Run the Docker Container**
   ```sh
   docker run -p 8080:8080 order-processing-app
   ```
3. **Push to Container Registry (AWS ECR)**
   ```sh
   aws ecr create-repository --repository-name order-processing-app
   docker tag order-processing-app <aws-account-id>.dkr.ecr.<region>.amazonaws.com/order-processing-app
   docker push <aws-account-id>.dkr.ecr.<region>.amazonaws.com/order-processing-app
   ```
4. **Deploy Using Terraform**
   ```sh
   cd terraform
   terraform init
   terraform apply
   ```
5. **CI/CD with Jenkins**
   - Configure Jenkins to trigger builds on repository changes.
   - Automate deployment steps using the Jenkinsfile.
   - Ensure infrastructure provisioning and application deployment are seamless.

## Repository Link

[GitHub Repo](https://github.com/parthgargv/order-processing-system)

