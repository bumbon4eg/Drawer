package org.example.view.menu;

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
    }

    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Фигура");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(new CommandActionListener(new SwitchShape(ShapeType.RECTANGULAR, state)));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(new CommandActionListener(new SwitchShape(ShapeType.ELLIPSE, state)));
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Заливать");
        fill.addActionListener(new CommandActionListener(new SwitchFill(true, state)));
        fillMenu.add(fill);
        group.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Не заливать");
        noFill.addActionListener(new CommandActionListener(new SwitchFill(false, state)));
        fillMenu.add(noFill);
        group.add(noFill);

        return fillMenu;
    }

    private JMenu createActionMenu() {
        JMenu actionMenu = new JMenu("Действие");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Создать");
        draw.addActionListener(new CommandActionListener(new SwitchAction(new ActionDraw(model), state)));
        actionMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        move.addActionListener(new CommandActionListener(new SwitchAction(new ActionMove(model), state)));
        actionMenu.add(move);
        group.add(move);

        return actionMenu;
    }

    public MyShape getSelectedShape() {
        return createShape(state);
    }

    public JToolBar createToolBar() {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar(JToolBar.VERTICAL);

        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        JRadioButton rgbButton = new JRadioButton();
        AppCommand colorCommand = new SwitchColor(state, false, null, rgbButton);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(new ActionDraw(model), state);
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(new ActionMove(model), state);
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));

        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(true, state);
        menuItems.add(new CommandActionListener("Заполнять", fillIco, fillCommand));

        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(false, state);
        menuItems.add(new CommandActionListener("Не заполнять", noFillIco, noFillCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(ShapeType.RECTANGULAR, state);
        menuItems.add(new CommandActionListener("Прямоугольник", rectangularIco, rectangularCommand));

        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(ShapeType.ELLIPSE, state);
        menuItems.add(new CommandActionListener("Эллипс", ellipseIco, ellipseCommand));

        return menuItems;
    }

    public JMenuBar createMenuBar() {
        JMenu shapeMenu = createShapeMenu();
        JMenu fillMenu = createFillMenu();
        JMenu actionMenu = createActionMenu();

        menu.add(shapeMenu);
        menu.add(fillMenu);
        menu.add(actionMenu);

        return menu;
    }
}
