package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;
import org.example.model.shape.Fill;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;

public class MenuController {
    private static MenuController instance;
    private final JMenuBar menu;
    private static MenuState state;

    public static synchronized MenuController getInstance(Model model) {
        state = new MenuState(model);
        if (instance==null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {
        menu = new JMenuBar();

        JMenu colorMenu = createColorMenu();
        JMenu shapeMenu = createShapeMenu();
        JMenu fillMenu = createFillMenu();

        menu.add(colorMenu);
        menu.add(shapeMenu);
        menu.add(fillMenu);

    }

    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Фигура");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> state.setShapeType(ShapeType.RECTANGULAR));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> state.setShapeType(ShapeType.ELLIPSE));
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(e -> state.setColor(Color.RED));
        colorMenu.add(red);
        group.add(red);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        blue.addActionListener(e -> state.setColor(Color.BLUE));
        colorMenu.add(blue);
        group.add(blue);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зелёный");
        green.addActionListener(e -> state.setColor(Color.GREEN));
        colorMenu.add(green);
        group.add(green);

        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Чёрный");
        black.addActionListener(e -> state.setColor(Color.BLACK));
        colorMenu.add(black);
        group.add(black);

        return colorMenu;
    }

    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Заливать");
        fill.addActionListener(e -> state.setFill(true));
        fillMenu.add(fill);
        group.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Не заливать");
        noFill.addActionListener(e -> state.setFill(false));
        fillMenu.add(noFill);
        group.add(noFill);

        return fillMenu;
    }

    public static ActionDraw getActionDraw() {
        return state.getActionDraw();
    }

    public JMenuBar getMenuBar() {
        return menu;
    }

    public ShapeType getSelectedShapeType() {
        return state.getSelectedShapeType();
    }

    public Color getSelectedColor() {
        return state.getSelectedColor();
    }

    public boolean getSelectedFill() {
        return state.getSelectedFill();
    }

    public MyShape getSelectedShape() {
        return state.getSelectedShape();
    }
}
