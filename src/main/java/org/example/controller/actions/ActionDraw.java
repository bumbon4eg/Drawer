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

    public ActionDraw(Model model) {
        this.model = model;
    }

    private void stretchShape(Point point) {
        secondPoint = point;

        model.changeShape(firstPoint, secondPoint);
    }

    private void createShape(Point point) {
        firstPoint = point;
        secondPoint = point;

        shape = MenuCreator.getInstance().getSelectedShape();
        shape.setFrame(firstPoint, secondPoint);

        MyShape clone = shape.clone();

        model.setCurrentShape(clone);
        model.addShape(clone);

        model.update();
    }

    @Override
    public void mousePressed(Point point) {
        createShape(point);
    }

    @Override
    public void mouseDragged(Point point) {
        stretchShape(point);
    }

    @Override
    public void mouseReleased() {

    }

    @Override
    public void execute() {
        model.addShape(shape);
        model.update();
    }

    @Override
    public void unexecute() {
        shape = model.pop();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model);
        actionDraw.shape = this.shape;
        return actionDraw;
    }

    @Override
    public boolean hasShape() {
        return shape!=null;
    }
}