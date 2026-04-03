-- Manual rollback of V7 (Flyway Community alternative)
ALTER TABLE orders DROP COLUMN IF EXISTS coupon_code;
ALTER TABLE orders DROP COLUMN IF EXISTS discount_amount;