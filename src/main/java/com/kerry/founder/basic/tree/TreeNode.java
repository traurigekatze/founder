package com.kerry.founder.basic.tree;

import lombok.Data;

/**
 * @author kerryhe
 * @date 2020/4/22
 */
@Data
public class TreeNode {

    /**
     * 内容
     */
    private int data;
    /**
     * 高度
     */
    private int height;
    /**
     * 左节点
     */
    private TreeNode left;
    /**
     * 右节点
     */
    private TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

}
