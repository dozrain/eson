package com.rain.utils.file.image;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2018-6-19 0019.
 */
public class DrawCircle extends JFrame{
    private final int OVAL_WIDTH = 80;
    private final int OVAL_HEIGHT = 80;
    public DrawCircle(){
        super();
        initialize();
    }
    private void initialize(){
        this.setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawPanel());
        this.setTitle("绘图实例1");
    }
    class DrawPanel extends JPanel{
        public void paint(Graphics graphics){
            super.paint(graphics);
            graphics.drawOval(10,10,OVAL_WIDTH,OVAL_HEIGHT);
            graphics.drawOval(80,10,OVAL_WIDTH,OVAL_HEIGHT);
            graphics.drawOval(150,10,OVAL_WIDTH,OVAL_HEIGHT);
            graphics.drawOval(50,70,OVAL_WIDTH,OVAL_HEIGHT);
            graphics.drawOval(120,70,OVAL_WIDTH,OVAL_HEIGHT);
        }
    }

    public static void main(String[] args) {
        new DrawCircle().setVisible(true);
    }
}
