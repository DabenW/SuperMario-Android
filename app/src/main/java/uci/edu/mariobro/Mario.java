package uci.edu.mariobro;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Mario {

    private int posX;
    private int posY;
    private int status;
    private boolean move = false;
    private boolean jump = false;
    private boolean fall = false;
    private boolean rise = false;
    private boolean dead = false;
    private boolean left;
    private boolean right;
    private final int offx = 15;
    private int offy = 10;
    static final int jumpMax = 4*GameBoard.scale+5;
    private int jumpDistance = 0;
    private int moveCounter = 0;
    private int orientation = -1;
    private boolean inSpace = false;
    private int lives = 3;




    public Mario(){
        posX = 2*GameBoard.scale;
        posY = 8*GameBoard.scale;
        status = 0;
    }

    public Mario(int lives){
        posX = 2*GameBoard.scale;
        posY = 8*GameBoard.scale;
        status = 0;
        this.lives = lives;
    }

    public void moveRight(){
        posX += offx;
    }


    public void moveLeft(){
        posX -= offx;
    }

    public boolean jump(int count){
        if ((jumpDistance+offy*count)<=jumpMax){
            rise = true;
            posY = posY - offy*count;
            jumpDistance+=offy*count;
            inSpace = true;
        }else{
            GameBoard.peekCounter = 4;
            posY = posY - (jumpMax-jumpDistance);
            rise = false;
            jump = false;
            jumpDistance = 0;
            return false;
        }
        return true;

    }

    public void gravity(int count){
        jumpDistance = 0;
        posY = posY + offy*count;
    }

    public void draw(Rect marioRect, int scale, Canvas canvas, CanvasViewer canvasView, boolean goRight, boolean goLeft, boolean goJump){
        moveCounter++;	if (moveCounter == 100)	moveCounter = 0;
        switch(status){
            case 0://small mario
                marioRect.set(posX,posY, posX+scale,posY+scale);
                if(goRight){
                    orientation = 0;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.marioRR1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.marioRR2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.marioRR3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.marioJR, null, marioRect, null);
                    }
                }
                else if(goLeft){
                    orientation = 1;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.marioRL1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.marioRL2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.marioRL3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.marioJL, null, marioRect, null);
                    }
                }else{
                    if(orientation == 1){
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.marioSL, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.marioJL, null, marioRect, null);
                        }
                    }else{
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.marioSR, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.marioJR, null, marioRect, null);
                        }
                    }
                }
                break;
            case 1://large mario
                marioRect.set(posX,posY - scale, posX+scale,posY+scale);
                if(goRight){
                    orientation = 0;
                    System.out.println(inSpace);
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.lmarioRR1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.lmarioRR2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.lmarioRR3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.lmarioJR, null, marioRect, null);
                    }
                }
                else if(goLeft){
                    orientation = 1;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.lmarioRL1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.lmarioRL2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.lmarioRL3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.lmarioJL, null, marioRect, null);
                    }
                }else{
                    if(orientation == 1){
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.lmarioSL, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.lmarioJL, null, marioRect, null);
                        }
                    }else{
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.lmarioSR, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.lmarioJR, null, marioRect, null);
                        }
                    }
                }
                break;
            case 2://flash mario
                marioRect.set(posX,posY, posX+scale,posY+scale);
                if(goRight){
                    orientation = 0;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.fmarioRR1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.fmarioRR2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.fmarioRR3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.fmarioJR, null, marioRect, null);
                    }
                }
                else if(goLeft){
                    orientation = 1;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.fmarioRL1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.fmarioRL2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.fmarioRL3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.fmarioJL, null, marioRect, null);
                    }
                }else{
                    if(orientation == 1){
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.fmarioSL, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.fmarioJL, null, marioRect, null);
                        }
                    }else{
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.fmarioSR, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.fmarioJR, null, marioRect, null);
                        }
                    }
                }
                break;
            case 3://flash large mario
                marioRect.set(posX,posY -scale, posX+scale,posY+scale);
                if(goRight){
                    orientation = 0;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.flmarioRR1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.flmarioRR2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.flmarioRR3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.flmarioJR, null, marioRect, null);
                    }
                }
                else if(goLeft){
                    orientation = 1;
                    if(move&&!inSpace){
                        if (moveCounter%3 == 0)
                            canvas.drawBitmap(canvasView.flmarioRL1, null, marioRect, null);
                        else if (moveCounter%3 == 1)
                            canvas.drawBitmap(canvasView.flmarioRL2, null, marioRect, null);
                        else if (moveCounter%3 == 2)
                            canvas.drawBitmap(canvasView.flmarioRL3, null, marioRect, null);
                    }else if(inSpace){
                        canvas.drawBitmap(canvasView.flmarioJL, null, marioRect, null);
                    }
                }else{
                    if(orientation == 1){
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.flmarioSL, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.flmarioJL, null, marioRect, null);
                        }
                    }else{
                        if(!inSpace){
                            canvas.drawBitmap(canvasView.flmarioSR, null, marioRect, null);
                        }else if(inSpace){
                            canvas.drawBitmap(canvasView.flmarioJR, null, marioRect, null);
                        }
                    }
                }
                break;
            case 4:
                marioRect.set(posX,posY, posX+scale,posY+scale);
                canvas.drawBitmap(canvasView.marioDead, null, marioRect, null);
        }

    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isMove() {
        return move;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isFall() {
        return fall;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }



    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getOffx() {
        return offx;
    }

    public int getOffy() {
        return offy;
    }

    public void setOffy(int offy) {
        this.offy = offy;
    }

    public boolean isRise() {
        return rise;
    }

    public void setRise(boolean rise) {
        this.rise = rise;
    }

    public void changeStatus(int newStatus){
        this.status = newStatus;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isInSpace() {
        return inSpace;
    }

    public void setInSpace(boolean inSpace) {
        this.inSpace = inSpace;
    }

    public int decreaseLives(){
        this.lives-=1;
        return lives;
    }

    public int getLives() {
        return lives;
    }
}

