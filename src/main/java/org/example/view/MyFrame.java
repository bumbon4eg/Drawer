/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    public MyFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

    public void setPanel(MyPanel panel) {
        add(panel);
    }


}