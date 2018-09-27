/*
 * Stephen Vondenstein
 *
 * Implementation of the Bakery Lock as demonstrated in the textbook
 */
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Bakery {
    //BakeryExample algorithm requires sequential consistency so we use atomic variables
    private AtomicBoolean[] flag;
    private AtomicInteger[] label;
    private static int n;

    public Bakery(int n) {
        this.n = n;
        flag = new AtomicBoolean[n];
        label = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            flag[i] = new AtomicBoolean();
            label[i] = new AtomicInteger();
        }
    }

    public void lock() {
        int i = ThreadID.get();
        flag[i].set(true);
        label[i].set(findMax(label) + 1);
        for (int k = 0; k < n; k++) {
            while ((k != i) && flag[k].get() && ((label[k].get() < label[i].get()) || ((label[k].get() == label[i].get()) && k < i))) {
                //wait
            }
        }
    }

    public void unlock() {
        flag[ThreadID.get()].set(false);
    }

    //Find the maximum value contained in an AtomicInteger array
    private int findMax(AtomicInteger[] a) {
        int max = 0; //starting at 0 is fine here because we will always have at least one thread.
        for (AtomicInteger e : a) {
            if (e.get() > max) {
                max = e.get();
            }
        }
        return max;
    }
}