--
-- Структура таблицы `drugs`
--
CREATE TABLE IF NOT EXISTS `drugs` (
                                          `id` int auto_increment primary key,
                                          `name` varchar(255) DEFAULT NULL,
                                          `description` varchar(255) DEFAULT NULL,
                                          `photo_URL` varchar(255) DEFAULT NULL,
                                          `warehouses_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Структура таблицы `orders`
--
CREATE TABLE IF NOT EXISTS `orders` (
                                          `id` int auto_increment primary key,
                                          `drug_name` varchar(255) DEFAULT NULL,
                                          `count` int DEFAULT NULL,
                                          `create_order` datetime(6) DEFAULT NULL,
                                          `order_status` enum('CREATE','IN_PROGRESS','READY','DONE') DEFAULT NULL,
                                          `delete_order` datetime(6) DEFAULT NULL,
                                          `updated_at` datetime(6) DEFAULT NULL,
                                          `qr_code` varchar(255) DEFAULT NULL,
                                          `pharmacy_id` int DEFAULT NULL,
                                          `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Структура таблицы `pharmacies`
--
CREATE TABLE IF NOT EXISTS `pharmacies` (
                                          `id` int auto_increment primary key,
                                          `pharmacyName` varchar(255) DEFAULT NULL,
                                          `pharmacyAddress` varchar(255) DEFAULT NULL,
                                          `phone` varchar(255) DEFAULT NULL,
                                          `description` varchar(255) DEFAULT NULL,
                                          `location` varchar(255) DEFAULT NULL,
                                          `photo_url` varchar(255) DEFAULT NULL,
                                          `warehouse_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Структура таблицы `users`
--
CREATE TABLE IF NOT EXISTS `users` (
                                          `id` int auto_increment primary key,
                                          `name` varchar(255) DEFAULT NULL,
                                          `surname` varchar(255) DEFAULT NULL,
                                          `lastname` varchar(255) DEFAULT NULL,
                                          `email` varchar(255) DEFAULT NULL,
                                          `password` varchar(255) DEFAULT NULL,
                                          `authorities` enum('USER','PHARMACY','ADMIN','DELIVERY') DEFAULT NULL,
                                          `photoURL` varchar(255) DEFAULT NULL,
                                          `phone` varchar(255) DEFAULT NULL,
                                          `password_temporary` bit(1) DEFAULT NULL,
                                          `pharmacy_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Структура таблицы `warehouses`
--
CREATE TABLE IF NOT EXISTS `warehouses` (
                                          `id` int auto_increment primary key,
                                          `drug_name` varchar(255) DEFAULT NULL,
                                          `count` int DEFAULT NULL,
                                          `reserve_count` int DEFAULT NULL,
                                          `create_data` datetime(6) DEFAULT NULL,
                                          `delete_data` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;