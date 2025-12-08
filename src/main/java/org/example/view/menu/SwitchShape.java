package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.MenuState;
import org.example.model.shape.factory.ShapeType;

@AllArgsConstructor
public class SwitchShape implements AppCommand{
    private ShapeType shapeType;
    private MenuState state;
    @Override
    public void execute() {
        state.setShapeType(shapeType);
    }
}
