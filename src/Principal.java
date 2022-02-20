public class Principal{
    public static void main(String[] args){
        Buffer bufferA = new Buffer(size,total);
        Buffer bufferB = new Buffer(size,total);
        Buffer bufferC = new Buffer(size,total);
        Buffer bufferD = new Buffer(size,total);

        Proceso productor1 = new Proceso(1, bufferA,bufferD, vel, env,rec, total);
        Proceso productor2 = new Proceso(2, bufferB,bufferA, vel, env,rec, 0);
        Proceso productor3 = new Proceso(3, bufferC,bufferB, vel, env,rec, 0);
        Proceso productor4 = new Proceso(4, bufferD,bufferC, vel, env,rec, 0);


        productor1.start();
        productor2.start();
        productor3.start();
        productor4.start();
    }
}