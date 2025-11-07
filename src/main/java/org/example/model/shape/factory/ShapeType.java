package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
    ELLIPSE {
        public MyShape createShape(Color color, FillBehavior fb) {
            return  new MyShape(color, new Ellipse2D.Double(), fb);
        }
    },
    RECTANGULAR {
        public MyShape createShape(Color color, FillBehavior fb) {
            return  new MyShape(color, new Rectangle2D.Double(), fb);
        }
    };
    public abstract MyShape createShape(Color color, FillBehavior fb);
}
