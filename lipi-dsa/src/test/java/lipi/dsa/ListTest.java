package lipi.dsa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lipi.dsa.List;

/**
 * Unit test for List.
 */
public class ListTest 
{
	private List<String> ls = new List<String>();
	
    @Test
    public void testAdditionAndRemoval()
    {
    	ls.clear();
    	ls.add("Hello");
    	ls.add("World");
    	assertEquals("Size check", 2, ls.size());
        assertEquals("Check Hello", "Hello", ls.get(0));
        ls.add("New wish", 0);
        assertEquals("Check inserstion and removal at 0", "New wish", ls.removeFirst());
        assertEquals("Recheck size", 2, ls.size());
    }
    
    @Test
    public void testClearList()
    {
    	ls.clear();
    	ls.add("Hello");
    	ls.add("World");
    	assertEquals("Size check", 2, ls.size());
    	ls.clear();
    	assertEquals("Size check", 0, ls.size());
    }
}
