INSERT INTO users (id, name, archive, email, password, role, cart_id)
VALUES (1, 'admin', false, 'admin@mail.ru', '123456', 'ADMIN', null);

ALTER SEQUENCE SEQ_USER RESTART WITH 2;
