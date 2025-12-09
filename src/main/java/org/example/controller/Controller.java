package org.example.controller;

import lombok.Getter;
import org.example.controller.actions.AppAction;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    @Getter
    private AppAction action;
    private final MenuCreator menu;
    private final MenuState state;
    @Getter
    private final UndoMachine undoMachine;

    private Controller() {
        model = new Model();
        frame = new MyFrame();
        state = new MenuState(model);
        undoMachine = new UndoMachine();

        panel = new MyPanel(this);
        model.addObserver(panel);
        frame.setPanel(panel);

        menu = MenuCreator.getInstance();
        menu.setController(this);
        menu.setState(state);
        menu.setModel(model);

        frame.setJMenuBar(menu.createMenuBar());
        frame.add(menu.createToolBar(), BorderLayout.WEST);

        updateAction();

        frame.revalidate();
    }


    public synchronized static Controller getInstance() {
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public void draw(Graphics2D g2) {
        updateAction();
        model.draw(g2);
    }

    private void updateAction() {
        action = state.getAction();
    }

    public void getPointOne(Point2D p) {
        updateAction();
        action.mousePressed((Point) p);
        undoMachine.add(action.cloneAction());
        undoMachine.updateButtons();
    }

    public void getPointTwo(Point2D p) {
        updateAction();
        action.mouseDragged((Point) p);
        undoMachine.add(action.cloneAction());
        undoMachine.updateButtons();
    }
}