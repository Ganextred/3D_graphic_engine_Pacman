package Pacman;


import java.awt.*;
// Класс на котором подготваливаютьса кадры.
public class Screen {
    //создание масива полигонов
    int n=14;
    Poligon[] p = new Poligon[100];
    //Создание псевдо экрана на котором будет подготавливатьса кадр
    Pixel[][] pix =new Pixel[800][600] ;
    //Начальные состояния пикселей
    Color back=Color.white; //Цвет фона
    long l= 100000000; //Этот параметр можно воспринимать как силу фонарика, при нажатие на E меняетьса на 10000
    Camera c;
    MyFrame fr;
    public Screen (MyFrame fr){
        //Ну тут я художник, знакомтесь это Пакман
        p[0]= new Poligon(0 ,100+1000-150 , 2000 -1000 ,1000,100,2000,0,100+1000,2000,new Color (225, 195, 0));
        p[1]= new Poligon(0 ,100+1000-150 , 2000-1000 ,-1000,100,2000,0,100+1000,2000,new Color (225, 225, 0));
        p[2]= new Poligon(0 ,100 , 2000 +1000 ,1000,100,2000,0,100+1000,2000,new Color (225, 225, 0));
        p[3]= new Poligon(0 ,100 , 2000+1000 ,-1000,100,2000,0,100+1000,2000,new Color (225, 195, 0));
        p[4]= new Poligon(0 ,100-1000+150 , 2000 -1000 ,1000,100,2000,0,100-1000,2000,new Color (225, 225, 0));
        p[5]= new Poligon(0 ,100-1000+150 , 2000-1000 ,-1000,100,2000,0,100-1000,2000,new Color (225, 195, 0));
        p[6]= new Poligon(0 ,100 , 2000 +1000 ,1000,100,2000,0,100-1000,2000,new Color (225, 195, 0));
        p[7]= new Poligon(0 ,100 , 2000+1000 ,-1000,100,2000,0,100-1000,2000,new Color (225, 225, 0));
        p[8]= new Poligon(0 ,100+1000-150 , 2000 -1000 ,1000,100,2000,-1000,100,2000,new Color (225, 155, 32));
        p[9]= new Poligon(0 ,100-1000+150 , 2000 -1000 ,1000,100,2000,-1000,100,2000,new Color (225, 155, 32));
        p[10]= new Poligon(0 ,100+1000-150-100 , 2000 -900 ,1000-50,100,2000,-1000+50,100,2000,new Color (1, 0, 0));
        p[11]= new Poligon(0 ,100-1000+150+100 , 2000 -900 ,1000-50,100,2000,-1000+50,100,2000,new Color (1, 0, 0));
        p[12]= new Poligon(-225-1,800,1500,-235-1, 820, 1700,-357.5-1,720,1850, new Color (225, 48, 21));
        p[13]= new Poligon(225+1,800,1500,235+1, 820, 1700,357.5+1,720,1850, new Color (225, 48, 21));
        //Инициальзация камеры и псевдо экрана
        c = new Camera (0,0,0,0,0 );
        for (int i = 0; i<800; i++)
            for (int j= 0; j<600; j++) {
                pix[i][j] = new Pixel ();
            }
        this.fr=fr;
    }
    //метод который формирует кадр на псведо экране
    public void fakepaint () {
        //обнуление пикселей
        for (int i = 0; i<800; i++)
            for (int j= 0; j<600; j++) {
                pix[i][j].r  = 1000000000;
                pix[i][j].color =back;
            }
        //вызов метода который отрисовывает полигоны
        for (int i=0 ;i<n; i++) {
                grov(p[i],c);
        }
    }
    //метод отрисовующий полигоны (тут страшно)
    public void grov(Poligon pt, Camera c) {
        double x1,y1,z1,x2,y2,z2,x3,y3,z3,xt,yt,zt;
        //сдвиг кординат полигона на кординаты камеры (чтоб после можно было предположить что камера в точке (0,0,0))
        x1=pt.x1-c.x; y1=pt.y1+c.y; z1=pt.z1-c.z; x2=pt.x2-c.x; y2=pt.y2+c.y; z2=pt.z2-c.z; x3=pt.x3-c.x; y3=pt.y3+c.y; z3=pt.z3-c.z;
        /*Вращение точек полигона на углы поворота камеры
          вместо того чтобы вращять камеру мы,пользуясь тем что камера в 0,0,0,вращяем точки вокруг
          Сначало вращяем вокруг  оси Oy потом вокруг оси Ox
        */
        //вращение первой точки
          xt=x1; zt=z1;
        x1=xt*Math.cos(c.Ay)+zt*Math.sin(c.Ay);
        z1=-xt*Math.sin(c.Ay)+zt*Math.cos(c.Ay);
        yt=y1; zt=z1;
        y1=yt*Math.cos(c.Ax)-zt*Math.sin(c.Ax);
        z1=yt*Math.sin(c.Ax)+zt*Math.cos(c.Ax);
        //вращение 2
          xt=x2; zt=z2;
        x2=xt*Math.cos(c.Ay)+zt*Math.sin(c.Ay);
        z2=-xt*Math.sin(c.Ay)+zt*Math.cos(c.Ay);
        yt=y2; zt=z2;
        y2=yt*Math.cos(c.Ax)-zt*Math.sin(c.Ax);
        z2=yt*Math.sin(c.Ax)+zt*Math.cos(c.Ax);
        //вращение 3
          xt=x3; zt=z3;
        x3=xt*Math.cos(c.Ay)+zt*Math.sin(c.Ay);
        z3=-xt*Math.sin(c.Ay)+zt*Math.cos(c.Ay);
         yt=y3; zt=z3;
        y3=yt*Math.cos(c.Ax)-zt*Math.sin(c.Ax);
        z3=yt*Math.sin(c.Ax)+zt*Math.cos(c.Ax);
        //расчет уравнение плоскости полигона по 3 новым точкам
        pt.A =  (y1*(z2 - z3) + y2*(z3 - z1) + y3*(z1 - z2));
        pt.B =  (z1 *(x2 - x3) + z2*(x3 - x1) + z3*(x1 - x2));
        pt.C = (x1* (y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2));
        pt.D = -(x1 *(y2*z3 - y3*z2) + x2*(y3*z1 - y1*z3) + x3*(y1*z2 - y2*z1));
        int X1,Y1,X2,Y2,X3,Y3,t;
        double r=400;
         int f=0;
        //находим проэкции вершин полигона на экран, ортогональные проэкции через точку (0,0,0)
        //при считаем что экран паралелен плоскости XOY и расположен на z=400 если точка находитьса на
        //z<0 - инвертируем прожкцию и получаем мнимую проэкцию.
        //Это делаетьса для того чтоб после в цикле пройти по пикселям внутри получившегося триугольника
        X1=(int)  (x1*(r/z1))+400; Y1= -(int) (y1*(r/z1))+300;  if (z1<0) {X1=-X1; Y1=-Y1 ;} if (z1>0 & 0<=X1 & X1<=800 & 0<=Y1 & Y1<=600) f=1;
        X2=(int)  (x2*(r/z2))+400; Y2= -(int) (y2*(r/z2))+300;  if (z2<0) {X2=-X2; Y2=-Y2; } if (z2>0 & 0<=X2 & X2<=800 & 0<=Y2 & Y2<=600) f=1;
        X3=(int)  (x3*(r/z3))+400; Y3= -(int) (y3*(r/z3))+300;  if (z3<0) {X3=-X3; Y3=-Y3 ;} if (z3>0 & 0<=X3 & X3<=800 & 0<=Y3 & Y3<=600) f=1;
        //f - отображает существует ли хотябы одна не мнимая проэкция точки в области экрана (иначе просто нет смысла пытатьса что-либо рисовать)
        if (f==1){
        //сортировка точек по высоте на экране
        if (Y2 < Y1){ t=Y1; Y1=Y2; Y2=t; t=X1; X1=X2; X2=t; }
        if (Y3 < Y2){ t=Y2; Y2=Y3; Y3=t; t=X2; X2=X3; X3=t; }
        if (Y2 < Y1){ t=Y1; Y1=Y2; Y2=t; t=X1; X1=X2; X2=t; }
        int YT1=Y1;int YT2=Y2;
        if (YT1<0) YT1=0; if (YT2>599) YT2=599;
        /*пару слов об алгоритме прохода по точкам триугольнка. Разбиваем триугольник на два горизонтальной линеей проходяшей через
        центральную по высоте точку. Дальше в цикле проходимся отдельно по верхнему и по нижнему треугольникам, каждый раз запуская цикл
        просматриваюший горизонтальные линии от одной прямой до другой
         */
        //верхний цикл
        for (int i = YT1; i<=YT2; i++){
          int j1, j2;
          //расцет правой и левой кординаты исходя из кравнения прямой построеной по точкам триугольнка
          j1 = (int) ((i-Y1)*(X2-X1)/(Y2-Y1+0.00001)+X1);
          j2 = (int) ((i-Y1)*(X3-X1)/(Y3-Y1+0.00001)+X1);
          if (j2<j1) {int tt = j1; j1 = j2; j2 = tt;}
          if (j1<0) j1=0; if (j2>799) j2=799;
            //"горизонтальый" цикл
              for (int j = j1; j<=j2; j++) {
                  /*расчет кординат точки пересечения прямой проходяшей через начало кординат и пиксель на экране
                  и плоскости полигона.
                  Сначало рассчитуем Z (подробней в методе findMz), после подстовляя z в уравнения прямой находим другие кординаты
                   */
                  double Mz = pix[j][i].findMz(pt, j - 400, -i + 300);
                  double Mx = (Mz) * (j - 400) / (400);
                  double My = (Mz) * (-i + 300) / (400);
                  if (Mz > 400) {
                      double rt;
                      //Находим растояние от камеры до полигона (вдоль луча)
                      rt = Math.sqrt(Mx * Mx + My * My + Mz * Mz);
                      //Если растояние до этого полигона меньше чем до придыдуших отрисованых на этом пикселе - то рисуем
                      if (rt < pix[j][i].r & rt <= l) {
                          // функция (1 - rt / l) - делает объекты вдали - темнее
                          pix[j][i].color = new Color((int) (pt.color.getRed() * (1 - rt / l)), (int) (pt.color.getGreen() * (1 - rt / l)), (int) (pt.color.getBlue() * (1 - rt / l)));
                          pix[j][i].r = rt;
                      }
                  }
          }
        }
        int YT3=Y3;
        if (YT2<0) YT2=0; if (YT3>599) YT3=599;
        //нижний цикл
        for (int i = YT2; i<=YT3; i++){
            int j1, j2;
            j1 = (int) ((i-Y2)*(X3-X2)/(Y3-Y2+0.000001)+X2);
            j2 = (int) ((i-Y1)*(X3-X1)/(Y3-Y1+0.000001)+X1);
            if (j2<j1) {int tt = j1; j1 = j2; j2 = tt;}
            if (j1<0) j1=0; if (j2>799) j2=799;
            for (int j = j1; j<=j2; j++){
                    double Mz = pix[j][i].findMz(pt, j - 400, -i + 300);
                    double Mx = (Mz) * (j - 400) / (400);
                    double My = (Mz) * (-i + 300) / (400);
                    if (Mz > 400) {
                        double rt;
                        rt = Math.sqrt(Mx * Mx + My * My + Mz * Mz);
                        if (rt < pix[j][i].r & rt <= l) {

                            pix[j][i].color = new Color((int) (pt.color.getRed() * (1 - rt / l)), (int) (pt.color.getGreen() * (1 - rt / l)), (int) (pt.color.getBlue() * (1 - rt / l)));
                            pix[j][i].r = rt;
                        }
                    }
            }
        }
        }
    }
}
// клас пиксель хранит цвет и растояние до объекта нарисованого на этом пикселе
 class Pixel {
    Color color;
    double r;
    public Pixel (){ }
    /*Метод полученый путем решения уравнений прямой и плоскости в общем виде. Находит пересечение плоскости полигона и
    луча проходящего через камеру и пиксель на экране. Вначале рассчитуем z так как кордината пикселя на экране по z != 0.
     */
    public double findMz (Poligon pt, double x2, double y2 ) {
        int x1 = 0; int y1 = 0; int z1=0;  double z2 = 400;
        double Oy = (y2-y1)/(z2);
        double Ox = (x2-x1)/(z2);
        double z=(-(pt.D)/(pt.C+pt.B*Oy+pt.A*Ox));
        return (z);
    }
}
