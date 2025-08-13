INSERT IGNORE INTO `users` (
    `id`,`name`,`email`,`password`,`authorities`,`password_temporary`)
VALUES
    (1,'admin','admin@gmail.com','$2a$10$h/EXE0cJ8xvfpKcvqxWbMufEjtUT2QEtVRSd3H1LR8rv5kJkA6vnq','ADMIN',false);