package hw2.csc4585;
/*
 * Stephen Vondenstein
 *
 * Setup and example execution of Lamport's Bakery Algorithm with 10 threads
 * When running this code on my machine, it took about 90 seconds to execute on average
 */
public class BakeryExample {

    public static void main(String[] args) {
        int n = 10; //number of threads
        Bakery b = new Bakery(n);
        //From example code:
        class Shared {
            public volatile int c = 0;
        }
        final Shared v = new Shared();

        Runnable r = new Runnable() {
            public void run() {
                for (int i = 0; i<10000; i++) {
                    b.lock();
                    try {
                        v.c++;
                    } finally {
                        b.unlock();
                    }
                }
                System.out.println("thread:" + ThreadID.get() + ", c-value = " + v.c);
            }
        };

        System.out.println("Bakery Lock algorithm with " + n + " threads...");

        for (int i = 0; i < n; i++) {
            new Thread(r).start();
        }

        System.out.println("Started " + n + " threads...");
    }
}