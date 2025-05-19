package co.edu.unbosque.controller;

import co.edu.unbosque.model.Facada_Model;
import co.edu.unbosque.view.Facada_Vista_View;
import test.*;

public class Controller {
    Facada_Model modelo = new Facada_Model();
    Facada_Vista_View vista = new Facada_Vista_View();
    public Controller(){
        ejecutar();
        
    }
    public void ejecutar(){

        
        /*
        bloque de prueba esta funcionando las clases y conexiones a bases de datos
        modelo.getUsuarios().crearUsuario("ales", "ale12312s", "a");
        System.out.println("usuario:" + modelo.getUsuarios().obtenerUsuarioPorId(4).get().getUsuario() );
        modelo.getUsuarios().actualizarUsuario(4, "ales1", "ales123123123", "usuario");
        System.out.println("usuario:" + modelo.getUsuarios().obtenerUsuarioPorId(4).get().getUsuario() );
        if(modelo.getUsuarios().eliminarUsuario(4)) {
        	System.out.println("eliminao");
        }else {
        	System.out.println("no");
        }
        
        modelo.getProductos().crearProducto("akes", "as", 12,12, 12);
        System.out.println("producto"+ modelo.getProductos().obtenerProductoPorId(1).get().getNombre());
        */

    }
    
}
