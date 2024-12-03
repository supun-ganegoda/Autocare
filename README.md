<h1>Inventory Management System (Microservices Architecture)</h1>
    <p>
        This is a <strong>Microservices-Based Inventory Management System</strong> built with 
        <strong>Spring Boot 3</strong> and designed for scalability, performance, and maintainability. 
        The system consists of four core services and a modern Angular frontend for user interactions.
    </p>

<h2>📜 Architecture Overview</h2>
    <ul>
        <li><strong>Core Microservices:</strong>
            <ul>
                <li><strong>Product Service</strong>: Handles product-related operations.</li>
                <li><strong>Order Service</strong>: Manages orders and transactions.</li>
                <li><strong>Inventory Service</strong>: Tracks stock levels and inventory status.</li>
                <li><strong>Notification Service</strong>: Sends asynchronous notifications for key events.</li>
            </ul>
        </li>
        <li><strong>Frontend</strong>: Developed with <strong>Angular</strong> to provide a responsive and user-friendly interface.</li>
    </ul>

<h2>🚀 Key Features</h2>

<h3>🛠️ Technology Stack</h3>
    <ul>
        <li><strong>Spring Boot 3</strong>: Framework for microservices.</li>
        <li><strong>Angular</strong>: Frontend framework.</li>
        <li><strong>SQL and NoSQL Databases</strong>: Hosted as Docker containers for modularity and ease of scaling.</li>
        <li><strong>Flyway</strong>: Database migration tool to manage schema evolution.</li>
        <li><strong>Dockerized Services</strong>: Each service is containerized to prepare for hosting on <strong>Kubernetes</strong> clusters.</li>
    </ul>

<h3>📡 Inter-Service Communication</h3>
    <ul>
        <li><strong>REST Client</strong>: Enables seamless communication between services.</li>
        <li><strong>Apache Kafka</strong>: Implements asynchronous messaging between the <strong>Order</strong> and <strong>Notification</strong> services.</li>
    </ul>

<h3>🛡️ Security and Authorization</h3>
    <ul>
        <li><strong>Keycloak</strong>: Manages user authentication and authorizes API requests via the <strong>API Gateway</strong>.</li>
    </ul>

<h3>📋 Documentation</h3>
    <ul>
        <li><strong>OpenAPI Docs</strong>: Integrated for comprehensive API documentation.</li>
        <li><strong>Swagger-UI</strong>: Enables exploration of endpoints for each service.</li>
    </ul>

<h3>🔧 Resilience and Fault Tolerance</h3>
    <ul>
        <li><strong>Resilient4j</strong>: Implements the Circuit Breaker pattern for graceful service degradation and fault tolerance.</li>
    </ul>

<h3>📊 Observability</h3>
    <ul>
        <li><strong>Grafana</strong>: Provides visualization dashboards.</li>
        <li><strong>Prometheus</strong>: Collects and monitors application metrics.</li>
    </ul>

<h2>✅ Testing</h2>
    <p><strong>Integration Tests</strong>: Ensures service consistency using <strong>WireMock</strong>.</p>

<h2>📂 Project Structure</h2>
    <pre>
├── product-service
├── order-service
├── inventory-service
├── notification-service
├── frontend (Angular)
└── gateway-service (API Gateway)
    </pre>

<h2>🛠️ Setup and Deployment</h2>
    <ol>
        <li><strong>Clone the repository</strong>:
            <pre>git clone https://github.com/supun-ganegoda/Autocare.git</pre>
        </li>
        <li><strong>Start the services</strong>:
            <pre>docker-compose up</pre>
        </li>
        <li><strong>Access the Angular frontend</strong> at <code>http://localhost:4200</code>.</li>
        <li><strong>Explore API Documentation:</strong>
            <ul>
                <li><strong>Swagger UI</strong>: Available at <code>/swagger-ui.html</code> for each service.</li>
                <li><strong>Grafana Dashboard</strong>: Available at <code>/grafana</code>.</li>
            </ul>
        </li>
    </ol>

<h2>🌐 Future Enhancements</h2>
    <ul>
        <li>Implement <strong>CI/CD pipelines</strong> for automated testing and deployment.</li>
        <li>Scale services dynamically using <strong>Kubernetes HPA</strong> (Horizontal Pod Autoscaler).</li>
    </ul>
