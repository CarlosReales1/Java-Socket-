/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.IOException;

/**
 *Clase main para iniciar el cliente
 * @author Carlos Reales
 */
public class MainClient {

    /**
     * Crea una instancia cliente y utiliza el metodo iniciarCliente
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Cliente cli = new Cliente(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.iniciarCliente(); //Se inicia el cliente
       
        
    }
    
}
