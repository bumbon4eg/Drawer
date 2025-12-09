package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Getter
public class Model extends Observable {
    @Setter
    private MyShape currentShape;
    private final List<MyShape> shapeList = new ArrayList<>();

    public void changeShape(Point2D x, Point2D y) {
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

    public void addShape(MyShape shape) {
        currentShape = shape;
        shapeList.add(currentShape);
    }

    public void update() {
        notifyObservers();
    }

    public MyShape pop() {
        MyShape shape = shapeList.get(shapeList.size()-1);
        shapeList.remove(shapeList.size()-1);
        update();
        return shape;
    }
}