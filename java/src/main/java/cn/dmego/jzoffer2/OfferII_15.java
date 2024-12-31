package cn.dmego.jzoffer2;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 */
public class OfferII_15 {

    /**
     同 lc 438
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int left = 0, right = 0;
        int[] needs = new int[128];
        int[] windows = new int[128];
        int needsCut = 0, needsLen = 0;
        for (int i = 0; i < p.length(); i++) {
            needs[p.charAt(i)]++;
        }
        for (int i = 0; i < needs.length; i++) {
            if (needs[i] != 0) {
                needsLen++;
            }
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs[c] != 0) {
                windows[c]++;
                if (needs[c] == windows[c]) {
                    needsCut++;
                }
            }
            // 窗口已经形成
            if (right >= p.length()) {
                if (needsCut == needsLen) {
                    result.add(left);
                }
                c = s.charAt(left);
                left++;
                if (needs[c] != 0) {
                    if (needs[c] == windows[c]) {
                        needsCut--;
                    }
                    windows[c]--;
                }
            }
        }
        return result;
    }
}
