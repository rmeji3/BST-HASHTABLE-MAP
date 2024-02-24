import java.lang.reflect.Array;
import java.util.Iterator;



public class HashMapDict<K, V> implements ProjOneDictionary<K, V> {

    private int size;
    private int population;

    class Node {
        K key;
        V value;
        Boolean hasHad;

        Node(){
            key = null;
            value = null;
            hasHad = false;
        }
        Node (K key, V value){
            this.key = key;
            this.value = value;
            hasHad = true;
        }
    }
    private Node[] buckets;
    public HashMapDict() {
        size = 10;
        population = 0;
        buckets = (Node[]) Array.newInstance(Node.class, size);

        for (int i = 0; i < size; i++) {
            buckets[i] = new Node();
        }
    }
    private void insertHelper(int index, K key, V value){
        buckets[index].hasHad = true;
        buckets[index].key = key;
        buckets[index].value = value;
    }
    @Override
    public boolean insert(K key, V value) throws NullValueException {
        if(value == null){
            throw new NullValueException();
        }
        if (population >= size / 2) {
            resize();
        }
        int hashIndex = key.hashCode() % size;
        for (int i = hashIndex; i < size + hashIndex; i++) {
            int index = i % size;
            if (buckets[index].key == null) {
                insertHelper(index, key, value);
                population++;
                return false;
            }
            else if (buckets[index].key.equals(key)){
                insertHelper(index, key, value);
                return true;
            }
        }
        return false;
    }


    private void resize() throws NullValueException {
        size *= 2; // Double the size

        Node[] oldBucket = buckets;

        buckets = (Node[]) Array.newInstance(Node.class, size);
        for (int i = 0; i < size; i++) {
            buckets[i] = new Node();
        }

        population = 0; // Reset population because it will be recalculated in insert

        for (Node node : oldBucket) {
            if (node.key != null) {
                insert(node.key, node.value);
            }
        }
    }


    @Override
    public V find(K key) {
        int hashIndex = key.hashCode() % size;
        if (buckets[hashIndex].key == key){
            return buckets[hashIndex].value;
        }
        for (int i = hashIndex; i < size + hashIndex; i++) {
            int index = i % size;
            if (!buckets[index].hasHad){
                return null;
            }
            else if (buckets[index].key == key) {
                return buckets[index].value;
            }
        }
        return null;
    }

    @Override
    public boolean delete(K key) {
        int hashIndex = key.hashCode() % size;
        if (buckets[hashIndex].key == key){
            buckets[hashIndex].key = null;
            buckets[hashIndex].value = null;
            population--;
            return true;
        }
        for (int i = hashIndex; i < size + hashIndex; i++) {
            int index = i % size;
            if (!buckets[index].hasHad){
                return false;
            }
            else if (buckets[index].key == key) {
                buckets[hashIndex].key = null;
                buckets[hashIndex].value = null;
                population--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return population;
    }

    private class HashIterator implements Iterator<K> {

        ListQueue<K> keysQ = new ListQueue<K>();

        private void insertToQ(){
            for(Node node: buckets){
                if (node.key != null)
                    keysQ.enqueue(node.key);
            }
        }

        private HashIterator() {
            insertToQ();
        }

        @Override
        public boolean hasNext() {
            return keysQ.getSize() != 0;
        }

        @Override
        public K next() {
            if (!hasNext())
                return null;
            return keysQ.dequeue();
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new HashIterator();
    }

}