import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Polygon;

class Swing03v2{
    public static void main(String args[]){
        JFrame window = new JFrame("Swing");
        MyCanvas canvas = new MyCanvas();   //Draw ton canvas

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 300);
        window.add(canvas);
        window.pack();  // set the "size of frame to preferred sizes"
        window.setResizable(false); // Do not change the size frame whit mouse
        window.setLocationRelativeTo(null); // center of window
        window.setVisible(true);
    }
}

class MyCanvas extends JPanel implements ActionListener{
    ArrayList<Car> cars = new ArrayList<Car>();
    ArrayList<Car2> cars2 = new ArrayList<Car2>();
    ArrayList<Car3> cars3=new ArrayList<Car3>();
    public MyCanvas () {
        setPreferredSize(new Dimension(400,300));
        setBackground(Color.GRAY);
        Timer timer = new Timer(80, this);
        timer.start();
        cars.add(new Car(10, 250, 2, 0, Color.BLACK, 60,30));
        cars2.add(new Car2(10, 200, 3, 0, Color.LIGHT_GRAY, 50,25));
        cars3.add(new Car3(10,100,1,0,Color.BLUE,40,20));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Paintbrush paintBrush = new Paintbrush(g);
        paintBrush.drawSky();
        paintBrush.drawMountains();
        for(Car c : cars){
            c.move();
            c.draw(g);
        }
        for(Car2 c2:cars2){
            c2.move();
            c2.draw(g);
        }
        for(Car3 c3:cars3){
            c3.move();
            c3.draw(g);
        }
        paintBrush.drawTree();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

class Paintbrush{
    private Graphics g;
    private static final Color COLOR_BROWN = new Color(34,27,21);
    private static final Color COLOR_TRUNK_TREE = new Color(50,27,30);
    private static final Color COLOR_TREE = new Color(16,46,60);

    public Paintbrush(Graphics g){
        this.g = g;
    }
    public void drawSky() {
        g.setColor(new Color (127,233,245));
        g.fillRect(0,0, 400, 100);

        g.setColor(Color.YELLOW);
        g.fillOval(40, 40, 20, 20);
    }
    public void drawMountains() {
        Polygon triangle = new Polygon();
        triangle.addPoint(15,120);
        triangle.addPoint(100,40);
        triangle.addPoint(180,130);
        g.setColor(COLOR_BROWN);
        g.fillPolygon(triangle);
    }
    public void drawTree() {
        int start = 200;
        int top = 50;

        // draw trunk
        g.setColor(COLOR_TRUNK_TREE);
        g.fillRect(start-10, top+110, 26, 40);

        g.setColor(COLOR_TREE);
        for(int i=0; i < 5; i++ ){
            Polygon triangle = new Polygon();
            int height = 50;
            int width = 70;
            int spacing = 15;

            triangle.addPoint(start, top + (spacing * i));
            triangle.addPoint(start - (width/2), top + height + (spacing*i));
            triangle.addPoint(start + (width/2), top + height + (spacing*i));

            g.fillPolygon(triangle);
        }
    }
}

//clase padre
class Vehicle{
    private int speedX;
    private int speedY;
    private Color color; 
    private Dot dot;

    public Vehicle (int x, int y, int speedX, int speedY, Color color){
        dot = new Dot(x,y);
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
    }
    public Vehicle (Dot d, int speedX,int speedY, Color color){
        dot = new Dot(d.getX(), d.getY());
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
    }
    public void move(){
        dot.addX(speedX);
        dot.addY(speedY);
    }
    public void setSpeedX(int speed){
        this.speedX=speed;
    }
    public void setSpeedY(int speed){
        this.speedY = speed;
    }
    public int getSpeedX(){
        return speedX;
    }
    public int getSpeedY(){
        return speedY;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public Dot getDot(){
        return dot;
    }

}

class Dot{
    private int x;
    private int y;
    public Dot(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void addX(int x) {
        this.x += x; 
    }
    public void addY(int y) {
        this.y += y; 
    }
    public boolean compare(Dot d){
        if ( x == d.getX() && y == d.getY())
        return true;
        return false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

}

class Car extends Vehicle{
    private int w, h;

    public Car(int x, int y, int speedX, int speedY, Color color, int w, int h){
        super(x, y, speedX, speedY, color);
        this.w = w;
        this.h = h;
    }
    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.fillRect(getDot().getX(), getDot().getY(), w, h);
        g.setColor(Color.WHITE);
        double x1 = w, x2 = w;
        double y = h;
        x1 *= 0.1; x2 *= 0.7;
        y *= 0.8;
        g.fillOval(getDot().getX() + (int)x1, getDot().getY() + (int)y, h/2, h/2);
        g.fillOval(getDot().getX() + (int)x2, getDot().getY() + (int)y, h/2, h/2);
    }
}

class Car2 extends Vehicle{
    private int w, h;

    public Car2(int x, int y, int speedX, int speedY, Color color, int w, int h){
        super(x, y, speedX, speedY, color);
        this.w = w;
        this.h = h;
    }
    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.fillRect(getDot().getX(), getDot().getY(), w, h);
        g.setColor(Color.WHITE);
        double x1 = w, x2 = w;
        double y = h;
        x1 *= 0.1; x2 *= 0.7;
        y *= 0.8;
        g.fillOval(getDot().getX() + (int)x1, getDot().getY() + (int)y, h/2, h/2);
        g.fillOval(getDot().getX() + (int)x2, getDot().getY() + (int)y, h/2, h/2);
    }
}

class Car3 extends Vehicle{
    private int w, h;

    public Car3(int x, int y, int speedX, int speedY, Color color, int w, int h){
        super(x, y, speedX, speedY, color);
        this.w = w;
        this.h = h;
    }
    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.fillRect(getDot().getX(), getDot().getY(), w, h);
        g.setColor(Color.WHITE);
        double x1 = w, x2 = w;
        double y = h;
        x1 *= 0.1; x2 *= 0.7;
        y *= 0.8;
        g.fillOval(getDot().getX() + (int)x1, getDot().getY() + (int)y, h/2, h/2);
        g.fillOval(getDot().getX() + (int)x2, getDot().getY() + (int)y, h/2, h/2);
    }
}
