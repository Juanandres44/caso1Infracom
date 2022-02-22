package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Principal{
	
	
	
    
    
    public static void main(String[] args) throws InterruptedException, IOException{
    	
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        System.out.println("Por favor ingrese el numero de mensajes a transmitir");

        String res = br.readLine();
        
        int total = Integer.parseInt(res);
        
        File file = new File("C:\\Users\\USER.DESKTOP-UDHIARP\\OneDrive\\Escritorio\\IFC\\Caso1\\caso1Infracom\\data\\config.txt");

        // scan is the object of scanner class. //
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
        
        
    	
    	
    	
    	
        Buffer bufferA = new Buffer(5,total);
        Buffer bufferB = new Buffer(4,total);
        Buffer bufferC = new Buffer(2,total);
        Buffer bufferD = new Buffer(4,total);

        Proceso productor1 = new Proceso(1, bufferA,bufferD, 20, "true","false", total);
        Proceso productor2 = new Proceso(2, bufferB,bufferA, 40, "true","true", 0);
        Proceso productor3 = new Proceso(3, bufferC,bufferB, 60, "false","true", 0);
        Proceso productor4 = new Proceso(4, bufferD,bufferC, 80, "false","false", 0);


        productor1.start();
        productor2.start();
        productor3.start();
        productor4.start();
    }
   }