package leetcode;

/**
 * 二叉树定义
 * @author Result Lv
 * @date 2021/4/12 10:00 下午
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    /**
     * 递归构建树
     * @param nums  数组
     * @param index 根节点下标
     * @return      根节点
     */
    public static TreeNode buildTree(Integer[] nums, int index){
        TreeNode root = nums[index] != null ? new TreeNode(nums[index]) : null;
        if (root == null) return root;
        if (2*index + 1 < nums.length){
            root.left = buildTree(nums, 2*index + 1);
        }else{
            root.left = null;
        }
        if (2*index + 2 < nums.length){
            root.right = buildTree(nums, 2*index + 2);
        }else{
            root.right = null;
        }
        return root;
    }

    /**
     * 先序遍历打印树
     */
    @Override
    public String toString() {
        String result = this.val + ",";
        TreeNode root = this;
        if (root.left != null){
            result += root.left.toString();
        }
        if (root.right != null){
            result += root.right.toString();
        }
        return result;
    }
}
