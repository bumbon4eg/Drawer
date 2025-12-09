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
    private MyShape drawableShape;

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

        shape = MenuCreator.getInstance().getSelectedShape().clone();
        drawableShape = shape.clone();

        shape.setFrame(firstPoint, secondPoint);
        drawableShape.setFrame(firstPoint, secondPoint);

        model.addShape(shape);
        model.update();
    }

    @Override
    public void mousePressed(Point point) { createShape(point); }

    @Override
    public void mouseDragged(Point point) { stretchShape(point); }

    @Override
    public void execute() {
        model.addShape(drawableShape);
        model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.pop();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model);
        actionDraw.shape = shape.clone();
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }
}