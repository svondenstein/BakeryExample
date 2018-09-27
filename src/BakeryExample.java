/*
 * Stephen Vondenstein
 *
 * Setup and example execution of Lamport's Bakery Algorithm with n threads
 * When running this code on my machine, it took about 90 seconds to execute with n = 10.
 */
import java.util.Scanner;

public class BakeryExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of threads in the bakery: ");
        int n = 0;

        //Ensure that we always get an integer > 0 for thread count
        while (n < 1) {
            System.out.print("Enter the number of threads (n > 0) in the bakery: ");
            try {
                n = Integer.parseInt(scanner.next()); //number of threads
            } catch (NumberFormatException e) {
                System.out.println("Whoops! Looks like you didn't enter an integer greater than 0.");
            }
        }

        Bakery b = new Bakery(n);
        //From example code:
        class Shared {
            public volatile int c = 0;
        }
        final Shared v = new Shared();

        Runnable r = () -> {
            for (int i = 0; i<10000; i++) {
                b.lock();
                try {
                    v.c++;
                } finally {
                    b.unlock();
                }
            }
            System.out.println("thread:" + ThreadID.get() + ", c-value = " + v.c);
        };

        System.out.println("Bakery Lock algorithm with " + n + " threads...");

        for (int i = 0; i < n; i++) {
            new Thread(r).start();
        }

        System.out.println("Started " + n + " threads...");
    }
}