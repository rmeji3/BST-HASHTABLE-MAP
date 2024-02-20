import org.junit.jupiter.api.Test;

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
        for(int i = 0; i < Math.floor(Math.random() * 100); i++ )
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
    public void noneDelete() throws NullValueException {
        ProjOneDictionary<Integer, String> d1 = newDictionary();
        assertFalse(d1.delete(1), "delete must return false when the BST is empty");
    }

}
