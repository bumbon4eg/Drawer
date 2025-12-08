package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable {
    private final Color color;
    @Setter
    @Getter
    private RectangularShape shape;
    private FillBehavior fb;


    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
        this.fb.setShape(shape);
        this.fb.setColor(color);
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
        fb.setShape(shape);
        fb.setColor(color);
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }

    @Override
    public MyShape clone() {
        return new MyShape(color, (RectangularShape) shape.clone(), fb.clone());
    }
}
