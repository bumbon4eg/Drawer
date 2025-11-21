package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private final ActionDraw actionDraw;
    private final MenuController menu;
    private Controller() {
        model = new Model();
        frame = new MyFrame();

        menu = MenuController.getInstance(model);
        actionDraw = menu.getActionDraw();
        frame.setJMenuBar(menu.getMenuBar());

        updateShapeFromMenu();

        panel = new MyPanel(actionDraw);

        model.addObserver(panel);
        frame.setPanel(panel);

    }

    public void updateShapeFromMenu() {
        MyShape shape = menu.getSelectedShape();
        model.setMyShape(shape);
    }

    public synchronized static Controller getInstance() {
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

}
