public class Proceso extends Thread{
    private int id;
    private Buffer bufEnv;
    private Buffer bufRec;
    private int vel;
    private String env;
    private String rec;
    private int tim;

    public Proceso(int id, Buffer bufEnv, Buffer bufRec, int vel, String env,String rec, int tim){
        this.id = id;
        this.bufEnv = bufEnv;
        this.bufRec = bufRec;
        this.vel = vel;
        this.env = env;
        this.rec = rec;
        this.tim = tim;
    }

    private void envioMensaje(int i) throws InterruptedException{
        if(this.env.equals("true")){
            Thread.sleep(vel);
            this.bufEnv.insertMessageActive(
                "El thread productor: "+this.id+"envia de forma activa el mensaje"
                +i+" de:"+ this.tim + "mensajes"
                );
        }
        else if(this.env.equals("false")){
            Thread.sleep(vel);
            this.bufEnv.insertMessagePasive(
                "El thread productor: "+this.id+"envia de forma pasiva el mensaje"
                +i+" de:"+ this.tim + "mensajes"
                );
        }
    }

    private void reciboMensaje(String message){
        tim++;
        if(this.env.equals("true") && this.rec.equals("true")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d A A al mensaje";
            System.out.println(String.format(prot,this.id,message));
        }
        else if(this.env.equals("true") && this.rec.equals("false")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d A S al mensaje";
            System.out.println(String.format(prot,this.id,message));
        }
        else if(this.env.equals("false") && this.rec.equals("true")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d S A al mensaje";
            System.out.println(String.format(prot,this.id,message));
        }
        else if(this.env.equals("false") && this.rec.equals("false")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d S S al mensaje";
            System.out.println(String.format(prot,this.id,message));
        }
        
}

    @Override
    public void run(){
        while(!this.bufEnv.isFull()){
            for(int i = 0; i<this.tim; i++){
                try {
                    this.envioMensaje(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while(this.bufRec.hasMessages()){
            if(this.rec.equals("true")){
                String message = this.bufRec.retrieveMessageActive();
                if (message == null){
                    return;
                }
                this.reciboMensaje(message);
            }
            else if(this.rec.equals("false")){
                String message = this.bufRec.retrieveMessagePasive();
                if (message == null){
                    return;
                }
                this.reciboMensaje(message);
            }
        }
    }
}

