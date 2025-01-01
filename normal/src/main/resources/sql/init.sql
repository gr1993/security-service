CREATE TABLE `user` (
    `username` VARCHAR(45) NOT NULL,
    `password` TEXT NULL,
    `age` TINYINT NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE `otp` (
    `username` VARCHAR(45) NOT NULL,
    `code` VARCHAR(45) NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE `role` (
    `username` VARCHAR(45) NOT NULL,
    `role` VARCHAR(45) NULL,
    PRIMARY KEY (`username`)
);