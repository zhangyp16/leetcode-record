package tree;

import java.util.*;

public class TreeDemo {

    public static void main(String[] args) {

    }

    //DFS递归写法
    public int maxDepthDFS(Node root) {
        if(root == null)
            return 0;
        int depth = 0;
        for(int i = 0;i<root.children.size();i++){
            depth = Math.max(depth,maxDepth(root.children.get(i)));
        }
        return depth+1;
    }

    //层序遍历解法
    public int maxDepth(Node root) {
        if(root == null)
            return 0;
        if(root.children.size() == 0)
            return 1;
        int depth = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            depth++;
            while(count > 0){
                Node node = queue.poll();
                if(node.children.size() != 0)
                    queue.addAll(node.children);
                count--;
            }
        }
        return depth;
    }


    // 中序遍历
    private void inorder(TreeNode root, List<Integer> vals){
        if (null == root){
            return;
        }
        inorder(root.left, vals);
        vals.add(root.val);
        inorder(root.right, vals);
    }

    // 前序遍历
    private void preorder(TreeNode root, List<Integer> vals){
        if (null == root){
            return;
        }
        vals.add(root.val);
        inorder(root.left, vals);
        inorder(root.right, vals);
    }

    // 后序遍历
    private void lastorder(TreeNode root, List<Integer> vals){
        if (null == root){
            return;
        }
        inorder(root.left, vals);
        inorder(root.right, vals);
        vals.add(root.val);
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // 690 员工的重要性
    public int getImportance(List<Employee> employees, int id) {
        int count = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees){
            map.put(employee.id, employee);
        }

        Queue<Employee> queue = new LinkedList<>();
        queue.add(map.get(id));
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                Employee employee = queue.poll();
                if (null != employee){
                    count += employee.importance;
                    for (int i = 0; i < employee.subordinates.size(); i++){
                        queue.add(map.get(employee.subordinates.get(i)));
                    }
                }
                size--;
            }
        }
        return count;
    }
}