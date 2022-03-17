package Pacman;
/*
  Класс для создания обекта типа камера (типо и так непонятно)
 */
public class Camera {
    double x,y,z;
    double Ax,Ay;
    public Camera ( double x, double y, double z,double  Ax, double Ay ){
        //кординаты камеры
        this.x =x-300; this.y =-1000; this.z =z;
        //углы поворота вокруг осей X и Y
        this.Ax =Ax; this.Ay =Ay;
    }

}
