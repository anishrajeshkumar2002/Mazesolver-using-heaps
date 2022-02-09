
/**Name: Anish Rajeshkumar
ID: A16670774
Email: arajeshkumar@ucsd.edu
Sources Used: tutors
File header: The purpose of this file is to implement all
the necessary methods needed to create a functioning heap
*/
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;
/**Class header: This class stores one method which
 return the key and value into string format
* 

*/
class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }
}
/**Class header: this class stores 
 * nultiple methods which are used
 * to create fully functioning 
 * heap. 
* 

*/
public class Heap<K,V> implements PriorityQueue<K,V> {
	public List<Entry<K, V>> entries;
	public Comparator<K> comparator;
	/**
	 * This constructor initilizez the instance vairables and 
	 * initilizez the entry list which contains the most recent
	 * list of entries as well as initilizies the comparator 
	 * which will do comaprison of keys to determine where
	 * in the heap they should go 
	
	 */
	public Heap(Comparator<K> comparator) {
		this.comparator = comparator;
		this.entries = new ArrayList<Entry<K, V>>();
	}
	/**
	 * A helper method 
	 * which returns the left the index which would be
	 * the left of the parent entry
	 * @param int index of parent
	 * @return left index of child
	 */
	public int left(int index) {
		return (index *2) + 1;
	}
	/**
	 * A helper method 
	 * which returns the right the index which would be
	 * the right of the parent entry
	 * @param int index of parent
	 * @return right index of child
	 */
	public int right(int index) {
		return (index *2) + 2;
	}
	/**
	 * A helper method 
	 * which essentially swaps the parent
	 * and entry
	 * @param int index of parent and child
	 * @return 
	 */
	public void swap(int i1, int i2) {
		Entry<K,V> temp = this.entries.get(i1);
		this.entries.set(i1,this.entries.get(i2));
		this.entries.set(i2,temp);
	}
	/**
	 * A helper method 
	 * which compares keys in entries
	 * to determine which entry in the relative 
	 * indexes is the larger entry
	 * @param int index of parent and child
	 * @return true if index 2 is greater
	 */
	public boolean existsAndGreater(int index1, int index2) {
		if(index1 >= this.entries.size()) {
			return false;
		}
		if(index2 >= this.entries.size()) {
			return false;
		}
		if(this.comparator.compare
		(this.entries.get(index1).key,
		this.entries.get(index2).key) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * A helper method 
	 * which  finds the parent 
	 * key/entry of the index given
	 * @param int index of child
	 * @return int index of parent
	 */
	public int parent(int index) {
		return (index - 1) /2;
	}
	/**
	 * A helper method 
	 * which essentially fixes the heap to
	 * ensure it follows max/min heap properties by
	 * swapping around values until the heap properties
	 * are met when a entry is removed
	 * @param int index 
	 * @return 
	 */
	public void bubbleDown(int index) {
		if(index >= this.entries.size()) {
			return;
		}
		int leftIndex = this.left(index);
		if(leftIndex >= this.entries.size()) {
			return;
		}
		int largerChildIndex = leftIndex;
		int rightIndex = this.right(index);
		if(this.existsAndGreater(rightIndex, largerChildIndex)) {
			largerChildIndex = rightIndex;
		}
		if(this.existsAndGreater(largerChildIndex, index)) {
			this.swap(index, largerChildIndex);
			this.bubbleDown(largerChildIndex);
		}
		
	}
	/**
	 * A helper method 
	 * which essentially fixes the heap to
	 * ensure it follows max/min heap properties by
	 * swapping around values until the heap properties
	 * are met when a entry is added
	 * @param int index 
	 * @return 
	 */
	public void bubbleUp(int index) {
		if(index <= 0) {
			return;
		}
		Entry<K,V> current = this.entries.get(index);
		Entry<K,V> parent = this.entries.get(this.parent(index));
		System.out.println(current.toString());
		System.out.println(parent.toString());
		System.out.println(this.entries.get(0));
		int comp = this.comparator.compare(current.key, parent.key);
		System.out.println(comp);
		if(comp > 0) {
			this.swap(index, this.parent(index));
			this.bubbleUp(parent(index));
			System.out.println(current.toString());
			System.out.println(parent.toString());
			System.out.println(this.entries.get(0));
		}
		else {
			return;
		}
	}
	/**
	 * A helper method 
	 * which essentially returns the 
	 * number of elements in
	 * the heap
	 * @param 
	 * @return 
	 */
	public int size() {
		return this.entries.size();
	}
	/**
	 * A helper method 
	 * which essentially returns the 
	 * heap in string format
	 * the heap
	 * @param 
	 * @return 
	 */
	public String toString() {
		String list = entries.toString();
		return list;
	}
	/**
	 * A method 
	 * which essentially adds
	 * an element to
	 * the heap 
	 * @param key and value to be added
	 * @return 
	 */
    public void add(K k, V v) {
    	Entry<K,V> entry = new Entry<K,V>(k,v);
    	this.entries.add(entry);
    	this.bubbleUp(this.entries.size() - 1);
    }
    /**
	 * A method 
	 * which essentially removes
	 * the root of the heap 
	 * @param 
	 * @return 
	 */
    public Entry<K,V> poll(){
    	if(this.entries.size() == 0) {
    		throw new NoSuchElementException();
    		}
    	Entry <K,V> root = this.entries.get(0);
    	this.entries.set(0,  this.entries.get( this.entries.size() - 1));
    	this.entries.remove(this.entries.size() - 1);
    	this.bubbleDown(0);
    	return root;
    }
    /**
	 * A method 
	 * which essentially returns
	 * the root of the heap 
	 * @param 
	 * @return 
	 */
    public Entry<K,V> peek(){
    	if(this.entries.size() == 0) {
    		throw new NoSuchElementException();
    	}
    	return this.entries.get(0);
    }
    /**
   	 * A method 
   	 * which essentially returns
   	 * heap in array format
   	 * @param 
   	 * @return 
   	 */
    public List<Entry<K,V>> toArray(){
    	return this.entries;
    }
    /**
   	 * A method 
   	 * which essentially returns
   	 * true if heap is empty
   	 * @param 
   	 * @return 
   	 */
    public boolean isEmpty() {
    	if(this.entries.size() == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
