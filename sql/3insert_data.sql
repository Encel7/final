INSERT INTO user (id, role, first_name, last_name, login, password, discount)
VALUES
(1, 0, 'Valentin', 'Danilov', 'Garbage', '1234', 10),
(2, 1, 'Andrey', 'Palevsky', 'Encel', 'qwer', 35),
(3, 0, 'lol', 'kek', 'cheburek', '11111', 8),
(4, 0, 'Maria', 'Petrova', 'Ch1kaP1ka', '2233', 18);

INSERT INTO souvenir (size, color, image, price, author_id)
VALUES
(23, 'white', 'images/paris.jpg',150, 2),
(23, 'white', 'images/ship.jpg',300, 2),
(23, 'white', 'images/saintp.png',300,2),
(23, 'grey', 'images/knife.jpg',250,2);

INSERT INTO balance (user_id, current_balance, overdruft)
VALUES
(1, 800, 150),
(2, 450, 350),
(3, 200, 100),
(4, 760, 200);