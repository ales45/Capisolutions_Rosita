CREATE TABLE IF NOT EXISTS CategoriaP (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    Categoria VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    idCategoriaP INT,
    descripcion TEXT,
    precio DECIMAL(10, 2),
    IVA DECIMAL(5, 2),
    FOREIGN KEY (idCategoriaP) REFERENCES CategoriaP(idProducto)
);

CREATE TABLE IF NOT EXISTS inventario (
    idInventario INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    stock INT,
    stockMinimo INT,
    ubicacion VARCHAR(100),
    ultimaActualizacion TIMESTAMP,
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS promociones (
    idPromocion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    descuento DECIMAL(5, 2),
    fechaInicio DATE,
    fechaFin DATE,
    idProducto INT,
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS proveedores (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    contacto VARCHAR(100),
    direccion TEXT,
    idProducto INT,
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS movimientosProveedoresINC (
    idMovimientoProveedorINC INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    idInventario INT,
    tipoMovimiento VARCHAR(50),
    cantidad INT,
    fecha DATE,
    motivo VARCHAR(255),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
    FOREIGN KEY (idInventario) REFERENCES inventario(idInventario)
);

CREATE TABLE IF NOT EXISTS movimientosInventarios (
    idMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    tipoMovimiento VARCHAR(50),
    cantidad INT,
    fecha DATE,
    motivo VARCHAR(255),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    rol VARCHAR(50),
    correo VARCHAR(100),
    cedula BIGINT,
    telefono BIGINT
);

CREATE TABLE IF NOT EXISTS facturas (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    fecha DATE,
    estadoPago VARCHAR(50),
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente)
);

CREATE TABLE IF NOT EXISTS ventasAnuladas (
    idAnulacion INT AUTO_INCREMENT PRIMARY KEY,
    idFactura INT,
    motivo TEXT,
    fechaAnulacion DATE,
    idCliente INT,
    FOREIGN KEY (idFactura) REFERENCES facturas(idFactura),
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente)
);

CREATE TABLE IF NOT EXISTS Fiado (
    idFiadoPK INT AUTO_INCREMENT PRIMARY KEY,
    idFactura INT,
    cuotas INT,
    estaPagado BOOLEAN,
    FOREIGN KEY (idFactura) REFERENCES facturas(idFactura)
);

CREATE TABLE IF NOT EXISTS detalleFactura (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idFactura INT,
    idProducto INT,
    idProveedor INT,
    idPromocion INT,
    tipo VARCHAR(50),
    cantidad INT,
    precioUnitario DECIMAL(10, 2),
    Total DECIMAL(10, 2),
    FOREIGN KEY (idFactura) REFERENCES facturas(idFactura),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
    FOREIGN KEY (idProveedor) REFERENCES proveedores(idProveedor),
    FOREIGN KEY (idPromocion) REFERENCES promociones(idPromocion)
);

CREATE TABLE IF NOT EXISTS devolucionesProveedor (
    idDevolucionPK INT AUTO_INCREMENT PRIMARY KEY,
    idProveedor INT,
    idProducto INT,
    cantidad INT,
    motivo VARCHAR(255),
    fecha DATE,
    FOREIGN KEY (idProveedor) REFERENCES proveedores(idProveedor),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS movimientosDevolucion (
    idMovimientoProveedor INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    tipoMovimiento VARCHAR(50),
    cantidad INT,
    fecha DATE,
    motivo VARCHAR(255),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE IF NOT EXISTS Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50),
    contraseña VARCHAR(50),
    acceso VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS HistorialCambios (
    idHistorialCambios INT AUTO_INCREMENT PRIMARY KEY,
    Producto VARCHAR(100),
    Cantidad VARCHAR(50),
    Fecha DATE
);


