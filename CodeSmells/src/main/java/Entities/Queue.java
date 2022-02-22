package Entities;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Queue<T> {

    BlockingQueue<T> queue;

    public Queue(int size)
    {
        queue = new ArrayBlockingQueue<T>(size);
    }

    public boolean put(T item){
        boolean a;
        try {
            queue.put(item);
            a=true;
        }catch (InterruptedException e){
            a=false;
        }
        return a;
    }

    public T take(){
    T a;
        try {
            a=queue.take();
        }catch (InterruptedException e){
            a=null;
        }
        return a;
    }

}
