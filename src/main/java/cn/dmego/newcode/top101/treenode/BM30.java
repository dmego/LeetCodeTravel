package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 二叉搜索树与双向链表
 * @author dmego
 * @date 2022/03/07 15:11
 */
public class BM30 {
    /**
     二叉搜索树的特点：
     左节点小于根节点值，右节点大于根节点值，对于左子树和右子树也是同样的
     我们对树进行中序遍历(左根右)，将会得到一个有序序列

     对树进行中序遍历，在遍历的过程中，将左右节点指向改变
     */
    // 定义一个前置节点，记录上一次遍历到的节点
    TreeNode preNode = null;
    // 定义最后返回的 root 节点，值是第一个被遍历到并且不为 null 的节点
    TreeNode root = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        // 如果节点为 null，结束递归返回 null
        if (pRootOfTree == null) return null;
        // 递归遍历左子树
        Convert(pRootOfTree.left);
        // 遍历到了当前节点
        // 如果 root = null, root 执行当前节点，也就是第一个遍历到的节点
        if (root == null) root = pRootOfTree;
        // 如果前一个遍历到的节点不为 null, 需要将前一个节点和当前节点重新连接为双向链表形式
        if (preNode != null) {
            // preNode.next = pRooOfTree
            preNode.right = pRootOfTree;
            // pRootOfTree.prev = preNode
            pRootOfTree.left = preNode;
        }
        // 更新前一个遍历到的节点指针，指向当前节点
        preNode = pRootOfTree;
        // 递归遍历右子树
        Convert(pRootOfTree.right);
        return root;
    }
}
