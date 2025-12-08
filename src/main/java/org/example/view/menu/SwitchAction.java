package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.MenuState;
import org.example.controller.action.AppAction;

@AllArgsConstructor
public class SwitchAction implements AppCommand{
    private AppAction action;
    private MenuState state;
    @Override
    public void execute() {
        state.setAction(action);
    }
}
