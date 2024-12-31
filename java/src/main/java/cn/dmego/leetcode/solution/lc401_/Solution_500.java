package cn.dmego.leetcode.solution.lc401_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dmego
 * @date 2021/10/31 19:58
 */
public class Solution_500 {

    public static String[] findWords(String[] words) {
        Set<Character> set1 = new HashSet<>(Arrays.asList('q','w','e','r','t','y','u','i','o','p'));
        Set<Character> set2 = new HashSet<>(Arrays.asList('a','s','d','f','g','h','j','k','l'));
        Set<Character> set3 = new HashSet<>(Arrays.asList('z','x','c','v','b','n','m'));

        List<String> resList = new ArrayList<>();
        for (String wordTrue : words) {
            String word = wordTrue.toLowerCase();
            int len = word.length();
            int j = 0;
            while (true) {
                while (j < len && set1.contains(word.charAt(j))) {
                    j++;
                }
                if (j == len) {
                    resList.add(wordTrue);
                    break;
                }
                j = 0;
                while (j < len && set2.contains(word.charAt(j))) {
                    j++;
                }
                if (j == len) {
                    resList.add(wordTrue);
                    break;
                }
                j = 0;
                while (j < len && set3.contains(word.charAt(j))) {
                    j++;
                }
                if (j == len) {
                    resList.add(wordTrue);
                    break;
                }
                break;
            }
        }
        return resList.toArray(new String[0]);
    }

    public static void main(String[] args) {

    }


}
