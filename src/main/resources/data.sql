--Brands
INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('Audi', 'Vorsprung durch Technik', 'Audi is a German automotive manufacturer of luxury vehicles...', 'Audi RS 6 Avant Performance', '/foto/audi/audi-logo.png');

INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('BMW', 'The Ultimate Driving Machine', 'Bayerische Motoren Werke AG, commonly referred to as BMW...', 'BMW M5 Competition', '/foto/bmw/bmw-logo.png');

INSERT INTO brand (name, short_description, long_description, flagship, img_url)
VALUES ('Mercedes-Benz', 'The best or nothing', 'Mercedes-Benz is a German global automobile marque...', 'Mercedes-AMG GT 63 S 4matic+', '/foto/mercedes/mercedes-logo.png');

--Car Models
INSERT INTO car_model (name, image_url, transmission, engine_config, engine_type, horsepower, torque_nm, drivetrain, weight_kg, zero_to_hundred, base_price_eur, brand_id, created_at, updated_at)
VALUES
('Audi A1 30 TFSI','/foto/audi/a3.png','Manual','I3 Turbo','Petrol',110,200,'FWD',1140,10.50,24500,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi A3 35 TFSI S tronic','/foto/audi/a3.png','DCT','I4 Turbo','Petrol',150,250,'FWD',1340,8.40,34000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi A5 40 TFSI quattro','/foto/audi/a3.png','DCT','I4 Turbo','Petrol',204,320,'AWD',1580,7.10,52000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi A6 45 TFSI quattro','/foto/audi/a3.png','Dual Clutch','I4 Turbo','Petrol',265,370,'AWD',1750,6.20,62000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi A6 e-tron','/foto/audi/rsetron.png','Automatic','Electric Motor','Electric',286,450,'RWD',2100,6.00,70000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi A8 L 60 TFSI e quattro','/foto/audi/a3.png','Automatic','V6 Hybrid','Hybrid',462,700,'AWD',2300,4.90,120000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi e-tron GT','/foto/audi/rsetron.png','Automatic','Dual Electric Motor','Electric',476,630,'AWD',2350,4.10,110000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q2 35 TFSI quattro','/foto/audi/q5.png','Dual Clutch','I4 Turbo','Petrol',150,250,'AWD',1420,8.20,40000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q3 40 TFSI quattro','/foto/audi/q5.png','Dual Clutch','I4 Turbo','Petrol',190,320,'AWD',1650,7.40,50000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q4 e-tron 50 quattro','/foto/audi/q5.png','Automatic','Dual Electric Motor','Electric',299,460,'AWD',2250,6.20,72000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q5 40 TDI quattro','/foto/audi/q5.png','Dual Clutch','I4 Turbo','Diesel',204,400,'AWD',1920,7.60,62000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q6 e-tron','/foto/audi/q5.png','Automatic','Single Electric Motor','Electric',286,450,'RWD',2300,6.60,75000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q7 50 TDI quattro','/foto/audi/q5.png','Dual Clutch','V6 Turbo','Diesel',286,600,'AWD',2250,6.20,85000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi Q8 55 TFSI quattro','/foto/audi/q5.png','Dual Clutch','V6 Turbo','Petrol',340,500,'AWD',2250,5.90,102000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi RS e-tron GT','/foto/audi/rsetron.png','Automatic','Dual Electric Motor','Electric',598,830,'AWD',2420,3.30,150000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi RS3 Sedan','/foto/audi/rsetron.png','Dual Clutch','I5 Turbo','Petrol',400,500,'AWD',1600,3.90,72000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi RS5 Sportback','/foto/audi/rsetron.png','Dual Clutch','V6 Turbo','Petrol',450,600,'AWD',1840,3.90,95000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi RS6 Avant','/foto/audi/rsetron.png','Dual Clutch','V8 Bi-Turbo','Petrol',600,800,'AWD',2075,3.60,135000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Audi RS Q8','/foto/audi/rsetron.png','Dual Clutch','V8 Bi-Turbo','Petrol',600,800,'AWD',2390,3.80,140000,1,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 120d','/foto/bmw/1-series.png','Automatic','I4 Turbo','Diesel',163,400,'FWD',1500,7.40,36000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 220i Coupé','/foto/bmw/1-series.png','Automatic','I4 Turbo','Petrol',184,300,'RWD',1480,7.50,40000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 220d Gran Coupé','/foto/bmw/1-series.png','Automatic','I4 Turbo','Diesel',150,350,'FWD',1450,8.00,39000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 320i Sedan','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Petrol',184,300,'RWD',1520,7.40,45000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 320d Touring','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Diesel',190,400,'RWD',1650,6.90,50000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 420i Coupé','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Petrol',184,300,'RWD',1550,7.50,48000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 420i Convertible','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Petrol',184,300,'RWD',1700,8.20,55000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 420d Gran Coupé','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Diesel',190,400,'RWD',1680,7.20,52000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 520i Sedan','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Petrol',208,330,'RWD',1800,7.50,62000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 520d Touring','/foto/bmw/3-touring.png','Automatic','I4 Turbo','Diesel',197,400,'RWD',1900,7.40,67000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 740d xDrive','/foto/bmw/3-touring.png','Automatic','I6 Turbo','Diesel',300,670,'AWD',2250,5.80,120000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 840i Coupé','/foto/bmw/3-touring.png','Automatic','I6 Turbo','Petrol',333,500,'RWD',1800,5.40,105000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 840i Gran Coupé','/foto/bmw/3-touring.png','Automatic','I6 Turbo','Petrol',333,500,'RWD',1850,5.50,107000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW 840i Convertible','/foto/bmw/3-touring.png','Automatic','I6 Turbo','Petrol',333,500,'RWD',1900,5.50,112000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X1 xDrive23i','/foto/bmw/x5.png','Automatic','I4 Turbo','Petrol',218,360,'AWD',1650,7.10,50000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X2 sDrive18d','/foto/bmw/x5.png','Automatic','I4 Turbo','Diesel',150,360,'FWD',1600,8.80,47000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X3 20d xDrive','/foto/bmw/x5.png','Automatic','I4 Turbo','Diesel',197,400,'AWD',1900,7.50,65000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X5 xDrive30d','/foto/bmw/x5.png','Automatic','I6 Turbo','Diesel',298,650,'AWD',2300,6.10,98000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X6 xDrive40i','/foto/bmw/x5.png','Automatic','I6 Turbo','Petrol',381,540,'AWD',2250,5.50,99000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X7 xDrive40i','/foto/bmw/x5.png','Automatic','I6 Turbo','Petrol',381,540,'AWD',2450,5.80,115000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW XM','/foto/bmw/x5.png','Automatic','V8 Plug-in Hybrid','Hybrid',653,800,'AWD',2750,4.30,165000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW Z4 sDrive30i','/foto/bmw/m4.png','Automatic','I4 Turbo','Petrol',258,400,'RWD',1500,5.40,62000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW i4 eDrive35','/foto/bmw/m4.png','Automatic','Electric','Electric',286,400,'RWD',2100,6.00,58000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW i5 eDrive40','/foto/bmw/m4.png','Automatic','Electric','Electric',340,430,'RWD',2300,6.00,75000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW i7 eDrive50','/foto/bmw/m4.png','Automatic','Electric','Electric',455,650,'RWD',2700,5.50,120000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW iX1 eDrive20','/foto/bmw/x5.png','Automatic','Electric','Electric',204,250,'FWD',2000,8.60,48000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW iX2 eDrive20','/foto/bmw/x5.png','Automatic','Electric','Electric',204,250,'FWD',1950,8.60,50000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW iX3','/foto/bmw/x5.png','Automatic','Electric','Electric',286,400,'RWD',2200,6.80,68000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW iX xDrive40','/foto/bmw/x5.png','Automatic','Electric','Electric',326,630,'AWD',2400,6.10,85000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW M2','/foto/bmw/m4.png','Automatic','I6 Turbo','Petrol',460,550,'RWD',1725,4.10,75000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW M3 Competition Sedan','/foto/bmw/m4.png','Automatic','I6 Twin Turbo','Petrol',510,650,'RWD',1800,3.90,95000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW M4 Competition Coupé','/foto/bmw/m4.png','Automatic','I6 Twin Turbo','Petrol',510,650,'RWD',1780,3.90,98000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW M5','/foto/bmw/m4.png','Automatic','V8 Twin Turbo','Petrol',600,750,'AWD',2000,3.40,140000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW M8 Competition Gran Coupé','/foto/bmw/m4.png','Automatic','V8 Twin Turbo','Petrol',625,750,'AWD',2050,3.30,168000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X5 M Competition','/foto/bmw/x5.png','Automatic','V8 Twin Turbo','Petrol',625,750,'AWD',2450,3.90,145000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('BMW X6 M Competition','/foto/bmw/x5.png','Automatic','V8 Twin Turbo','Petrol',625,750,'AWD',2450,3.90,148000,2,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes A 180','/foto/mercedes/c-clas.png','Dual Clutch','1.3L I4 Turbo','Petrol',136,200,'FWD',1350,9.20,35000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes B 180','/foto/mercedes/c-clas.png','Dual Clutch','1.3L I4 Turbo','Petrol',136,200,'FWD',1425,9.10,36000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes C 200','/foto/mercedes/c-clas.png','Automatic','1.5L I4 Turbo','Petrol',204,300,'RWD',1680,7.30,51000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes CLA 200','/foto/mercedes/c-clas.png','Dual Clutch','1.3L I4 Turbo','Petrol',163,250,'FWD',1470,8.20,42000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes CLE 200','/foto/mercedes/c-clas.png','Automatic','2.0L I4 Turbo','Petrol',204,320,'RWD',1700,7.40,58000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes E 220d','/foto/mercedes/c-clas.png','Automatic','2.0L I4 Turbo Diesel','Diesel',197,440,'RWD',1850,7.30,60000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes G 450d','/foto/mercedes/gle.png','Automatic','3.0L I6 Turbo Diesel','Diesel',367,750,'AWD',2550,5.80,145000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes GLA 200','/foto/mercedes/gle.png','Dual Clutch','1.3L I4 Turbo','Petrol',163,250,'FWD',1520,8.70,42000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes GLB 200','/foto/mercedes/gle.png','Dual Clutch','1.3L I4 Turbo','Petrol',163,250,'FWD',1625,9.30,44000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes GLC 200 4MATIC','/foto/mercedes/gle.png','Automatic','2.0L I4 Turbo','Petrol',204,320,'AWD',1850,7.80,65000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes GLE 300d 4MATIC','/foto/mercedes/gle.png','Automatic','2.0L I4 Turbo Diesel','Diesel',269,550,'AWD',2200,6.90,85000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes V 220d','/foto/mercedes/gle.png','Automatic','2.0L I4 Turbo Diesel','Diesel',163,380,'RWD',2200,11.10,72000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes AMG GT 43','/foto/mercedes/amggt.png','Automatic','3.0L I6 Turbo Mild Hybrid','Hybrid',367,500,'RWD',1780,4.90,105000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes AMG GT 43 4MATIC+ 4-Door','/foto/mercedes/amggt.png','Automatic','3.0L I6 Turbo Mild Hybrid','Hybrid',367,500,'AWD',1950,4.90,110000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes AMG SL 43','/foto/mercedes/amggt.png','Automatic','2.0L I4 Turbo Mild Hybrid','Hybrid',381,480,'RWD',1800,4.90,125000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQA 250','/foto/mercedes/c-clas.png','Automatic','Single Motor','Electric',190,385,'FWD',2050,8.60,47000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQB 250+','/foto/mercedes/c-clas.png','Automatic','Single Motor','Electric',190,385,'FWD',2100,8.90,53000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQE 300','/foto/mercedes/c-clas.png','Automatic','Single Motor','Electric',245,550,'RWD',2350,7.30,75000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQE SUV 350+','/foto/mercedes/gle.png','Automatic','Single Motor','Electric',292,565,'RWD',2500,6.70,90000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQS 450+','/foto/mercedes/c-clas.png','Automatic','Single Motor','Electric',333,568,'RWD',2500,6.20,110000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQS SUV 450+','/foto/mercedes/gle.png','Automatic','Single Motor','Electric',360,568,'RWD',2700,6.20,125000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00'),
('Mercedes EQV 300','/foto/mercedes/gle.png','Automatic','Single Motor','Electric',204,365,'FWD',2650,10.0,85000,3,'2025-01-01 00:00:00','2025-01-01 00:00:00');

-- Test users
INSERT INTO app_user (first_name, last_name, username, birth_date, email, password, profile_photo_url, created_at, admin)
VALUES ('Gabriele', 'Pjetra', 'gab', '1999-12-23', 'gabriele@example.com', '$2a$10$jz3xMj7oXPkOpprMP9FSJ.9Cik97EWCDMJFur1s1a8y.9FkEVJa9C', NULL, '2025-01-01 00:00:00', TRUE),
       ('Christian', 'Vollstedt', 'cri', '2000-11-12', 'christian@example.com', '$2a$10$jz3xMj7oXPkOpprMP9FSJ.9Cik97EWCDMJFur1s1a8y.9FkEVJa9C', NULL, '2025-01-01 00:00:00', FALSE);

-- Test favorite cars
INSERT INTO user_favorite_cars (user_id, car_model_id)
VALUES
(1, 16),
(1, 7),
(1, 45),
(1, 67),
(1, 2),
(2, 3),
(2, 17),
(2, 44),
(2, 65),
(2, 71);

-- Test comments
INSERT INTO car_comment (content, created_at, car_model_id, app_user_id)
VALUES
('The RS3 feels incredibly sharp for a compact sedan. The launch traction is the real party trick.', '2025-01-02 10:15:00', 16, 1),
('The e-tron GT is heavier than it looks, but the instant torque makes it feel effortless in normal driving.', '2025-01-03 14:30:00', 7, 1),
('For the price, the A3 is still one of the most balanced daily cars here. Comfortable, clean interior, and enough power.', '2025-01-04 09:45:00', 2, 1),
('The M3 has the more playful rear-drive feeling, but the RS5 feels easier to drive quickly in bad weather.', '2025-01-05 18:20:00', 45, 1),
('The GLC makes sense as a family car. It is not the fastest option, but the comfort and AWD setup are strong points.', '2025-01-06 12:10:00', 67, 1);

-- Test replies
INSERT INTO car_comment (content, created_at, car_model_id, app_user_id, parent_comment_id)
VALUES
('Agree on the RS3. It feels much more planted than the size suggests.', '2025-01-02 11:05:00', 16, 2, 1),
('The A3 is probably the sensible choice, but the RS3 is hard to ignore if budget allows.', '2025-01-04 10:20:00', 2, 2, 3),
('AWD really changes the character in bad weather. The RS5 feels very confidence inspiring.', '2025-01-05 19:00:00', 45, 2, 4);

-- Test notifications
INSERT INTO notification (message, created_at, is_read, recipient_id, related_car_model_id)
VALUES
('cri replied to your comment on Audi RS3 Sedan.', '2025-01-02 11:05:00', FALSE, 1, 16);
