package com.Snake;

import com.Snake.GameObj.BodyObj;
import com.Snake.GameObj.FoodObj;
import com.Snake.GameObj.HeadObj;
import com.Snake.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    public static int score = 0;

    public  static int state = 0;

    int winWidth = 800;
    int winHeight = 600;

    HeadObj headObj = new HeadObj(GameUtils.rightImg, 30, 570, this);

    public List<BodyObj> bodyObjList = new ArrayList<>();

    public FoodObj foodObj = new FoodObj().getFood();

    public void launch(){
        this.setVisible(true);
        this.setSize(winWidth, winHeight);
        this.setLocationRelativeTo(null);
        this.setTitle("Snake");
        this.setAlwaysOnTop(true);
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 0, 570, this));
        while(true){
                repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, winWidth, winHeight);
        g.setColor(Color.black);
        for (int i = 0; i < 20; i++) {
            g.drawLine(0, (i * 30), 600, (i * 30));
            g.drawLine((i * 30), 0, (i * 30), 600);
        }

        for (int i =  bodyObjList.size() - 1; i >= 0; i--) {
            bodyObjList.get(i).paintSelf(g);
        }

        headObj.paintSelf(g);

        foodObj.paintSelf(g);

        GameUtils.drawWord(g, score + " points", Color.RED, 25,650, 300);

        prompt(g);

    }

    void prompt (Graphics g){
        if (state == 4){
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Passed!", Color.YELLOW, 35, 150, 290);
        }
        if (state == 3){
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Failed", Color.YELLOW, 35, 150, 290);
        }
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }

}
