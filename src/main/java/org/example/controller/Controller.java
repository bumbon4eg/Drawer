package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.MyShapeType;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.w3c.dom.events.MouseEvent;

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

    private Controller() {
        model = new Model();
        MyShape shape = MyShapeFactory.createShape(MyShapeType.RECTANGLE2D, Color.BLUE, true);
        shape.setFb(new NoFill());
        model.setMyShape(shape);

        ActionDraw drawer = new ActionDraw(shape, model);

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

    public void mousePressed(MouseEvent arg0) {
        //TODO:сделать обработку события с вызовом stretchShape();
    }

    public void mouseDragged(MouseEvent arg0) {
        //TODO:сделать обработку события с вызовом createShape();
    }

}
