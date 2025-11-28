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
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    private void move(Point point) {
        //TODO:Реализовать вычисление смещения фигуры
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
