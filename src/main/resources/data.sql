-- Inserimento dei 3 Brand
INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('Audi', 'Vorsprung durch Technik', 'Audi is a German automotive manufacturer of luxury vehicles...', 'Audi RS 6 Avant Performance', 'foto/audi.svg');

INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('BMW', 'The Ultimate Driving Machine', 'Bayerische Motoren Werke AG, commonly referred to as BMW...', 'BMW M5 Competition', 'foto/bmw.svg');

INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('Mercedes-Benz', 'The best or nothing', 'Mercedes-Benz is a German global automobile marque...', 'Mercedes-AMG GT 63 S 4matic+', 'mercedes.svg');

-- Inserimento di 10 Modelli di Auto (Soddisfa il requisito del progetto!)
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('A3 Sportback', 'Audi', 150, 32000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('RS6 Avant', 'Audi', 600, 135000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('R8 Coupe', 'Audi', 620, 170000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('Serie 1', 'BMW', 136, 30500.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('M3 Competition', 'BMW', 510, 105000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('X5', 'BMW', 286, 80000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('Z4 Roadster', 'BMW', 340, 65000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('A Class', 'Mercedes-Benz', 163, 35000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('G Class', 'Mercedes-Benz', 422, 130000.00);
INSERT INTO car_model (name, brand, horsepower, start_price) VALUES ('AMG GT', 'Mercedes-Benz', 585, 175000.00);