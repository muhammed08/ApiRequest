CREATE TABLE Rate (
    from_currency_code VARCHAR(3) PRIMARY KEY,
    to_currency_code VARCHAR(3),
    rate FLOAT
);

INSERT into Rate VALUES('USD','AUD',1.75);
INSERT into Rate VALUES('IND','AUD',1.76);
INSERT into Rate VALUES('EUR','AUD',1.77);
INSERT into Rate VALUES('SND','AUD',1.78);
INSERT into Rate VALUES('AUD','AUD',1.79);
INSERT into Rate VALUES('DNR','AUD',1.80);