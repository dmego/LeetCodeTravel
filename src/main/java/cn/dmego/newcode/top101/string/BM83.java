package cn.dmego.newcode.top101.string;

import java.util.Arrays;

/**
 * @author dmego
 * @date 2022/03/10 23:28
 */
public class BM83 {

    public String trans(String s, int n) {
        // write code here
        // 先将 s 的所有字符大小写反转
        StringBuilder ns = new StringBuilder();
        int f = 'a' - 'A';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + f);
            } else if (c >= 'a' && c<= 'z') {
                c = (char) (c - f);
            }
            ns.append(c);
        }
        String[] strs = ns.toString().split(" ", -1);
        int i = 0; int j = strs.length - 1;
        while (i < j) {
            String temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
            i++;
            j--;
        }
        StringBuilder res = new StringBuilder();
        for (i = 0; i < strs.length; i++) {
            if (i == strs.length - 1) res.append(strs[i]);
            else res.append(strs[i]).append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "h i ";
        BM83 bm = new BM83();
        String trans = bm.trans(s, s.length());
        System.out.println(trans);
    }
}
