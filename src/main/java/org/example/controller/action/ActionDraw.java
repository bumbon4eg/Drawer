package org.example.controller.action;

import org.example.controller.MenuController;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {

    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;
    private MyShape currentShape;

    public ActionDraw(Model model) {
        this.model = model;
    }

    public void stretchShape(Point point) {
        secondPoint = point;
        model.changeShape(firstPoint,secondPoint,currentShape);
    }
    public void createShape(Point point) {
        firstPoint = point;
        secondPoint = point;

        currentShape = MenuController.getInstance(model).getSelectedShape().clone();
        currentShape.setFrame(firstPoint, secondPoint);
        model.createCurrentShape(currentShape);
    }

    @Override
    public void mousePressed(Point point) { createShape(point); }

    @Override
    public void mouseDragged(Point point) {
        stretchShape(point);
    }

}
