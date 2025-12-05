package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;

@Setter
@Getter
public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private AppAction action;
    private Model model;

    public MenuState(Model model) {
        this.model = model;
        fill = true;
        color = Color.BLACK;
        shapeType = ShapeType.RECTANGULAR;
        action = new ActionDraw(this.model);
    }

    public void setAction(boolean act) {
        this.action = act ? new ActionDraw(model) : new ActionMove(model);
    }
}
