import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.
public class Subset {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        int temp = k;
        LinkedDeque queue = new LinkedDeque();

        // fills up queue and limits size to k elements
        while (!StdIn.isEmpty() && temp != 0) {
            String input = StdIn.readString();
            queue.addFirst(input);
            temp--;
        }
        //generates random number that is used to see if it will remove from the front of the queue or the end
        // then prints it to screen
        while (k != 0) {

            int place = StdRandom.uniform(queue.size() + 1);
            if (place % 2 == 0) {
                System.out.println(queue.removeFirst());
            } else {
                System.out.println(queue.removeLast());
            }
            k--;
        }
    }
}
