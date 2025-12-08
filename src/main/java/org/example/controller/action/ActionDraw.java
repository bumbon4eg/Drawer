package org.example.controller.action;

import org.example.view.menu.MenuCreator;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {

    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;

    public ActionDraw(Model model) {
        this.model = model;
    }

    public void stretchShape(Point point) {
        secondPoint = point;

        MyShape currentShape = model.getCurrentShape();
        if (currentShape == null) {
            createShape(point);
        } else {
            model.changeShape(firstPoint, secondPoint);
        }
    }

    public void createShape(Point point) {
        firstPoint = point;
        secondPoint = point;

        MyShape newShape = MenuCreator.getInstance().getSelectedShape().clone();
        newShape.setFrame(firstPoint, secondPoint);

        model.createCurrentShape(newShape);
        model.update();
    }

    @Override
    public void mousePressed(Point point) { createShape(point); }

    @Override
    public void mouseDragged(Point point) { stretchShape(point); }
}
