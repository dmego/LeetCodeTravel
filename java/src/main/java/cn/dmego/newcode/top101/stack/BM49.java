package cn.dmego.newcode.top101.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 表达式求值
 请写一个整数计算器，支持加减乘三种运算和括号。
 数据范围： 0 ≤ ∣s∣ ≤ 100，保证计算结果始终在整型范围内
 * @author dmego
 * @date 2022/03/15 09:55
 */
public class BM49 {

    /**
     (-2*(3-4*5)+6)
     对于表达式的求值都可以使用两个栈来实现
     Stack1 (ops): 用来保存运算符 ( ) + - * 等运算符
     Stack2 (nums): 用来保存运算数

     如何遍历表达式进行求值：
     1. 遇到空格: 表达式中可能存在空格，我们需要先将字符串中的空格全部删除, s.replace(" "，"")
     2. 遇到 ( : 直接入 ops 栈，等待与之匹配的 )
     3. 遇到 ) : ops 栈出栈一个元素，nums 出栈两个元素， 进行运算求值, 然后将运算结果入到 nums 栈 (注意nums后出栈的元素是运算时左边的元素)
     4. 遇到 数字: 不能直接入栈，因为不止一位，需要继续往前遍历，取出一整个运算数，然后入栈到 nums
     5. 遇到 + - *: 将运算符入 ops 栈。 在入栈前，需要先把当前运算符之前能计算的都计算：
        5.1 判断当前运算符(c) 与 ops 栈顶运算符(peek)的优先级： peek >= c 时，nums 和 ops 出栈元素进行运算
        5.2 重复 5.1 直到没有运算符或者 遇到了左括号 （
     6. 注意点：
        6.1 为了保证负数和减法运算的正确性，把负数 X 看成 0 - |x|, 所有遇到 - 号时，如果前一位不是数字，则将数字 0 加入 nums 栈
        6.2 从理论上，我们还需要考虑计算中间是大数的情况（将int 换成 long）


     */
    public int solve (String s) {
        if (s == null || s.length() == 0) return 0;
        // 运算符栈
        Deque<Character> ops = new ArrayDeque<>();
        // 运算数栈
        Deque<Integer> nums = new ArrayDeque<>();
        // 运算符优先级 Map
        Map<Character, Integer> priority = new HashMap<Character, Integer>() {{
            put('+', 1);
            put('-', 1);
            put('*', 2);
        }};
        // 去除表达式里的空格
        s = s.replaceAll(" ", "");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 左括号入 ops 栈
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近的一个左括号为止
                while (!ops.isEmpty()) {
                    //ops 和 nums 出栈，计算结果
                    if (ops.peekLast() != '(') {
                        calc(ops, nums);
                    } else {
                        // 左括号出栈，退出循环
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                // 如果字符 c 是数字
                if (Character.isDigit(c)) {
                    int j = i;
                    int n = 0;
                    // 循环遍历表达式，取出完整的数字
                    while (j < s.length() && Character.isDigit(s.charAt(j))) {
                        n = n * 10 + (s.charAt(j++) - '0');
                    }
                    // 将数字添加到 nums 栈
                    nums.addLast(n);
                    // 因为下标 j 是下一个待遍历的字符，在大的 for 循环内，最后还需要 i++，所以 i = j - 1;
                    i = j - 1;
                } else {
                    // 如果是第一个字符，并且是 + 或 -, 为了能正常计算，nums先添加一个 0
                    // 如果前一个元素也是运算符，为了能正常计算，nums也先添加一个 0
                    if ((i == 0 && (c == '-' || c == '+')) ||
                            (i > 0 && (s.charAt(i - 1) == '('  || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-'))) {
                        nums.addLast(0);
                    }

                    // 在将当前运算符添加到 ops 栈之前，先把栈内能计算的先计算了
                    while (!ops.isEmpty()) {
                        // 如果 ops 栈顶运算符优先级 >= 当前运算符 c 才进行计算
                        if (ops.peekLast() != '(' && priority.get(ops.peekLast()) >= priority.get(c)) {
                            calc(ops, nums);
                        } else {
                            // 退出循环
                            break;
                        }
                    }
                    // 将当前运算符添加到 ops 栈, 不能在循环内添加，有可能栈为空
                    ops.addLast(c);
                }
            }
        }

        while(!ops.isEmpty() && ops.peekLast() != '(') calc(ops, nums);
        return nums.peekLast();
    }

    // nums 出栈两个元素，ops 出栈一个元素，进行计算
    public void calc(Deque<Character> ops, Deque<Integer> nums) {
        // 栈元素不够运算直接返回
        if (ops.isEmpty()) return;
        if (nums.isEmpty() || nums.size() < 2) return;
        char c = ops.pollLast();
        int b = nums.pollLast();
        int a = nums.pollLast();
        if (c == '+') nums.addLast(a + b);
        if (c == '-') nums.addLast(a - b);
        if (c == '*') nums.addLast(a * b);
    }


    public static void main(String[] args) {
        BM49 bm = new BM49();
        int solve = bm.solve("- ( 3 + 2)* 3 *(-4+1* 3 ) ");
        System.out.println(solve);
    }



}
