public class Consumidor extends Thread{
    private int id;
    private Buffer buf;
    private int vel;
    private String rec;

    public Consumidor(int id, Buffer buf, int vel, String rec){
        this.id = id;
        this.buf = buf;
        this.vel = vel;
        this.rec = rec;
    }


    private void reciboMensaje(String message){
            String prot ="";
            System.out.println(String.format(prot,this.id,message));
    }

    
    public void run(){
        while(this.buf.hasMessages()){
            if(this.rec.equals("true")){
                String message = this.buf.retrieveMessageActive();
                if (message == null){
                    return;
                }
                this.reciboMensaje(message);
            }
            else if(this.rec.equals("false")){
                String message = this.buf.retrieveMessagePasive();
                if (message == null){
                    return;
                }
                this.reciboMensaje(message);
            }
        }
        
    }
}
