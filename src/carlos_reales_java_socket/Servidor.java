/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author aisca
 */
public class Servidor extends Conexion {
    
    public Servidor() throws IOException{
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    public void iniciarServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            clientSock = serverSock.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");
            System.out.println("Inciando calculadora");
            calculadora();
        }catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public void calculadora(){
        try{
             //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(clientSock.getOutputStream());
            entradaCliente = new DataInputStream(clientSock.getInputStream());
            //Se le envía un mensaje al cliente usando su flujo de salida
            int tipo = entradaCliente.readInt();
            int n1 = 0, n2 = 0;
            
            switch(tipo){
                case 1:
                    salidaCliente.writeUTF("Has elegido suma");
                    salidaCliente.writeInt(sumar(recibirNumeroUno(n1),recibirNumeroDos(n2)));
                    break;
                case 2:
                    salidaCliente.writeUTF("Has elegido resta");
                    salidaCliente.writeInt(restar( recibirNumeroUno(n1),recibirNumeroDos(n2)));
                    break;
                case 3:
                    salidaCliente.writeUTF("Has elegido multiplicar");
                    salidaCliente.writeInt(multiplicar(recibirNumeroUno(n1),recibirNumeroDos(n2)));
                    break;
                    
                case 4:
                    salidaCliente.writeUTF("Has elegido dividir");
                    salidaCliente.writeInt(dividir(recibirNumeroUno(n1),recibirNumeroDos(n2)));
                    break;
                default:
                    System.out.println("Fin de la conexión");
                    serverSock.close();//Se finaliza la conexión con el cliente
                    
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
            
    }    


        

    
    public int recibirNumeroUno(int n) throws IOException{

        n = entradaCliente.readInt();
        System.out.println("Primer número recibido");

        return n;
    }
        public int recibirNumeroDos(int n) throws IOException{
       
            n = entradaCliente.readInt();
            System.out.println("Segundo número recibido");
            return n;
    }
    public int sumar(int a, int b){
        return a + b;
    }
    
    public int restar(int a, int b){
        return a - b;
    }
    
    public int multiplicar(int a, int b){
        return a * b;
    }
    
    public int dividir(int a, int b){
        return a / b;
    }
}
