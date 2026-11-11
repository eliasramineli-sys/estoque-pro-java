CREATE TABLE products (
 id BIGSERIAL PRIMARY KEY,
 sku VARCHAR(60) NOT NULL UNIQUE,
 name VARCHAR(150) NOT NULL,
 price NUMERIC(14,2) NOT NULL CHECK (price >= 0),
 quantity INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
 min_stock INTEGER NOT NULL DEFAULT 0 CHECK (min_stock >= 0),
 version BIGINT NOT NULL DEFAULT 0
);
CREATE TABLE movements (
 id BIGSERIAL PRIMARY KEY,
 product_id BIGINT NOT NULL REFERENCES products(id),
 type VARCHAR(12) NOT NULL,
 quantity INTEGER NOT NULL CHECK (quantity > 0),
 reason VARCHAR(250) NOT NULL,
 created_at TIMESTAMP WITH TIME ZONE NOT NULL
);
CREATE INDEX idx_movements_product ON movements(product_id);
