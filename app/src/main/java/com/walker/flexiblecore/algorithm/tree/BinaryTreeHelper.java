package com.walker.flexiblecore.algorithm.tree;

import android.util.Log;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Walker
 * @Date 2020-03-01 22:09
 * @Summary 二叉树遍历练习
 */
public class BinaryTreeHelper {
    private static final String TAG = "BinaryTreeHelper";

    private static BinaryTreeHelper sInstance;

    private BinaryTreeHelper() {
    }

    public static BinaryTreeHelper get() {
        if (sInstance == null) {
            synchronized (BinaryTreeHelper.class) {
                if (sInstance == null) {
                    sInstance = new BinaryTreeHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 构建二叉树
     *
     * @param inputList 输入序列
     * @return
     */
    public TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 二叉树前序遍历
     *
     * @param node 二叉树节点
     */
    public void preOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        Log.d(TAG, node.data + "  ");
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树中序遍历
     *
     * @param node 二叉树节点
     */
    public void inOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraveral(node.leftChild);
        Log.d(TAG, node.data + "  ");
        inOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树后序遍历
     *
     * @param node 二叉树节点
     */
    public void postOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        Log.d(TAG, node.data + "  ");
    }

    /**
     * 二叉树非递归前序遍历
     *
     * @param root 二叉树根节点
     */
    public void preOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.empty()) {
            //迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                Log.d(TAG, treeNode.data + "  ");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问该节点的右节点
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }

        }
    }

    /**
     * 二叉树非递归中序遍历
     *
     * @param root 二叉树根节点
     */
    public void inOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.empty()) {
            //迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问该节点的右节点
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                Log.d(TAG, treeNode.data + "  ");
                treeNode = treeNode.rightChild;
            }

        }
    }

    /**
     * 二叉树非递归后序遍历
     *
     * @param root 二叉树根节点
     */
    public void postOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> recordStack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.empty()) {
            //迭代访问节点的右孩子，并入栈
            while (treeNode != null) {
                stack.push(treeNode);
                recordStack.push(treeNode);
                treeNode = treeNode.rightChild;
            }
            //如果节点没有右孩子，则弹出栈顶节点，访问该节点的右节点
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.leftChild;
            }
        }
        //遍历结束后，从record栈中打印信息
        while (!recordStack.isEmpty()) {
            treeNode = recordStack.pop();
            Log.d(TAG, treeNode.data + "  ");
        }
    }

    /**
     * 二叉树层序遍历（队列实现）
     *
     * @param root 二叉树根节点
     */
    public void levelOrderTraveral(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Log.d(TAG, node.data + "  ");
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }

    public void onTest() {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{1, 2, 4, null, null, 5, null, null, 3, null, 6}));
        TreeNode treeNode = createBinaryTree(inputList);
        Log.d(TAG, "\n#### 递归实现 ####\n");
        Log.d(TAG, "前序遍历二叉树\n");
        preOrderTraveral(treeNode);
        Log.d(TAG, "中序遍历二叉树\n");
        inOrderTraveral(treeNode);
        Log.d(TAG, "后序遍历二叉树\n");
        postOrderTraveral(treeNode);

        Log.d(TAG, "\n#### 非递归实现 ####\n");
        Log.d(TAG, "\n\n前序遍历二叉树(栈实现)\n");
        preOrderTraveralWithStack(treeNode);
        Log.d(TAG, "\n\n中序遍历二叉树(栈实现)\n");
        inOrderTraveralWithStack(treeNode);
        Log.d(TAG, "\n\n后序遍历二叉树(栈实现)\n");
        postOrderTraveralWithStack(treeNode);
        Log.d(TAG, "\n\n层序遍历二叉树(队列实现)\n");
        levelOrderTraveral(treeNode);
    }
}
