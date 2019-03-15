UPDATE user SET discount = discount + 10 WHERE role = 0;
UPDATE souvenir SET price = price - 20 WHERE size >= 20;
UPDATE balance SET overdruft = overdruft + 20 WHERE user_id IN
                                                    (SELECT id FROM user WHERE role = 1);
SELECT first_name, last_name FROM user WHERE role = 0;
SELECT id FROM balance WHERE user_id IN (SELECT id FROM user WHERE first_name = '')

SELECT SUM(souvenir.price) as summ FROM souvenir JOIN souvenir_order ON souvenir.id = souvenir_order.souvenir_id JOIN user_order ON user_order.id = souvenir_order.order_id WHERE user_order.id=15