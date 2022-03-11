package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

import java.util.LinkedList;

/**
 * 判断是不是完全二叉树
 * @author dmego
 * @date 2022/03/11 14:40
 */
public class BM35 {

    public boolean isCompleteTree (TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        boolean hasNull = false;// 标记是否遍历到 null
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node == null) {
                    // 记录遍历到 null 节点
                    hasNull = true;
                } else {
                    // 如果 node != null 且之前遍历到了 null 节点，说明不满足完全二叉树条件，返回 false
                    if (hasNull) return false;
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                }
            }
        }
        return true;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        BM35  bm = new BM35();
        boolean completeTree = bm.isCompleteTree(root);
        System.out.println(completeTree);

    }
}
