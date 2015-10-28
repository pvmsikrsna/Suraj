-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 28, 2015 at 12:36 PM
-- Server version: 5.6.24
-- PHP Version: 5.3.10-1ubuntu3.19

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `UserInfo`
--
DROP DATABASE `UserInfo`;
CREATE DATABASE `UserInfo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `UserInfo`;

-- --------------------------------------------------------

--
-- Table structure for table `UserProfile`
--

CREATE TABLE IF NOT EXISTS `UserProfile` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Users_Id` bigint(20) NOT NULL,
  `FirstName` varchar(15) DEFAULT NULL,
  `LastName` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT '1900-01-01',
  `Gender` varchar(2) DEFAULT NULL,
  `Current_Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Updated_Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `UserProfile`
--

INSERT INTO `UserProfile` (`Id`, `Users_Id`, `FirstName`, `LastName`, `DOB`, `Gender`, `Current_Timestamp`, `Updated_Timestamp`) VALUES
(3, 11, 'Suraj', 'Anthwal', '1992-02-13', 'M', '2015-10-27 16:58:42', '2015-10-27 17:54:43'),
(4, 12, 'vikas', 'rawat', '1990-05-28', 'M', '2015-10-27 18:19:27', '2015-10-27 18:40:04'),
(6, 14, 'ved', 'singh', '1996-12-25', 'M', '2015-10-27 18:51:05', '2015-10-27 19:43:28'),
(7, 17, NULL, NULL, NULL, NULL, '2015-10-28 06:35:48', '2015-10-28 06:35:48'),
(8, 18, NULL, NULL, NULL, NULL, '2015-10-28 06:56:02', '2015-10-28 06:56:02');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EmailID` varchar(25) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Authorization_Token` varchar(100) NOT NULL,
  `Current_Timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Updated_Timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`Id`, `EmailID`, `Password`, `Authorization_Token`, `Current_Timestamp`, `Updated_Timestamp`) VALUES
(11, 'suraj@gmail.com', '$2a$10$/4DuURYooEUcmo.UkfFu4OBaqUWXjPfcPoEOq9PcMPbP95ojVtwoe', '79e6aff0-f42b-4d92-95c9-37ae848c481d', '2015-10-27 22:28:42', '2015-10-27 22:28:42'),
(12, 'vikas@gmail.com', '$2a$10$p1uguKPGDGTye1vI8w7aV.gNuvatVIMMA/0R3Pd7ADuHytdpvi62y', '855af695-16a5-4acd-be49-c3087c562e27', '2015-10-27 23:49:27', '2015-10-27 23:49:27'),
(14, 'ved@gmail.com', '$2a$10$0LMYsQoTZ021r9CT/AVy3.zrZCllGncbj2TvoBN5UDsrPKoPL5rs6', '494bb163-eff9-4a62-a218-8f6b8cd6aeb0', '2015-10-28 00:21:05', '2015-10-28 00:21:05'),
(17, 'brick@gmail.com', '$2a$10$Y3o7gkj3fakqTgI4Sjc5t.pWYRcLgy8/JvoKdHcBew4ObiE0YHdPq', '854477af-99b9-42d6-83f2-d28d6b97f266', '2015-10-28 12:05:48', '2015-10-28 12:05:48'),
(18, 'raj@gmail.com', '$2a$10$JYgr9etOrr3SKSAGkwbun.IaYXQYYcfDtP4ywRU51bbfA./C23pWy', 'b374b5d6-41d1-4138-bc18-1505b6b1554e', '2015-10-28 12:26:02', '2015-10-28 12:26:02');

-- --------------------------------------------------------

--
-- Table structure for table `play_evolutions`
--

CREATE TABLE IF NOT EXISTS `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` mediumtext,
  `revert_script` mediumtext,
  `state` varchar(255) DEFAULT NULL,
  `last_problem` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
