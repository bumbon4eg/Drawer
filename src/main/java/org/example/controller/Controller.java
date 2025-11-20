package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final ActionDraw actionDraw;
    private Controller() {
        model = new Model();
        panel = new MyPanel(this);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
        frame.setJMenuBar(MenuController.getInstance().getMenuBar());

        updateShapeFromMenu();

        actionDraw = new ActionDraw(model);

        // TODO: Поменять наблюдатель на более современную реализацию

    }

    public void updateShapeFromMenu() {
        MyShape shape = MenuController.getInstance().getSelectedShape();
        model.setMyShape(shape);
    }

    public synchronized static Controller getInstance() {
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public void mousePressed(Point firstPoint) {
        updateShapeFromMenu();
        actionDraw.createShape(firstPoint);
    }
    public void mouseDragged(Point secondPoint) {
        actionDraw.stretchShape(secondPoint);
    }

}
