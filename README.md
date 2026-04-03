# 🚀 Flyway DB Migration — From Scratch

A Spring Boot project demonstrating **Flyway database migrations from project inception** — complete schema version control using Flyway, with Hibernate having no control over table creation.

---

## 🗄️ Flyway Migration History

| Version | File | Description |
|---------|------|-------------|
| V1 | `V1__create_users_table.sql` | Create users table |
| V2 | `V2__create_products_table.sql` | Create products table |
| V3 | `V3__create_orders_table.sql` | Create orders table |
| V4 | `V4__create_order_items_table.sql` | Create order_items table |
| V5 | `V5__add_seed_data.sql` | Add seed data |
| V6 | `V6__add_indexes.sql` | Add indexes for performance |
| V7 | `V7__add_coupon_column.sql` | Add coupon feature (ALTER TABLE) |
| V8 | `V8__revert_coupon_column.sql` | Revert coupon column (manual rollback) |

---

## 🛠️ Flyway Setup (How I Built This)

### 1. From Scratch Approach
- `ddl-auto=none` — Hibernate does NOT create/modify tables
- Flyway handles complete schema management from day one
- All schema changes versioned as SQL migration files

### 2. Dependencies Added
```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
</dependency>
```

### 3. Flyway Configuration
```properties
# Flyway handles schema — Hibernate does not
spring.jpa.hibernate.ddl-auto=none

# Flyway config
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=false
```

### 4. Verify Migrations
```sql
SELECT * FROM flyway_schema_history;
```

---

## ⚙️ How Flyway Works
```
App Startup → Flyway checks flyway_schema_history table
            → Runs pending migrations in order (V1 → V8)
            → Marks each migration as applied
            → App starts with fully configured schema
```

---

## 🌱 Spring Profiles

| Profile | Usage |
|---------|-------|
| `local` | Local development with local DB config |
| `dev` | Development with RDS config |
| `prod` | Production environment |
```bash
# Run with local profile
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Run with dev profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Run with prod profile
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 📡 API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by id |
| POST | `/api/users` | Create user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by id |
| GET | `/api/products/category/{category}` | Get by category |
| POST | `/api/products` | Create product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get order by id |
| GET | `/api/orders/status/{status}` | Get by status |
| GET | `/api/orders/user/{userId}` | Get by user |
| POST | `/api/orders` | Create order |
| PATCH | `/api/orders/{id}/status` | Update order status |
| DELETE | `/api/orders/{id}` | Delete order |

---

## 📂 Project Structure
```
src/
├── main/
│   ├── java/com/springboot/flyway/dbmigration/
│   │   ├── controllers/
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── entities/
│   │   ├── exceptions/
│   │   ├── repositories/
│   │   └── services/
│   └── resources/
│       ├── db/
│       │   └── migration/
│       │       ├── V1__create_users_table.sql
│       │       ├── V2__create_products_table.sql
│       │       ├── V3__create_orders_table.sql
│       │       ├── V4__create_order_items_table.sql
│       │       ├── V5__add_seed_data.sql
│       │       ├── V6__add_indexes.sql
│       │       ├── V7__add_coupon_column.sql
│       │       └── V8__revert_coupon_column.sql
│       ├── application.properties
│       ├── application-dev-example.properties
│       └── application-prod.properties
├── pom.xml
└── README.md
```

---

## 🚀 Quick Start (Local)

**Prerequisites:** Java 21+, Maven, PostgreSQL
```bash
git clone -b from-scratch https://github.com/MansiArora-dev/flyway-db-migration.git
cd flyway-db-migration

# Create database
psql -U postgres -c "CREATE DATABASE flyway_dbmigration;"

# Create application-local.properties
cp src/main/resources/application-dev-example.properties \
   src/main/resources/application-local.properties
# Fill in your local DB credentials

# Run
mvn spring-boot:run
```

Flyway will automatically run all 8 migrations on startup! ✅

---

## 💡 Key Concepts Demonstrated

| Concept | Details |
|---------|---------|
| `ddl-auto=none` | Hibernate schema management disabled |
| Sequential migrations | V1→V8 run in order automatically |
| `ALTER TABLE` | Schema evolution via SQL |
| Manual rollback | V8 reverts V7 — Flyway Community alternative |
| Indexes | Performance optimization via migration |
| Seed data | Initial data via migration |
| Multi-profile | local/dev/prod configurations |
| `flyway_schema_history` | Migration tracking table |

> 💡 For mid-project Flyway integration approach, check the [`mid-project`](../../tree/mid-project) branch

---

## 💻 Technologies

- **Java 21** | **Spring Boot 3.5.13** | **Maven**
- **Flyway** — Database migration tool
- **PostgreSQL 15** | **Spring Data JPA** | **Hibernate**
- **Lombok**

---

## 👩‍💻 Developer

**Mansi Arora** — Software Engineer





