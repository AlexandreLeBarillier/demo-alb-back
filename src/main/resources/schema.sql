DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    label  VARCHAR(250) NOT NULL,
    status VARCHAR(25) NOT NULL
);
