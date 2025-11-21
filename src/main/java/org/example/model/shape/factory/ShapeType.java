package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;
import org.example.model.shape.Fill;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
    ELLIPSE {
        public MyShape createShape(Color color, boolean fb) {
            return  new MyShape(color, new Ellipse2D.Double(), (fb?new Fill():new NoFill()));
        }
    },
    RECTANGULAR {
        public MyShape createShape(Color color, boolean fb) {
            return  new MyShape(color, new Rectangle2D.Double(), (fb?new Fill():new NoFill()));
        }
    };
    public abstract MyShape createShape(Color color, boolean fb);
}
