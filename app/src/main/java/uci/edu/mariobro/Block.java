package uci.edu.mariobro;

import java.util.Random;
import android.graphics.Rect;

public class Block {
    private int itemPositoinY = 0;
    final int offsetY = 12;
    private int count = 0;
    private boolean pop = false;
    private boolean used = false;
    Rect blockRect = new Rect();	// Breakable Block
    Rect ub_bloackRect = new Rect();	// Unbreakbale Block
    Rect qblockRect = new Rect();
    Rect itemRect = new Rect();	// Items
    private int index = -1;
    private boolean finish = false;

    Random randomNum = new Random();

    public int pop(){
        pop = true;

        index = randomNum.nextInt(6);
        return index;
    }

    public void move(){
        boolean moveOver = offsetY*count<=GameBoard.scale;
        if(moveOver){
            count++;
            itemPositoinY-=offsetY;
            finish = false;
        }else{
            finish = true;
        }
    }

    public int getItemIdenx(){
        return this.index;
    }


    public int getItemPositoinY(){
        return this.itemPositoinY;
    }

    public boolean isPop() {
        return pop;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isFinish() {
        return finish;
    }

    public Rect getBlockRect(){
        return this.blockRect;
    }

    public Rect getUb_bloackRect(){
        return this.ub_bloackRect;
    }

    public Rect getItemRect(){
        return this.itemRect;
    }

    public void setBlockRect(int itemLeft,int itemTop,int itemRight,int itemBottom){
        blockRect.set(itemLeft, itemTop, itemRight, itemBottom);
    }

    public void setItemRect(int itemLeft,int itemTop,int itemRight,int itemBottom){
        itemRect.set(itemLeft, itemTop, itemRight, itemBottom);
    }

    public void setBlockRectBroken(){
        this.blockRect = null;
    }

    public Rect getQblockRect() {
        return qblockRect;
    }

    public void setQblockRectBroken() {
        this.qblockRect = null;
    }
}
