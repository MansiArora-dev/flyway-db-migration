# 🚀 Flyway DB Migration — Spring Boot

A Spring Boot project demonstrating **two approaches** of using Flyway for database schema version control.

---

## 📌 Two Approaches Demonstrated

### 🔄 Branch: `main` / `mid-project` — Flyway Mid-Project Integration
> Real world scenario — project started with `ddl-auto=update`, Flyway integrated mid-development.

👉 [View mid-project branch](../../tree/mid-project)

**Highlights:**
- Phase 1: Hibernate managed schema (`ddl-auto=update`)
- Phase 2: Flyway integrated with `V1__baseline.sql`
- Phase 3: New features via Flyway (V2-V7)

---

### 🌱 Branch: `from-scratch` — Flyway From Inception
> Flyway used from day one — complete schema version control from project start.

👉 [View from-scratch branch](../../tree/from-scratch)

**Highlights:**
- `ddl-auto=none` from the beginning
- V1-V8 migrations — create, seed, index, alter, rollback
- Clean migration history from day one

---

## 💻 Technologies

- **Java 21** | **Spring Boot 3.5.13** | **Maven**
- **Flyway** — Database migration tool
- **PostgreSQL 15** | **Spring Data JPA** | **Hibernate**
- **Lombok**

---

## 👩‍💻 Developer

**Mansi Arora** — Software Engineer
