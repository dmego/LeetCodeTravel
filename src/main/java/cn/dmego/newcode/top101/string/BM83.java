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

    public static void main(String[] args) {
        String s = "h i ";
        BM83 bm = new BM83();
        String trans = bm.trans(s, s.length());
        System.out.println(trans);
    }
}
