package org.example.model.shape.factory;

import org.example.model.MyShape;

import java.awt.*;

public class MyShapeFactory {
    public static MyShape createShape(Color color, boolean fb, ShapeType type){
        return type.createShape(color, fb);}
}
