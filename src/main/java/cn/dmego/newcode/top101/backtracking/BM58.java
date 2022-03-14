package cn.dmego.newcode.top101.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 字符串的排列
 * @author dmego
 * @date 2022/03/14 10:08
 */
public class BM58 {

    // 结果数组
    ArrayList<String> result = new ArrayList<>();
    StringBuilder buff = new StringBuilder();
    // 判断当前字符是否使用的数组
    boolean used[];
    // 回溯函数
    public void backtracking(char[] strs) {
        // 结果字符串已经包含原始字符串所有字符，递归结束
        if (buff.length() == strs.length) {
            // 将字符串加到结果集
            result.add(buff.toString());
        }
        // 横向遍历
        for (int i = 0; i < strs.length; i++) {
            // 在横向上去重：strs[i] = strs[i - 1] 并且 strs[i - 1] 没有正在使用，
            // 说明是横向上的，跳过
            if (i > 0 && strs[i] == strs[i - 1] && used[i - 1]) continue;
            // 如果 strs[i] 没有被使用，used[i] = false
            if (!used[i]) {
                // 标记正在使用
                used[i] = true;
                // 当前字符加到字符串
                buff.append(strs[i]);
                // 纵向递归
                backtracking(strs);
                // 清除标记
                used[i] = false;
                // 从字符串删除当前字符
                buff.deleteCharAt(buff.length() - 1);
            }
        }
    }

    public ArrayList<String> Permutation(String str) {
        // 空字符串，直接返回
        if (str == null || str.length() == 0) return result;
        // 初始化使用数组
        used = new boolean[str.length()];
        // 字符串转字符数组
        char[] strs = str.toCharArray();
        // 为了去重，先排序
        Arrays.sort(strs);
        backtracking(strs);
        return result;
    }

}
