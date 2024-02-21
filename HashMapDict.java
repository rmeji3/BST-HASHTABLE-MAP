import java.util.Iterator;



public class HashMapDict<K, V> implements ProjOneDictionary<K, V> {

    private int size;
    private int population;

    private K[] keysArr;
    private V[] valuesArr;
    public HashMapDict() {
        size = 10;
        population = 0;
        keysArr = (K[]) new Object[size];
        valuesArr = (V[]) new Object[size];

        for (int i = 0; i < size; i++) {
            keysArr[i] = null;
            valuesArr[i] = null;
        }
    }
    @Override
    public boolean insert(K key, V value) throws NullValueException {
        // Resize if necessary
        if (population >= size / 2) {
            resize();
        }

        int hashIndex = key.hashCode() % size;
        for (int i = hashIndex; i < size + hashIndex; i++) {
            int index = i % size;
            if (keysArr[index] == null || keysArr[index].equals(key)) {
                keysArr[index] = key;
                valuesArr[index] = value;
                if (keysArr[index] == null) {
                    population++;
                }
                return true;
            }
        }


        return false;
    }

    private void resize() throws NullValueException {
        size *= 2; // Double the size
        K[] oldKeysArr = keysArr;
        V[] oldValuesArr = valuesArr;

        keysArr = (K[]) new Object[size];
        valuesArr = (V[]) new Object[size];
        population = 0; // Reset population because it will be recalculated in insert

        for (int i = 0; i < oldKeysArr.length; i++) {
            if (oldKeysArr[i] != null) {
                insert(oldKeysArr[i], oldValuesArr[i]);
            }
        }
    }


    @Override
    public V find(K key) {
        int hashIndex = key.hashCode() % size;
        if(keysArr[hashIndex] == key){
            return valuesArr[hashIndex];
        }
        int currIndex = hashIndex;
        while (keysArr[currIndex] != key){
            currIndex++;
            if (hashIndex == currIndex){
                return null;
            }
            if (currIndex == size - 1){
                currIndex = 0;
            }
        }
        return valuesArr[currIndex];
    }

    @Override
    public boolean delete(K key) {
        int hashIndex = key.hashCode() % size;
        if(keysArr[hashIndex] == key){
            keysArr[hashIndex] = null;
            valuesArr[hashIndex] = null;
            return true;
        }
        int currIndex = hashIndex;
        while (keysArr[currIndex] != key){
            currIndex++;
            if (hashIndex == currIndex){
                return false;
            }
            if (currIndex == size - 1){
                currIndex = 0;
            }
        }
        keysArr[hashIndex] = null;
        valuesArr[hashIndex] = null;
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
