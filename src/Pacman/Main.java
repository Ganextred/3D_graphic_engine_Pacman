package Pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
/*
  Тут реализуетьса оконый интерфейс
 */
class Main {
    public static void main(String[] args) {
        MyFrame mf = new MyFrame();
    }
}
// клас основного окна, используется swing так как там при  вызове repeint() перерисовуется не все окно
class MyFrame extends JFrame implements  KeyListener{
    JMenuBar jm;
    public MyFrame() {
        super();
        setSize(800+22, 600+56+17);
        setLocation(200, 100);
        setBackground(new Color(255, 255, 255));
        setLayout (null);
        MyFrame th = this;
        // Создание меню
        JMenuBar jmenubar = new JMenuBar();
        JMenu info = new JMenu("info");
        jmenubar.add (info);
        JMenuItem open = info.add (new JMenuItem("open"));
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 InfoFrame inf =new InfoFrame(th);
            }
        });
        JMenu ad = new JMenu("add");
        jmenubar.add (ad);
        JMenuItem poligon = ad.add (new JMenuItem("poligon"));
        poligon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PoliFrame inf =new PoliFrame(th);
            }
        });
        setJMenuBar (jmenubar);
        this.jm=jmenubar;
        getContentPane().addKeyListener (this);
        getContentPane().setFocusable(true);
        getContentPane().revalidate();
        setVisible(true);
    }
     Screen scr = new Screen(this);
    public void paint (Graphics g) {
        //в потоке ресуем кадр
       MyThread pain = new MyThread("1");
       pain.run(g,scr);
        // ресуем меню, так как после вызова repeint() элементы окна не ресуются
       jm.repaint();
   }
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent we){};
    public void windowOpened(WindowEvent we) {};
    public void windowActivated(WindowEvent we){};
    public void windowIconified(WindowEvent we){};
    public void windowDeiconified(WindowEvent we){};
    public void windowDeactivated(WindowEvent we){};
    public void keyPressed(KeyEvent e){
      //  управление
      double x,z,y,xt,zt,yt,v;
      v=10;
      x=0; y=0; z=0;
        if (e.getKeyChar() == 'e') {
            if (scr.back==Color.white)
                scr.back=new Color(255,255,255);
            else scr.back=Color.white;
            if (scr.l==100000000)
                scr.l=5000;
            else scr.l=100000000;;
        }
     if (e.getKeyChar() == 'w') {
            x=0; y=0; z=2;
        }
     if (e.getKeyChar() == 's') {
         x=0; y=0; z=-2;
     }
     if (e.getKeyChar() == 'd') {
         x=2; y=0; z=0;
     }
     if (e.getKeyChar() == 'a') {
         x=-2; y=0; z=0;
     }
     if (e.getKeyChar() == ' ') {
         x=0; y=-1; z=0;
     }
     if (e.getKeyCode() == 16) {
         x=0; y=1; z=0;
     }
     if (e.getKeyCode() == 37) {
            scr.c.Ay = scr.c.Ay + 0.05;
     }
     if (e.getKeyCode() == 39) {
            scr.c.Ay = scr.c.Ay - 0.05;
     }
     if (e.getKeyCode() == 38) {
            scr.c.Ax = scr.c.Ax + 0.05;
     }
     if (e.getKeyCode() == 40) {
            scr.c.Ax = scr.c.Ax - 0.05;
     }
       //реализация движения с учетом напровления камеры
        xt=x; zt=z;
        x=xt*Math.cos(-scr.c.Ay)+zt*Math.sin(-scr.c.Ay);
        z=-xt*Math.sin(-scr.c.Ay)+zt*Math.cos(-scr.c.Ay);
        scr.c.x = scr.c.x+x*v;
        scr.c.y = scr.c.y+y*v;
        scr.c.z = scr.c.z+z*v;
        repaint ();
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
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