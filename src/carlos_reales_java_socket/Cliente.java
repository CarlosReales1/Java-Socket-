/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *Clase Cliente, derivada de Conexión que engloba la parte front de la calculadora 
 * que se mostrará al usuario
 * 
 * @author Carlos Reales
 */
public class Cliente extends Conexion
{
     
/**
 * Utilizará el constructor de la clase principal con una string para
 * conectar el cliente con el servidor
 * @throws IOException 
 */
public Cliente() throws IOException{
    super("cliente");
} //Se usa el constructor para cliente de Conexion

/**
 * Inicia el flujo de datos hacia el servidor, genera la parte del menu de la calculadora 
 * que verá el usuario y muestra el resultado proprocionado por el servidor
 */
 public void iniciarCliente()
 {
     try
     {
         //Flujo de datos hacia el servidor
         salidaServidor = new DataOutputStream(clientSock.getOutputStream());
         entradaServidor = new DataInputStream(clientSock.getInputStream());
         int tipo = 0;

             System.out.println ("""
                                 Elige la operación a realizar
                                 1.Suma
                                 2.Resta
                                 3.Multiplicar
                                 4.Salir
                                 """);

             reader = new Scanner(System.in);
             do{
                 System.out.println("Operacion a realizar: ");
                 tipo = reader.nextInt();
             }while (tipo <= 0 || tipo > 4);
             if (tipo == 4){
                 System.out.println("Gracias por utilizar nuestro Servidor Calculadora");
                 clientSock.close(); //Finaliza desde el socket del cliente
             }
             else{
                 salidaServidor.writeInt(tipo);//Envia al servidor el tipo de operación elegida
                 System.out.println(entradaServidor.readUTF());//Recibe del servidor el mensaje informando de la operación elegida
                 System.out.println("Dame dos numeros\n" + "Numero 1: ");
                 salidaServidor.writeInt(reader.nextInt());//Envia al servidor el primer numero
                 System.out.println("Numero 2: ");
                 salidaServidor.writeInt(reader.nextInt());//Envia al servidor el segundo numero
                 int resultado = entradaServidor.readInt();//Recibe del servidor el resultado de la operación
                 System.out.println("\nEl resultado es " + resultado + "\n");
                 iniciarCliente();
             }

 

     }
     catch (IOException|InputMismatchException e)
     {
         System.out.println("Error: " + e.getMessage() + "\n. Fin de la conexion");
         
         
         
         
     }

} 
 /**
  * Metodo que cierra la conexion del cliente
  * @throws IOException 
  */
 public void finConexion()throws IOException{
     clientSock.close();
     System.out.println("Cliente finalizado");
 }
}
