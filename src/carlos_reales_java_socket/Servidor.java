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


        

    /**
     * Funcion  que se encarga de verificar que se está recibiendo correctamente 
     * los números de la operación
     * @param n el numero enviado por el cliente
     * @return 
     * @throws IOException 
     */
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
}
