package org.example.controller;

import lombok.Getter;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    @Getter
    private AppAction action;
    private final MenuCreator menu;
    private final MenuState state;

    private Controller() {
        model = new Model();
        frame = new MyFrame();
        state = new MenuState(model);

        menu = MenuCreator.getInstance();
        menu.setState(state);
        menu.setModel(model);
        frame.setJMenuBar(menu.getMenu());

        action = state.getAction();

        updateShapeFromMenu();

        panel = new MyPanel(this);

        model.addObserver(panel);
        frame.setPanel(panel);
        frame.add(menu.createToolBar(), BorderLayout.NORTH);

        frame.revalidate();
    }

    public void updateShapeFromMenu() {
        model.setMyShape(menu.getSelectedShape());
    }

    public synchronized static Controller getInstance() {
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public void draw(Graphics2D g2) {
        action = state.getAction();
        model.draw(g2);
    }
}
