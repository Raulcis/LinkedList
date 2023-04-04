import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
        first = null;
        last = null;
        size = 0;
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
        return size;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        // If its the only node in the list then it does a "special" link
        if (first == null) {
            first = new Node();
            first.next = null;
            first.prev = null;
            first.item = item;
            last = first;
            size++;
        } else { // if more than one node in the list it does this link pattern
            Node oldfirst = first;
            first = new Node();
            first.prev = null;
            first.next = oldfirst;
            first.item = item;
            oldfirst.prev = first;
            size++;
        }


    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        // If its the only node in the list then it does a "special" link
        if (last == null) {
            last = new Node();
            last.next = null;
            last.prev = null;
            last.item = item;
            first = last;
            size++;
        } else {// if more than one node in the list it does this link pattern
            Node oldlast = last;
            last = new Node();
            last.prev = oldlast;
            last.next = null;
            last.item = item;
            oldlast.next = last;
            size++;
        }


    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        //assigns first to the new first while removing old first
        Node oldfirst = first;
        first = oldfirst.next;

        if (first == null) last = null;
        size--;

        return oldfirst.item;

    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        //assigns last to the new last while removing old last
        Node oldlast = last;
        last = oldlast.prev;

        if (last == null) first = null;
        size--;

        return oldlast.item;

    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        DequeIterator() {

        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            //if current is null then no more elements to iterate through
            if (current == null) throw new NoSuchElementException();

            // gets item and moves the current variable to the next node
            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
