package cn.dmego.leetcode.solution.lc350_lc400;

/**
 * 两整数之和
 * 不使用 + - 符号实现
 *
 * @author dmego
 * @date 2022/10/10 14:31
 */
public class Solution_371 {

    /**
     首先我们要知道，在计算机底层，是没有直接的 + - 运算的，所有的运算都是通过对二进制数据进行位运算得到的
     在说如何使用位运算来求解之前，先看看日常使用的十进制是如何求加法运算的：
        1. 假设现在求 56 + 48 的值，
        2. 按照十进制求解，先进行个位计算：6 + 8 = 14，结果超过了10，需要进位，也就是进位 1，个位是 4
        3. 再进行十位计算：5 + 4 = 9，同时还需要加上上一步的进位数 1，也就是 10，正好满足进位，也就是还需要进位 1，十位是 0
        4. 最后的结果是 百位 1， 十位 0， 个位 4，结果就是 104

     上面在进行计算时，还是按照 + 运算思维。可以换个角度 从进位和非进位的角度来计算：
        1. 先计算进位：个位(6 + 8 = 14, 也就是进位 1)，十位(5 + 4 = 9, 也就是进位 0)，总进位就是 1 * 10 + 0* 100 = 10
        2. 再计算非进位：个位(6 + 8 = 14, 非进位 4)，十位(5 + 4 = 9，也就是非进位 9)，总非进位就是 9 * 10 + 4 = 94
        3. 我们可以看到 (56 + 48 = 94 + 10)，将 56 + 48 的求和转为 10 + 94 的求和，我们对其继续进行 1. 2. 两步操作
        4. 先计算进位：个位(0 + 4 = 4, 进位 0)，十位(1 + 9 = 10, 进位 1)，总进位：0 * 10 + 1* 100 = 100
        5. 再计算非进位：个位(0 + 4 = 4, 非进位 4)，十位(1 + 9 = 10, 非进位 0)，总非进位：4 + 0*10 = 4
        6. 56 = 48 = 94 + 10 = 100 + 4， 继续对 (100 + 4) 进行递归的进位和非进位计算
        7. 进位：个位(0 + 4 = 4, 进位 0)，十位(0 + 0 = 0, 进位 0)， 百位(1 + 0 = 1, 进位 0)，总进位 0
        8. 非进位：个位(0 + 4 = 4, 非进位 4)，十位(0 + 0 = 0, 非进位 0)， 百位(1 + 0 = 1, 非进位 1)，总非进位 4 + 0*10 + 1*100 = 104
        9. 当进位值等于 0，时，递归停止，此时可以看到非进位上的数就是结果 104

     上述是十进制的计算，并且使用的还是 + 符合思维，我们在二进制下，如何进行计算，也就是如何求 0, 1 两个数进行 + 的进位和非进位
     我们先看 0 1 运算时进行进位的情况：
        00 + 00 = 00，00 + 01 = 01， 01 + 00 = 01， 01 + 01 = 10, 可以看到只要 1 和 1进行相加时，会进位 1，其他都是 0
        这符合按位与(&)的规则: 只要 1 & 1 = 1， 其他都是 0
     再看 0 1 运算进行非进位情况：
        00 + 00 = 00，00 + 01 = 01， 01 + 00 = 01， 01 + 01 = 10, 可以看到只要 1 和 1，0 和 0 相加时，非进位 0，其他都是 1
        这符合异或(^)的规则：1 ^ 0 = 1, 0 ^ 1 = 1, 0 ^ 0 = 0, 1 ^ 1 = 0

     所以我们可以利用按位与 和 异或运算来进行求和

     思考：如何求两数之差，也就是做减法运算:
        a - b = a + (-b) ---> 关键如何求 b 的相反数
        另一个知识点：一个数的相反数是 这个数按位取反 再加上 1
        也就是 a - b = a + (~b + 1)

     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        return add(a, b);
    }

    /**
     *
     * @param carry 进位
     * @param ret 非进位
     */
    public int add(int carry, int ret) {
        // 当 进位等于 0 时，非进位 ret 就是结果，进行返回
        if (carry == 0) return ret;
        int oldCarry = carry;
        carry = (carry & ret) << 1;
        ret = oldCarry ^ ret;
        return add(carry, ret);
    }

    // 极简写法
    public int getSum2(int a, int b) {
        return b == 0 ? a : getSum2(a ^ b, (a & b) << 1);
    }

    // 减法运算
    public int getCut(int a, int b) {
        return add(a, add(~b, 1));
    }

    public static void main(String[] args) {
        Solution_371 s = new Solution_371();
        int sum = s.getSum(56, 48);
        System.out.println(sum);
        System.out.println(~4 + 1);
        System.out.println(~(-4) + 1);
    }



}
