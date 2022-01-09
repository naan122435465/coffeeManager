-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2022 at 12:41 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffee`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `all_item` ()  BEGIN
SELECT i.id, i.id_order, i.ten_mon,i.so_luong,i.discount,i.tong, o.ngay_order, n.ho_ten 
FROM order_item i
INNER JOIN orders o  on o.id = i.id_order
INNER JOIN nhan_vien n on o.id_nv =n.id
ORDER BY o.ngay_order;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `chi_phi_nguyen_lieu` (IN `thang` INT(45), IN `nam` INT, OUT `chi_phi` INT)  BEGIN
SELECT
sum(gia*so_luong) INTO @tong
FROM nguyen_lieu
WHERE MONTH(ngay_nhap) = thang and YEAR(ngay_nhap) = nam

GROUP BY MONTH(ngay_nhap);
if(@tong is null) then set @tong  = 0; end if ;
set chi_phi = @tong;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `chi_phi_nhan_vien` (OUT `doanh_thu` INT)  BEGIN
SELECT
sum(luong) INTO @tong
FROM nhan_vien;
set doanh_thu = @tong;
if(@tong is null) then set @tong  = 0; end if ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `chi_phi_thang` (IN `thang` INT, IN `nam` INT, OUT `cp` INT)  BEGIN
SELECT
sum(gia*so_luong) INTO @cpnl
FROM nguyen_lieu
WHERE MONTH(ngay_nhap) = thang and YEAR(ngay_nhap) = nam
GROUP BY MONTH(ngay_nhap);
SELECT
sum(luong) INTO @cpnv
FROM nhan_vien;
SET cp = @cpnl+ @cpnv;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `doanh_thu_nam` ()  BEGIN
SELECT Year(ngay_order) year,
SUM(tong)tong
FROM orders o
INNER JOIN order_item i on i.id_order = o.id
GROUP BY  year(ngay_order)
ORDER BY year(ngay_order);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `doanh_thu_thang` (IN `thang` INT, IN `nam` INT, OUT `doanh_thu` INT)  BEGIN
SELECT 
SUM(tong)INTO @tong
FROM orders o
INNER JOIN order_item i on i.id_order = o.id
WHERE MONTH(ngay_order) = thang 
and YEAR(ngay_order) = nam
GROUP BY  MONTH(ngay_order);
if(@tong is null) then set @tong  = 0; end if ;
set doanh_thu = @tong;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dt` (OUT `ln` INT)  BEGIN
SELECT Year(ngay_order) year,
SUM(tong)INTO@tongdt
FROM orders o
INNER JOIN order_item i on i.id_order = o.id
GROUP BY  year(ngay_order)
ORDER BY year(ngay_order);
SELECT YEAR(ngay_nhap)year ,
sum(gia*so_luong)into @tongcp
FROM nguyen_lieu;
SELECT ln = @tongdt -@tongcp;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ln_thang` (IN `thang` INT, IN `nam` INT, OUT `ln` INT)  BEGIN
CALL doanh_thu_thang(thang,nam,@dt);
CALL chi_phi_thang(thang,nam,@cp);
if(@dt is null) then set @dt  = 0; end if ;
if(@cp is null) then set @cp  = 0; end if ;
SET ln = ( @dt - @cp);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `name` VARCHAR(45), IN `pass` VARCHAR(45))  BEGIN
SELECT *FROM users WHERE 	username = name and password = pass;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_add` (IN `ten_mon` VARCHAR(45), IN `gia` INT)  BEGIN
CALL menu_id(@id);
INSERT menu VALUES (@id, ten_mon, gia );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_all` ()  BEGIN 
SELECT * FROM menu ORDER BY ten_mon  ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM menu
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_gia` (IN `p_ten_mon` VARCHAR(45), OUT `p_gia` INT)  BEGIN
SELECT gia INTO @gia1 
FROM menu
WHERE ten_mon = p_ten_mon ;
set p_gia = @gia1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_id` (OUT `p_id` INT)  BEGIN
SELECT MAX(id) INTO @max
FROM menu;
if(@max is null) then set @max  = 0; end if ;
set p_id = @max + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_ten_all` ()  BEGIN
SELECT ten_mon
FROM menu
ORDER by ten_mon;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menu_update` (IN `p_id` INT, IN `p_ten_mon` VARCHAR(45), IN `p_gia` INT)  BEGIN
UPDATE menu SET   ten_mon=p_ten_mon, gia=p_gia	
WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_add` (IN `tenNL` VARCHAR(45), IN `gia` INT, IN `so_luong` INT, IN `ngay_nhap` DATE)  BEGIN
CALL nguyen_lieu_id(@id);
INSERT nguyen_lieu VALUES ( @id,tenNL	,gia,	so_luong	,ngay_nhap	
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_all` ()  BEGIN 
SELECT * FROM nguyen_lieu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM nguyen_lieu
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_id` (OUT `p_id` INT)  BEGIN
SELECT MAX(id) INTO @max
FROM nguyen_lieu;
if(@max is null) then set @max  = 0; end if ;
set p_id = @max + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_ngay` (IN `ngay` DATE, OUT `tong` INT)  BEGIN
SELECT SUM(gia) INTO @NL
FROM nguyen_lieu WHERE ngay_nhap = ngay;
SET tong = @NL;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_search` (IN `date1` DATE, IN `date2` DATE)  BEGIN
SELECT * FROM nguyen_lieu
WHERE ngay_nhap BETWEEN date1 AND date2;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_time` (IN `begin_date` INT, IN `end_date` INT, OUT `tong` INT)  BEGIN
SELECT sum(gia) into @tNL
FROM nguyen_lieu
WHERE ngay_nhap BETWEEN begin_date  AND end_date ;
set tong = @tNL;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nguyen_lieu_update` (IN `p_id` INT, IN `p_tenNL` VARCHAR(45), IN `p_gia` INT, IN `p_so_luong` INT, IN `p_ngay_nhap` DATE)  BEGIN
UPDATE nguyen_lieu SET  tenNL = p_tenNL	, gia = p_gia ,	 so_luong = p_so_luong , ngay_nhap = p_ngay_nhap 
WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_add` (IN `ho_ten` VARCHAR(45), IN `ngay_sinh` DATE, IN `chuc_vu` VARCHAR(45), IN `luong` INT)  BEGIN
CALL nhan_vien_id(@id);
INSERT nhan_vien VALUES ( @id,	ho_ten,	ngay_sinh,chuc_vu	,luong	
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_all` ()  BEGIN 
SELECT * FROM nhan_vien;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM nhan_vien
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_getById` (IN `p_id` INT)  BEGIN
SELECT * FROM nhan_vien WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_id` (OUT `p_id` INT)  BEGIN
SELECT MAX(id) INTO @max
FROM nhan_vien;
if(@max is null) then set @max  = 0; end if ;
set p_id = @max + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_tenall` ()  BEGIN 
SELECT ho_ten
FROM nhan_vien;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nhan_vien_update` (IN `p_id` INT, IN `p_ho_ten` VARCHAR(45), IN `p_ngay_sinh` DATE, IN `p_chuc_vu` VARCHAR(45), IN `p_luong` INT)  BEGIN
UPDATE nhan_vien SET ho_ten =p_ho_ten,
ngay_sinh =p_ngay_sinh	, chuc_vu =p_chuc_vu  ,	
 luong = p_luong 	
WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_add` (IN `id_nv` INT, IN `ngay_order` DATE, OUT `id_order` INT)  BEGIN
CALL orders_id(@id);
INSERT orders VALUES ( @id, id_nv,	ngay_order	
);
SET id_order = @id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_all` ()  BEGIN 
SELECT * FROM orders;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM orders
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_getById` (IN `p_id` INT)  BEGIN
SELECT * FROM orders WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_id` (OUT `p_id` INT)  BEGIN
SELECT MAX(id) INTO @max
FROM orders;
if(@max is null) then set @max  = 0; end if ;
set p_id = @max + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orders_update` (IN `p_id` INT, IN `p_id_nv` INT, IN `p_ngay_order` DATE)  BEGIN
UPDATE orders SET 	p_id_nv = id_nv	,
p_ngay_order = ngay_order	
 
WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_getBydate` (IN `date` DATE)  BEGIN
SELECT * FROM orders
WHERE ngay_order = date;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_getByTime` (IN `begin_date` DATE, IN `end_date` DATE)  BEGIN
SELECT i.id, i.id_order, i.ten_mon,i.so_luong,i.discount,i.tong, o.ngay_order, n.ho_ten 
FROM order_item i
INNER JOIN orders o  on o.id = i.id_order
INNER JOIN nhan_vien n on o.id_nv =n.id
WHERE ngay_order BETWEEN begin_date  AND end_date
ORDER BY o.ngay_order;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_add` (IN `id_order` INT, IN `ten_mon` VARCHAR(45), IN `so_luong` INT, IN `discount` DECIMAL)  BEGIN
CALL order_item_id(@id);
CALL menu_gia(ten_mon, @gia);

SET @tong = @gia * so_luong * discount;
INSERT order_item VALUES ( @id,id_order,	ten_mon	,so_luong	,discount,	@tong);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_all` ()  BEGIN 
SELECT * FROM order_item;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM order_item
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_getById` (IN `p_id` INT)  BEGIN
SELECT * FROM order_item WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_getByOrderID` (IN `p_id_order` INT)  BEGIN
SELECT id, ten_mon,so_luong,	tong
FROM order_item WHERE id_order = p_id_order ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_id` (OUT `p_id` INT)  BEGIN
SELECT MAX(id) INTO @max
FROM order_item;
if(@max is null) then set @max  = 0; end if ;
set p_id = @max + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_tongdon` (OUT `bill` INT, IN `p_id_order` INT)  BEGIN
SELECT SUM(tong) INTO @tong
FROM order_item WHERE id_order = p_id_order;
SET bill = @tong;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_item_update` (IN `p_id` INT, IN `p_id_order` INT, IN `p_ten_mon` VARCHAR(45), IN `p_so_luong` INT, IN `p_discount` DECIMAL, IN `p_tong` INT)  BEGIN
UPDATE order_item SET p_id_order=id_order , p_ten_mon	=	ten_mon	, p_so_luong = so_luong	,p_discount = discount	, p_tong =tong	

WHERE id = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tien_luong_thang` (OUT `luong1` INT)  BEGIN
SELECT sum(luong) INTO @thang
FROM nhan_vien;
set luong1 = @thang;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `users_getById` (IN `p_id` INT)  BEGIN
SELECT * FROM users WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `users_id` (OUT `p_id` INT)  BEGIN
SELECT max(id) INTO @new 
from users;
if(@new is null) then set @new  = 0; end if ;
set p_id = @new + 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_add` (IN `username` VARCHAR(45), IN `password` VARCHAR(100), IN `id_nv` INT, IN `role` INT)  BEGIN
CALL users_id(@id);
INSERT users VALUES ( @id,username,password,id_nv, role);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_all` ()  BEGIN 
SELECT * FROM users;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_delete` (IN `p_id` INT)  BEGIN
DELETE 
FROM users
WHERE id = p_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_update` (IN `p_id` INT, IN `p_username` VARCHAR(45), IN `p_password` VARCHAR(100), IN `p_id_nv` INT, IN `p_role` INT)  BEGIN
UPDATE users SET username =  p_username, 
password = p_password , id_nv =p_id_nv, role =p_role 
WHERE id = p_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `ten_mon` varchar(45) NOT NULL,
  `gia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `ten_mon`, `gia`) VALUES
