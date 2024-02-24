import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest extends DictionaryTest {
    @Override
    public ProjOneDictionary<Integer, String> newDictionary() {
        return new BinarySearchTreeDict<Integer,String>();
    }
    @Test
    public void noneInsert() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"haiii :3"), "insert must return false when the BST is empty");
    }
    @Test
    public void oneInsert() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"haiii :3"), "insert must return false when the BST is empty");
        assertTrue(d1.insert(1,"bye :("), "insert must return true when the BST is has one with the same key");
        ProjOneDictionary<Integer, String> d2 = newDictionary();
        assertFalse(d2.insert(1,"haiii :3"), "insert must return false when the BST is empty");
        assertFalse(d2.insert(2,"bye :("), "insert must return true when the BST is has one with the different key");
    }
    @Test
    public void manyInsert() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"haiii :3"), "insert must return false when the BST is empty");
        for(int i = 0; i < 100; i++ )
        {
            assertFalse(d1.insert(i+2," yo : " + (i + 2)), "insert must return false when the BST is inserting many different keys");
        }
    }
    @Test
    public void noneFind() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertNull(d1.find(2), "find must return null when the BST is empty");
    }
    @Test
    public void oneFind() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"haiii :3"), "insert must return false when the BST is empty");
        assertEquals("haiii :3" , d1.find(1),"find must return the correct value when the BST has a single element");
    }
    @Test
    public void manyFind() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        for(int i = 0; i < 100; i++)
        {
            assertFalse(d1.insert(i,"yo : " + i), "insert must return false when the BST is inserting many different keys");
        }
        for(int i = 0; i < 100; i++)
        {
            assertEquals("yo : " + i ,d1.find(i),"find must return the correct value when the BST has a many elements");
        }

    }
    @Test
    public void noneDelete() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.delete(1), "delete must return false when the BST is empty");
    }
    @Test
    public void oneDelete() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"haiii :3"), "insert must return false when the BST is empty");
        assertTrue(d1.delete(1), "delete must return true when the BST has one element");
    }
    @Test
    public void manyDelete() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        for(int i = 0; i < 100; i++)
        {
            assertFalse(d1.insert(i,"yo : " + i), "insert must return false when the BST is inserting many different keys");
        }
        for(int i = 0; i < 100; i++)
        {
            assertTrue(d1.delete(i),"find must return true when the BST has a many elements deleted");
        }
    }
    @Test
    public void noneSize() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();

        assertEquals(0, d1.getSize(), "getSize must return appropriate size with no elements");
    }
    @Test
    public void oneSize() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();

        assertFalse(d1.insert(1,"yo"), "insert must return false when the BST is inserting one key");

        assertEquals(1,d1.getSize(), "getSize must return appropriate size with one elements");
    }
    @Test
    public void manySize() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        for(int i = 0; i < 100; i++)
        {
            assertFalse(d1.insert(i,"yo : " + i), "insert must return false when the BST is inserting many different keys");
        }
        assertEquals(100, d1.getSize(),"getSize must return appropriate size with many elements");
        for(int i = 0; i < 50; i++)
        {
            assertTrue(d1.delete(i),"find must return true when the BST has a many elements deleted");
        }
        assertEquals(50, d1.getSize(),"getSize must return appropriate size with many elements");
    }
    @Test
    public void noneNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        Iterator<Integer> iter = d1.iterator();

        assertNull(iter.next(), "next must return null when BST is empty");
    }
    @Test
    public void oneNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"yo"), "insert must return false when the BST is inserting one key");
        Iterator<Integer> iter = d1.iterator();
        assertEquals(1,iter.next(), "next must return proper key when BST has one element");
    }
    @Test
    public void manyNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        for(int i = 0; i < 100; i++)
        {
            assertFalse(d1.insert(i,"yo : " + i), "insert must return false when the BST is inserting many different keys");
        }
        Iterator<Integer> iter = d1.iterator();
        for(int i = 0; i < 100; i++)
        {
            assertEquals(i,iter.next(), "next must return proper value when BST has many elements");
        }
    }
    @Test
    public void noneHasNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        Iterator<Integer> iter = d1.iterator();

        assertFalse(iter.hasNext(), "hasNext must return false when BST is empty");
    }
    @Test
    public void oneHasNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.insert(1,"yo"), "insert must return false when the BST is inserting one key");
        Iterator<Integer> iter = d1.iterator();
        assertTrue(iter.hasNext(), "hasNext must return true when BST is has one element");
        assertEquals(1,iter.next(), "next must return proper value when BST has one elements");
        assertFalse(iter.hasNext(), "hasNext must return false when BST has one element and was already called");
    }
    @Test
    public void manyHasNext() throws NullValueException{
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        for(int i = 0; i < 100; i++)
        {
            assertFalse(d1.insert(i,"yo : " + i), "insert must return false when the BST is inserting many different keys");
        }
        Iterator<Integer> iter = d1.iterator();
        for(int i = 0; i < 100; i++)
        {
            assertTrue(iter.hasNext(),"hasNext must return true when BST has many elements but does not reach the end yet" );
            assertEquals(i,iter.next(), "next must return proper value when BST has many elements");
        }
        assertFalse(iter.hasNext(), "hasNext must return false when BST has gone to the last element");
    }

}
