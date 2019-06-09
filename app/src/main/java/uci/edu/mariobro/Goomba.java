package uci.edu.mariobro;

import android.graphics.Rect;

public class Goomba {
    private final int offsetX = 8;
    private int positionX = 0;
    private int positionY = 0;
    Rect drawRect = new Rect();
    private boolean dead = false;
    private boolean left = true;
    private boolean goombDead = false;

    public Goomba(){
        this.positionX = 0;
        this.positionY = 0;
    }

    public void move(){
        if(!dead){
            if(left){
                positionX += offsetX;
                if((positionX - 2* GameBoard.scale)>=0){
                    left = false;
                }
            }else if(!left){
                positionX -= offsetX;
                if((positionX + 5* GameBoard.scale)<=0){
                    left = true;
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


    public void setDrawRectDead() {
        this.drawRect = null;
    }

    public boolean isGoombDead() {
        return goombDead;
    }

    public void setGoombDead(boolean goombDead) {
        this.goombDead = goombDead;
    }
}
