package com.kerry.founder.basic.tree;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 二叉树测试(Binary Search Tree)
 * 左子节点比右子节点小，数据格式如下：
 *          1O
 *      05        20
 *  03      08 15     25
 *
 * @author kerryhe
 * @date 2020/4/20
 */
@Slf4j
public class BstTest {

    /**
     * 节点
     */
    static class Node {
        // 内容
        public String content;
        // 父节点
        public Node parent;
        // 左子节点
        public Node left;
        // 右子节点
        public Node right;
        // 当前节点的高度
        public int height;

        public Node(String content) {
            this.content = content;
        }
    }

    /**
     * 跟节点
     */
    public Node root;
    /**
     * 高度
     */
    public volatile int height;

    /**
     * 查找节点
     * @param content
     * @return
     */
    public Node search(String content) {
        Node r = root;
        while (r != null) {
            if (r.content.equals(content)) {
                return r;
            } else if (content.compareTo(r.content) >= 0) {
                r = r.right;
            } else if (content.compareTo(r.content) < 1) {
                r = r.left;
            }
        }
        return null;
    }

    /**
     * 新增节点
     * @param content
     */
    public void insert(String content) {
        if (StringUtils.isBlank(content)) {
            log.warn("content must notBlank");
            return;
        }
        // step1:
        Node node = new Node(content);
        if (root == null) {
            node.height = height = 1;
            root = node;
            return;
        }
        insert(node);
    }

    private void insert(Node node) {
        Node r = root;
        Node parent = r;
        while (r != null) {
            parent = r;
            if (node.content.compareTo(r.content) >= 0) {
                r = r.right;
            } else {
                r = r.left;
            }
        }
        if (node.content.compareTo(parent.content) >= 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
        node.height = 1;
        Node n = node;
        while (n.parent != null) {
            n.parent.height = n.height + 1;
            height = n.parent.height;
            n = n.parent;
        }
    }


    public static void main(String[] args) {
        BstTest bst = new BstTest();
        bst.insert("0003");
        bst.insert("0005");
        bst.insert("0008");
//        bst.insert("0010");
//        bst.insert("0015");
//        bst.insert("0020");
//        bst.insert("0025");
        log.info("bst:{}", JSON.toJSONString(bst.root));
    }

}
