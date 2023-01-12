package cn.dmego.newcode.top101.string;


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


    public String trans2(String s, int n) {
        char[] c = s.toCharArray();
        reverse(c, 0, n - 1);
        int i = 0;
        boolean flag = false;
        for (int j = 0; j < n; j++) {
            if ('a' <= c[j] && c[j] <= 'z' || 'A' <= c[j] && c[j] <= 'Z') {
                if (!flag) {
                    i = j;
                    flag = true;
                }
            } else if (c[j] == ' ') {
                if (flag) {
                    flag = false;
                    reverse(c, i, j - 1);
                    upLower(c, i, j - 1);
                }
            }
        }
        if (flag) {
            reverse(c, i, n - 1);
            upLower(c, i, n - 1);
        }
        StringBuilder buff = new StringBuilder();
        for (char ch : c) {
            buff.append(ch);
        }
        return buff.toString();
    }

    // 反转字符串 s, 从 l 到 r
    public void reverse(char[] s, int l, int r) {
        while (l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++;
            r--;
        }
    }

    // 将字符串大小写反转, 从 l 到 r
    public void upLower(char[] s, int l, int r) {
        int f = ('a' - 'A');
        while (l <= r) {
            if ('A' <= s[l] && s[l] <= 'Z') {
                s[l] = (char) (s[l] + f);
            } else if ('a' <= s[l] && s[l] <= 'z') {
                s[l] = (char) (s[l] - f);
            }
            l++;
        }
    }


    /**
     - 将字符串 s 以空格进行分割，注意要保留首位空格，以及中间多余空格。使用 split(" ", -1)
     - 从后往前遍历分割后的字符串数组，保证单词反序
     - 对于每一个单词，从前往后遍历每个字符，进行大小写转换。
     - 双层 for 循环，在第二层每次大小写转换完之后，将字符添加到 StringBuffer
     - 在第二层结束后，第一层最后需要将空格添加回 StringBuffer
     - 注意最后返回结果时，需要删除最后一个多余空格
     */
    public static String trans3(String s, int n) {
        // limit 设置成负数，保证不会将字符串结尾空格丢弃
        String[] vals = s.split(" ", -1);
        StringBuilder buff = new StringBuilder();
        for (int i = vals.length - 1; i >= 0; i--) {
            String word = vals[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                // 大写字母 ASCII 范围. [65, 90]
                // 小写字母 ASCII 范围. [97, 122]
                if (65 <= c && c <= 90) {
                    c = (char) (c + 32);
                } else if (97 <= c && c <= 122) {
                    c = (char) (c - 32);
                }
                /*
                int f = 'a' - 'A';
                if ('A' <= c && c <= 'Z') {
                    c = (char) (c + f);
                } else if ('a' <= c && c <= 'z') {
                    c = (char) (c - f);
                }
                */
                buff.append(c);
            }
            buff.append(" ");
        }
        buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }

    public static void main(String[] args) {
        String s = "h i ";
        BM83 bm = new BM83();
        String trans = bm.trans(s, s.length());
        System.out.println(trans);
    }
}
