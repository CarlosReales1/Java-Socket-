/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author aisca
 */
public class Cliente extends Conexion
{
     
    
   public Cliente() throws IOException{
       super("cliente");
   } //Se usa el constructor para cliente de Conexion

    public void iniciarCliente() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(clientSock.getOutputStream());
            entradaServidor = new DataInputStream(clientSock.getInputStream());
            int tipo = 0;
          
                System.out.println ("Elige la operación a realizar\n" 
                        + "1.Suma\n" + "2.Resta\n" + "3.Multiplicar\n" 
                        + "4.Dividir\n" + "5.Salir\n");
                
                reader = new Scanner(System.in);
                do{
                    System.out.println("Operacion a realizar: ");
                    tipo = reader.nextInt();
                }while (tipo <= 0 || tipo > 5);
                if (tipo == 5){
                    System.out.println("Gracias por utilizar nuestro Servidor Calculadora");
                    clientSock.close();
                }
                else{
                    salidaServidor.writeInt(tipo);
                    //Se enviarán dos mensajes
                    System.out.println(entradaServidor.readUTF());
                    System.out.println("Dame dos numeros\n" + "Numero 1: ");
                    salidaServidor.writeInt(reader.nextInt());
                    System.out.println("Numero 2: ");
                    salidaServidor.writeInt(reader.nextInt());
                    int resultado = entradaServidor.readInt();
                    System.out.println("El resultado es " + resultado);
                    iniciarCliente();
                }
           
           //Fin de la conexión

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
    } 
}
