DROP DATABASE IF EXISTS movflix_database; 
CREATE DATABASE IF NOT EXISTS movflix_database;
USE movflix_database;

-- ------------------------------- USER INFO -------------------------------
-- User table
CREATE TABLE user(
	User_ID				INT(6)				PRIMARY KEY,	
    First_Name			VARCHAR(20)			NOT NULL,
    Last_Name			VARCHAR(20)			NOT NULL,
    Email				VARCHAR(50)			NOT NULL,
    User_Password		VARCHAR(60)			NOT NULL,					
    User_Role			VARCHAR(12)			NOT NULL
);
ALTER TABLE user
ADD UNIQUE INDEX email_unique (Email ASC) ;
INSERT INTO user (User_ID, First_Name, Last_Name, Email, User_Password, User_Role) VALUES
(900001, "Pongpop", "Lapvikai", "pongpop.lap@student.mahidol.ac.th", "$2b$10$.r8rIMTbsWbAZFxrGwzTiuro/nK2.vLxglKvuKbEKt9O/FSlN3uj6", "admin"),
(900002, "Kittapon", "Lerdpayub", "kittapon.ler@student.mahidol.ac.th", "$2b$10$hs/SQNU0tmX9TiL5AStJleV17v03xMg1Yf8Ok/YfBwA.diOgU3qzm", "admin"),
(900003, "Paveena", "Kumnerdpun", "paveena@student.mahidol.ac.th", "$2b$10$xTY.RNoK7U/1thTACUS5rO80AWN4VnbC9SfqbjmNF8Jh.Im6B4h1y", "admin"),
(900004, "Kanin", "Prakaikowit", "kanin.pra@student.mahidol.ac.th", "$2b$10$hhjpQIrbzAP/v/b7TdP8TOdY3e9UKHtQQE./Lri7mHYzzQuM9xEzK", "admin"),
(100001, "Karl", "Schneider", "karl.s@gmail.com",  "$2b$10$epIIk..vrAhVu1Gc1odeXO65DMuf0/wQ6cnp5QHTaG4dTFgPou87e", "normal user"),
(100002, "Leadale", "Jones", "lea_j@gmail.com", "$2b$10$ShQ.WIeBv.T1sRmWG0VD4O7y18WH4CJMEIvu5Vyanj6HRm8plBLom", "normal user"),
(100003, "Hiroyuki", "Sawano", "sh.pro@gmail.com", "$2b$10$UrqyECrkrokzXnfvj2EnA.14SQwx7s30Uhmn2LXpHhJ5Rp4uGPAw.", "normal user"),
(100004, "Theo", "Magath", "Marley.forever@gmail.com", "$2b$10$tL2sawv3lVyJvP4HLZWHeO5/EVJ06FxthK9oM2VuWTlwUU6M7KrUm", "normal user"),
(100005, "Mary", "Somers", "Horizon_dear@gmail.com", "$2b$10$S/lXkzmM14YOdUCpT868jeh57xoB5JYUbDVqVCYUPLTv097OciOKG", "normal user"),
(100006, "Walter", "Fitzroy", "Fuse_HellYeah@gmail.com", "$2b$10$Zv9WVVc2a0A6WHh7F4Y2r.SQ0r6yRbQiPW24EVArsvi7lZnC1hCs6", "normal user"),
(100007, "Kairi", "Imahara", "Valk_wings@gmail.com", "$2b$10$Vizu7uFPezgx07Z9bDyRo.QB8z6AdHapiyXuycuQ51SrdFRvVcWL6", "normal user"),
(100008, "Obi", "Edolasim", "Seer_Heartbeat@gmail.com", "$2b$10$I6rceRs6zNFSn0y/u59xRecHPb3jqIiRRwQJOR2t0B0Bfw2bRnE7C", "normal user"),
(100009, "Ramya", "Parekh", "Rampart_mates@gmail.com", "$2b$10$k9MijSrplvLwX9APWz5QS.zYv35trWICxDIgGGOn.7zFualJZ5ZN6", "normal user"),
(100010, "Alexander", "Maxwell", "Caustic_trap@gmail.com", "$2b$10$UFMyQsIWKvwyP6xvY7NaEuIDVkBATrCfgSmJiDc7EOZDSc61ogVKG", "normal user"),
(100011, "Kaleb", "Cross", "Revenant_silence@gmail.com", "$2b$10$J2MRqCN9yypZhyqIZjsFcObfEzHQWX6D/NWmdiqwVkd2toNNSbE1a", "normal user");
-- SELECT * from user;
-- login_info table
CREATE TABLE login_info(
	Login_ID			INT(6)				PRIMARY KEY,	
    User_ID				INT(6)				NOT NULL,		
    CONSTRAINT	fk_userID FOREIGN KEY	(User_ID) 
    REFERENCES	user(User_ID),
    Email				VARCHAR(50)			NOT NULL,
    User_Password		VARCHAR(120)		NOT NULL,
    User_Role			VARCHAR(12)			NOT NULL,
    Login_Log			DATETIME			NOT NULL
);
INSERT INTO login_info (Login_ID, User_ID, Email, User_Password, User_Role, Login_Log) VALUES
(144125, 100001, "karl.s@gmail.com", "$2b$10$epIIk..vrAhVu1Gc1odeXO65DMuf0/wQ6cnp5QHTaG4dTFgPou87e", "normal user", "2022-03-26 14:41:25"),
(180831, 100007, "Valk_wings@gmail.com", "$2b$10$Vizu7uFPezgx07Z9bDyRo.QB8z6AdHapiyXuycuQ51SrdFRvVcWL6", "normal user", "2022-03-21 18:08:31"),
(152104, 100010, "Caustic_trap@gmail.com", "$2b$10$UFMyQsIWKvwyP6xvY7NaEuIDVkBATrCfgSmJiDc7EOZDSc61ogVKG", "normal user", "2021-07-24 15:21:04"),
(120346, 900001, "pongpop.lap@student.mahidol.ac.th", "$2b$10$.r8rIMTbsWbAZFxrGwzTiuro/nK2.vLxglKvuKbEKt9O/FSlN3uj6", "admin", "2022-03-26 02:03:46"),
(231135, 900004, "kanin.pra@student.mahidol.ac.th", "$2b$10$hhjpQIrbzAP/v/b7TdP8TOdY3e9UKHtQQE./Lri7mHYzzQuM9xEzK", "admin", "2022-02-17 23:11:35"),
(220111, 100003, "sh.pro@gmail.com", "$2b$10$UrqyECrkrokzXnfvj2EnA.14SQwx7s30Uhmn2LXpHhJ5Rp4uGPAw.", "normal user", "2022-01-11 11:11:11"),
(220324, 100007, "Valk_wings@gmail.com", "$2b$10$Vizu7uFPezgx07Z9bDyRo.QB8z6AdHapiyXuycuQ51SrdFRvVcWL6", "normal user", "2022-03-24 17:48:52"),
(224127, 100009, "Rampart_mates@gmail.com", "$2b$10$k9MijSrplvLwX9APWz5QS.zYv35trWICxDIgGGOn.7zFualJZ5ZN6", "normal user", "2022-03-19 22:41:27"),
(204645, 900002, "kittapon.ler@student.mahidol.ac.th" , "$2b$10$hs/SQNU0tmX9TiL5AStJleV17v03xMg1Yf8Ok/YfBwA.diOgU3qzm", "admin", "2022-03-27 20:46:45"),
(200142, 900003, "paveena@student.mahidol.ac.th", "$2b$10$xTY.RNoK7U/1thTACUS5rO80AWN4VnbC9SfqbjmNF8Jh.Im6B4h1y", "admin", "2022-03-27 20:01:42");

