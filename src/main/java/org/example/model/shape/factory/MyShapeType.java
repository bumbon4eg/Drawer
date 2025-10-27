package org.example.model.shape.factory;

import org.example.model.MyShape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum MyShapeType {
    RECTANGLE2D {
      public MyShape createShape() {
          return new MyShape(new Rectangle2D.Double());
      }
    },
    ELLIPSE2D {
        public MyShape createShape() {
            return new MyShape(new Ellipse2D.Double());
        }
    };
    public abstract MyShape createShape();
}