(1, 'coc', 20),
(3, 'ca phe', 30);

-- --------------------------------------------------------

--
-- Table structure for table `nguyen_lieu`
--

CREATE TABLE `nguyen_lieu` (
  `id` int(11) NOT NULL,
  `tenNL` varchar(45) NOT NULL,
  `gia` int(11) NOT NULL,
  `so_luong` decimal(10,0) NOT NULL,
  `ngay_nhap` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nguyen_lieu`
--

INSERT INTO `nguyen_lieu` (`id`, `tenNL`, `gia`, `so_luong`, `ngay_nhap`) VALUES
(2, 'ca phe', 5, '8', '2017-12-13'),
(4, 'muối', 56, '2', '2022-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `id` int(11) NOT NULL,
  `ho_ten` varchar(45) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `chuc_vu` varchar(45) NOT NULL,
  `luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `ho_ten`, `ngay_sinh`, `chuc_vu`, `luong`) VALUES
(1, 'na', '2021-12-16', 'na', 1),
(2, 'update2', '2007-12-03', 'na', 10),
(3, 'nanan', '2007-12-03', 'na', 10),
(7, 'nanan', '2007-12-03', 'na', 10),
(8, 'nanan', '2007-12-03', 'na', 10),
(9, 'nanan', '2007-12-03', 'na', 10),
(10, 'nanan', '2007-12-03', 'na', 10),
(12, 'nanan', '2007-12-03', 'na', 10),
(16, 'nanan', '2007-12-03', 'na', 10),
(18, 'nanan', '2007-12-03', 'na', 10),
(24, 'nanan', '2007-12-03', 'na', 10),
(30, 'nanan', '2007-12-03', 'na', 10),
(31, 'na', '2007-12-03', '', 10),
(32, 'na', '2007-12-03', '', 10),
(33, 'vhj', '2002-12-03', '', 10);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `id_nv` int(11) NOT NULL,
  `ngay_order` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `id_nv`, `ngay_order`) VALUES
