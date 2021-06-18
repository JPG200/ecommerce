CREATE TABLE PRODUCT(
PRODUCT_ID int primary key,
PRODUCT_NAME varchar(100) not null,
PRODUCT_PRICE double(2,0) not null,
PRODUCT_TAX double(2,0) not null,
PRODUCT_DESCRIPTION varchar(280) not null,
PRODUCT_STATE varchar(30) not null,
PRODUCT_INVENTORY int not null
);

CREATE TABLE USER(
USER_NAME varchar(100) primary key,
USER_PASS varchar(100) not null,
USER_TYPE varchar(2) not null
);

CREATE TABLE CLIENT(
CLIENT_ID varchar(100) primary key,
CLIENT_NAME varchar(200) not null,
CLIENT_LASTNAME varchar(200) not null,
CLIENT_UBICATION varchar(200) not null,
CLIENT_TYPEID varchar(2) not null
);

CREATE TABLE SELL
{
SELL_ID varchar(200) primary key,
PRODUCT_ID int primary key,
PRODUCT_NAME varchar(100),
PRODUCT_PRICE double(2,0) not null,
PRODUCT_TAX double(2,0) not null,
PRODUCT_INVENTORY int not null,
CLIENT_ID varchar(100) primary key,
CLIENT_NAME varchar(200) not null,
CLIENT_LASTNAME varchar(200) not null,
CLIENT_UBICATION varchar(200) not null,
CLIENT_TYPEID varchar(2) not null,
FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(CLIENT_ID)
}

