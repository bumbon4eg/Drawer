package org.example.model.shape.fill;

import org.example.model.MyShape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;

public interface FillBehavior {
    void draw(Graphics2D g);

    void setColor(Color c);

    void setShape(MyShape s);

    FillBehavior clone();
}