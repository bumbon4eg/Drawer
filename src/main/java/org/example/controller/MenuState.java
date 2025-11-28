package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;

public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private ActionDraw actionDraw;

    public MenuState(Model model) {
        fill = true;
        color = Color.BLACK;
        shapeType = ShapeType.RECTANGULAR;
        actionDraw = new ActionDraw(model);
    }

    public ShapeType getSelectedShapeType() {
        return shapeType;
    }

    public Color getSelectedColor() {
        return color;
    }

    public boolean getSelectedFill() {
        return fill;
    }

    public MyShape getSelectedShape() {
        return MyShapeFactory.createShape(color, fill, shapeType);
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public ActionDraw getActionDraw() {
        return actionDraw;
    }
}
