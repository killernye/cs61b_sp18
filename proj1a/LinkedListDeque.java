/**
* Create Deque class based on LinkedList.
 */
public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private Node next;
        private T item;

        private Node(Node prevN, Node nextN, T x) {
            prev = prevN;
            next = nextN;
            item = x;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item  anything you want to put in the front of the deque.
     */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, sentinel.next, item);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item   anything you want to put at the back of the deque.
     */
    public  void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, sentinel, item);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Test if the Deque is empty or not.
     * @return  True if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return sentinel == sentinel.next;
    }

    /**
     * return the size of the list.
     * @return      the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, seqarated by a space.
     */
    public void printDeque() {
        Node ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the back of the deque.
     * @return   the last item if it exists, null otherwise.
     */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            Node last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last.item;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return   The first item if it exists, null otherwise.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            Node first = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first.item;
        }
    }

    /**
     * Gets the item at the given index if no such item exists, return null.
     * @param index    where 0 is the front , 1 is the next, and so forth.
     * @return   The item at the given index.
     */
    public T get(int index) {
        Node ptr = sentinel;
        int s = size;

        while(s > 0) {
            ptr = ptr.next;
            if (index == 0) {
                return ptr.item;
            }
            index -= 1;
            s -= 1;
        }
        return null;
    }

    private T getHelper(int i, Node n, int s) {
        if (s == 0){
            return null;
        } else if (i == 0) {
            return n.item;
        } else {
            return getHelper(i-1, n.next, s-1);
        }
    }


    /**
     *  The same functionality as get but uses recursion.
     * @param index ..
     * @return ...
     */
    public T getRecursive(int index) {
        return getHelper(index+1, sentinel, size+1);
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(19);
        deque.addLast(3);
        deque.addLast(5);
        deque.printDeque();
        System.out.println(123);
    }
}

