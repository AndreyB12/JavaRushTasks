package com.javarush.task.task36.task3604;

import java.lang.reflect.Field;

/**
 * Created by butkoav on 08.02.2017.
 */
public class RBTHelper {

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

