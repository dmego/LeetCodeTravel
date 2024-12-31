
/**
 * @author dmego
 * @date 2021/12/31 08:45
 */
public class Test {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws InterruptedException {
       int i = 5;
        System.out.println(i == ((5 + 6) / 2));
    }




    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static int roundUpToPowerOf2(int cap) {
        return cap >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (cap > 1) ? Integer.highestOneBit((cap - 1) << 1) : 1;
    }

    static int tableSizeFor_JDK11(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
