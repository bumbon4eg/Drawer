package org.example.model.shape.factory;

import org.example.controller.MenuState;
import org.example.model.MyShape;

import java.awt.*;

public class MyShapeFactory {
    public static MyShape createShape(Color color, boolean fb, ShapeType type){
        return type.createShape(color, fb);
    }
    public static MyShape createShape(MenuState state) {
        return state.getShapeType().createShape(state.getColor(), state.isFill());
    }
}
