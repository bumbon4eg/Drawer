package org.example.controller.actions;

import org.example.view.menu.MenuCreator;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;
    private MyShape shape;
    private Model.ModelSnapshot beforeState;
    private Model.ModelSnapshot afterState;
    private boolean isNewAction = true;

    public ActionDraw(Model model) {
        this.model = model;
    }

    private void stretchShape(Point point) {
        secondPoint = point;

        shape = model.getCurrentShape();
        if (shape == null) {
            createShape(point);
        } else {
            model.changeShape(firstPoint, secondPoint);
        }

        model.update();
    }

    private void createShape(Point point) {
        firstPoint = point;
        secondPoint = point;

        if (isNewAction) {
            beforeState = model.saveSnapshot();
            isNewAction = false;
        }

        shape = MenuCreator.getInstance().getSelectedShape().clone();
        shape.setFrame(firstPoint, secondPoint);

        model.addShape(shape);

        afterState = model.saveSnapshot();

        model.update();
    }

    @Override
    public void mousePressed(Point point) {
        isNewAction = true;
        createShape(point);
    }

    @Override
    public void mouseDragged(Point point) {
        stretchShape(point);

        if (!isNewAction) {
            afterState = model.saveSnapshot();
        }
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
        ActionDraw clone = new ActionDraw(model);
        clone.firstPoint = firstPoint != null ? (Point2D) firstPoint.clone() : null;
        clone.secondPoint = secondPoint != null ? (Point2D) secondPoint.clone() : null;
        clone.shape = shape != null ? shape.clone() : null;
        clone.beforeState = beforeState;
        clone.afterState = afterState;
        clone.isNewAction = false;
        return clone;
    }
}