(1, 1, '2021-12-30'),
(2, 2, '2022-05-01'),
(3, 1, '2022-05-01'),
(4, 1, '2022-05-01'),
(5, 1, '2022-05-01'),
(6, 1, '2022-01-06'),
(7, 1, '2022-01-06'),
(8, 1, '2022-01-06'),
(9, 1, '2022-01-06'),
(10, 1, '2022-01-06'),
(11, 1, '2022-01-06'),
(12, 1, '2022-01-06'),
(13, 1, '2022-01-06'),
(14, 1, '2022-01-06'),
(15, 1, '2022-01-06'),
(16, 1, '2022-01-06'),
(17, 1, '2022-01-06'),
(18, 1, '2022-01-06'),
(19, 1, '2022-01-06'),
(20, 1, '2022-01-06'),
(21, 1, '2022-01-06'),
(22, 1, '2022-01-06'),
(23, 1, '2022-01-06'),
(24, 1, '2022-01-06'),
(25, 1, '2022-01-06'),
(26, 1, '2022-01-06'),
(27, 1, '2022-01-06'),
(28, 1, '2022-01-06'),
(29, 1, '2022-01-06'),
(30, 1, '2022-01-06'),
(31, 1, '2022-01-06'),
(32, 1, '2022-01-06'),
(33, 1, '2022-01-06'),
(34, 1, '2022-01-06'),
(35, 1, '2022-01-06'),
(36, 1, '2022-01-06'),
(37, 1, '2022-01-06'),
(38, 1, '2022-01-06'),
(39, 1, '2022-01-06'),
(40, 1, '2022-01-06'),
(41, 1, '2022-01-07'),
(42, 1, '2022-01-07'),
(43, 2, '2022-01-07'),
(44, 2, '2022-01-07'),
(45, 2, '2022-01-07'),
(46, 2, '2022-01-07'),
(47, 2, '2022-01-07'),
(48, 2, '2022-01-07'),
(49, 2, '2022-01-07'),
(50, 2, '2022-01-07'),
(51, 1, '2022-05-01'),
(52, 2, '2022-01-08'),
(53, 2, '2022-01-08'),
(54, 2, '2022-01-08'),
(55, 2, '2022-01-08'),
(56, 2, '2022-01-08'),
(57, 2, '2022-01-08'),
(58, 2, '2022-01-08'),
(59, 2, '2022-01-08'),
(60, 2, '2022-01-08'),
(61, 2, '2022-01-08'),
(62, 2, '2022-01-08'),
(63, 2, '2022-01-08'),
(64, 2, '2022-01-08'),
(65, 2, '2022-01-08'),
(66, 2, '2022-01-08'),
(67, 2, '2022-01-08'),
(68, 2, '2022-01-08'),
(69, 2, '2022-01-08'),
(70, 2, '2022-01-08'),
(71, 2, '2022-01-08'),
(72, 2, '2022-01-08'),
(73, 2, '2022-01-08'),
(74, 2, '2022-01-08'),
(75, 2, '2022-01-08'),
(76, 2, '2022-01-08'),
(77, 2, '2022-01-08'),
(78, 2, '2022-01-08'),
(79, 2, '2022-01-08'),
(80, 2, '2022-01-08'),
(81, 2, '2022-01-08'),
(82, 2, '2022-01-08'),
(83, 2, '2022-01-08'),
(84, 2, '2022-01-08'),
(85, 2, '2022-01-08'),
(86, 2, '2022-01-08'),
(87, 2, '2022-01-08'),
(88, 2, '2022-01-09'),
(89, 2, '2022-01-09'),
(90, 2, '2022-01-09'),
(91, 2, '2022-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `ten_mon` varchar(45) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `discount` decimal(10,0) DEFAULT 1,
  `tong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `id_order`, `ten_mon`, `so_luong`, `discount`, `tong`) VALUES
(2, 1, 'coc', 2, '1', 240),
(3, 15, 'ca phe', 1, '1', 30),
(4, 16, 'ca phe', 1, '1', 30),
(5, 17, 'ca phe', 1, '0', 0),
(6, 17, 'ca phe', 1, '0', 0),
(7, 17, 'ca phe', 1, '0', 0),
(8, 17, 'ca phe', 2, '0', 0),
(9, 17, 'ca phe', 2, '1', 60),
(10, 18, 'ca phe', 1, '99', 2970),
(11, 18, 'ca phe', 1, '99', 2970),
(12, 19, 'ca phe', 1, '99', 2970),
(13, 19, 'ca phe', 1, '99', 2970),
(14, 19, 'ca phe', 1, '99', 2970),
(15, 19, 'ca phe', 1, '99', 2970),
(16, 24, 'ca phe', 1, '99', 2970),
(18, 34, 'ca phe', 1, '99', 2970),
(19, 36, 'ca phe', 1, '99', 2970),
(20, 42, 'ca phe', 1, '99', 2970),
(21, 42, 'ca phe', 1, '99', 2970),
(22, 42, 'ca phe', 1, '99', 2970),
(23, 42, 'coc', 1, '99', 1980),
(24, 68, 'ca phe', 1, '1', 30),
(25, 68, 'ca phe', 1, '1', 30),
(26, 68, 'ca phe', 1, '1', 30),
(27, 68, 'ca phe', 1, '1', 30),
(28, 68, 'ca phe', 1, '1', 30),
(29, 68, 'ca phe', 1, '1', 30),
(30, 69, 'ca phe', 1, '1', 30),
(31, 69, 'ca phe', 1, '1', 30),
(32, 70, 'ca phe', 1, '1', 30),
(33, 70, 'ca phe', 1, '1', 30),
(34, 71, 'ca phe', 1, '1', 30),
(35, 71, 'ca phe', 1, '1', 30),
(36, 72, 'ca phe', 1, '1', 30),
(37, 72, 'ca phe', 1, '1', 30),
(38, 73, 'ca phe', 1, '1', 30),
(39, 73, 'ca phe', 1, '1', 30),
(40, 74, 'ca phe', 1, '1', 30),
(41, 74, 'ca phe', 1, '1', 30),
(43, 75, 'ca phe', 1, '1', 30),
(44, 76, 'ca phe', 1, '1', 30),
(45, 76, 'ca phe', 1, '1', 30),
(46, 77, 'ca phe', 1, '1', 30),
(47, 77, 'ca phe', 1, '1', 30),
(48, 78, 'ca phe', 1, '1', 30),
(49, 78, 'ca phe', 1, '1', 30),
(50, 79, 'ca phe', 1, '1', 30),
(51, 79, 'ca phe', 1, '1', 30),
(52, 80, 'ca phe', 1, '1', 30),
(53, 80, 'ca phe', 1, '1', 30),
(54, 81, 'ca phe', 1, '1', 30),
(55, 81, 'ca phe', 1, '1', 30),
(56, 82, 'ca phe', 1, '1', 30),
(57, 82, 'ca phe', 1, '1', 30),
(58, 84, 'ca phe', 1, '1', 30),
(59, 84, 'ca phe', 1, '1', 30),
(60, 84, 'ca phe', 1, '1', 30),
(61, 85, 'ca phe', 1, '1', 30),
(62, 85, 'ca phe', 1, '1', 30),
(63, 85, 'ca phe', 1, '1', 30),
(64, 86, 'ca phe', 1, '1', 30),
(65, 86, 'ca phe', 1, '1', 30),
(66, 86, 'ca phe', 1, '1', 30),
(67, 87, 'ca phe', 1, '1', 30),
(68, 88, 'ca phe', 1, '1', 30),
(69, 89, 'ca phe', 1, '1', 30),
(70, 89, 'ca phe', 1, '1', 30),
(71, 90, 'coc', 1, '1', 20),
(72, 90, 'coc', 1, '1', 20),
(73, 90, 'coc', 1, '1', 20),
(74, 91, 'ca phe', 1, '1', 30);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_nv` int(11) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `id_nv`, `role`) VALUES
(1, 'nana', '1234', 1, 0),
(2, 'bao', '4567', 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nguyen_lieu`
--
ALTER TABLE `nguyen_lieu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nv` (`id_nv`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_order` (`id_order`),
  ADD KEY `ten_mon` (`ten_mon`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nv` (`id_nv`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_nv`) REFERENCES `nhan_vien` (`id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_nv`) REFERENCES `nhan_vien` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
