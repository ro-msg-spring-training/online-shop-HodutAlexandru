CREATE TABLE customers (
    id INTEGER NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id INTEGER NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    street VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_details (
    id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    order_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE locations (
    id INTEGER NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products (
    id INTEGER NOT NULL,
    description VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    weight DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_categories (
    id INTEGER NOT NULL,
    description VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE revenues (
    id INTEGER NOT NULL,
    date DATE NOT NULL,
    sum DECIMAL(19,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE stocks (
    id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE suppliers (
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customers_orders (
    customer_id INTEGER NOT NULL,
    orders_id INTEGER NOT NULL
);

CREATE TABLE locations_orders (
    location_id INTEGER NOT NULL,
    orders_id INTEGER NOT NULL
);

CREATE TABLE locations_revenues (
    location_id INTEGER NOT NULL,
    revenues_id INTEGER NOT NULL
);

CREATE TABLE order_details_products (
    order_detail_id INTEGER NOT NULL,
    products_id INTEGER NOT NULL
);

CREATE TABLE product_categories_products (
    product_category_id INTEGER NOT NULL,
    products_id INTEGER NOT NULL
);

CREATE TABLE stocks_locations (
    stock_id INTEGER NOT NULL,
    locations_id INTEGER NOT NULL
);

CREATE TABLE stocks_products (
    stock_id INTEGER NOT NULL,
    products_id INTEGER NOT NULL
);

CREATE TABLE suppliers_products (
    supplier_id INTEGER NOT NULL,
    products_id INTEGER NOT NULL
);

ALTER TABLE customers_orders ADD CONSTRAINT UK_c23uaum5y9y2r1f0a3jprvu19 UNIQUE (orders_id);
ALTER TABLE locations_orders ADD CONSTRAINT UK_eq52jepi99ekyeshgf3sk7ccr UNIQUE (orders_id);
ALTER TABLE locations_revenues ADD CONSTRAINT UK_s0mbgckw3o43rl1yuqfr0ll71 UNIQUE (revenues_id);
ALTER TABLE order_details_products ADD CONSTRAINT UK_fvuh00bjr43n3hln3l99t7tq6 UNIQUE (products_id);
ALTER TABLE product_categories_products ADD CONSTRAINT UK_f4pw28yqb5y3ie64q2krgkeaw UNIQUE (products_id);
ALTER TABLE stocks_locations ADD CONSTRAINT UK_phumf90cf989yivt24pjegs9d UNIQUE (locations_id);
ALTER TABLE stocks_products ADD CONSTRAINT UK_iqihl8kp3676w7fe2vnw4mrg UNIQUE (products_id);
ALTER TABLE suppliers_products ADD CONSTRAINT UK_3h6w2cs5uu8u8diqnlmqgow03 UNIQUE (products_id);
ALTER TABLE customers_orders ADD CONSTRAINT FKcgubpw142krbvsbpfumpss2wl FOREIGN KEY (orders_id) REFERENCES orders;
ALTER TABLE customers_orders ADD CONSTRAINT FK23d13l73po2yhpnru16evw5o3 FOREIGN KEY (customer_id) REFERENCES customers;
ALTER TABLE locations_orders ADD CONSTRAINT FKbeg2j94ur3ied0s8g82irrp8d FOREIGN KEY (orders_id) REFERENCES orders;
ALTER TABLE locations_orders ADD CONSTRAINT FKguhts7n9bpvfxyghgibdht7ea FOREIGN KEY (location_id) REFERENCES locations;
ALTER TABLE locations_revenues ADD CONSTRAINT FKkcqj5ks4pclwdypw9dfm8pq2e FOREIGN KEY (revenues_id) REFERENCES revenues;
ALTER TABLE locations_revenues ADD CONSTRAINT FKgg1l9jwou2bsgxhrpl97593e1 FOREIGN KEY (location_id) REFERENCES locations;
ALTER TABLE order_details ADD CONSTRAINT FKrvaeqebfullbx4m6bj5iakibb FOREIGN KEY (order_id) REFERENCES orders;
ALTER TABLE order_details_products ADD CONSTRAINT FK2dujlinmsrrqvg1p98rwo0pkf FOREIGN KEY (products_id) REFERENCES products;
ALTER TABLE order_details_products ADD CONSTRAINT FKdj3hemau8xdc3ty0qbyql1ji2 FOREIGN KEY (order_detail_id) REFERENCES order_details;
ALTER TABLE product_categories_products ADD CONSTRAINT FK7pn7ti4acadiyukhgl2wdqy31 FOREIGN KEY (products_id) REFERENCES products;
ALTER TABLE product_categories_products ADD CONSTRAINT FK9l6aqompluvyaufs48o7ehwl2 FOREIGN  KEY (product_category_id) REFERENCES product_categories;
ALTER TABLE stocks_locations ADD CONSTRAINT FK61x1vko24pm2wchsbhkps1ctn FOREIGN KEY (locations_id) REFERENCES locations;
ALTER TABLE stocks_locations ADD CONSTRAINT FK8wvrnwxd1dyu98tqsrnnr2ccr FOREIGN KEY (stock_id) REFERENCES stocks;
ALTER TABLE stocks_products ADD CONSTRAINT FKouh3ggxsqj0ll8xqdi6ih0tom FOREIGN KEY (products_id) REFERENCES products;
ALTER TABLE stocks_products ADD CONSTRAINT FK9m838mg1orooek7lptbbb3ud4 FOREIGN KEY (stock_id) REFERENCES stocks;
ALTER TABLE suppliers_products ADD CONSTRAINT FKl2ny02g9d0u0m6lqabfb9o7t0 FOREIGN KEY (products_id) REFERENCES products;
ALTER TABLE suppliers_products ADD CONSTRAINT FKdpy1wg96qkljeo1xh1cox05r9 FOREIGN KEY (supplier_id) REFERENCES suppliers;
