package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;

    public ActionDraw(MyShape shape, Model model) {
        this.model = model;
        this.shape = shape;
        this.sampleShape = shape;
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
