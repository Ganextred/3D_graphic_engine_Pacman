package Pacman;

import server.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
/*
  Тут реализуетьса оконый интерфейс
 */
public class Main {
    static public MyFrame mf;
    public static void main(String[] args) throws InterruptedException, IOException {
        mf = new MyFrame();
        Server.start();
    }
}

//Клас окна с информациией
class InfoFrame extends JFrame {
    MyFrame mf;
    public InfoFrame(MyFrame mf) {
        super();
        setSize(350, 215);
        setLocation(500, 400);
        setBackground(new Color(255, 255, 255));
        setLayout (null);
        setVisible(true);
        this.mf = mf;
     }
    public void paint (Graphics g) {
        g.drawString("Управление:",70,80);
        g.drawString("W,A,S,D - движеник",70,95);
        g.drawString("Стрелочки -  поворот камеры",70,110);
        g.drawString("Space/shiwt - взлет/спуск",70,125);
        g.drawString("E - включить освещение",20,140);
        g.drawString("(предметы дальше - темнее)",20,155);
        g.drawString("Ротань Кирилл & Ротань Илья",20,200);
        mf.repaint();
    }
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
}
//Клас окна для создания полигонов
class PoliFrame extends JFrame {
    MyFrame mf;
    JTextField x1,x2,x3,y1,y2,y3,z1,z2,z3,r,g,b;
    public PoliFrame(MyFrame mf) {
        super();
        setSize(400, 170);
        setLocation(500, 400);
        getContentPane().setBackground(new Color(1, 100, 110));
        setVisible(true);
        x1=new JTextField("x1",8); x2=new JTextField("x2",8); x3=new JTextField("x3",8);
        y1=new JTextField("y1",8); y2=new JTextField("y2",8); y3=new JTextField("y3",8);
        z1=new JTextField("z1",8); z2=new JTextField("z2",8); z3=new JTextField("z3",8);
        r=new JTextField("Red",8); g=new JTextField("Green",8); b=new JTextField("Blue",8);
        x1.setLocation(100,100);
        JPanel con = new JPanel();
        con.add (x1); con.add (y1); con.add (z1);
        con.add (x2); con.add (y2); con.add (z2);
        con.add (x3); con.add (y3); con.add (z3);
        con.add (r); con.add (g); con.add (b);
        setContentPane(con);
        JButton mb1 = new JButton();
        mb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    mf.scr.n = mf.scr.n+1;
                    mf.scr.p[mf.scr.n-1] = new Poligon(Integer.valueOf(x1.getText()),Integer.valueOf(y1.getText()),Integer.valueOf(z1.getText()),Integer.valueOf(x2.getText()),Integer.valueOf(y2.getText()),Integer.valueOf(z2.getText()),Integer.valueOf(x3.getText()),Integer.valueOf(y3.getText()),Integer.valueOf(z3.getText()),
                            new Color (Integer.valueOf(r.getText()),Integer.valueOf(g.getText()),Integer.valueOf(b.getText())));
            }
        });
        con.add(mb1);
        mf.repaint ();
        this.setFocusable(true);
    }
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
}