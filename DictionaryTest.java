import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public abstract class DictionaryTest {
    public abstract ProjOneDictionary<Integer,String> newDictionary();

    @Test
    void testEmptyFind1(){
        ProjOneDictionary<Integer,String> dict = newDictionary();
        assertNull(dict.find(3),"Incorrect empty find behavior");
    }
    @Test
    void testSingleFind1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        assertEquals("three",dict.find(3),"Incorrect single element find behavior");
    }
    @Test
    void testSingleDelete1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        assertTrue(dict.delete(3));
        assertNull(dict.find(3),"Incorrect single element delete behavior");
    }
    @Test
    void testIteratorSingle1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        Iterator<Integer> iter = dict.iterator();
        assertTrue(iter.hasNext(), "Incorrect single element hasNext Iterator behavior");
        assertEquals(3,iter.next(), "Incorrect single element iterator behavior");
        assertFalse(iter.hasNext(), "Incorrect emptied hasNext Iterator behavior");
    }
    @Test
    void testManyFind1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(1,"one");
        dict.insert(2,"two");
        dict.insert(3,"three");
        assertEquals("one", dict.find(1), "Incorrect multiple element find behavior");
        assertEquals("two", dict.find(2), "Incorrect multiple element find behavior");
        assertEquals("three", dict.find(3), "Incorrect multiple element find behavior");
    }
    @Test
    void testEmptyDelete1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        assertFalse(dict.delete(1), "Incorrect single element delete behavior");

    }
    @Test
    void testManyDelete1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(1,"one");
        dict.insert(2,"two");
        dict.insert(3,"three");
        dict.insert(4,"four");
        dict.insert(5,"five");
        //delete the front to see if the list is properly
        assertTrue(dict.delete(1), "Incorrect many element delete behavior");
        assertEquals(4, dict.getSize(), "Incorrect size after many element delete behavior");
        //make sure the back is properly set
        assertTrue(dict.delete(5), "Incorrect many element delete behavior");
        assertEquals(3, dict.getSize(), "Incorrect size after many element delete behavior");
        assertNull(dict.find(5), "didnt delete tail");
        //make sure the list is all properly connected
        int i = 2;
        for(Integer x: dict)
        {
            assertEquals(i++, x, "Incorrect many element delete behavior");
        }
        //testing the middle deletion
        assertTrue(dict.delete(3), "Incorrect many element delete behavior");
        int j = 2;
        assertEquals(2, dict.getSize(), "Incorrect size after many element delete behavior");
        for(Integer x: dict)
        {
            assertEquals(j, x, "Incorrect many element delete behavior");
            j+=2;
        }
    }
    @Test
    void testIteratorEmpty1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        Iterator<Integer> iter = dict.iterator();
        assertFalse(iter.hasNext(), "Incorrect empty element hasNext Iterator behavior");
    }
    @Test
    void testIteratorMany1() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        for(int i = 0; i < 26; i++)
        {
            dict.insert(i, String.valueOf('a' + i) );
        }
        Iterator<Integer> iter = dict.iterator();
        for(int i = 0; i < 26; i++)
        {
            assertTrue(iter.hasNext(), "Incorrect many element hasNext Iterator behavior");
            assertEquals(i,iter.next(), "Incorrect many element iterator behavior");
        }

        assertFalse(iter.hasNext(), "Incorrect emptied hasNext Iterator behavior");
    }
    @Test
    void testEmptyFind() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        assertNull(dict.find(3),"Incorrect empty find behavior");
    }
    @Test
    void testSingleFind() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        assertEquals("three",dict.find(3),"Incorrect single element find behavior");
    }
    @Test
    void testSingleDelete() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        assertTrue(dict.delete(3));
        assertNull(dict.find(3),"Incorrect single element delete behavior");
    }
    @Test
    void testIteratorSingle() throws NullValueException{
        ProjOneDictionary<Integer,String> dict = newDictionary();
        dict.insert(3,"three");
        Iterator<Integer> iter = dict.iterator();
        assertTrue(iter.hasNext(), "Incorrect single element hasNext Iterator behavior");
        assertEquals(3,iter.next(), "Incorrect single element iterator behavior");
        assertFalse(iter.hasNext(), "Incorrect emptied hasNext Iterator behavior");
    }
    @Test
    void testEmpty()throws NullValueException {
        ProjOneDictionary<Integer, String> dict = newDictionary();

        Iterator<Integer> iter = dict.iterator();
        assertFalse(iter.hasNext(), "Incorrect empty hasNext Iterator behavior");
        assertNull(dict.find(3), "Incorrect empty find behavior");
        assertFalse(dict.delete(3), "Incorrect empty delete behavior");
        assertEquals(dict.getSize(), 0, "Incorrect empty size");
    }
    @Test
    void testManyFindAndDelete() throws NullValueException{
        ProjOneDictionary<Integer, String> dict = newDictionary();

        for (int i = 0; i < 20; i++){
            dict.insert(i, Integer.toString(i));
        }
        for (int k: dict){
            assertEquals(Integer.toString(k),dict.find(k), "Incorrect iterator find behavior at index " + k);
        }
        assertEquals(dict.getSize(), 20, "Incorrect dict size");
        for (int k:dict){
            assertTrue(dict.delete(k), "Incorrect iterator delete behavior at index " + k);
        }
        for (int i = 0; i < 20; i++){
            assertNull(dict.find(i));
        }
    }

}
