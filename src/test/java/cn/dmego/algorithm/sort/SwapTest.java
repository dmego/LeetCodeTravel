package cn.dmego.algorithm.sort;
import org.junit.Test;

public class SwapTest {

    public static final int[] nums = {1, 2};
    public static final int times = 100000000;

    /*
    swapTemp time: 13755 毫秒
    swapCal time: 43353 毫秒
    swapXor time: 105456 毫秒
     */
    @Test
    public void testSwap() throws InterruptedException {
        Thread thread1 = new Thread(this::testSwapTemp);
        Thread thread2 = new Thread(this::testSwapCal);
        Thread thread3 = new Thread(this::testSwapXor);
        thread1.run();
        thread2.run();
        thread3.run();
        thread1.join();
        thread2.join();
        thread3.join();
    }


    public void testSwapTemp() {
        long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            Swap.swap(nums, 0, 1);
        }
        System.out.println("swapTemp time: " + ((System.nanoTime() - start)/1000) + " 毫秒");
    }

    public void testSwapCal() {
        long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            Swap.swapCal(nums, 0, 1);
        }
        System.out.println("swapCal time: " + ((System.nanoTime() - start)/1000) + " 毫秒");
    }

    public void testSwapXor() {
        long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            Swap.swapXor(nums, 0, 1);
        }
        System.out.println("swapXor time: " + ((System.nanoTime() - start)/1000) + " 毫秒");
    }

}
