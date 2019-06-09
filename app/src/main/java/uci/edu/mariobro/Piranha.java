package uci.edu.mariobro;

import android.graphics.Rect;

public class Piranha {
    private final int offsetY = 5;
    private int positionX = 0;
    private int positionY = 0;
    Rect drawRect = new Rect();
    private boolean dead = false;
    private boolean up = true;
    long t1;
    long t2;
    long t3 = System.currentTimeMillis();
    long t4;

    public Piranha(){
        this.positionX = 0;
        this.positionY = 0;
    }

    public void move(){
        if(!dead){
            if(up){
                t4 = System.currentTimeMillis();
                if((t4-t3)/1000>3) {
                    positionY -= offsetY;
                    if ((positionY + GameBoard.scale) <= 0) {
                        up = false;
                        t1 = System.currentTimeMillis();
                    }
                }
            }else if(!up){
                t2 = System.currentTimeMillis();
                if((t2-t1)/1000> 2){
                    positionY += offsetY;
                    if(positionY ==0){
                        up = true;
                        t3 = System.currentTimeMillis();
                    }
                }
            }
        }
    }

    public void setDead(){
        this.dead = true;
    }

    public boolean isDead(){
        return this.dead;
    }

    public Rect getDrawRect() {
        return drawRect;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
