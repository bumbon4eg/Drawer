package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.NoFill;

import java.awt.*;

public class MyShapeFactory {
    public static MyShape createShape(MyShapeType type, Color color, boolean fb ) {
        MyShape shape;
        if (fb) {
            Fill fl = new Fill();
            shape = type.createShape();
            fl.setColor(color);
            fl.setShape(shape);
        } else {
            NoFill fl = new NoFill();
            shape = type.createShape();
            fl.setColor(color);
            fl.setShape(shape);
        }
        return shape;
    }
}
