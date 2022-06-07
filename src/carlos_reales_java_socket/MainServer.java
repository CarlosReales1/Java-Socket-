/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.IOException;

/**
 *Clase main para iniciar el servidor
 * @author Carlos Reales
 */
public class MainServer {

    /**
     * Crea una instancia servidor y utiliza el metodo iniciarServer
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Servidor serv = new Servidor(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.iniciarServer(); //Se inicia el servidor
    }
    
}
