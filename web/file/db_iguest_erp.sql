-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 13, 2017 at 03:38 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_iguest`
--

-- --------------------------------------------------------

--
-- Table structure for table `TD_EMPLOYEE`
--

CREATE TABLE `TD_EMPLOYEE` (
  `ID_EMPLOYEE` int(11) NOT NULL,
  `ID_JNS_EMPLOYEE` int(11) DEFAULT NULL,
  `NAMA` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ALAMAT` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HP` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TD_EMPLOYEE`
--

INSERT INTO `TD_EMPLOYEE` (`ID_EMPLOYEE`, `ID_JNS_EMPLOYEE`, `NAMA`, `ALAMAT`, `HP`, `EMAIL`) VALUES
(1, 2, 'Dewa', 'Jalan sesat', '081933103232', 'dewagdeadi@icloud.com'),
(16, 1, 'Ketut', 'jalan jalan lagi', '1234567', 'managerketut@gmail.com'),
(17, 4, 'RoomService', 'sepanjang jalan', '43123124124214', 'roomservice1@gmail.com'),
(18, 3, 'Hel', 'jalan-jalan', '1234', 'frontoffice1@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `TD_GUEST`
--

CREATE TABLE `TD_GUEST` (
  `ID_GUEST` int(11) NOT NULL,
  `ID_JNS_IDENTITAS` int(11) DEFAULT NULL,
  `NAMA` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NO_IDENTITAS` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ALAMAT` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WARGA_NEGARA` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KONTAK` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TD_GUEST`
--

INSERT INTO `TD_GUEST` (`ID_GUEST`, `ID_JNS_IDENTITAS`, `NAMA`, `NO_IDENTITAS`, `ALAMAT`, `WARGA_NEGARA`, `KONTAK`, `EMAIL`) VALUES
(2, 2, 'nama', '123456', 'di suatu ketika', 'Indonesia', '7744636237', 'c@a.com'),
(3, 1, 'seseorang', '1234567', 'di suatu ketika', 'Indonesia', '7744636237', 'c@a.com'),
(4, 2, 'Bayu', '123456789', 'Jakarta', 'Indonesia', '0812xxxxxxx', 'aku@gmail.com'),
(5, 2, 'joko', '123456790', 'jakarta', 'Indonesia', '0852123xxxx', 'joko@gmail.com'),
(6, 1, 'seseorang', '111', 'di suatu ketika', 'Indonesia', '7744636237', 'c@a.com'),
(7, 1, 'sdf', '22222', 'sdf', 'asds', '3453', 'sd'),
(8, 2, 'serw', '23', 'dsfwef', 's', '234324', 'we');

-- --------------------------------------------------------

--
-- Table structure for table `TD_ROOM`
--

CREATE TABLE `TD_ROOM` (
  `ID_ROOM` int(11) NOT NULL,
  `ID_JNS_ROOM` int(11) DEFAULT NULL,
  `NAMA_ROOM` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MAX_GUEST` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TD_ROOM`
--

INSERT INTO `TD_ROOM` (`ID_ROOM`, `ID_JNS_ROOM`, `NAMA_ROOM`, `MAX_GUEST`) VALUES
(1, 1, 'Asoka', 2),
(2, 1, 'Cempaka', 2),
(4, 1, '123', 12),
(5, 6, '1234', 12),
(6, 3, '301', 2);

-- --------------------------------------------------------

--
-- Table structure for table `TD_USER`
--

CREATE TABLE `TD_USER` (
  `ID_USER` int(11) NOT NULL,
  `ID_EMPLOYEE` int(11) DEFAULT NULL,
  `ID_JNS_USER` int(11) DEFAULT NULL,
  `FLAG_AKTIF` tinyint(1) DEFAULT NULL,
  `USERNAME` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TD_USER`
--

INSERT INTO `TD_USER` (`ID_USER`, `ID_EMPLOYEE`, `ID_JNS_USER`, `FLAG_AKTIF`, `USERNAME`, `PASSWORD`) VALUES
(1, 1, 1, 1, 'dewa', 'e5a908dfcb83a8c799770b7a1e1ae6a170c3695b44942016ddf127da96f9e2df'),
(9, 16, 2, 1, 'ketut1', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),
(10, 18, 3, 1, 'hel', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c'),
(11, 17, 4, 1, 'roomservice1', '113459eb7bb31bddee85ade5230d6ad5d8b2fb52879e00a84ff6ae1067a210d3'),
(12, 1, 3, 1, 'fo', '9c3aee7110b787f0fb5f81633a36392bd277ea945d44c874a9a23601aefe20cf'),
(13, 1, 4, 1, 'room', '1f1c5b2fad778434024f1537986346927917f4755a6e7d3fd91f22653b7c3132'),
(14, 1, 2, 1, 'manager', '6ee4a469cd4e91053847f5d3fcb61dbcc91e8f0ef10be7748da4c4a1ba382d17');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_EMPLOYEE`
--

CREATE TABLE `TR_JNS_EMPLOYEE` (
  `ID_JNS_EMPLOYEE` int(11) NOT NULL,
  `JNS_EMPLOYEE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_EMPLOYEE`
--

INSERT INTO `TR_JNS_EMPLOYEE` (`ID_JNS_EMPLOYEE`, `JNS_EMPLOYEE`) VALUES
(1, 'Manager'),
(2, 'Admin'),
(3, 'Front Office'),
(4, 'Room Service');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_IDENTITAS`
--

CREATE TABLE `TR_JNS_IDENTITAS` (
  `ID_JNS_IDENTITAS` int(11) NOT NULL,
  `JNS_IDENTITAS` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_IDENTITAS`
--

INSERT INTO `TR_JNS_IDENTITAS` (`ID_JNS_IDENTITAS`, `JNS_IDENTITAS`) VALUES
(1, 'Paspor'),
(2, 'Ktp');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_LOG_ROOM`
--

CREATE TABLE `TR_JNS_LOG_ROOM` (
  `ID_JNS_LOG_ROOM` int(11) NOT NULL,
  `JNS_LOG_ROOM` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_LOG_ROOM`
--

INSERT INTO `TR_JNS_LOG_ROOM` (`ID_JNS_LOG_ROOM`, `JNS_LOG_ROOM`) VALUES
(1, 'Occupied'),
(2, 'Vacant'),
(3, 'Occupied Clean'),
(4, 'Occupied Dirty'),
(5, 'Vacant Clean Inspected'),
(6, 'Vacant Clean'),
(7, 'Do not Disturb'),
(8, 'Sleep Out'),
(9, 'Out of Service'),
(10, 'Cancel'),
(11, 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_LOG_SISTEM`
--

CREATE TABLE `TR_JNS_LOG_SISTEM` (
  `ID_JNS_LOG_SISTEM` int(11) NOT NULL,
  `JNS_LOG_SISTEM` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_LOG_SISTEM`
--

INSERT INTO `TR_JNS_LOG_SISTEM` (`ID_JNS_LOG_SISTEM`, `JNS_LOG_SISTEM`) VALUES
(1, 'Log in'),
(2, 'Log out');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_PAYMENT`
--

CREATE TABLE `TR_JNS_PAYMENT` (
  `ID_JNS_PAYMENT` int(11) NOT NULL,
  `JNS_PAYMENT` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_PAYMENT`
--

INSERT INTO `TR_JNS_PAYMENT` (`ID_JNS_PAYMENT`, `JNS_PAYMENT`) VALUES
(1, 'Cash'),
(2, 'Credit Card'),
(3, 'Bank Transfer'),
(4, 'Paypal');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_ROOM`
--

CREATE TABLE `TR_JNS_ROOM` (
  `ID_JNS_ROOM` int(11) NOT NULL,
  `JNS_ROOM` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_ROOM`
--

INSERT INTO `TR_JNS_ROOM` (`ID_JNS_ROOM`, `JNS_ROOM`) VALUES
(1, 'STANDARD ROOM'),
(2, 'SUPERIOR ROOM'),
(3, 'DELUXE ROOM '),
(4, 'JUNIOR SUITE ROOM'),
(5, 'SUITE ROOM'),
(6, 'PRESIDENTIAL');

-- --------------------------------------------------------

--
-- Table structure for table `TR_JNS_USER`
--

CREATE TABLE `TR_JNS_USER` (
  `ID_JNS_USER` int(11) NOT NULL,
  `JNS_USER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_JNS_USER`
--

INSERT INTO `TR_JNS_USER` (`ID_JNS_USER`, `JNS_USER`) VALUES
(1, 'Super User'),
(2, 'Manager'),
(3, 'Front Office'),
(4, 'Room Service');

-- --------------------------------------------------------

--
-- Table structure for table `TR_PAYMENT_STATUS`
--

CREATE TABLE `TR_PAYMENT_STATUS` (
  `ID_PAYMENT_STATUS` int(11) NOT NULL,
  `PAYMENT_STATUS` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TR_PAYMENT_STATUS`
--

INSERT INTO `TR_PAYMENT_STATUS` (`ID_PAYMENT_STATUS`, `PAYMENT_STATUS`) VALUES
(1, 'Complete'),
(2, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `TT_LOG_ROOM`
--

CREATE TABLE `TT_LOG_ROOM` (
  `ID_LOG_ROOM` int(11) NOT NULL,
  `ID_JNS_LOG_ROOM` int(11) DEFAULT NULL,
  `ID_ROOM_RATE` int(11) DEFAULT NULL,
  `ID_ROOM_RENT` int(11) DEFAULT NULL,
  `WAKTU_LOG_ROOM` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TT_LOG_ROOM`
--

INSERT INTO `TT_LOG_ROOM` (`ID_LOG_ROOM`, `ID_JNS_LOG_ROOM`, `ID_ROOM_RATE`, `ID_ROOM_RENT`, `WAKTU_LOG_ROOM`) VALUES
(3, 11, 3, 3, '2017-07-05 11:37:12'),
(7, 11, 4, 7, '2017-07-05 11:49:46'),
(10, 11, 4, 10, '2017-07-06 14:14:42'),
(11, 1, 4, 7, '2017-07-06 16:12:16'),
(12, 11, 4, 11, '2017-07-07 14:51:33'),
(13, 11, 4, 12, '2017-07-07 14:53:10'),
(14, 11, 6, 13, '2017-07-07 14:55:30'),
(15, 1, 6, 13, '2017-07-07 15:17:18'),
(16, 1, 3, 3, '2017-07-07 15:25:59'),
(17, 1, 4, 12, '2017-07-07 15:38:19'),
(18, 11, 4, 14, '2017-07-13 20:08:42'),
(19, 11, 3, 15, '2017-07-13 20:16:55');

-- --------------------------------------------------------

--
-- Table structure for table `TT_LOG_SISTEM`
--

CREATE TABLE `TT_LOG_SISTEM` (
  `ID_LOG_SISTEM` int(11) NOT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_JNS_LOG_SISTEM` int(11) DEFAULT NULL,
  `WAKTU_LOG` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `TT_PAYMENT`
--

CREATE TABLE `TT_PAYMENT` (
  `ID_PAYMENT` int(11) NOT NULL,
  `ID_JNS_PAYMENT` int(11) DEFAULT NULL,
  `ID_PAYMENT_STATUS` int(11) DEFAULT NULL,
  `TOTAL` float DEFAULT NULL,
  `WAKTU_PAYMENT` datetime DEFAULT NULL,
  `DISC` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `TT_ROOM_RATE`
--

CREATE TABLE `TT_ROOM_RATE` (
  `ID_ROOM_RATE` int(11) NOT NULL,
  `RATE` float DEFAULT NULL,
  `ID_ROOM` int(11) DEFAULT NULL,
  `WAKTU_RATE` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TT_ROOM_RATE`
--

INSERT INTO `TT_ROOM_RATE` (`ID_ROOM_RATE`, `RATE`, `ID_ROOM`, `WAKTU_RATE`) VALUES
(1, 500, 4, '2017-07-04 09:39:46'),
(3, 100, 2, '2017-07-04 09:39:46'),
(4, 120, 1, '2017-07-04 09:39:46'),
(5, 200, 5, '2017-07-04 09:39:46'),
(6, 500000, 6, '2017-07-10 14:51:49');

-- --------------------------------------------------------

--
-- Table structure for table `TT_ROOM_RENT`
--

CREATE TABLE `TT_ROOM_RENT` (
  `ID_ROOM_RENT` int(11) NOT NULL,
  `ID_GUEST` int(11) DEFAULT NULL,
  `ID_PAYMENT` int(11) DEFAULT NULL,
  `CHECKIN` datetime DEFAULT NULL,
  `CHECKOUT` datetime DEFAULT NULL,
  `WAKTU_REKAM` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TT_ROOM_RENT`
--

INSERT INTO `TT_ROOM_RENT` (`ID_ROOM_RENT`, `ID_GUEST`, `ID_PAYMENT`, `CHECKIN`, `CHECKOUT`, `WAKTU_REKAM`) VALUES
(3, 2, NULL, '2017-07-04 00:00:00', '2017-07-12 00:00:00', '2017-07-05 11:37:12'),
(7, 3, NULL, '2017-07-04 00:00:00', '2017-07-28 00:00:00', '2017-07-05 11:49:46'),
(10, 3, NULL, '2017-07-06 00:00:00', '2017-07-12 00:00:00', '2017-07-06 14:14:42'),
(11, 4, NULL, '2017-07-10 00:00:00', '2017-07-11 00:00:00', '2017-07-07 14:51:33'),
(12, 3, NULL, '2017-07-04 00:00:00', '2017-07-12 00:00:00', '2017-07-07 14:53:10'),
(13, 5, NULL, '2017-07-10 00:00:00', '2017-07-12 00:00:00', '2017-07-07 14:55:30'),
(14, 7, NULL, '2017-07-13 00:00:00', '2017-07-21 00:00:00', '2017-07-13 20:08:42'),
(15, 8, NULL, '2017-07-13 00:00:00', '2017-07-15 00:00:00', '2017-07-13 20:16:55');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `TD_EMPLOYEE`
--
ALTER TABLE `TD_EMPLOYEE`
  ADD PRIMARY KEY (`ID_EMPLOYEE`),
  ADD KEY `FK_RELATIONSHIP_18` (`ID_JNS_EMPLOYEE`);

--
-- Indexes for table `TD_GUEST`
--
ALTER TABLE `TD_GUEST`
  ADD PRIMARY KEY (`ID_GUEST`),
  ADD KEY `FK_RELATIONSHIP_11` (`ID_JNS_IDENTITAS`);

--
-- Indexes for table `TD_ROOM`
--
ALTER TABLE `TD_ROOM`
  ADD PRIMARY KEY (`ID_ROOM`),
  ADD KEY `FK_RELATIONSHIP_7` (`ID_JNS_ROOM`);

--
-- Indexes for table `TD_USER`
--
ALTER TABLE `TD_USER`
  ADD PRIMARY KEY (`ID_USER`),
  ADD KEY `FK_RELATIONSHIP_1` (`ID_EMPLOYEE`),
  ADD KEY `FK_RELATIONSHIP_2` (`ID_JNS_USER`);

--
-- Indexes for table `TR_JNS_EMPLOYEE`
--
ALTER TABLE `TR_JNS_EMPLOYEE`
  ADD PRIMARY KEY (`ID_JNS_EMPLOYEE`);

--
-- Indexes for table `TR_JNS_IDENTITAS`
--
ALTER TABLE `TR_JNS_IDENTITAS`
  ADD PRIMARY KEY (`ID_JNS_IDENTITAS`);

--
-- Indexes for table `TR_JNS_LOG_ROOM`
--
ALTER TABLE `TR_JNS_LOG_ROOM`
  ADD PRIMARY KEY (`ID_JNS_LOG_ROOM`);

--
-- Indexes for table `TR_JNS_LOG_SISTEM`
--
ALTER TABLE `TR_JNS_LOG_SISTEM`
  ADD PRIMARY KEY (`ID_JNS_LOG_SISTEM`);

--
-- Indexes for table `TR_JNS_PAYMENT`
--
ALTER TABLE `TR_JNS_PAYMENT`
  ADD PRIMARY KEY (`ID_JNS_PAYMENT`);

--
-- Indexes for table `TR_JNS_ROOM`
--
ALTER TABLE `TR_JNS_ROOM`
  ADD PRIMARY KEY (`ID_JNS_ROOM`);

--
-- Indexes for table `TR_JNS_USER`
--
ALTER TABLE `TR_JNS_USER`
  ADD PRIMARY KEY (`ID_JNS_USER`);

--
-- Indexes for table `TR_PAYMENT_STATUS`
--
ALTER TABLE `TR_PAYMENT_STATUS`
  ADD PRIMARY KEY (`ID_PAYMENT_STATUS`);

--
-- Indexes for table `TT_LOG_ROOM`
--
ALTER TABLE `TT_LOG_ROOM`
  ADD PRIMARY KEY (`ID_LOG_ROOM`),
  ADD KEY `FK_RELATIONSHIP_9` (`ID_JNS_LOG_ROOM`),
  ADD KEY `FK_RELATIONSHIP_8` (`ID_ROOM_RATE`) USING BTREE,
  ADD KEY `FK_RELATIONSHIP_17` (`ID_ROOM_RENT`) USING BTREE;

--
-- Indexes for table `TT_LOG_SISTEM`
--
ALTER TABLE `TT_LOG_SISTEM`
  ADD PRIMARY KEY (`ID_LOG_SISTEM`),
  ADD KEY `FK_RELATIONSHIP_4` (`ID_USER`),
  ADD KEY `FK_RELATIONSHIP_5` (`ID_JNS_LOG_SISTEM`);

--
-- Indexes for table `TT_PAYMENT`
--
ALTER TABLE `TT_PAYMENT`
  ADD PRIMARY KEY (`ID_PAYMENT`),
  ADD KEY `FK_RELATIONSHIP_14` (`ID_JNS_PAYMENT`),
  ADD KEY `FK_RELATIONSHIP_16` (`ID_PAYMENT_STATUS`);

--
-- Indexes for table `TT_ROOM_RATE`
--
ALTER TABLE `TT_ROOM_RATE`
  ADD PRIMARY KEY (`ID_ROOM_RATE`),
  ADD KEY `FK_ID_ROOM` (`ID_ROOM`) USING BTREE;

--
-- Indexes for table `TT_ROOM_RENT`
--
ALTER TABLE `TT_ROOM_RENT`
  ADD PRIMARY KEY (`ID_ROOM_RENT`),
  ADD KEY `FK_RELATIONSHIP_13` (`ID_GUEST`),
  ADD KEY `FK_RELATIONSHIP_15` (`ID_PAYMENT`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `TD_EMPLOYEE`
--
ALTER TABLE `TD_EMPLOYEE`
  MODIFY `ID_EMPLOYEE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `TD_GUEST`
--
ALTER TABLE `TD_GUEST`
  MODIFY `ID_GUEST` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `TD_ROOM`
--
ALTER TABLE `TD_ROOM`
  MODIFY `ID_ROOM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `TD_USER`
--
ALTER TABLE `TD_USER`
  MODIFY `ID_USER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `TT_LOG_ROOM`
--
ALTER TABLE `TT_LOG_ROOM`
  MODIFY `ID_LOG_ROOM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `TT_PAYMENT`
--
ALTER TABLE `TT_PAYMENT`
  MODIFY `ID_PAYMENT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `TT_ROOM_RATE`
--
ALTER TABLE `TT_ROOM_RATE`
  MODIFY `ID_ROOM_RATE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `TT_ROOM_RENT`
--
ALTER TABLE `TT_ROOM_RENT`
  MODIFY `ID_ROOM_RENT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `TD_EMPLOYEE`
--
ALTER TABLE `TD_EMPLOYEE`
  ADD CONSTRAINT `fk_id_jns_employee` FOREIGN KEY (`ID_JNS_EMPLOYEE`) REFERENCES `TR_JNS_EMPLOYEE` (`ID_JNS_EMPLOYEE`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TD_GUEST`
--
ALTER TABLE `TD_GUEST`
  ADD CONSTRAINT `fk_id_jns_identitas` FOREIGN KEY (`ID_JNS_IDENTITAS`) REFERENCES `TR_JNS_IDENTITAS` (`ID_JNS_IDENTITAS`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TD_ROOM`
--
ALTER TABLE `TD_ROOM`
  ADD CONSTRAINT `fk_id_jns_room` FOREIGN KEY (`ID_JNS_ROOM`) REFERENCES `TR_JNS_ROOM` (`ID_JNS_ROOM`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TD_USER`
--
ALTER TABLE `TD_USER`
  ADD CONSTRAINT `fk_id_employee` FOREIGN KEY (`ID_EMPLOYEE`) REFERENCES `TD_EMPLOYEE` (`ID_EMPLOYEE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_jns_user` FOREIGN KEY (`ID_JNS_USER`) REFERENCES `TR_JNS_USER` (`ID_JNS_USER`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TT_LOG_ROOM`
--
ALTER TABLE `TT_LOG_ROOM`
  ADD CONSTRAINT `fk_id_jns_log_room` FOREIGN KEY (`ID_JNS_LOG_ROOM`) REFERENCES `TR_JNS_LOG_ROOM` (`ID_JNS_LOG_ROOM`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_room_rate` FOREIGN KEY (`ID_ROOM_RATE`) REFERENCES `TT_ROOM_RATE` (`ID_ROOM_RATE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_room_rent` FOREIGN KEY (`ID_ROOM_RENT`) REFERENCES `TT_ROOM_RENT` (`ID_ROOM_RENT`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `TT_LOG_SISTEM`
--
ALTER TABLE `TT_LOG_SISTEM`
  ADD CONSTRAINT `fk_id_jns_log_sistem` FOREIGN KEY (`ID_JNS_LOG_SISTEM`) REFERENCES `TR_JNS_LOG_SISTEM` (`ID_JNS_LOG_SISTEM`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`ID_USER`) REFERENCES `TD_USER` (`ID_USER`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TT_PAYMENT`
--
ALTER TABLE `TT_PAYMENT`
  ADD CONSTRAINT `fk_id_jns_payment` FOREIGN KEY (`ID_JNS_PAYMENT`) REFERENCES `TR_JNS_PAYMENT` (`ID_JNS_PAYMENT`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_payment_status` FOREIGN KEY (`ID_PAYMENT_STATUS`) REFERENCES `TR_PAYMENT_STATUS` (`ID_PAYMENT_STATUS`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `TT_ROOM_RATE`
--
ALTER TABLE `TT_ROOM_RATE`
  ADD CONSTRAINT `fk_id_rooms` FOREIGN KEY (`ID_ROOM`) REFERENCES `TD_ROOM` (`ID_ROOM`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `TT_ROOM_RENT`
--
ALTER TABLE `TT_ROOM_RENT`
  ADD CONSTRAINT `fk_id_guest` FOREIGN KEY (`ID_GUEST`) REFERENCES `TD_GUEST` (`ID_GUEST`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_payment` FOREIGN KEY (`ID_PAYMENT`) REFERENCES `TT_PAYMENT` (`ID_PAYMENT`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
