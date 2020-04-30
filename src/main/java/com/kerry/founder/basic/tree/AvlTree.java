package com.kerry.founder.basic.tree;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kerryhe
 * @date 2020/4/22
 */
@Slf4j
public class AvlTree {

    /**
     * LL 左旋
     * @param node
     * @return
     */
    public TreeNode singleRotateLeft(TreeNode node) {
        TreeNode treeNode = node.getLeft();
        node.setLeft(treeNode.getRight());
        node.setLeft(treeNode.getRight());
        treeNode.setRight(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        treeNode.setHeight(Math.max(height(treeNode.getLeft()), treeNode.getHeight()) + 1);
        return treeNode;
    }

    /**
     * RR 旋转
     * @param node
     * @return
     */
    public TreeNode singleRotateRight(TreeNode node){
        TreeNode treeNode = node.getRight();
        node.setRight(treeNode.getLeft());
        treeNode.setLeft(node);
        node.setHeight(Math.max(height(node.getRight()), height(node.getLeft())) + 1);
        treeNode.setHeight(Math.max(height(treeNode.getRight()), node.getHeight()) + 1);
        return treeNode;
    }

    /**
     * LR旋转
     * @param treeNode
     * @return
     */
    public TreeNode doubleRotateLeft(TreeNode treeNode){
        //在X和Y之间旋转
        treeNode.setLeft(singleRotateRight(treeNode.getLeft()));
        //在Z和Y之间旋转
        return singleRotateLeft(treeNode);
    }

    /**
     * RL旋转
     * @param treeNode
     * @return
     */
    public TreeNode doubleRotateRight(TreeNode treeNode){
        //在Z和Y之间旋转
        treeNode.setRight(singleRotateRight(treeNode));
        //在X和Y之间旋转
        return singleRotateRight(treeNode);
    }

    /**
     * insert
     * @param root
     * @param data
     * @return
     */
    public TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            //若原树为空， 生成并返回一个结点的AVL树
            root = new TreeNode(data);
        } else if (data < root.getData()) {
            root.setLeft(insert(root.getLeft(), data));
            if (height(root.getLeft())-height(root.getRight()) == 2) {
                if (data < root.getLeft().getData()) {
                    root = singleRotateLeft(root);
                } else {
                    root = doubleRotateLeft(root);
                }
            }
        } else if (data > root.getData()) {
            root.setRight(insert(root.getRight(), data));
            if (height(root.getRight()) - height(root.getLeft()) == 2) {
                if (data < root.getRight().getData()) {
                    root = singleRotateRight(root);
                } else {
                    root = doubleRotateRight(root);
                }
            }
        }
        /*否则，数据已经存在，程序什么也不做。*/
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        return root;
    }

    /**
     * 获取树的高度
     * @param treeNode
     * @return
     */
    public int height(TreeNode treeNode) {
        return treeNode == null ? 0 : treeNode.getHeight();
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        TreeNode root = tree.insert(null, 3);
        root = tree.insert(root, 5);
        root = tree.insert(root, 8);
        log.info("bst:{}", JSON.toJSONString(root));
    }

}
