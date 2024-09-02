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
       ('Double R Diner');

INSERT INTO DISH (NAME, DATE, PRICE, RESTAURANT_ID)
VALUES ('Ratatouille', '2024-09-02', 100, 1),
       ('Spaghetti', '2024-09-02', 120.50, 1),
       ('Pizza', '2024-09-01', 55.55, 1),
       ('Burger', '2024-09-02', 50, 2),
       ('Fries', '2024-09-02', 30, 2),
       ('Fries', '2024-09-01', 20, 2),
       ('Cherry pie', '2024-09-02', 45.10, 3),
       ('Fried eggs', '2024-09-01', 45.10, 3);

INSERT INTO VOTE (DATE, RESTAURANT_ID, USER_ID)
VALUES ('2024-09-02', 1, 1),
       ('2024-09-02', 1, 2),
       ('2024-09-02', 2, 3),
       ('2024-09-01', 3, 1),
       ('2024-09-01', 3, 2),
       ('2024-09-01', 3, 3);
