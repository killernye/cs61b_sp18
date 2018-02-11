/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out size checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out value checks. */
	public static boolean checkIntValue(Integer expected, Integer actual) {
		if (expected != actual) {
			System.out.println("Remove/get() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}


	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");


		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		lld1.addLast(5);
		//should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeLast();
		//should be empyt
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		System.out.println("Print out the deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	public static void removeGetTest() {

		System.out.println("Running remove/get test for int list.");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

		//should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(5);
		int v1 = lld1.removeFirst();

		// 5 should be returned
		passed = checkIntValue(5, v1) && passed;

		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);

		System.out.println("Print out the deque:");
		lld1.printDeque();

		//the size should be 3
		passed = checkSize(3, lld1.size()) && passed;

		// get the 0th item use get method
		int v2 = lld1.get(0);

		// v2 should be 1
		passed = checkIntValue(3, v2) && passed;

		// the 2th item got by getRecursive method should be 3
		passed = checkIntValue(1, lld1.getRecursive(2)) && passed;

		printTestStatus(passed);
	}

	public static void getOutOfIndexTest() {
		System.out.println("Running get test on outOfIndex situation.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		lld1.addFirst(1);
		lld1.addLast(2);
		lld1.addLast(3);

		System.out.println("Print out the deque:");
		lld1.printDeque();

		// check the size, should be 3
		boolean passed = checkSize(3, lld1.size());

		// test for the out of index situation for get method
		passed = checkIntValue(null, lld1.get(5)) && passed;

		// test for the out of index situation for getRecursive method
		passed = checkIntValue(null, lld1.getRecursive(10)) && passed;

		printTestStatus(passed);
	}


	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		removeGetTest();
		getOutOfIndexTest();
	}
} 