package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ActionMove implements AppAction {
    private double originalX, originalY;
    private double finalX, finalY;
    private Point2D dragOffset;
    private final Model model;
    private MyShape shape;

    public ActionMove(Model model) {
        this.model = model;
    }

    private void searchShape(Point point) {
        shape = find(point);
        if (shape != null) {
            Rectangle2D bounds = shape.getShape().getBounds2D();
            originalX = bounds.getX();
            originalY = bounds.getY();

            dragOffset = new Point2D.Double(point.x - originalX, point.y - originalY);

            model.setCurrentShape(shape);
            model.update();
        }
    }

    private void move(Point point) {
        if (shape == null || dragOffset == null) return;

        double newX = point.x - dragOffset.getX();
        double newY = point.y - dragOffset.getY();

        Rectangle2D bounds = shape.getShape().getBounds2D();
        shape.getShape().setFrame(newX, newY, bounds.getWidth(), bounds.getHeight());

        model.update();
    }

    private MyShape find(Point2D point) {
        return model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
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
    public void mouseReleased() {
        if (shape != null) {
            Rectangle2D bounds = shape.getShape().getBounds2D();
            finalX = bounds.getX();
            finalY = bounds.getY();
        }
    }

    @Override
    public void execute() {
        model.setCurrentShape(shape);
        Rectangle2D bounds = shape.getShape().getBounds2D();
        shape.getShape().setFrame(finalX, finalY, bounds.getWidth(), bounds.getHeight());
        model.update();
    }

    @Override
    public void unexecute() {
        model.setCurrentShape(shape);
        Rectangle2D bounds = shape.getShape().getBounds2D();
        shape.getShape().setFrame(originalX, originalY, bounds.getWidth(), bounds.getHeight());
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionMove clone = new ActionMove(model);
        clone.shape = this.shape;
        clone.originalX = this.originalX;
        clone.originalY = this.originalY;
        clone.finalX = this.finalX;
        clone.finalY = this.finalY;
        clone.dragOffset = this.dragOffset != null ? (Point2D) this.dragOffset.clone() : null;
        return clone;
    }

    @Override
    public boolean hasShape() {
        return shape!=null;
    }
}