package cn.dmego.newcode.top101.dp;

/**
 * 斐波拉契数列
 *
 * @author dmego
 * @date 2022/04/20 17:49
 */
public class BM62 {

    public int Fibonacci(int n) {
        if (n == 1 || n == 2) return 1;
        int a = 1, b = 1;
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) {
                a = a + b;
            } else {
                b = a + b;
            }
        }
        return n % 2 == 1 ? a : b;
    }

}
