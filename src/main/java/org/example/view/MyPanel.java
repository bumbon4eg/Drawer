package org.example.view;

import org.example.controller.Controller;
import org.example.controller.action.ActionDraw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements Observer {
    private final ActionDraw actionDraw;

    public MyPanel(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                actionDraw.mousePressed(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                actionDraw.mouseDragged(arg0.getPoint());
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        actionDraw.draw(g2);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
