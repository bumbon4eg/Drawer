package org.example.model.shape.fill;

import org.example.model.MyShape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RectangularShape;

public class NoFill implements FillBehavior, Cloneable {
    private Color color;
    private MyShape shape;

    public NoFill() {
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        Paint paint = g.getPaint();
        g.setPaint(color);
        g.draw(shape);
        g.setPaint(paint);
    }

    @Override
    public void setShape(MyShape s) {
        shape = s;
    }

    @Override
    public NoFill clone() {
        NoFill clone = new NoFill();
        clone.setColor(color);
        clone.setShape(shape);
        return clone;
    }
}
