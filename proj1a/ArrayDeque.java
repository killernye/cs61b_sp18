/* Invariants
addFirst: The next item we want to add, will go into the position nextFirst
getFirst: The item we want to return is in the position nextFirst + 1 or 0
addLast: The last item we want to add, will go into position nextLast
getLast: The last item we want to return is in the position item.length - 1 or addLast - 1
size: The number of items in the list shoud be size.
 */

import java.util.Arrays;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;


    /** Creates an new empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    /** Inserts item into the front of the list. */
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            this.resize(items.length * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = this.minusOne(nextFirst);
    }

    /** Inserts item into the back of the list. */
    public void addLast(T item) {
        if(nextFirst == nextLast) {
            this.resize(items.length * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = this.plusOne(nextLast);
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the memory usage rate of the deque. */
    private double usageRate() {
        return this.size() * 1.0 / this.items.length;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int index = plusOne(nextFirst);
        for (int i = 0, len = size(); i < len; i++) {
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }

        System.out.println("UsageRate: " + this.usageRate());
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = this.plusOne(nextFirst);
        size -= 1;
        T removed = items[nextFirst];
        items[nextFirst] = null;
        if (items.length > 8 && usageRate() < 0.25) {
            resize(items.length / 2);
        }
        return removed;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = this.minusOne(nextLast);
        size -= 1;
        T removed = items[nextLast];
        items[nextLast] = null;
        if (items.length > 8 && usageRate() < 0.25) {
            resize(items.length / 2);
        }
        return removed;
    }

    /** Gets the item at the given index. */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[plusX(nextFirst,index + 1)];
    }

    private int minusOne(int index){
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int plusX(int index, int x) {
        if (x <= this.items.length - 1 - index) {
            return index + x;
        } else {
            return index - (size() - x);
        }
    }


    /** Resizs the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst+1, a, 0, nextLast-nextFirst-1);
        } else if (nextFirst == items.length - 1) {
            System.arraycopy(items, 0, a, 0, nextLast);
        } else {
            System.arraycopy(items, nextFirst+1, a, 0, items.length-nextFirst-1);
            System.arraycopy(items, 0, a, items.length-nextFirst-1, nextLast);
        }
        nextFirst = capacity - 1;
        nextLast = this.size();
        this.items = a;
    }

    /**
     *  Testing!!
     */
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

    public boolean checkValue(T expected, T actual) {
        if (!expected.equals(actual)) {
            System.out.println("Remove/get returns " + actual + ", but expected " + expected);
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

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test: ");

        ArrayDeque<Integer> arr = new ArrayDeque<>();
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(3);
        arr.addLast(4);

        System.out.println("Print the deque:");
        arr.printDeque();

        boolean passed = arr.checkEmpty(false, arr.isEmpty());

        passed = arr.checkSize(4, arr.size()) && passed;

        printTestStatus(passed);
    }

    public static void addRemoveTest() {
        System.out.println("Running add/remove tests: ");

        ArrayDeque<String> arr = new ArrayDeque<>();
        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst("one");
        arr.addLast("two");
        arr.addLast("three");
        arr.addLast("four");
        arr.addLast("five");
        arr.addLast("six");
        arr.addLast("seven");
        arr.addLast("eight");
        arr.removeLast();
        arr.removeLast();
        arr.addFirst("new");


        System.out.println("Print the deque:");
        arr.printDeque();

        passed = checkSize(7, arr.size()) && passed;

        passed = arr.checkValue("new", arr.get(0)) && passed;

        //passed = arr.checkValue("one", arr.removeFirst()) && passed;

        //passed = arr.checkValue("seven", arr.removeLast()) && passed;

        System.out.println("Print the final deque:");

        arr.printDeque();

        printTestStatus(passed);
    }

    public static void addResizeTest() {
        System.out.println("Running add/resize tests: ");

        ArrayDeque<Integer> arr = new ArrayDeque<>();
        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst(8);
        arr.addFirst(7);
        arr.addFirst(6);
        arr.addFirst(5);
        arr.addFirst(4);
        arr.addFirst(3);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(9);

        passed = checkSize(9, arr.size()) && passed;

        arr.printDeque();

        for(int i = 0; i < 6; i++) {
            arr.removeFirst();
            arr.printDeque();
        }

        passed = checkSize(3, arr.size()) && passed;
        printTestStatus(passed);

    }


    public static void main(String[] args) {

        System.out.println("Running tests: \n");
        addIsEmptySizeTest();
        addRemoveTest();
        addResizeTest();

    }
}
