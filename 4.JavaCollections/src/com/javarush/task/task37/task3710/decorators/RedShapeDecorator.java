package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

/**
 * Created by butkoav on 01.03.2017.
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }
}
