import java.util.Random;

/**
A class of bags whose entries are stored in a chain of linked nodes.
The bag is never full.
@author Frank M. Carrano
 * This code is from Chapter 4 of
 * Data Structures and Abstractions with Java 4/e
 *      by Carrano
 * 
 * The toString method is overwritten to give a nice display of the items in
 * the bag in this format Bag{Size:# [1] [2] [3] [4] }
 * //- * @version 4.0
 */
public final class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; // Reference to first node
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/** Sees whether this bag is empty.
    @return true if the bag is empty, or false if not */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the current number of entries in this bag.
    @return the integer number of entries currently in the bag */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize


	/** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry.
    @return True. */
	public boolean add(T newEntry) // OutOfMemoryError possible
	{
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
		// (firstNode is null if chain is empty)
		firstNode = newNode; // New node is at beginning of chain
		numberOfEntries++;
		return true;
	} // end add

	/** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. */
	public T[] toArray() {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // end while
		return result;
	} // end toArray

	/** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted.
    @return The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;
		while ((loopCounter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			}
			loopCounter++;
			currentNode = currentNode.next;
		} // end while
		return frequency;
	} // end getFrequencyOf

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) 
				found = true;
			else 
				currentNode = currentNode.next;
		} // end while
		return found;
	} // end contains

	/** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false otherwise. */
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {
			nodeN.data = firstNode.data; // Replace located entry with entry
			// in first node
			firstNode = firstNode.next; // Remove first node
			numberOfEntries--;
			result = true;
		} // end if
		return result;
	} // end remove

	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.next;
			}
		} // end while
		return currentNode;
	} // end getReferenceTo

	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	} // end clear

	private class Node {

		private T data; // Entry in bag
		private Node next; // link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

	/** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful,
    or null. */
	public T remove() {
		T result = null;

		// MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG
		
//		int index = new Random().nextInt(numberOfEntries);
//        Node current = firstNode;
//        Node prev = firstNode;
//        for (int i = 0; i < index; i++) {
//            prev = current;
//            current = current.next;
//        }
//        prev.next = current.next;
//        current.next = null;
//        numberOfEntries--;
//        
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain
			numberOfEntries--;
		} // end if

		// end remove
		return result;
	}

	/** Override the toString method so that we can inspect the contents of the bag.
	 * @return A string representation of the contents of the bag. */
	public String toString() {

		String result = "Bag{Size:" + numberOfEntries + " ";

		Node scout = firstNode;

		for (int index = 0; index < numberOfEntries; index++) {
			result += "[" + scout.data + "] ";
			scout = scout.next;
		} // end for

		result += "}";
		return result;
	} // end toString

	/*********************************************************************
	 * 
	 * METHODS TO BE COMPLETED
	 * 
	 * 
	 ************************************************************************/

	/** Check to see if two bags are equals.  
	 * @param aBag Another object to check this bag against.
	 * @return True if the two bags contain the same objects with the same frequencies.
	 */
	public boolean equals(LinkedBag<T> aBag) {
		boolean result = false; // result of comparison of bags
		T [] array1 = aBag.toArray(); //creates aBag array
		T [] array2 = this.toArray(); //creates global variable array
		boolean sameLength = false;
		//System.out.println(this.toString()); for testing
		//System.out.println(aBag.toString()); for testing
		if (array1.length == array2.length){//checks to see if each bag has the same number of entities 
			sameLength = true; //if same number of entities same length is true
		}

		if(sameLength){//if the two bags have the same length runs through this if statement
			if (aBag.isEmpty() && this.isEmpty()) {//check to see if the bags are empty
				result = true;//if the bags are empty then they are equal 
			}
			if (aBag.getCurrentSize() == this.getCurrentSize()) { //checks to see if bags other than 0 have the same size
				for(int i = 0; i < this.getCurrentSize(); i++) //for loop to cycle through the bag 
				{
					if(aBag.getFrequencyOf(array1[i]) == this.getFrequencyOf(array1[i])){	//checks the frequency of each index to see if they are equal to the other bag
						result = true;//returns true if they are equal
					}else {
						return false;//returns false if they are not equal
					}
				}
			}
		}
		return result;
	}

	/** Duplicate all the items in a bag.
	 * @return True if the duplication is possible.
	 */
	public boolean duplicateAll() {
		boolean success = true; // should always return true
		// if there is a problem allocating nodes
		// an exception will be thrown

		// COMPLETE THIS METHOD
		T [] thisArray  = this.toArray(); //converts the bag to array with new variable
		if(this.isEmpty()) {//checks to see if the bag is empty
			success = true; //returns true
		}else {//if bag is not empty
			for (int i = 0; i < thisArray.length; i++) {//for loop that goes through the bag
				success = this.add(thisArray[i]);//checks to see if success is true if it succeeds returns true if not then false
			}
		}
		return success;
	}  // end duplicateAll

	/** Remove all duplicate items from a bag
	 */
	public void removeDuplicates() {

		// COMPLETE THIS METHOD 
		for (int i = 0; i < getCurrentSize(); i++) {//forloop to iterate through the bags
			while (this.getFrequencyOf(toArray()[i]) > 1) {//gets the frequency of the bag changes it to an array and will remove if frequency is greater than 1
				this.remove(toArray()[i]);//removes all variables with a higher frequency than 1
			}
		}
		return;
	}  // end removeDuplicates
}
