package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape = MyShapeFactory.createShape(Color.BLACK, new Fill(), ShapeType.RECTANGULAR);;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(MyShape shape, Model model) {
        this.model = model;
        this.shape = shape;
    }

    public void stretchShape(Point point) {
        firstPoint = point;
        shape.setFrame(firstPoint,secondPoint);
    }
    public void createShape(Point point) {
        secondPoint = point;
        shape = sampleShape.clone();
        model.createCurrentShape(shape);
    }
}
