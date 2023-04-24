use loyalbank;

CREATE TABLE `Customer` (
  `id` bigint NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Account` (
  `id` bigint NOT NULL,
  `account_no` bigint NOT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `account_opened_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;