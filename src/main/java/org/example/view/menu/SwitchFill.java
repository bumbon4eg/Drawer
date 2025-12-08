package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.MenuState;

@AllArgsConstructor
public class SwitchFill implements AppCommand{
    private boolean fill;
    private MenuState state;
    @Override
    public void execute() {
        state.setFill(fill);
    }
}
