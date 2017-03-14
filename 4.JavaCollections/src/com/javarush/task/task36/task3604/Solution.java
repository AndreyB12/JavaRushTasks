package com.javarush.task.task36.task3604;

import java.lang.reflect.Field;

/*
Разбираемся в красно-черном дереве
*/
public class Solution {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        RedBlackTree rbt = new RedBlackTree();

        rbt.insert(50);
        rbt.insert(40);
        rbt.insert(30);
        rbt.insert(35);
        rbt.insert(36);
        rbt.insert(20);
        rbt.insert(10);
        rbt.insert(5);
        rbt.insert(7);

        System.out.println(toString(getRoot(rbt)));
        System.out.println(toString(findNode(rbt, 50)));
        System.out.println(toString(findNode(rbt, 40)));
        System.out.println(toString(findNode(rbt, 30)));
        System.out.println(toString(findNode(rbt, 35)));
        System.out.println(toString(findNode(rbt, 36)));
        System.out.println(toString(findNode(rbt, 20)));
        System.out.println(toString(findNode(rbt, 10)));
        System.out.println(toString(findNode(rbt, 5)));
        System.out.println(toString(findNode(rbt, 7)));

    }

    public static RedBlackTree.Node getRoot(RedBlackTree tree) throws NoSuchFieldException, IllegalAccessException {
        return getNodeValue("right",getFromTreeNodeByName ("header", tree));
    }

    public static String toString(RedBlackTree.Node node) throws NoSuchFieldException, IllegalAccessException {
        RedBlackTree.Node empty = getEmptyNode();
        if(node==empty) return "nil";
        RedBlackTree.Node right = getNodeValue("right", node);
        RedBlackTree.Node left = getNodeValue("left", node);
        StringBuilder sb = new StringBuilder();
        sb.append(left == empty ? "nil" : getNodeElm(left) + getColor(left));

        sb.append("|");
        sb.append(getNodeElm(node));
        sb.append(getColor(node));
        sb.append("|");
        sb.append(right == empty ? "nil" : getNodeElm(right) + getColor(right));

        return sb.toString();
    }

    public static String getColor(RedBlackTree.Node node) throws NoSuchFieldException, IllegalAccessException {
        Field colorField = node.getClass().getDeclaredField("color");
        colorField.setAccessible(true);
        RedBlackTree.Color color = (RedBlackTree.Color) colorField.get(node);

        return color == RedBlackTree.Color.BLACK ? "B" : "R";
    }

    public static RedBlackTree.Node findNode(RedBlackTree tree, int item) throws NoSuchFieldException, IllegalAccessException {
        RedBlackTree.Node current = getFromTreeNodeByName("header", tree);
        RedBlackTree.Node empty = getEmptyNode();

        while (!(item == getNodeElm(current) || current == empty)) {
            current = item > getNodeElm(current) ? getNodeValue("right", current) : getNodeValue("left", current);
        }

        return current;
    }

    public static int getNodeElm(RedBlackTree.Node node) throws NoSuchFieldException, IllegalAccessException {
        Field nodeField = node.getClass().getDeclaredField("element");
        nodeField.setAccessible(true);
        return (int) nodeField.get(node);
    }

    public static void changeNode(String nodName, RedBlackTree tree, String fieldName, RedBlackTree.Node newValue) throws
            Exception {
        RedBlackTree.Node header = getFromTreeNodeByName(nodName, tree);
        setNewNodeValue(fieldName, newValue, header);
    }

    public static void setNewNodeValue(String fieldName, RedBlackTree.Node newValue, RedBlackTree.Node node) throws
            NoSuchFieldException, IllegalAccessException {
        Field nodeField = node.getClass().getDeclaredField(fieldName);
        nodeField.setAccessible(true);
        nodeField.set(node, newValue);
    }

    public static RedBlackTree.Node getNodeValue(String fieldName, RedBlackTree.Node node) throws
            NoSuchFieldException, IllegalAccessException {
        Field nodeField = node.getClass().getDeclaredField(fieldName);
        nodeField.setAccessible(true);
        return (RedBlackTree.Node) nodeField.get(node);
    }

    public static RedBlackTree.Node getFromTreeNodeByName(String nodName, RedBlackTree tree) throws
            NoSuchFieldException,
            IllegalAccessException {
        Field headerField = RedBlackTree.class.getDeclaredField(nodName);
        headerField.setAccessible(true);
        return (RedBlackTree.Node) headerField.get(tree);
    }

    public static void changeColor(RedBlackTree.Node node, RedBlackTree.Color newColor) throws Exception {
        Field colorField = node.getClass().getDeclaredField("color");
        colorField.setAccessible(true);
        colorField.set(node, newColor);
    }

    public static RedBlackTree.Node getEmptyNode() throws NoSuchFieldException, IllegalAccessException {
        Field field = RedBlackTree.class.getDeclaredField("EMPTY");
        field.setAccessible(true);
        return (RedBlackTree.Node) field.get(RedBlackTree.class);
    }
}
