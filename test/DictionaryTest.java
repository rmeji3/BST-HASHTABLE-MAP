import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public abstract class DictionaryTest {
    public abstract ProjOneDictionary<Integer,String> newDictionary();

    @Test
    public void testSize(){
        ProjOneDictionary<Integer, String> dict = newDictionary();
        assertEquals(0,dict.getSize());
    }

    @Test
    public void testNullValue(){
        ProjOneDictionary<Integer, String> dict = newDictionary();
        try{
            dict.insert(0,null);
            fail();
        } catch (NullValueException e){
            return;
        }
    }
}
