# 🚀 Flyway DB Migration — Mid-Project Integration

A Spring Boot project demonstrating **Flyway database migrations integrated mid-project** — real world scenario where project starts with `ddl-auto=update` and later adopts Flyway for schema version control.

---

## 🗄️ Flyway Migration History

| Version | File | Description |
|---------|------|-------------|
| V1 | `V1__baseline.sql` | Baseline — existing schema captured |
| V2 | `V2__create_orders_table.sql` | New feature — orders table via Flyway |
| V3 | `V3__create_order_items_table.sql` | New feature — order_items table via Flyway |
| V4 | `V4__add_seed_data.sql` | Add seed data |
| V5 | `V5__add_indexes.sql` | Add indexes for performance |
| V6 | `V6__add_coupon_column.sql` | Add coupon feature (ALTER TABLE) |
| V7 | `V7__revert_coupon_column.sql` | Revert coupon column (manual rollback) |

---

## 🛠️ Flyway Setup (How I Built This)

### 1. Phase 1 — Before Flyway
- Project started with `ddl-auto=update`
- Hibernate managed schema automatically
- No migration history

### 2. Phase 2 — Flyway Integration
- Flyway dependencies added mid-project
- `ddl-auto=update` → `none` (Flyway takes over)
- `V1__baseline.sql` created to capture existing schema
- `baseline-on-migrate=true` on production

### 3. Phase 3 — New Features via Flyway
- All new schema changes managed via Flyway migrations
- Orders feature added via V2, V3
- Seed data, indexes, alter via V4-V7

### 4. Dependencies Added
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

### 5. Flyway Configuration
```properties
# Flyway handles schema — Hibernate does not
spring.jpa.hibernate.ddl-auto=none

# Flyway configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1
```

### 6. Verify Migrations
```sql
SELECT * FROM flyway_schema_history;
```

---

## ⚙️ How Mid-Project Integration Works
```
Before Flyway:
App Startup → Hibernate checks entities
            → Creates/updates tables automatically (ddl-auto=update)
            → No migration history

After Flyway:
App Startup → Flyway checks flyway_schema_history
            → Runs pending migrations in order
            → Hibernate only validates schema (ddl-auto=none)
```

---

## 🌱 Spring Profiles

| Profile | `ddl-auto` | Flyway |
|---------|-----------|--------|
| `local` | `none` | `baseline-on-migrate=false` |
| `dev` | `update` | `baseline-on-migrate=false` |
| `prod` | `validate` | `baseline-on-migrate=true` |
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
│   │   ├── controller/
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── repository/
│   │   └── service/
│   │       └── impl/
│   └── resources/
│       ├── db/
│       │   └── migration/
│       │       ├── V1__baseline.sql
│       │       ├── V2__create_orders_table.sql
│       │       ├── V3__create_order_items_table.sql
│       │       ├── V4__add_seed_data.sql
│       │       ├── V5__add_indexes.sql
│       │       ├── V6__add_coupon_column.sql
│       │       └── V7__revert_coupon_column.sql
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
git clone -b mid-project https://github.com/MansiArora-dev/flyway-db-migration.git
cd flyway-db-migration

# Create database
psql -U postgres -c "CREATE DATABASE flyway_midproject;"

# Create application-local.properties
cp src/main/resources/application-dev-example.properties \
   src/main/resources/application-local.properties
# Fill in your local DB credentials

# Run
mvn spring-boot:run
```

Flyway will automatically run all 7 migrations on startup! ✅

---

## 💡 Key Concepts Demonstrated

| Concept | Details |
|---------|---------|
| Mid-project integration | Flyway added to existing project |
| `V1__baseline.sql` | Existing schema captured |
| `baseline-on-migrate=true` | Production baseline setup |
| `ddl-auto=update` → `none` | Hibernate → Flyway transition |
| New features via Flyway | V2-V3 tables created by Flyway |
| `ALTER TABLE` | Schema evolution via SQL |
| Manual rollback | V7 reverts V6 |
| Service interfaces | Clean architecture with impl pattern |

> 💡 For Flyway from scratch approach, check the [`from-scratch`](../../tree/from-scratch) branch

---

## 💻 Technologies

- **Java 21** | **Spring Boot 3.5.13** | **Maven**
- **Flyway** — Database migration tool
- **PostgreSQL 15** | **Spring Data JPA** | **Hibernate**
- **Lombok**

---

## 👩‍💻 Developer

**Mansi Arora** — Software Engineer

