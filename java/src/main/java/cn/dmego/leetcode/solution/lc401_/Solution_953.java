package cn.dmego.leetcode.solution.lc401_;

import java.util.HashMap;

/**
 * @author dmego
 * @date 2022/05/17 22:57
 */
public class Solution_953 {

    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 0; i <= 20; i++) {
            for (int j = 1; j < words.length; j++) {
                if (i >= words[j - 1].length()) return true;
                else {
                    if (i >= words[j].length()) return false;
                    else {
                        int num1 = map.get(words[j - 1].charAt(i));
                        int num2 = map.get(words[j].charAt(i));
                        if (num1 > num2) return false;
                        else if (num1 < num2) return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"word","world","row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        Solution_953 s = new Solution_953();
        boolean sorted = s.isAlienSorted(words, order);
        System.out.println(sorted);
    }

}
