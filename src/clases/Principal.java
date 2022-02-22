package clases;
public class Principal{

    
    public static void main(String[] args){
        Buffer bufferA = new Buffer(5,15);
        Buffer bufferB = new Buffer(4,15);
        Buffer bufferC = new Buffer(2,15);
        Buffer bufferD = new Buffer(4,15);

        Proceso productor1 = new Proceso(1, bufferA,bufferD, 20, "true","false", 15);
        Proceso productor2 = new Proceso(2, bufferB,bufferA, 40, "true","true", 0);
        Proceso productor3 = new Proceso(3, bufferC,bufferB, 60, "false","true", 0);
        Proceso productor4 = new Proceso(4, bufferD,bufferC, 80, "false","false", 0);


        productor1.start();
        productor2.start();
        productor3.start();
        productor4.start();
    }
}