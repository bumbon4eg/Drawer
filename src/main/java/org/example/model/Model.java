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

    public ModelSnapshot saveSnapshot() {
        return new ModelSnapshot(this);
    }

    public void restoreSnapshot(ModelSnapshot snapshot) {
        this.shapeList.clear();
        for (MyShape shape : snapshot.getShapes()) {
            this.shapeList.add(shape.clone());
        }
        if (!this.shapeList.isEmpty()) {
            this.currentShape = this.shapeList.get(this.shapeList.size() - 1);
        } else {
            this.currentShape = null;
        }
        update();
    }

    @Getter
    public static class ModelSnapshot {
        private final List<MyShape> shapes;

        public ModelSnapshot(Model model) {
            this.shapes = new ArrayList<>();
            for (MyShape shape : model.getShapeList()) {
                this.shapes.add(shape.clone());
            }
        }
    }

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
        setChanged();
        notifyObservers();
    }
}