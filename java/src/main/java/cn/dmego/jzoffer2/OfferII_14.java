package cn.dmego.jzoffer2;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 * 同 lc 567
 */
public class OfferII_14 {

    public boolean checkInclusion(String s1, String s2) {
        int left = 0, right = 0;
        int[] needs = new int[128];
        int[] windows = new int[128];
        int needsLen = 0, needsCut = 0;
        for (int i = 0; i < s1.length(); i++) {
            needs[s1.charAt(i)]++;
        }
        for (int i = 0; i < needs.length; i++) {
            if (needs[i] != 0)
                needsLen++;
        }
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (needs[c] != 0) {
                windows[c]++;
                if (needs[c] == windows[c]) {
                    needsCut++;
                }
            }
            // 窗口大小形成
            if (right >= s1.length()) {
                if (needsCut == needsLen) {
                    return true;
                }
                c = s2.charAt(left);
                left++;
                if (needs[c] != 0) {
                    if (windows[c] == needs[c]) {
                        needsCut--;
                    }
                    windows[c]--;
                }
            }
        }
        return false;
    }

}
