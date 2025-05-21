	/*
	 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
	 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
	 */
	package co.edu.unbosque.model;
	
	import java.sql.Connection;
	import java.sql.SQLException;
	
	public class Facada_Model {
		Inventario inventario ;
		Productos productos ;
		Promociones promociones ;
		Proveedores proveedores ;
		TipoP tipoP ;
		Clientes clientes ;
	    Usuarios usuarios ;
	    Facturas facturas ;
	    MoviProveIn moviProveIn ;
	    VentasAnu ventasAnu ;
	    Dfactura dfactura ;
	    Movi_Invento movi_Invento ;
	    Fiado fiado ;
	    MovimientoDevo movimientoDevo;
	    DevoProvedo devoProvedo ;
	  
	    
		public Facada_Model() {
			try {
				// Primero establecer la conexión
				Connection conexion = DatabaseManager.getConnection();
				
				// Luego inicializar todos los componentes
				this.inventario = new Inventario();
				this.productos = new Productos();
				this.promociones = new Promociones();
				this.proveedores = new Proveedores();
				this.tipoP = new TipoP();
				this.clientes = new Clientes();
				this.usuarios = new Usuarios();
				this.facturas = new Facturas();
				this.moviProveIn = new MoviProveIn();
				this.ventasAnu = new VentasAnu();
				this.dfactura = new Dfactura();
				this.movi_Invento = new Movi_Invento();
				this.fiado = new Fiado();
				this.movimientoDevo = new MovimientoDevo();
				this.devoProvedo = new DevoProvedo();
				
			} catch (SQLException e) {
				// Manejar el error apropiadamente
				System.err.println("Error al conectar con la base de datos: " + e.getMessage());
				e.printStackTrace();
				// Aquí podrías lanzar una excepción personalizada o manejar el error de otra forma
			}
		}
	
	    //setters y getters
	    
		public Usuarios getUsuarios() {
			return usuarios;
		}
	
		public void setUsuarios(Usuarios usuarios) {
			this.usuarios = usuarios;
		}
	
		public Inventario getInventario() {
			return inventario;
		}
	
		public void setInventario(Inventario inventario) {
			this.inventario = inventario;
		}
	
		public Productos getProductos() {
			return productos;
		}
	
		public void setProductos(Productos productos) {
			this.productos = productos;
		}
	
		public Promociones getPromociones() {
			return promociones;
		}
	
		public void setPromociones(Promociones promociones) {
			this.promociones = promociones;
		}
	
		public Proveedores getProveedores() {
			return proveedores;
		}
	
		public void setProveedores(Proveedores proveedores) {
			this.proveedores = proveedores;
		}
	
		public TipoP getTipoP() {
			return tipoP;
		}
	
		public void setTipoP(TipoP tipoP) {
			this.tipoP = tipoP;
		}
	
		public Clientes getClientes() {
			return clientes;
		}
	
		public void setClientes(Clientes clientes) {
			this.clientes = clientes;
		}
	
		public Facturas getFacturas() {
			return facturas;
		}
	
		public void setFacturas(Facturas facturas) {
			this.facturas = facturas;
		}
	
		public MoviProveIn getMoviProveIn() {
			return moviProveIn;
		}
	
		public void setMoviProveIn(MoviProveIn moviProveIn) {
			this.moviProveIn = moviProveIn;
		}
	
		public VentasAnu getVentasAnu() {
			return ventasAnu;
		}
	
		public void setVentasAnu(VentasAnu ventasAnu) {
			this.ventasAnu = ventasAnu;
		}
	
		public Dfactura getDfactura() {
			return dfactura;
		}
	
		public void setDfactura(Dfactura dfactura) {
			this.dfactura = dfactura;
		}
	
		public Movi_Invento getMovi_Invento() {
			return movi_Invento;
		}
	
		public void setMovi_Invento(Movi_Invento movi_Invento) {
			this.movi_Invento = movi_Invento;
		}
	
		public Fiado getFiado() {
			return fiado;
		}
	
		public void setFiado(Fiado fiado) {
			this.fiado = fiado;
		}
	
		public MovimientoDevo getMovimientoDevo() {
			return movimientoDevo;
		}
	
		public void setMovimientoDevo(MovimientoDevo movimientoDevo) {
			this.movimientoDevo = movimientoDevo;
		}

		public DevoProvedo getDevoProvedo() {
			return devoProvedo;
		}

		public void setDevoProvedo(DevoProvedo devoProvedo) {
			this.devoProvedo = devoProvedo;
		}
	
	    
		
	    
	    
	}
