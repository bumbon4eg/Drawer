package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;
    private MyShape shape;
    private MyShape drawableShape;
    private double originalX, originalY;
    private double width, height;

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

        if (shape != null) {
            originalX = shape.getShape().getX();
            originalY = shape.getShape().getY();
            width = shape.getShape().getWidth();
            height = shape.getShape().getHeight();
        }
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

        drawableShape = shape.clone();

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
    public void execute() {
        if (drawableShape != null) {
            MyShape shapeToMove = model.getShapeList().stream()
                    .filter(s -> s.equals(shape) ||
                            (s.getShape().getX() == originalX &&
                                    s.getShape().getY() == originalY))
                    .findFirst()
                    .orElse(null);

            if (shapeToMove != null) {
                double deltaX = drawableShape.getShape().getX() - originalX;
                double deltaY = drawableShape.getShape().getY() - originalY;

                shapeToMove.getShape().setFrame(
                        originalX + deltaX,
                        originalY + deltaY,
                        width,
                        height
                );

                shape = shapeToMove;
                drawableShape = shapeToMove.clone();
            }
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (drawableShape != null) {
            MyShape shapeToReset = model.getShapeList().stream()
                    .filter(s -> s.equals(drawableShape) ||
                            (Math.abs(s.getShape().getX() - drawableShape.getShape().getX()) < 0.1 &&
                                    Math.abs(s.getShape().getY() - drawableShape.getShape().getY()) < 0.1))
                    .findFirst()
                    .orElse(null);

            if (shapeToReset != null) {
                shapeToReset.getShape().setFrame(
                        originalX,
                        originalY,
                        width,
                        height
                );

                shape = shapeToReset;
                drawableShape = shapeToReset.clone();
            }
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove actionMove = new ActionMove(model);

        actionMove.firstPoint = firstPoint == null ? null : (Point2D) firstPoint.clone();
        actionMove.secondPoint = secondPoint == null ? null : (Point2D) secondPoint.clone();
        actionMove.shape = shape == null ? null : shape.clone();
        actionMove.drawableShape = drawableShape == null ? null : drawableShape.clone();

        actionMove.originalX = originalX;
        actionMove.originalY = originalY;
        actionMove.width = width;
        actionMove.height = height;

        return actionMove;
    }
}
