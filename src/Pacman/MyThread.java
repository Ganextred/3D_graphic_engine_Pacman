package Pacman;


//test commit

import java.awt.*;


//server commit

//resolved merge


class MyThread extends Thread {
    public MyThread(String name){
        super(name);
    }

    public void run(Graphics g, Screen scr){
        // метод который подготавливает кадр (создает масив пикселей)
            scr. fakepaint ();
            //отрисовка кадра на экран
            for (int i = 0; i<800; i++)
                for (int j= 0; j<600; j++) {
                    g.setColor(scr.pix[i][j].color);
                    g.drawLine (i+11,j+45+17,i+11,j+45+17);
                }
    }
}
