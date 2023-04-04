import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] array;

    // Construct an empty queue.
    @SuppressWarnings("unchecked")
    public ResizingArrayRandomQueue() {
        size = 0;
        array = (Item[]) new Object[1]; // queue items
    }

    // Is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // The number of items on the queue.
    public int size() {
        return size;
    }

    // Add item to the queue.
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null");

        if (size == array.length)
            resize(2 * array.length); // doubles size of array

        array[size++] = item; // adds item to queue
    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("Randomized Queue is empty");

        //generates random number and uses that to remove an item
        int pos = StdRandom.uniform(size);
        Item item = array[pos];
        array[pos] = array[--size];

        array[size] = null; // avoid loitering

        // shrink size of array
        if (size > 0 && size == array.length / 4)
            resize(array.length / 2);

        return item;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        if (size == 0) throw new NoSuchElementException("Randomized Queue is empty");

        // generates random number that picks an item to return
        int pos = StdRandom.uniform(size);
        return array[pos];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    @SuppressWarnings("unchecked")
    private class RandomQueueIterator implements Iterator<Item> {

        private int i = size;
        private Item[] RandomizedItems;

        RandomQueueIterator() {
            //creates a copy of array so when can use it to shuffle the items
            //also makes resizing the array not an issue as the copy is made
            //to the new size as well
            RandomizedItems = (Item[]) new Object[size];
            System.arraycopy(array, 0, RandomizedItems, 0, size);
            StdRandom.shuffle(RandomizedItems);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return RandomizedItems[--i];
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                temp[i] = array[i];
            }
        }
        array = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
                new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
