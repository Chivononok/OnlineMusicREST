INSERT INTO users(
    id, name, password, roles)
VALUES
    (3, 'User7', '$2a$10$juFBI3qTNIfRI2MufACKXOC8pZX9aqp1MKsyWNVe/JmV3gKsANkNK', 'USER'),
    (4, 'User4', '$2a$10$mtiHJWw7jrxkER6IaZQbRemUA4Mdq6Bq3c2tw3WpSyFmcLPH5TqWG', 'ADMIN');

INSERT INTO song(
    id, link, name, album_id, performer_id)
VALUES
    (302, 'link11', 'name57', 4, 2),
    (303, 'link12', 'name53', 4, 2);

INSERT INTO performer(
    id, isgroup, name)
VALUES
    (2, 'false', 'Исполнитель2'),
    (3, 'true',  'Исполнитель3');

INSERT INTO album(
    id, name, year)
VALUES
    (4, 'Альбом 4', 2012),
    (5, 'Альбом 5', 2024);