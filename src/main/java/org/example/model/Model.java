package org.example.model;

import lombok.Getter;
import org.example.view.MyPanel;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    @Getter
    private final List<MyShape> shapeList = new ArrayList<>();
    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D x, Point2D y, MyShape currentShape) {
        this.currentShape = currentShape;
        this.currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        if (currentShape!=null) {
            currentShape.draw(g);
            shapeList.forEach(myShape -> myShape.draw(g));
        }
    }

    public void createCurrentShape(MyShape shape) {
        currentShape = shape;
        shapeList.add(currentShape);
    }

    public void update() {
        notifyObservers();
    }
}
