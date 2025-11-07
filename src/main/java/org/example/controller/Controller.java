package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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
        MyShape shape = MyShapeFactory.createShape(Color.BLACK, new Fill(), ShapeType.RECTANGULAR);
        model.setMyShape(shape);

        actionDraw = new ActionDraw(shape, model);

        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
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
        actionDraw.stretchShape(firstPoint);
    }
    public void mouseDragged(Point secondPoint) {
        actionDraw.createShape(secondPoint);
    }

}
