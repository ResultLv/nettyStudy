package leetcode.middle;

import leetcode.TreeNode;

/**
 * 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author Result Lv
 * @date 2021/4/12 10:01 下午
 */
public class Solution236 {

    private static TreeNode ans = null;

    /**
     * 复杂度较高
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        // root作为根节点，包含p,q时，再递归查找左右子树
        if (isContainsNode(root, p) && isContainsNode(root, q)){
            // 查左子树不为空，则继续递归左子树查找；为空则再递归查找右子树
            TreeNode temp = lowestCommonAncestor(root.left, p, q);
            if (temp != null){
                ancestor = temp;
            }else{
                // 左子树找不到时递归查找右子树
                temp = lowestCommonAncestor(root.right, p, q);
                ancestor = temp != null ? temp : ancestor;
            }
        }else{
            return null;
        }
        return ancestor;
    }

    /**
     * 判断二叉树是否包含某个节点
     */
    public static boolean isContainsNode(TreeNode root, TreeNode node){
        if (root == null) return false;
        if (root == node) return true;
        boolean flag1 = false, flag2 = false;
        if (root.left != null){
            flag1 = isContainsNode(root.left, node);
        }
        if (root.right != null){
            flag2 = isContainsNode(root.right, node);
        }
        return flag1 || flag2;
    }

    /**
     * 深度优先遍历（DFS）查找最近公共祖先
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    /**
     * 深度优先遍历（DFS）
     */
    public static boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        //在当前节点
        boolean inCurrentNode = root == p || root == q;
        //在左节点
        boolean inLeft = dfs(root.left,p,q);
        //在右节点
        boolean inRight = dfs(root.right,p,q);
        if((inLeft && inRight) || (inCurrentNode && (inLeft || inRight))){
            ans = root;
        }
        return inLeft || inRight || inCurrentNode;
    }

    /**
     * 最简写法
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    public static void main(String[] args){
//        Integer[] nums = new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        Integer[] nums = new Integer[]{1,2};
        TreeNode root = TreeNode.buildTree(nums, 0);

        TreeNode p = root;
        TreeNode q = root.left;

        System.out.println(isContainsNode(root.left, p));
        System.out.println(isContainsNode(root.left, q));


        System.out.println(lowestCommonAncestor(root, p, q));
        System.out.println(lowestCommonAncestor1(root, p, q));
        System.out.println(lowestCommonAncestor2(root, p, q));
    }
}
