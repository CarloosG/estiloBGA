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

CREATE TABLE Usuario (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    numero_documento VARCHAR(50),
    correo_electronico VARCHAR(100),
    password VARCHAR(255),
    celular VARCHAR(20),
    rol ENUM('admin', 'cliente', 'estilista'),
    fecha_registro DATE
);

CREATE TABLE Cliente (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Estilista (
    estilista_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT UNIQUE,
    anios_experiencia INT,
    especializacion VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Horario_Estilista (
    horario_id INT PRIMARY KEY AUTO_INCREMENT,
    estilista_id INT,
    dia_semana VARCHAR(15),
    intervalo_disponible VARCHAR(50),
    FOREIGN KEY (estilista_id) REFERENCES Estilista(estilista_id)
);

CREATE TABLE Servicio (
    servicio_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_servicio VARCHAR(100),
    condiciones_previas TEXT
);

CREATE TABLE Cita (
    cita_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT,
    estilista_id INT,
    fecha_cita TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id),
    FOREIGN KEY (estilista_id) REFERENCES Estilista(estilista_id)
);

CREATE TABLE Horario_Estilista_Cita (
    horario_id INT,
    cita_id INT,
    PRIMARY KEY (horario_id, cita_id),
    FOREIGN KEY (horario_id) REFERENCES Horario_Estilista(horario_id),
    FOREIGN KEY (cita_id) REFERENCES Cita(cita_id)
);

CREATE TABLE Cita_Servicio (
    cita_id INT,
    servicio_id INT,
    precio_servicio INT,
    tiempo_estimado_servicio INT,
    promocion BOOLEAN,
    PRIMARY KEY (cita_id, servicio_id),
    FOREIGN KEY (cita_id) REFERENCES Cita(cita_id),
    FOREIGN KEY (servicio_id) REFERENCES Servicio(servicio_id)
);

CREATE TABLE Pago (
    pago_id INT PRIMARY KEY AUTO_INCREMENT,
    cita_id INT UNIQUE,
    cliente_id INT,
    fecha_pago DATE,
    metodo_pago ENUM('efectivo', 'tarjeta', 'transferencia'),
    FOREIGN KEY (cita_id) REFERENCES Cita(cita_id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id)
);

CREATE TABLE Reporte (
    reporte_id INT PRIMARY KEY AUTO_INCREMENT,
    estilista_id INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    cantidad_citas INT,
    total_ingresos DECIMAL(10,2),
    servicio_top1 VARCHAR(100),
    fecha_generado TIMESTAMP,
    FOREIGN KEY (estilista_id) REFERENCES Estilista(estilista_id)
);

CREATE TABLE Cita_Reporte (
    cita_id INT,
    reporte_id INT,
    PRIMARY KEY (cita_id, reporte_id),
    FOREIGN KEY (cita_id) REFERENCES Cita(cita_id),
    FOREIGN KEY (reporte_id) REFERENCES Reporte(reporte_id)
);

-- Volcando datos para la bd_estilobga
-- Insertar datos de Usuarios (2 clientes, 2 estilistas, 1 admin)
INSERT INTO Usuario (nombres, apellidos, numero_documento, correo_electronico, password, celular, rol, fecha_registro)
VALUES 
('Ana María', 'Gómez López', '1234567890', 'ana.gomez@email.com', 'password123', '3001234567', 'cliente', '2024-01-15'),
('Carlos', 'Rodríguez Pérez', '0987654321', 'carlos.rodriguez@email.com', 'securepass456', '3109876543', 'cliente', '2024-02-20'),
('Laura', 'Martínez Díaz', '5678901234', 'laura.martinez@salon.com', 'stylistpass789', '3154567890', 'estilista', '2023-11-10'),
('Javier', 'Sánchez Moreno', '4567890123', 'javier.sanchez@salon.com', 'hairpro2024', '3207654321', 'estilista', '2023-12-05'),
('Sofía', 'Torres Vega', '7890123456', 'sofia.torres@salon.com', 'adminpass2024!', '3005551122', 'admin', '2023-10-01');

-- Insertar datos de Clientes
INSERT INTO Cliente (usuario_id)
VALUES 
(1),
(2);

-- Insertar datos de Estilistas
INSERT INTO Estilista (usuario_id, anios_experiencia, especializacion)
VALUES 
(3, 5, 'Colorimetría y cortes'),
(4, 8, 'Peinados y tratamientos capilares');

-- Insertar datos de Horarios de Estilistas
INSERT INTO Horario_Estilista (estilista_id, dia_semana, intervalo_disponible)
VALUES 
(1, 'Lunes', '8:00-12:00'),
(1, 'Martes', '14:00-18:00'),
(1, 'Jueves', '8:00-16:00'),
(2, 'Lunes', '14:00-18:00'),
(2, 'Miércoles', '9:00-17:00');

-- Insertar datos de Servicios
INSERT INTO Servicio (nombre_servicio, condiciones_previas)
VALUES 
('Corte de cabello', 'Cabello limpio preferiblemente'),
('Tinte', 'No lavar el cabello 24 horas antes. Realizar prueba de alergia previa.'),
('Peinado para eventos', 'Cabello limpio y seco'),
('Tratamiento hidratante', 'Sin condiciones previas'),
('Manicure', 'Uñas sin esmalte');

-- Insertar datos de Citas
INSERT INTO Cita (cliente_id, estilista_id, fecha_cita)
VALUES 
(1, 1, '2025-04-10 10:00:00'),
(1, 2, '2025-04-15 15:00:00'),
(2, 1, '2025-04-12 14:30:00'),
(2, 2, '2025-04-14 11:00:00'),
(1, 1, '2025-04-20 09:30:00');

-- Insertar datos de relación Horario_Estilista_Cita
INSERT INTO Horario_Estilista_Cita (horario_id, cita_id)
VALUES 
(1, 1),
(4, 2),
(2, 3),
(5, 4),
(3, 5);

-- Insertar datos de Cita_Servicio
INSERT INTO Cita_Servicio (cita_id, servicio_id, precio_servicio, tiempo_estimado_servicio, promocion)
VALUES 
(1, 1, 30000, 30, false),
(1, 4, 45000, 45, true),
(2, 3, 65000, 60, false),
(3, 2, 90000, 120, false),
(4, 5, 25000, 40, true);

-- Insertar datos de Pagos
INSERT INTO Pago (cita_id, cliente_id, fecha_pago, metodo_pago)
VALUES 
(1, 1, '2025-04-10', 'efectivo'),
(2, 1, '2025-04-15', 'tarjeta'),
(3, 2, '2025-04-12', 'transferencia'),
(4, 2, '2025-04-14', 'efectivo'),
(5, 1, '2025-04-20', 'tarjeta');

-- Insertar datos de Reportes
INSERT INTO Reporte (estilista_id, fecha_inicio, fecha_fin, cantidad_citas, total_ingresos, servicio_top1, fecha_generado)
VALUES 
(1, '2025-04-01', '2025-04-15', 2, 165000.00, 'Corte de cabello', '2025-04-16 08:00:00'),
(2, '2025-04-01', '2025-04-15', 2, 90000.00, 'Peinado para eventos', '2025-04-16 09:30:00'),
(1, '2025-03-01', '2025-03-31', 5, 320000.00, 'Tinte', '2025-04-01 10:15:00'),
(2, '2025-03-01', '2025-03-31', 4, 275000.00, 'Manicure', '2025-04-01 11:45:00'),
(1, '2025-02-01', '2025-02-28', 6, 420000.00, 'Tratamiento hidratante', '2025-03-01 09:00:00');

-- Insertar datos de relación Cita_Reporte
INSERT INTO Cita_Reporte (cita_id, reporte_id)
VALUES 
(1, 1),
(3, 1),
(2, 2),
(4, 2),
(5, 1);

    
/*!40000 ALTER TABLE `cita`ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;