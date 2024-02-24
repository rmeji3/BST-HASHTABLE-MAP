public class ListQueue<E> implements Queue<E>{
    private class Node{
        E value;
        Node next;
        Node(E newValue){
            super();
            value = newValue;
        }
    }

    //public static E test;
    private Node front;
    private Node back;

    private int size;

    public ListQueue(){
        super();
        front = null;
        back = null;
        size=0;
    }


    public int getSize(){
        return size;
    }
    @Override
    public void enqueue(E toAdd) {
        if (front==null){
            front = new Node(toAdd);
            back = front;
            size = 1;
            return;
        } else {
            back.next = new Node(toAdd);
            back = back.next;
            size++;
        }
    }

    @Override
    public E dequeue() {
        if (front==null){ return null;}
        E toRet = front.value;
        front = front.next;
        size--;
        return toRet;
    }

    @Override
    public E front() {
        if (front == null) { return null;}
        return front.value;
    }

}
