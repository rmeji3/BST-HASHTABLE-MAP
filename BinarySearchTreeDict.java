import java.util.Iterator;

public class BinarySearchTreeDict<K extends Comparable<K>,V> implements ProjOneDictionary<K,V> {
    class Node
    {
        public V value;

        public K key;

        public Node parent;
        public Node left;
        public Node right;

        public Node(K key, V input, Node prev)
        {
            left = null;
            right = null;
            parent = prev;
            value = input;
            this.key = key;

        }
    }
    private Node root;
    private int size = 0;
    private Boolean insertHelper(Node curr,K key, V value) {
        if (curr == null) {
            return true;
        }
        int comparison = curr.key.compareTo(key);
        if(comparison == 0) {
            curr.value = value;
            return true;
        } else if(comparison > 0) {
            if(curr.left == null) {
                curr.left = new Node(key, value, curr);
                return false;
            } else {
                return insertHelper(curr.left, key, value);
            }
        } else {
            if(curr.right == null) {
                curr.right = new Node(key, value, curr);
                return false;
            } else {
                return insertHelper(curr.right, key, value);
            }
        }
    }

    @Override
    public boolean insert(K key, V value) throws NullValueException {
        if(value == null){
            throw new NullValueException();
        }
        if(root == null){
            this.root = new Node(key, value, null);
        }
        else if(insertHelper(root ,key, value))
        {
            return true;
        }
        size++;
        return false;
    }



    private V findHelper(Node curr, K target)
    {
        if (curr == null)
        {
            return null;
        }
        int comparison = curr.key.compareTo(target);
        if (comparison == 0)
        {
            return curr.value;
        }
        if (comparison > 0)
        {
            return findHelper(curr.left, target);
        }
        return findHelper(curr.right, target);
    }

    @Override
    public V find(K key) {

        return findHelper(root, key);
    }


    private boolean deleteHelper(Node curr, Node parent, K target) {
        if (curr == null) {
            return false;
        }

        if (target.compareTo(curr.key) < 0) {
            return deleteHelper(curr.left, curr, target);
        } else if (target.compareTo(curr.key) > 0) {
            return deleteHelper(curr.right, curr, target);
        } else {
            if (curr.left != null && curr.right != null) {
                Node successor = goLeft(curr.right);
                curr.key = successor.key;
                deleteHelper(curr.right, curr, successor.key);
            } else {
                Node child = (curr.left != null) ? curr.left : curr.right;
                if (child != null) {
                    child.parent = parent;
                }
                if (curr == root) {
                    root = child;
                } else if (parent.left == curr) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            return true;
        }
    }

    private Node goLeft(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    @Override
    public boolean delete(K key) {
        if(deleteHelper(root, null, key))
        {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }




    private class BSTIterator implements Iterator<K> {

        ListQueue<K> keysQ = new ListQueue<K>();

        private void insertToQ(Node curr){
            if(curr == null)
                return;
            keysQ.enqueue(curr.key);
            insertToQ(curr.left);
            insertToQ(curr.right);
        }

        private BSTIterator() {
            insertToQ(root);
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
        return new BSTIterator();
    }

}
