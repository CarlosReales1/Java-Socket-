/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlos_reales_java_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *Cuando sea llamada, esta clase llamará a la superclase Conexión pàra crear el 
 * servidor y el cliente
 * @author Carlos Reales
 */
public class Servidor extends Conexion {
    
    /**
     * Utilizará el constructor de la clase principal con la string clave para
     * crear el servidor
     * @throws IOException 
     */
    public Servidor() throws IOException{
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    /**
     * Inicia el servidor usando esperando la conexion del cliente para iniciar
     * la comunicacion
     */
    public void iniciarServer()
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
    
    /**
     * Metodo que crea los flujos de entrada y de salida con el cliente. En 
     * primer lugar se espera para recibir un número entero que segun cual sea
     * llamará a una de las funciones de operaciones matemáticas de esta clase.
     */
    public void calculadora(){
        try{
             //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(clientSock.getOutputStream());
            entradaCliente = new DataInputStream(clientSock.getInputStream());
            
            int tipo = entradaCliente.readInt();//Lee el tipo de operacion enviada por el cliente
            int n1 = 0, n2 = 0;
            float d1, d2;
            
            /*Informa al cliente del tipo de operación elegida y tras pasar por
            la función correspondiente envia al clinete el resultado
            */
            switch(tipo){
                
                case 1:
                    System.out.println("Operacion recibida");
                    salidaCliente.writeUTF("Has elegido suma");
                    n1=entradaCliente.readInt();
                    System.out.println("Primer número recibido(" + n1 +")");
                    n2=entradaCliente.readInt();
                    System.out.println("Segundo número recibido(" + n2 +")");
                    salidaCliente.writeInt(sumar(n1,n2));
                    System.out.println("Fin de la operación");
                    calculadora();
                    break;
                case 2:
                    System.out.println("Operacion recibida");
                    salidaCliente.writeUTF("Has elegido resta");
                    n1=entradaCliente.readInt();
                    System.out.println("Primer número recibido(" + n1 +")");
                    n2=entradaCliente.readInt();
                    System.out.println("Segundo número recibido(" + n2 +")");
                    salidaCliente.writeInt(restar(n1,n2));
                    System.out.println("Fin de la operación");
                    calculadora();
                    break;
                case 3:
                    System.out.println("Operacion recibida");
                    salidaCliente.writeUTF("Has elegido multiplicar");
                    n1=entradaCliente.readInt();
                    System.out.println("Primer número recibido(" + n1 +")");
                    n2=entradaCliente.readInt();
                    System.out.println("Segundo número recibido(" + n2 +")");
                    salidaCliente.writeInt(multiplicar(n1,n2));
                    System.out.println("Fin de la operación");
                    calculadora();
                    break;

                default:
                    System.out.println("Fin de la conexión");
                    serverSock.close();//Se finaliza la conexión con el cliente
                    
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
           
        
    }        
  
    /**
     * Funcion que realiza la suma de los números y devuelve el resultado
     * @param a
     * @param b
     * @return 
     */
    public int sumar(int a, int b){
        return a + b;
    }
    
    /**
     * Funcion que realiza la resta de los números y devuelve el resultado
     * @param a
     * @param b
     * @return 
     */
    public int restar(int a, int b){
        return a - b;
    }
    
        /**
     * Funcion que realiza la multiplicación de los números y devuelve el resultado
     * @param a
     * @param b
     * @return 
     */
    public int multiplicar(int a, int b){
        return a * b;
    }
    
        /**
     * Funcion que realiza la división de los números y devuelve el resultado
     * @param a
     * @param b
     * @return 
     */
    public int dividir(int a, int b){
        return a / b;
    }
    
    /**
  * Metodo que cierra la conexion del servidor
  * @throws IOException 
  */
 public void finConexion()throws IOException{
     serverSock.close();
      System.out.println("Servidor finalizado");
 }
}
