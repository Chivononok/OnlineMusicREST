INSERT INTO users(id, name, password, roles) VALUES (1, 'TestUser', 'password123', 'USER');
INSERT INTO performer(id, isgroup, name) VALUES (1, TRUE, 'TestPerformer');
INSERT INTO album(id, name, year) VALUES (1, 'TestAlbum', 2024);
INSERT INTO song(id, link, name, album_id, performer_id) VALUES (1, 'http://example.com/song1', 'TestSong', 1, 1);