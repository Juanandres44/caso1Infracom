import java.util.List;
import java.util.LinkedList;


public class Buffer {
    
    private List<String> buffer;

    private int size;
    
    private int pendingMsg;


    public Buffer(int size, int pendingMsg){
        this.size = size;
        this.pendingMsg = pendingMsg;
        this.buffer = new LinkedList<String>();
    }

    public synchronized boolean hasMessages(){
        return this.buffer.size()>0;
    }

    public synchronized boolean isFull(){
        return this.buffer.size()==this.size;
    }

    public synchronized void insertMessagePasive(String message){
        while(isFull()){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.buffer.add(message);
        notify();
    }

    public synchronized void insertMessageActive(String message){
        while(isFull()){
        }

        this.buffer.add(message);
    }

    public synchronized String retrieveMessagePasive(){
        while(this.buffer.size()==0 && pendingMsg>0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        String msg = null;
        if(pendingMsg>0){
            msg = this.buffer.remove(0);
            notify();
            pendingMsg--;
        }
        return msg;
    }

    public synchronized String retrieveMessageActive(){
        while(this.buffer.size()==0 && pendingMsg>0){
        }

        String msg = null;
        if(pendingMsg>0){
            msg = this.buffer.remove(0);
            notify();
            pendingMsg--;
        }
        return msg;
    }    
}
