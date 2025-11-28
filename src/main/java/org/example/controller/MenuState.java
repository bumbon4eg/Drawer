package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;

@Setter
@Getter
public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;

    private AppAction action;

    public MenuState(Model model) {
        fill = true;
        color = Color.BLACK;
        shapeType = ShapeType.RECTANGULAR;
        action = new ActionDraw(model);
    }

    public MyShape getSelectedShape() {
        return MyShapeFactory.createShape(color, fill, shapeType);
    }
}
