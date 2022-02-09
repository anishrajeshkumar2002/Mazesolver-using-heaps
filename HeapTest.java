import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class HeapTest {
	//Comparator<Integer> compare = new Comparator<Integer>();
	Heap<Integer, String> testHeap = new Heap<Integer,String>(new IntComparator());
	@Test
    public void testAdd(){
		testHeap.add(3, "d");
    	testHeap.add(0, "a");
    	//testHeap.add(1, "b");
    	//testHeap.add(2, "c");
    	System.out.println(testHeap.toString());
    }
}
