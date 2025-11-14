package org.example;

import org.example.model.shape.factory.ShapeType;

import javax.swing.*;

public class MenuController {
    private static MenuController instance;
    private final JMenuBar menu;
    private  ShapeType selectedShape;

    public static synchronized MenuController getInstance() {
        if (instance==null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {
        selectedShape = ShapeType.RECTANGULAR;

        menu = new JMenuBar();
        JMenu colorMenu = createShapeMenu();
        menu.add(colorMenu);
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

    public JMenuBar getMenuBar() {
        return menu;
    }
}
