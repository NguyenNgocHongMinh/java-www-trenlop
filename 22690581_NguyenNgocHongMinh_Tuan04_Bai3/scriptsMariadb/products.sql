
create table products
(
    ID int auto_increment primary key,
    MODEL       varchar(50) default '0' not null,
    DESCRIPTION varchar(50) default '0' not null,
    QUANTITY    int         default 0   not null,
    PRICE       double      default 0   not null
);
INSERT INTO shopdb.products (ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL) VALUES (1, 'dien thoai', 'aaaaa', 5, 10000, 'iphone.jpg');
INSERT INTO shopdb.products (ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL) VALUES (2, 'may tinh', 'bbbbb', 5, 20000, 'mayanh.jpg');
INSERT INTO shopdb.products (ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL) VALUES (3, 'may xay sinh to', 'ccccc', 5, 30000, 'photo.jpg');
INSERT INTO shopdb.products (ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL) VALUES (4, 'tivi', 'dddd', 5, 40000, 'tivi.jpg');
