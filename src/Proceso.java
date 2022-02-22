import java.util.ArrayList;

public class Proceso extends Thread{
    private int id;
    private Buffer bufEnv;
    private Buffer bufRec;
    private int vel;
    private String env;
    private String rec;
    private int tim;

    private ArrayList<String> mensajesP1;

    public Proceso(int id, Buffer bufEnv, Buffer bufRec, int vel, String env,String rec, int tim){
        this.id = id;
        this.bufEnv = bufEnv;
        this.bufRec = bufRec;
        this.vel = vel;
        this.env = env;
        this.rec = rec;
        this.tim = tim;
        this.mensajesP1 = new ArrayList<String>();
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
            String fin =String.format(prot,this.id,message);
            System.out.println(fin);
            mensajesP1.add(fin);
        }
        else if(this.env.equals("true") && this.rec.equals("false")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d A S al mensaje";
            String fin =String.format(prot,this.id,message);
            System.out.println(fin);
            mensajesP1.add(fin);
        }
        else if(this.env.equals("false") && this.rec.equals("true")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d S A al mensaje";
            String fin =String.format(prot,this.id,message);
            System.out.println(fin);
            mensajesP1.add(fin);
        }
        else if(this.env.equals("false") && this.rec.equals("false")){
            String prot ="El proceso %d recupero el siguiente mensaje: %s y le agrega %d S S al mensaje";
            String fin =String.format(prot,this.id,message);
            System.out.println(fin);
            mensajesP1.add(fin);
        }
    }


    

    @Override
    public void run(){
        String fin = "";
        while(!this.bufEnv.isFull() && !fin.equals("FIN")){
            for(int i = 0; i<this.tim; i++){
                try {
                    this.envioMensaje(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(this.id == 1){
                System.out.println("los mensajes fueron:"+mensajesP1);
                System.out.println("FIN");
                fin = "FIN";
            }
        }
        while(this.bufRec.hasMessages() && !fin.equals("FIN")){
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