-- ------------------------------ PRODUCT INFO ------------------------------
-- Movie table
CREATE TABLE movie(
	Movie_ID			INT(6)				PRIMARY KEY,
    Movie_Name			VARCHAR(50)			NOT NULL,	
    Movie_img_src		VARCHAR(400)		NOT NULL,
    Release_Date		DATE				NOT NULL,
    Run_Time			TIME				NOT NULL,
    Director			VARCHAR(40)			NOT NULL,
    Rate				VARCHAR(10) 		NOT NULL,
    Tag					VARCHAR(60)			NOT NULL,
    Movie_Description	VARCHAR(1000) 		NOT NULL		
);
INSERT INTO movie (Movie_ID, Movie_Name, Movie_img_src, Release_Date, Run_Time, Director, Rate, Tag, Movie_Description) VALUES
(130001, "Avengers: Endgame", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg", "2019-04-26", "03:01:00", "Anthony Russo, Joe Russo", "PG-13", "action", "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."),
(130002, "47 Ronin", "https://m.media-amazon.com/images/M/MV5BMTc0MjE2NzE0OV5BMl5BanBnXkFtZTgwNTU5MjE1MDE@._V1_FMjpg_UX1000_.jpg", "2013-12-06", "02:08:00", "Carl Rinsch", "PG-13", "action", "A band of samurai set out to avenge the death and dishonor of their master at the hands of a ruthless shogun."),
(130003, "The Last Samurai", "https://m.media-amazon.com/images/M/MV5BMzkyNzQ1Mzc0NV5BMl5BanBnXkFtZTcwODg3MzUzMw@@._V1_.jpg", "2019-01-21", "02:34:00", "Edward Zwick", "R", "action", "An American military advisor embraces the Samurai culture he was hired to destroy after he is captured in battle."),
(130004, "Hotel Transylvania 3: Summer Vacation", "https://m.media-amazon.com/images/M/MV5BNjA1MzU5MTY3OF5BMl5BanBnXkFtZTgwNTU5MDA3NTM@._V1_FMjpg_UX1000_.jpg", "2018-07-13", "01:37:00", "Genndy Tartakovsky", "PG", "comedy", "Count Dracula and company participate in a cruise for sea-loving monsters, unaware that their boat is being commandeered by the monster-hating Van Helsing family."),
(130005, "Zootopia", "https://lumiere-a.akamaihd.net/v1/images/movie_poster_zootopia_866a1bf2.jpeg", "2016-04-21", "01:48:00", "Rich Moore, Byron Howard", "PG", "comedy", "In a city of anthropomorphic animals, a rookie bunny cop and a cynical con artist fox must work together to uncover a conspiracy."),
(130006, "The silence", "https://m.media-amazon.com/images/M/MV5BMmZlOGZkMjMtZDc4Ny00ZTQ1LWFmYTQtOThlY2JjYjg1NGQyXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_.jpg", "2019-05-16", "01:30:00", "John R. Leonetti", "PG-16", "horor", "With the world under attack by deadly creatures who hunt by sound, a teen and her family seek refuge outside the city and encounter a mysterious cult."),
(130007, "A WHISKER AWAY", "https://m.media-amazon.com/images/M/MV5BNDI5ODBhYzMtNDc4Yi00NjEwLWJiZWUtMGE2Mzc4MGVjN2E0XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg", "2020-06-18", "01:45:00", "Junichi Sato, Tomotaka Shibayama", "PG", "fantasy", "A peculiar girl transforms into a cat to catch her crush's attention. But before she realizes it, the line between human and animal starts to blur."),
(130008, "Godzilla: King of the Monsters", "https://m.media-amazon.com/images/M/MV5BOGFjYWNkMTMtMTg1ZC00Y2I4LTg0ZTYtN2ZlMzI4MGQwNzg4XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg", "2019-05-31", "02:11:00", "Michael Dougherty", "PG-13", "sci-fi", "When rogue scientists set out to reset the balance of humanity by awakening the world's monsters, Godzilla must rise to fend off these chaotic titans."),
(130009, "Ghost Wife", "https://m.media-amazon.com/images/M/MV5BMjFjYjEwNTgtZTlkMi00YjY4LTkzNjUtZjVjYjlmMmFiZGQ0XkEyXkFqcGdeQXVyNTY0MTg0NzM@._V1_.jpg", "2018-11-22", "01:40:00", "Mate Yimsomboon", "PG-16", "horor", "After being separated from the boy she loves, a pregnant teen girl dies only for her vengeful spirit to return and haunt her old home."),
(130010, "Pee Mak", "https://m.media-amazon.com/images/M/MV5BZmIwZjkyZjQtNGIxZC00MjhmLWExODgtYTU4OGFmOTg2MzU2XkEyXkFqcGdeQXVyMjUzNDk4OTQ@._V1_FMjpg_UX1000_.jpg", "2013-03-28", "01:55:00", "Banjong Pisanthanakun", "PG-16", "horor, comedy", "Mak's friends just want to protect him, but his wife Nak won't let a small thing like her own death get in the way of true love in this horror-comedy."),
(130011, "2012", "https://m.media-amazon.com/images/M/MV5BMTY0MjEyODQzMF5BMl5BanBnXkFtZTcwMTczMjQ4Mg@@._V1_FMjpg_UX1000_.jpg", "2009-11-13", "02:38:00", "Roland Emmerich", "PG-13", "sci-fi", "When a flood of natural disasters begins to destroy the world, a divorced dad desperately tries to save his family by outrunning the cataclysmic chaos."),
(130012, "The Platform", "https://m.media-amazon.com/images/M/MV5BNzAxYmQzYmYtYjEzYS00ZTFkLWE3NjUtODhjOTQzM2U5ZWI1XkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_.jpg", "2019-11-08", "01:34:00", "Galder Gaztelu-Urrutia", "PG-18", "horor", "A slab of food descends floor by floor in a prison. The inmates above eat heartily, leaving those below starving and desperate. A rebellion is imminent."),
(130013, "13 Hours: The Secret Soldiers of Benghazi", "https://m.media-amazon.com/images/M/MV5BYjY0OWVjMGQtNTIzZi00OGU5LWI4N2EtMGU0YzQ4OWM4ZmVhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg", "2016-01-21", "02:25:00", "Michael Bay", "PG-18", "action", "Members of an elite security team battle to save the lives of trapped U.S. Consulate personnel under attack by armed terrorists in Benghazi, Libya."),
(130014, "Deep Impact", "https://m.media-amazon.com/images/M/MV5BYTUwMTY1YmMtN2U5NC00YjkzLTg0YWQtZmEwNTEzZjdkNzQ2XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg", "1998-05-08", "02:02:00", "Mimi Leder", "PG-13", "sci-fi", "With a massive comet threatening to destroy Earth, the U.S. president appoints a steely veteran astronaut to lead a crew into space to destroy it."),
(130015, "Teenage Mutant Ninja Turtles", "https://m.media-amazon.com/images/M/MV5BNjUzODQ5MDY5NV5BMl5BanBnXkFtZTgwOTc1NzcyMjE@._V1_.jpg", "2014-08-06", "01:42:00", "Jonathan Liebesman", "PG-13", "action", "With New York City in the criminal clutches of the villainous Shredder, four unlikely vigilantes rise from their sewer lair to take back the city.");

-- to see changes after CRUD
SELECT * from user;
SELECT * from login_info;
SELECT * from movie;