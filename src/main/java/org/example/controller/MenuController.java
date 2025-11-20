package org.example.controller;

import org.example.controller.action.ActionDraw;
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
    private ShapeType selectedShape;
    private Color selectedColor;
    private FillBehavior selectedFill;

    public static synchronized MenuController getInstance() {
        if (instance==null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {
        selectedShape = ShapeType.RECTANGULAR;
        selectedColor = Color.BLACK;
        selectedFill = new NoFill();

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
        square.addActionListener(e -> selectedShape = ShapeType.RECTANGULAR);
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> selectedShape = ShapeType.ELLIPSE);
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(e -> selectedColor = Color.RED);
        colorMenu.add(red);
        group.add(red);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        red.addActionListener(e -> selectedColor = Color.BLUE);
        colorMenu.add(blue);
        group.add(blue);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зелёный");
        red.addActionListener(e -> selectedColor = Color.GREEN);
        colorMenu.add(green);
        group.add(green);

        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Чёрный");
        red.addActionListener(e -> selectedColor = Color.BLACK);
        colorMenu.add(black);
        group.add(black);

        return colorMenu;
    }

    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Заливать");
        fill.addActionListener(e -> selectedFill = new Fill());
        fillMenu.add(fill);
        group.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Не заливать");
        fill.addActionListener(e -> selectedFill = new NoFill());
        fillMenu.add(noFill);
        group.add(noFill);

        return fillMenu;
    }

    public JMenuBar getMenuBar() {
        return menu;
    }
}
