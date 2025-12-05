package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;
    private MyShape shape;

    public ActionMove(Model model) {
        this.model = model;
    }

    private void searchShape(Point point) {
        firstPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    private void move(Point point) {
        secondPoint = point;
        if (shape == null){
            return;
        }
        double deltaX = secondPoint.getX() - firstPoint.getX();
        double deltaY = secondPoint.getY() - firstPoint.getY();
        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);
        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);
        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint,
                newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }

    @Override
    public void mousePressed(Point point) {
        searchShape(point);
    }

    @Override
    public void mouseDragged(Point point) {
        move(point);
    }
}
