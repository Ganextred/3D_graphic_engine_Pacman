package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// клас основного окна, используется swing так как там при  вызове repeint() перерисовуется не все окно
public class MyFrame extends JFrame implements KeyListener {
    JMenuBar jm;

    public MyFrame() {
        super();
        setSize(800 + 22, 600 + 56 + 17);
        setLocation(200, 100);
        setBackground(new Color(255, 255, 255));
        setLayout(null);
        MyFrame th = this;
        // Создание меню
        JMenuBar jmenubar = new JMenuBar();
        JMenu info = new JMenu("info");
        jmenubar.add(info);
        JMenuItem open = info.add(new JMenuItem("open"));
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InfoFrame inf = new InfoFrame(th);
            }
        });
        JMenu ad = new JMenu("add");
        jmenubar.add(ad);
        JMenuItem poligon = ad.add(new JMenuItem("poligon"));
        poligon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PoliFrame inf = new PoliFrame(th);
            }
        });
        setJMenuBar(jmenubar);
        this.jm = jmenubar;
        getContentPane().addKeyListener(this);
        getContentPane().setFocusable(true);
        getContentPane().revalidate();
        setVisible(true);
    }

    Screen scr = new Screen(this);

    public void paint(Graphics g) {
        //в потоке ресуем кадр
        MyThread pain = new MyThread("1");
        pain.run(g, scr);
        // ресуем меню, так как после вызова repeint() элементы окна не ресуются
        jm.repaint();
    }

    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent we) {
    }

    ;

    public void windowOpened(WindowEvent we) {
    }

    ;

    public void windowActivated(WindowEvent we) {
    }

    ;

    public void windowIconified(WindowEvent we) {
    }

    ;

    public void windowDeiconified(WindowEvent we) {
    }

    ;

    public void windowDeactivated(WindowEvent we) {
    }

    ;

    public void keyPressed(KeyEvent e) {
        //  управление
        control(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }

    public void control(int keyCode) {
        double x, z, y, xt, zt, yt, v, rotationVelocity;
        rotationVelocity = 1;
        v = 10;
        x = 0;
        y = 0;
        z = 0;
        if (keyCode == 69) {
            if (scr.back == Color.white)
                scr.back = new Color(255, 255, 255);
            else scr.back = Color.white;
            if (scr.l == 100000000)
                scr.l = 5000;
            else scr.l = 100000000;
        }
        if (keyCode == 87) {
            x = 0;
            y = 0;
            z = 2;
        }
        if (keyCode == 83) {
            x = 0;
            y = 0;
            z = -2;
        }
        if (keyCode == 68) {
            x = 2;
            y = 0;
            z = 0;
        }
        if (keyCode == 65) {
            x = -2;
            y = 0;
            z = 0;
        }
        if (keyCode == 32) {
            x = 0;
            y = -1;
            z = 0;
        }
        if (keyCode == 16) {
            x = 0;
            y = 1;
            z = 0;
        }
        if (keyCode == 37) {
            scr.c.Ay = scr.c.Ay + 0.05 * rotationVelocity;
        }
        if (keyCode == 39) {
            scr.c.Ay = scr.c.Ay - 0.05 * rotationVelocity;
        }
        if (keyCode == 38) {
            scr.c.Ax = scr.c.Ax + 0.05 * rotationVelocity;
        }
        if (keyCode == 40) {
            scr.c.Ax = scr.c.Ax - 0.05 * rotationVelocity;
        }
        //реализация движения с учетом напровления камеры
        xt = x;
        zt = z;
        x = xt * Math.cos(-scr.c.Ay) + zt * Math.sin(-scr.c.Ay);
        z = -xt * Math.sin(-scr.c.Ay) + zt * Math.cos(-scr.c.Ay);
        for (int i = 0; i < 10; i++) {
            scr.c.x = scr.c.x + x * v;
            scr.c.y = scr.c.y + y * v;
            scr.c.z = scr.c.z + z * v;
            repaint();
        }
    }
}
