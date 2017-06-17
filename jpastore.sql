-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2017 at 02:00 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jpastore`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `EMAIL`, `first_name`, `last_name`) VALUES
(1, 'perap@gmail.com', 'Pera', 'Peric'),
(3, 'mikaantic@gmail.com', 'Mika', 'Antic');

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE IF NOT EXISTS `invoices` (
  `invoice_number` bigint(20) NOT NULL,
  `invoice_date` date DEFAULT NULL,
  `is_processed` tinyint(1) DEFAULT '0',
  `CUSTOMER_customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`invoice_number`),
  KEY `FK_invoices_CUSTOMER_customer_id` (`CUSTOMER_customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`invoice_number`, `invoice_date`, `is_processed`, `CUSTOMER_customer_id`) VALUES
(4, '2016-10-23', 1, 3),
(7, '2016-12-10', 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `lineitems`
--

CREATE TABLE IF NOT EXISTS `lineitems` (
  `line_item_id` bigint(20) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `invoice_number` bigint(20) DEFAULT NULL,
  `PRODUCT_product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`line_item_id`),
  KEY `FK_lineitems_PRODUCT_product_id` (`PRODUCT_product_id`),
  KEY `FK_lineitems_invoice_number` (`invoice_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lineitems`
--

INSERT INTO `lineitems` (`line_item_id`, `QUANTITY`, `invoice_number`, `PRODUCT_product_id`) VALUES
(5, 100, 4, 2),
(6, 3, 4, 2),
(8, 15, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `product_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `unit_price` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `name`, `unit_price`) VALUES
(2, 'pegla', '990.99');

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '50');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `FK_invoices_CUSTOMER_customer_id` FOREIGN KEY (`CUSTOMER_customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Constraints for table `lineitems`
--
ALTER TABLE `lineitems`
  ADD CONSTRAINT `FK_lineitems_invoice_number` FOREIGN KEY (`invoice_number`) REFERENCES `invoices` (`invoice_number`),
  ADD CONSTRAINT `FK_lineitems_PRODUCT_product_id` FOREIGN KEY (`PRODUCT_product_id`) REFERENCES `products` (`product_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
