package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private Point2D firstPoint;
    private final Model model;
    private int shapeIndex = -1;
    private Model.ModelSnapshot beforeState;
    private Model.ModelSnapshot afterState;
    private boolean isNewAction = true;
    private boolean isDragging = false;

    public ActionMove(Model model) {
        this.model = model;
    }

    private void searchShape(Point point) {
        firstPoint = point;
        var shapes = model.getShapeList();

        for (int i = shapes.size() - 1; i >= 0; i--) {
            MyShape shape = shapes.get(i);
            if (shape.getShape().contains(point)) {
                shapeIndex = i;

                if (isNewAction) {
                    beforeState = model.saveSnapshot();
                    isNewAction = false;
                    isDragging = true;
                }
                break;
            }
        }
    }

    private void move(Point point) {
        if (shapeIndex < 0|| !isDragging) {
            return;
        }

        MyShape shape = model.getShapeList().get(shapeIndex);

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        double x = shape.getShape().getX();
        double y = shape.getShape().getY();
        double width = shape.getShape().getWidth();
        double height = shape.getShape().getHeight();

        shape.getShape().setFrame(x + deltaX, y + deltaY, width, height);

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

    @Override
    public void mouseReleased(Point point) {
        afterState = model.saveSnapshot();
        model.update();
    }

    @Override
    public void execute() {
        if (afterState != null) {
            model.restoreSnapshot(afterState);
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (beforeState != null) {
            model.restoreSnapshot(beforeState);
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove clone = new ActionMove(model);
        clone.firstPoint = firstPoint != null ? (Point2D) firstPoint.clone() : null;
        clone.beforeState = beforeState;
        clone.afterState = afterState;
        clone.isNewAction = false;
        clone.isDragging = isDragging;
        return clone;
    }
}