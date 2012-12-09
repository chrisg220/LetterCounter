// Christopher Gonzales
//
// This program keeps track of an inventory of letters of the alphabet. The program takes a
// String and computes how many of each letter are in the String.

public class LetterCounter {
	private int[] inventoryData;		// list of integers
	private int size;					// current sum of letter counts in the LetterInventory
    	
	public static final int DEFAULT_CAPACITY = 26;
	
	// pre : data is a String
	// post: constructs a LetterInventory object with an inventory of each letter passed in
	public LetterCounter(String data) {
		inventoryData = new int[DEFAULT_CAPACITY];
		size = 0;
	
		String data_lowerCase = data.toLowerCase();
		for (int i = 0; i < data_lowerCase.length(); i++) {
			char newChar = data_lowerCase.charAt(i);
			if (newChar >= 'a' && newChar <= 'z') {
				inventoryData[Math.abs('a' - newChar)]++;
				size++;
			}
		}
	}
	
	// post: returns the current sum of letter counts in the LetterInventory instance
	public int size() {
		return size;
	}
	 
	// post: returns true if array is empty, false otherwise
	public boolean isEmpty() {
		return size == 0;
	}
	
	// post: returns a sorted lowercase String of the inventory surrounded with square brackets
	public String toString() {
		String result = "[";
		for (int j = 0; j < DEFAULT_CAPACITY; j++) {
			for (int k = 0; k < inventoryData[j]; k++) {
				result += (char) ('a' + j);
			}
		}
		result += "]";
		
		return result;
	}
	
	// pre : char is a letter (throws IllegalArgumentException if not)
	// post: returns the count for the given letter
	public int get(char letter) {
		char lowerCase = processLetter(letter);
		
		return inventoryData[Math.abs('a' - lowerCase)];
	}
	
	// pre : char is a letter && value >= 0 (throws IllegalArgumentException if not)
	// post: sets the count for given letter to given value
	public void set(char letter, int value) {
		char lowerCase = processLetter(letter);
		if (value >= 0) {
			size += value - inventoryData[Math.abs('a' - lowerCase)];
			inventoryData[Math.abs('a' - lowerCase)] = value;
		}
	}
	
	// pre : char is a letter (throws IllegalArgumentException if not)
	// post: returns the lowercase letter of given letter
	private char processLetter(char letter) {
		char lowerCase = Character.toLowerCase(letter);
		if (lowerCase < 'a' || lowerCase > 'z')
			throw new IllegalArgumentException("checkLetter: Illegal characters were passed in.");
		
		return lowerCase;
	}
	
	// pre : other argument must be a LetterCounter reference
	// post: returns a new LetterCounter object that represents the sum of this LetterCounter
	// and the other given LetterCounter.
	public LetterCounter add(LetterCounter other) {
		LetterCounter inventory = new LetterCounter("");
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			inventory.inventoryData[i] = this.inventoryData[i] + other.inventoryData[i];
		}
		inventory.size = this.size() + other.size();
		
		return inventory;
	}
	
	// pre : other argument must be a LetterCounter reference
	// post: returns a new LetterInventory object that represents the difference between the other
	// 		 LetterCounter from this LetterCounter.
	public LetterCounter subtract(LetterCounter other) {
		LetterCounter inventory = new LetterCounter(""); 
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			inventory.inventoryData[i] = this.inventoryData[i] - other.inventoryData[i];
			if (inventory.inventoryData[i] < 0)
				return null;
		}
		inventory.size = this.size() - other.size();
		
		return inventory;
	}
}