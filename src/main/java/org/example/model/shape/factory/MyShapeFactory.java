package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;

import java.awt.*;

public class MyShapeFactory {
    public static MyShape createShape(Color color, FillBehavior fb, ShapeType type){
        return type.createShape(color, fb);};
}
