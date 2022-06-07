/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author aisca
 */
public class Conexion {
   
    private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket serverSock; //Socket del servidor
    protected Socket clientSock; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente;
    protected DataInputStream entradaServidor, entradaCliente;
    protected Scanner reader;//Flujo de datos de salida
    

    public Conexion(String tipo) throws IOException //Constructor
    {
        if(tipo.equalsIgnoreCase("servidor"))
        {
            serverSock = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            clientSock = new Socket(); //Socket para el cliente
        }
        else
        {
            clientSock = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
 
}
