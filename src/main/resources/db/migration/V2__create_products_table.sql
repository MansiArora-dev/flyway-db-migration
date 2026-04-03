CREATE TABLE products (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    price       DECIMAL(10,2) NOT NULL,
    stock       INT DEFAULT 0,
    category    VARCHAR(50),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);