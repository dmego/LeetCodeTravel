package cn.dmego.newcode.top101.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 没有重复项数组的全排列
 * @author dmego
 * @date 2022/03/01 15:59
 */
public class BM55 {

    // 结果
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    // 记录一组结果
    LinkedList<Integer> list = new LinkedList<>();
    // used 数组，记录元素是否被使用
    boolean[] used;

    // 回溯函数
    public void backtracking(int[] num) {
        // 递归终止条件
        if (list.size() == num.length) {
            result.add(new ArrayList<>(list));
        }
        // 横向遍历
        for (int i = 0; i < num.length; i++) {
            // 如果第 i 个元素没有被使用过
            if (!used[i]) {
                used[i] = true;
                list.addLast(num[i]);
                // 纵向遍历，递归
                backtracking(num);
                // 撤销
                used[i] = false;
                list.removeLast();
            }
        }
    }
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        used = new boolean[num.length];
        backtracking(num);
        return result;
    }
}
