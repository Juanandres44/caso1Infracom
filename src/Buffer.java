import java.util.List;
import java.util.LinkedList;


public class Buffer {
    
    private List<String> buffer;

    private int size;
    
    private int total;


    public Buffer(int size, int total){
        this.size = size;
        this.total = total;
        this.buffer = new LinkedList<String>();
    }

    public synchronized boolean hasMessages(){
        return this.buffer.size()>0;
    }

    public synchronized void insertMessagePasive(String message){
        while(this.buffer.size() == this.size){
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
        while(this.buffer.size() == this.size){
            
        }
        this.buffer.add(message);

    }

    public synchronized String retrieveMessagePasive(){
        while(this.buffer.size()==0 && total>0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        String msg = null;
        if(total>0){
            msg = this.buffer.remove(0);
            notify();
            total--;
        }
        return msg;
    }

    public synchronized String retrieveMessageActive(){
        while(this.buffer.size()==0){
        }

        String msg = null;
        if(total>0){
            msg = this.buffer.remove(0);
            notify();
            total--;
        }
        return msg;
    }

    
}
