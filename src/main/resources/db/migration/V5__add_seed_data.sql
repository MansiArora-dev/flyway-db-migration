INSERT INTO users (name, email, phone) VALUES
    ('Arjun',   'arjun@shopkart.com',   '9876543210'),
    ('Priya',   'priya@shopkart.com',   '9876543211'),
    ('Rohan',   'rohan@shopkart.com',   '9876543212'),
    ('Sneha',   'sneha@shopkart.com',   '9876543213'),
    ('Karan',   'karan@shopkart.com',   '9876543214'),
    ('Neha',    'neha@shopkart.com',    '9876543215'),
    ('Vikram',  'vikram@shopkart.com',  '9876543216'),
    ('Ananya',  'ananya@shopkart.com',  '9876543217');

INSERT INTO products (name, description, price, stock, category) VALUES
    ('MacBook Pro',         'Apple M3 chip, 16GB RAM, 512GB SSD',  150000.00, 10, 'Electronics'),
    ('iPhone 15',           'A16 Bionic chip, 256GB, Titanium',     80000.00, 25, 'Electronics'),
    ('AirPods Pro',         'Active noise cancellation, H2 chip',   20000.00, 50, 'Accessories'),
    ('iPad Air',            'M1 chip, 10.9 inch, Wi-Fi + Cellular', 60000.00, 15, 'Electronics'),
    ('Samsung 4K TV',       '55 inch QLED, Smart TV, HDR10+',       75000.00,  8, 'Electronics'),
    ('Sony Headphones',     'WH-1000XM5, 30hr battery, ANC',        25000.00, 30, 'Accessories'),
    ('Logitech Mouse',      'MX Master 3S, Wireless, Ergonomic',     7000.00, 40, 'Accessories'),
    ('Mechanical Keyboard', 'Keychron K2, RGB, Hot-swappable',      10000.00, 20, 'Accessories'),
    ('Nike Air Max',        'Running shoes, Lightweight',            8000.00, 35, 'Footwear'),
    ('Adidas Ultraboost',   'Boost cushioning, Primeknit upper',     9500.00, 28, 'Footwear'),
    ('Levi Jeans 511',      'Slim fit, Stretch denim, Dark wash',    3500.00, 60, 'Clothing'),
    ('Zara Casual Shirt',   '100% cotton, Regular fit, White',       2000.00, 45, 'Clothing');

INSERT INTO orders (user_id, total_amount, status) VALUES
    (1, 150000.00, 'COMPLETED'),
    (2, 105000.00, 'PENDING'),
    (3,  20000.00, 'SHIPPED'),
    (4,  17000.00, 'COMPLETED'),
    (5,  75000.00, 'CANCELLED'),
    (6,  11500.00, 'SHIPPED');

INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
    (1, 1, 1, 150000.00),
    (2, 2, 1,  80000.00),
    (2, 3, 1,  20000.00),
    (2, 7, 1,   5000.00),
    (3, 3, 1,  20000.00),
    (4, 9, 1,   8000.00),
    (4, 7, 1,   7000.00),
    (4, 8, 1,  10000.00),
    (5, 5, 1,  75000.00),
    (6, 9, 1,   8000.00),
    (6, 11, 1,  3500.00);