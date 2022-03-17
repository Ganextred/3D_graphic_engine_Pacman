package Pacman;

import java.awt.*;
/*
  Класс для создания обекта типа полигон (очевидно)
 */
public class Poligon {
    double A,B,C,D;
    double x1; double y1; double z1; double x2; double y2; double z2; double x3; double y3; double z3;
    Color color;
    public Poligon (double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, Color color){
        //Кординаты вершин
        this.x1 = x1; this.y1 = y1; this.z1 =z1;
        this.x2 = x2; this.y2 = y2; this.z2 =z2;
        this.x3 = x3; this.y3 = y3; this.z3 =z3;
        //Коефиценты уравнения плоскости полигона вида Ax+By+Cz+D = 0
        this.A =  y1*(z2 - z3) + y2*(z3 - z1) + y3*(z1 - z2);
        this.B =  z1 *(x2 - x3) + z2*(x3 - x1) + z3*(x1 - x2);
        this.C = x1* (y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2);
        this.D = -(x1 *(y2*z3 - y3*z2) + x2*(y3*z1 - y1*z3) + x3*(y1*z2 - y2*z1));
        // Цвет
        this.color = color;
    }
}
