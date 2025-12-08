package org.example.view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommandActionListener extends AbstractAction {
    public static final String COMMAND = "Command";
    @Override
    public void actionPerformed(ActionEvent e) {
        AppCommand command = (AppCommand) getValue(COMMAND);
        command.execute();
    }

    public CommandActionListener(String name, Icon icon, AppCommand command) {
        super(name, icon);
        putValue(COMMAND, command);
    }
    public CommandActionListener(AppCommand command) {
        super();
        putValue(COMMAND, command);
    }

}
