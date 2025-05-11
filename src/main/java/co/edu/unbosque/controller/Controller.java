package co.edu.unbosque.controller;

import co.edu.unbosque.model.Facada_Model;
import co.edu.unbosque.view.Facada_Vista_View;

public class Controller {
    Facada_Model modelo = new Facada_Model();
    Facada_Vista_View vista = new Facada_Vista_View();
    public Controller(){
        ejecutar();
        
    }
    public void ejecutar(){
        System.out.println("co.edu.unbosque.controller.Controller.ejecutar()");
    }
    
}
