INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT(NAME)
VALUES ('Gusteaus'),
       ('Los Pollos Hermanos'),
       ('Double R Diner'),
       ('Burger King');

INSERT INTO DISH (NAME, DATE, PRICE, RESTAURANT_ID)
VALUES ('Ratatouille', current_date, 100, 1),
       ('Spaghetti', current_date, 120.50, 1),
       ('Pizza', current_date, 55.55, 1),
       ('Burger', current_date, 50, 2),
       ('Fries', current_date, 30, 2),
       ('Fries', current_date - 1, 20, 2),
       ('Cherry pie', current_date, 45.10, 3),
       ('Fried eggs', current_date + 1, 45.10, 3);

INSERT INTO VOTE (DATE, RESTAURANT_ID, USER_ID)
VALUES (current_date, 1, 1),
       (current_date, 1, 2),
       (current_date, 2, 3),
       (current_date - 1, 3, 1),
       (current_date, 3, 2),
       (current_date + 1, 3, 3);
