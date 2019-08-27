-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2019 at 05:30 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `giai_phap_sms`
--
CREATE DATABASE IF NOT EXISTS `giai_phap_sms` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `giai_phap_sms`;

-- --------------------------------------------------------

--
-- Table structure for table `info_send_sms`
--

DROP TABLE IF EXISTS `info_send_sms`;
CREATE TABLE IF NOT EXISTS `info_send_sms` (
  `id_info_send_sms` int(11) NOT NULL AUTO_INCREMENT,
  `number_phone` varchar(15) CHARACTER SET utf8 NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `status` int(2) NOT NULL,
  `type_send` int(2) NOT NULL,
  `sender` varchar(15) CHARACTER SET utf8 NOT NULL,
  `send_now` int(1) NOT NULL,
  `date_time_send` datetime NOT NULL,
  `repeat_time` int(1) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_info_send_sms`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `otp_input`
--

DROP TABLE IF EXISTS `otp_input`;
CREATE TABLE IF NOT EXISTS `otp_input` (
  `otp_input_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NOT NULL,
  `id_info_send_sms` int(11) NOT NULL,
  `otp` varchar(8) CHARACTER SET utf8 NOT NULL,
  `status` int(2) NOT NULL,
  `type` int(2) NOT NULL,
  `create_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `update_date` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`otp_input_id`,`user_id`,`id_info_send_sms`),
  KEY `otp_input_user_fk` (`user_id`),
  KEY `otp_input_info_send_fk` (`id_info_send_sms`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(2) NOT NULL,
  `name_role` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `update_date` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `name_role`, `create_date`, `update_date`) VALUES
(1, 'ROLE_ADMIN', '2019-08-27 14:47:50.868605', '0000-00-00 00:00:00.000000'),
(2, 'ROLE_USER', '2019-08-27 14:47:50.868605', '0000-00-00 00:00:00.000000'),
(3, 'ROLE_OTP_REG_ACC', '2019-08-27 14:47:50.868605', '0000-00-00 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` int(1) NOT NULL,
  `first_name` varchar(15) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(15) CHARACTER SET utf8 NOT NULL,
  `number_phone` int(15) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `users_roles_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NOT NULL,
  `role_id` int(2) NOT NULL,
  `create_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `update_date` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`users_roles_id`),
  KEY `user_role_role_fk` (`role_id`),
  KEY `user_role_user_fk` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `otp_input`
--
ALTER TABLE `otp_input`
  ADD CONSTRAINT `otp_input_info_send_fk` FOREIGN KEY (`id_info_send_sms`) REFERENCES `info_send_sms` (`id_info_send_sms`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `otp_input_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `user_role_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_role_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
