-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.3.7-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bd_clientes
CREATE DATABASE IF NOT EXISTS `bd_estilobga` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_estiloBGA`;

-- Volcando estructura para tabla bd_clientes.cliente
CREATE TABLE IF NOT EXISTS `cita` (
  `idCita` int(100) NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(100) DEFAULT NULL,
  `nombre_estilista` varchar(100) DEFAULT NULL,
  `servicio` varchar(100) DEFAULT NULL,
  `fecha` DATE DEFAULT NULL,
  `precio_servicio` int(7) DEFAULT NULL,
  `promocion` BOOLEAN,
  PRIMARY KEY (`idcita`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_clientes.cliente: ~34 rows (aproximadamente)
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` (`idcita`, `nombre_cliente`, `nombre_estilista`, `servicio`, `fecha`, `precio_servicio`,`promocion`) VALUES
(1, 'Carloina Giraldo','Daniela Rueda','Aminoshot','2025-03-12',280000,0),
(2, 'Maria Suarez','Daniela Rueda','Hidratacion','2025-04-22',210000,0),
(3, 'Camia Quiroga','Andrea Jaimes','keratina','2025-01-15',420000,1);

    
/*!40000 ALTER TABLE `cita`ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;