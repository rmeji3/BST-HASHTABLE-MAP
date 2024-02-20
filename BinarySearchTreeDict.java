import jdk.jfr.Description;

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
    private Boolean insertHelper(Node curr, Node prev, K key, V value)
    {

        if (curr == null)
        {
            return false;
        }
        if(curr.key.compareTo(key) == 0){
            curr.value = value;
            return true;
        }
        else if (curr.key.compareTo(key) < 0 && curr.left == null)
        {
            curr.left = new Node(key, value, curr);
        }
        else if (curr.key.compareTo(key) > 0 && curr.right == null)
        {
            curr.right = new Node(key, value, curr);
        }
        else if (curr.key.compareTo(key) < 0)
        {
            insertHelper(curr.left, curr ,key, value);
        }
        else if (curr.key.compareTo(key) > 0)
        {
            insertHelper(curr.left, curr ,key, value);
        }

        return false;
    }
    @Override
    public boolean insert(K key, V value) throws NullValueException {
        if(value == null){
            throw new NullValueException();
        }
        if(root == null){
            Node root = new Node(key, value, null);
            this.root = root;
            return false;
        }
        if(insertHelper(root, null ,key, value))
        {
            return true;
        }
        else
        {
            size++;
        }
        return false;
    }



    private V findHelper(Node curr, K target)
    {
        if (curr == null)
        {
            return null;
        }

        if (curr.key.compareTo(target) == 0)
        {
            return curr.value;
        }
        if (curr.key.compareTo(target) < 0)
        {
            return findHelper(curr.left, target);
        }
        if (curr.key.compareTo(target) > 0)
        {
            return findHelper(curr.right, target);
        }
        return null;
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
        Node cursor;
        Node previous;

        private BSTIterator() {
            cursor = root;
            previous = null;
            while (cursor != null && cursor.left != null) {
                cursor = cursor.left;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public K next() {
            if (!hasNext())
                return null;

            previous = cursor;

            if (cursor.right != null) {
                cursor = cursor.right;
                while (cursor.left != null) {
                    cursor = cursor.left;
                }
            } else {
                Node child = cursor;
                Node tempParent = cursor.parent;
                while (tempParent != null && child == tempParent.right) {
                    child = tempParent;
                    tempParent = tempParent.parent;
                }
                cursor = tempParent;
            }

            return previous.key;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

}
