-- Se crea la tabla Usuario.
-- El atributo TotalCompras se actualizará tras cada inserción de una nueva compra.
CREATE TABLE Usuario (
    ID VARCHAR(20) PRIMARY KEY,
    Password VARCHAR(20),
    TotalCompras DECIMAL
);

-- Se crea la tabla Compra.
-- Tiene una referencia a la PK de Usuario.
CREATE TABLE Compra (
    ID INTEGER PRIMARY KEY,
    ID_Usuario VARCHAR(20),
    Descripcion VARCHAR(20),
    Precio DECIMAL(10, 2),
    Fecha DATE,
    Imagen VARCHAR(999),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID)
);

-- Se insertan datos en la tabla Usuario.
INSERT INTO Usuario (ID, Password, TotalCompras) VALUES
('johndoe', 'pass123', 0),
('janedoe', 'securePwd', 0),
('techlover', 'techPass2024', 0);

-- Creación de un trigger (disparador) llamado ActualizarTotalCompras al insertar una nueva compra.
-- Actualiza el campo TotalCompras para cada compra realizada por el Usuario correspondiente.
CREATE TRIGGER ActualizarTotalCompras
AFTER INSERT ON Compra
REFERENCING NEW AS nuevaCompra
FOR EACH ROW
UPDATE Usuario
SET TotalCompras = TotalCompras + nuevaCompra.Precio
WHERE ID = nuevaCompra.ID_Usuario;

-- Se insertan datos en la tabla Compra.
INSERT INTO Compra (ID, ID_Usuario, Descripcion, Precio, Fecha, Imagen) VALUES
(1, 'johndoe', 'Laptop ASUS', 899.99, '2024-02-24', 'laptop_asus.png'),
(2, 'johndoe', 'Monitor LG 1440p', 329.99, '2024-02-23', 'monitor_lg.png'),
(3, 'janedoe', 'Teclado mecánico', 149.99, '2024-02-22', 'teclado_mecanico.png'),
(4, 'techlover', 'Razer Hyperspeed X', 49.99, '2024-02-24', 'mouse_razer.png'),
(5, 'techlover', 'Disco SSD 1TB', 129.99, '2024-02-23', 'samsung_ssd.png'),
(6, 'johndoe', 'Impresora Canon', 249.99, '2024-02-22', 'impresora_canon.png'),
(7, 'janedoe', 'NVIDIA RTX 4070', 799.99, '2024-02-24', 'nvidia_4070.png'),
(8, 'techlover', 'Razer Barracuda X', 89.99, '2024-02-23', 'razer_barracuda.png'),
(9, 'johndoe', 'Cable USB-C', 7.99, '2024-02-22', 'cable_usbc.png'),
(10, 'janedoe', 'Kingston RAM 16GB', 119.99, '2024-02-24', 'ram_16gb.png'),
(11, 'techlover', 'Webcam ElGato HD', 54.99, '2024-02-23', 'webcam_elgato.png'),
(12, 'johndoe', 'Silla Markus', 179.99, '2024-02-22', 'silla_markus.png'),
(13, 'janedoe', 'Cargador portátil', 29.99, '2024-02-24', 'cargador_portatil.png'),
(14, 'techlover', 'Funda para laptop', 24.99, '2024-02-23', 'funda_laptop.png'),
(15, 'johndoe', 'Televisión 4K', 999.99, '2024-02-22', 'television_4k.png');



