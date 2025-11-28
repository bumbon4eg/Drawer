package org.example.controller.action;

import org.example.controller.MenuController;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction{

    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;
    private MyShape currentShape;
    private MyShape sampleShape;

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

        updateSampleShapeFromMenu();

        currentShape = sampleShape.clone();
        currentShape.setFrame(firstPoint, secondPoint);
        model.createCurrentShape(currentShape);
    }

    private void updateSampleShapeFromMenu() {
        sampleShape = MenuController.getInstance(model).getSelectedShape();
    }

    @Override
    public void mousePressed(Point point) { createShape(point); }

    @Override
    public void mouseDragged(Point point) {
        stretchShape(point);
    }

}
