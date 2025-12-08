package org.example.view.menu;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.MenuState;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import static org.example.model.shape.factory.MyShapeFactory.createShape;

public class MenuCreator {
    private static MenuCreator instance;
    @Getter
    private final JMenuBar menu;
    @Setter
    private Model model;
    @Setter
    private MenuState state;

    public static synchronized MenuCreator getInstance() {
        if (instance==null) {
            instance = new MenuCreator();
        }
        return instance;
    }

    private MenuCreator() {
        menu = new JMenuBar();

        JMenu colorMenu = createColorMenu();
        JMenu shapeMenu = createShapeMenu();
        JMenu fillMenu = createFillMenu();
        JMenu actionMenu = createActionMenu();

        menu.add(colorMenu);
        menu.add(shapeMenu);
        menu.add(fillMenu);
        menu.add(actionMenu);
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

    private JMenu createActionMenu() {
        JMenu actionMenu = new JMenu("Действие");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Создать");
        draw.addActionListener(new CommandActionListener(new SwitchAction(new ActionDraw(model), state))); //TODO: сделать также везде
        actionMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        move.addActionListener(e -> state.setAction(new ActionMove(model)));
        actionMenu.add(move);
        group.add(move);

        return actionMenu;
    }

    public MyShape getSelectedShape() {
        return createShape(state);
    }

    public JToolBar createToolBar() {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();

        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        URL colorUrl = getClass().getClassLoader().getResource("ico/color.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        JRadioButton rgbButton = new JRadioButton();
        AppCommand colorCommand = new SwitchColor(state, false, null, rgbButton);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        return menuItems;
    }
}
