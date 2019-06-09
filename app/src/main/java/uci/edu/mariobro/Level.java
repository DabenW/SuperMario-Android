package uci.edu.mariobro;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Level {
    private int level;
    private int[][] itemList;
    private Block[][] block = new Block[GameBoard.MaxHeight][GameBoard.Maxwidth];
    private Goomba[][] goomba = new Goomba[GameBoard.MaxHeight][GameBoard.Maxwidth];
    private Piranha[][] piranha = new Piranha[GameBoard.MaxHeight][GameBoard.Maxwidth];
    private Rect ground;
    private int scale;
    private int drawCounter = 0;
    private int itemTop, itemLeft, itemBottom, itemRight;


    public Level(int scale){
        this.level = MainActivity.level;
        this.scale = scale;
        itemList = new int[GameBoard.MaxHeight][GameBoard.Maxwidth];
    }

    public void initialMap(){
        switch (level){
            case 1:
                for(int row = 0; row< GameBoard.MaxHeight; row++){
                    for(int col = 0; col<GameBoard.Maxwidth; col++){
                        itemList[row][col] = 0;
                        itemList[9][col] = 1;  //ground
                        if(col ==12 || col==14 || (col>=18 && col<=21)){
                            itemList[5][col] = 2;  //breakable block
                        }
                        if(col == 13|| col==15 || (col>=26 && col<=28)){
                            itemList[5][col] = 4;  //question -> ubreakable block
                        }
                        if(col == 14){
                            itemList[8][col] = 5;  //goomba
                        }
                        if(col == 24){
                            itemList[6][col] = 6;  //piranha
                        }
                        if(col == 24){
                            itemList[7][col] = 7;  //pipe
                        }
                        if(col == 31){
                            itemList[6][col] = 8;  //big pip
                        }
                    }
                }
                break;
            case 2:
                for(int row = 0; row< GameBoard.MaxHeight; row++){
                    for(int col = 0; col<GameBoard.Maxwidth; col++){
                        itemList[row][col] = 0;
                        itemList[9][col] = 1;  //ground
                        if(col ==12 || col==14 || (col>=18 && col<=19)){
                            itemList[5][col] = 2;  //breakable block
                        }
                        if(col == 13|| col==15 ){
                            itemList[5][col] = 4;  //ubreakable block
                        }
                        if(col == 27|| col==28){
                            itemList[4][col] = 2;
                        }
                        if(col == 14  || col ==28){
                            itemList[8][col] = 5;  //goomba
                        }
                        if(col == 31){
                            itemList[5][col] = 6;  //piranha
                        }
                        if(col == 22){
                            itemList[7][col] = 7;  //pipe
                        }
                        if(col == 31 || col == 36){
                            itemList[6][col] = 8;  //big pip
                        }
                    }
                }
                break;
            case 3:
                for(int row = 0; row< GameBoard.MaxHeight; row++){
                    for(int col = 0; col<GameBoard.Maxwidth; col++){
                        itemList[row][col] = 0;
                        itemList[9][col] = 1;  //ground
                        if(col ==12 || col==14 || (col>=18 && col<=19)){
                            itemList[5][col] = 2;  //breakable block
                        }
                        if(col == 13|| col==15 ){
                            itemList[5][col] = 4;  //ubreakable block
                        }
                        if(col == 27|| col==28){
                            itemList[4][col] = 2;
                        }
                        if(col == 14  || col ==28){
                            itemList[8][col] = 5;  //goomba
                        }
                        if(col == 31){
                            itemList[5][col] = 6;  //piranha
                        }
                        if(col == 22){
                            itemList[7][col] = 7;  //pipe
                        }
                        if(col == 31 || col == 36){
                            itemList[6][col] = 8;  //big pip
                        }
                    }
                }
                break;
        }
    }

    public void draw(Canvas canvas, CanvasViewer canvasViewer, int offsetx){
        drawCounter++;
        if (drawCounter == 100){
            drawCounter = 0;
        }

        for (int row = 0; row < GameBoard.MaxHeight; row++) {
            for (int col = 0; col < GameBoard.Maxwidth; col++) {
                itemLeft = col * scale - offsetx;
                itemTop = row * scale;
                itemRight = itemLeft + scale;
                itemBottom = itemTop + scale;

                switch (itemList[row][col]) {
                    case 1:  //ground
                        ground.set(itemLeft, itemTop, itemRight, itemBottom);
                        break;
                    case 2:  //breakable block
                        block[row][col].setBlockRect(itemLeft, itemTop, itemRight, itemBottom);
                        canvas.drawBitmap(canvasViewer.bblock, null, block[row][col].getBlockRect(), null);
                        break;
                    case 3: //unbreakable block
                        if (block[row][col].getItemRect() != null && block[row][col].isPop() && !block[row][col].isUsed()) {
                            block[row][col].setItemRect(itemLeft, itemTop + block[row][col].getItemPositoinY(), itemRight, itemBottom + block[row][col].getItemPositoinY());
                            if (block[row][col].getItemIdenx()==0) { // mushroom
                                canvas.drawBitmap(canvasViewer.mushroom, null, block[row][col].getItemRect(), null);
                            } else if (block[row][col].getItemIdenx() == 1) { //star
                                canvas.drawBitmap(canvasViewer.star, null, block[row][col].getItemRect(), null);
                            } else if (block[row][col].getItemIdenx()>=2 && block[row][col].getItemIdenx()<6) {//coin
                                canvas.drawBitmap(canvasViewer.coin, null, block[row][col].getItemRect(), null);
                            }
                        }
                        block[row][col].getUb_bloackRect().set(itemLeft, itemTop, itemRight, itemBottom);
                        canvas.drawBitmap(canvasViewer.ubblock, null, block[row][col].getUb_bloackRect(), null);
                        break;
                    case 4://question
                        if(block[row][col].getQblockRect()!=null){
                            block[row][col].getQblockRect().set(itemLeft, itemTop, itemRight, itemBottom);
                            canvas.drawBitmap(canvasViewer.qblock, null, block[row][col].getQblockRect(), null);
                        }
                        break;
                    case 5://Goomba
                        goomba[row][col].getDrawRect().set(itemLeft+goomba[row][col].getPositionX(), itemTop+goomba[row][col].getPositionY(), itemRight+goomba[row][col].getPositionX(), itemBottom+goomba[row][col].getPositionY());
                        if(goomba[row][col].isDead()){
                            if(!goomba[row][col].isGoombDead()){
                                canvas.drawBitmap(canvasViewer.goombad,null, goomba[row][col].getDrawRect(),null);
                                goomba[row][col].setGoombDead(true);
                            }
                        }else if(drawCounter%4 ==0 ||  drawCounter%4 ==1){
                            canvas.drawBitmap(canvasViewer.goombar1,null, goomba[row][col].getDrawRect(),null);
                        }else if(drawCounter%4 ==2 || drawCounter%4 ==3){
                            canvas.drawBitmap(canvasViewer.goombar2,null, goomba[row][col].getDrawRect(),null);
                        }
                        break;
                    case 6:
                        piranha[row][col].getDrawRect().set(itemLeft+piranha[row][col].getPositionX(), itemTop+piranha[row][col].getPositionY()+scale, itemRight+piranha[row][col].getPositionX(), itemBottom+piranha[row][col].getPositionY()+scale);
                        if(piranha[row][col].isDead()){

                        }else if(drawCounter%4 ==0 ||  drawCounter%4 ==1){
                            canvas.drawBitmap(canvasViewer.piranhao,null, piranha[row][col].getDrawRect(),null);
                        }else if(drawCounter%4 ==2 || drawCounter%4 ==3 ){
                            canvas.drawBitmap(canvasViewer.piranhac,null, piranha[row][col].getDrawRect(),null);
                        }
                        break;
                    case 7://pipe
                        block[row][col].getUb_bloackRect().set(itemLeft, itemTop, itemRight, itemBottom+scale);
                        canvas.drawBitmap(canvasViewer.pipe, null, block[row][col].getUb_bloackRect(), null);
                        break;
                    case 8://pipe
                        block[row][col].getUb_bloackRect().set(itemLeft, itemTop, itemRight, itemBottom+2*scale);
                        canvas.drawBitmap(canvasViewer.pipe, null, block[row][col].getUb_bloackRect(), null);
                        break;
                }
            }

        }
    }

    public void moveItem(int row, int col, int offsetx) {
//        for (int row = 0; row < GameBoard.MaxHeight; row++) {
//            for (int col = 0; col < GameBoard.Maxwidth; col++) {
                switch (itemList[row][col]) {
                    case 1: //ground
                        if (ground == null) {
                            ground = new Rect();
                        }
                        break;
                    case 2: //breakable block
                        if (block[row][col] == null) {
                            block[row][col] = new Block();
                        }
                        break;
                    case 3://unbreakable block
                        if (block[row][col] == null) {
                            block[row][col] = new Block();
                        }
                        if (block[row][col].getItemRect() != null) {
                            block[row][col].move();
                        }
                        break;
                    case 4:
                        if (block[row][col] == null) {
                            block[row][col] = new Block();
                        }
                        break;
                    case 5: //goomba
                        if (goomba[row][col] == null) {
                            goomba[row][col] = new Goomba();
                        }
                        goomba[row][col].move();
                        break;
                    case 6:  //flower
                        if (piranha[row][col] == null) {
                            piranha[row][col] = new Piranha();
                        }
                        piranha[row][col].move();
                        break;
                    case 7: //pipe
                        if (block[row][col] == null) {
                            block[row][col] = new Block();
                        }
                        break;
                    case 8:
                        if (block[row][col] == null) {
                            block[row][col] = new Block();
                        }
                        break;
                }
//            }
//        }
    }

    public int isCollided(int row, int col, Mario mario, Rect marioRect, int offsetx){
        int collisionIndex = 1;
        itemLeft = col * scale - offsetx;
        itemTop = row * scale;
        itemRight = itemLeft + scale;
        itemBottom = itemTop + scale;
        int collisionLeft = itemLeft - scale +1;
        int collisionRight = itemLeft + scale;
        int collisionTop = itemTop - scale;
        int collisionBottom = itemTop + scale;

        int item = itemList[row][col];

        switch(item){
            case 1: //ground
                if(mario.getPosY()+scale >= itemTop){
                    mario.setPosY(itemTop-scale);
                    collisionIndex = 0;
                    mario.setInSpace(false);
                }
//                System.out.println("ground:"+collisionIndex);
                break;
            case 2:  //breakable block
                if((mario.getPosY()<=itemBottom && mario.getPosY()>itemBottom-scale/2)&&(mario.getPosX()>= collisionLeft)&&(mario.getPosX()<=collisionRight)&&mario.isRise()){ //collide the breakable block

                    if(mario.getStatus()==1 ||mario.getStatus()==3){
                        itemList[row][col] = 0;
                        block[row][col].setBlockRectBroken();
                        if(mario.getPosY()-scale<=itemBottom){
                            mario.setPosY(itemBottom+scale);
                            collisionIndex = 4;
                        }
                    }else{
                        mario.setPosY(itemBottom);
                        collisionIndex = 4;
                    }
                }else if((mario.getPosY()+scale <= itemTop+scale/2&&mario.getPosY()+scale>=itemTop-5) && (mario.getPosX()>= collisionLeft)&&(mario.getPosX()<=collisionRight)){ //on the block
                    mario.setPosY(itemTop-scale);
                    collisionIndex = 0;
                    mario.setInSpace(false);
                }else if((mario.getPosX()+scale >= itemLeft&& mario.getPosX()+scale<=itemLeft)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom)){//touch the left of block
                    collisionIndex = 2;
                }else if((mario.getPosX() <= itemRight && mario.getPosX()>=itemRight-scale)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom)){ //touch the right of the block
                    collisionIndex = 3;
                }else{
                    collisionIndex = 1;
                }
//                System.out.println("breakable:"+collisionIndex);
                break;
            case 3: //unbreakable block
                if(mario.getStatus()==1 ||mario.getStatus()==3) {
                    if ((mario.getPosY()- scale <= itemBottom && mario.getPosY()- scale > itemBottom - scale / 2) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight) && mario.isRise()) { //collide the breakable block

                        if (mario.getPosY() - scale <= itemBottom) {
                            mario.setPosY(itemBottom + scale);
                            collisionIndex = 4;
                        }
                    } else if ((mario.getPosY() + scale <= itemTop + scale / 2 && mario.getPosY() + scale >= itemTop - 5) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight)) { //on the block
                        mario.setPosY(itemTop - scale);
                        collisionIndex = 0;
                        mario.setInSpace(false);
                    } else if ((mario.getPosX() + scale >= itemLeft && mario.getPosX() + scale <= itemLeft) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) {//touch the left of block
                        collisionIndex = 2;
                    } else if ((mario.getPosX() <= itemRight && mario.getPosX() >= itemRight - scale) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) { //touch the right of the block
                        collisionIndex = 3;
                    } else {
                        collisionIndex = 1;
                    }
                }else{
                    if ((mario.getPosY() <= itemBottom && mario.getPosY() > itemBottom - scale / 2) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight) && mario.isRise()) { //collide the breakable block

                            mario.setPosY(itemBottom);
                            collisionIndex = 4;
                    } else if ((mario.getPosY() + scale <= itemTop + scale / 2 && mario.getPosY() + scale >= itemTop - 5) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight)) { //on the block
                        mario.setPosY(itemTop - scale);
                        collisionIndex = 0;
                        mario.setInSpace(false);
                    } else if ((mario.getPosX() + scale >= itemLeft && mario.getPosX() + scale <= itemLeft) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) {//touch the left of block
                        collisionIndex = 2;
                    } else if ((mario.getPosX() <= itemRight && mario.getPosX() >= itemRight - scale) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) { //touch the right of the block
                        collisionIndex = 3;
                    } else {
                        collisionIndex = 1;
                    }
                }

                if(Rect.intersects(block[row][col].itemRect,marioRect)&&!block[row][col].isUsed()&&block[row][col].isFinish()){
                    if(block[row][col].getItemIdenx()==0){//mushroom
                        GameBoard.score+= 50;
                        if(mario.getStatus()==0){
                            mario.setStatus(1);
                        }else if(mario.getStatus()==2){
                            mario.setStatus(3);
                        }

                    }
                    if(block[row][col].getItemIdenx()==1){//star
                        GameBoard.score+= 200;
                        mario.setStatus(2);
                    }
                    if(block[row][col].getItemIdenx()>=2 && block[row][col].getItemIdenx()<6){//coin
                        GameBoard.score+= 200;
                    }
                    block[row][col].setUsed(true);
                }
//                System.out.println("unbreakable:"+collisionIndex);
                break;
            case 4: //question
                if(mario.getStatus()==1 ||mario.getStatus()==3) {
                    if ((mario.getPosY() - scale <= itemBottom && mario.getPosY() - scale > itemBottom - scale / 2) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight) && mario.isRise()) { //collide the breakable block

                        if (!block[row][col].isPop()) {
                            block[row][col].pop();
                        }
                        itemList[row][col] = 3;
                        block[row][col].setQblockRectBroken();
                        if (mario.getPosY() - scale <= itemBottom) {
                            mario.setPosY(itemBottom + scale);
                            collisionIndex = 4;
                        }
                    } else if ((mario.getPosY() + scale <= itemTop + scale / 2 && mario.getPosY() + scale >= itemTop - 5) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight)) { //on the block
                        mario.setPosY(itemTop - scale);
                        collisionIndex = 0;
                        mario.setInSpace(false);
                    } else if ((mario.getPosX() + scale >= itemLeft && mario.getPosX() + scale <= itemLeft) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) {//touch the left of block
                        collisionIndex = 2;
                    } else if ((mario.getPosX() <= itemRight && mario.getPosX() >= itemRight - scale) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) { //touch the right of the block
                        collisionIndex = 3;
                    } else {
                        collisionIndex = 1;
                    }
                }else{
                    if ((mario.getPosY() <= itemBottom && mario.getPosY() > itemBottom - scale / 2) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight) && mario.isRise()) { //collide the breakable block

                        if (!block[row][col].isPop()) {
                            block[row][col].pop();
                        }
                        itemList[row][col] = 3;
                        block[row][col].setQblockRectBroken();
                            mario.setPosY(itemBottom);
                            collisionIndex = 4;

                    } else if ((mario.getPosY() + scale <= itemTop + scale / 2 && mario.getPosY() + scale >= itemTop - 5) && (mario.getPosX() >= collisionLeft) && (mario.getPosX() <= collisionRight)) { //on the block
                        mario.setPosY(itemTop - scale);
                        collisionIndex = 0;
                        mario.setInSpace(false);
                    } else if ((mario.getPosX() + scale >= itemLeft && mario.getPosX() + scale <= itemLeft) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) {//touch the left of block
                        collisionIndex = 2;
                    } else if ((mario.getPosX() <= itemRight && mario.getPosX() >= itemRight - scale) && (mario.getPosY() >= collisionTop) && (mario.getPosY() <= collisionBottom)) { //touch the right of the block
                        collisionIndex = 3;
                    } else {
                        collisionIndex = 1;
                    }
                }
                break;
            case 5: //goomba
                if(marioRect.intersect(goomba[row][col].getDrawRect())&& !goomba[row][col].isDead()&&mario.getPosY()<=goomba[row][col].getDrawRect().exactCenterY()-scale/2-5){//on top o goomba
                    goomba[row][col].setDead();
                    collisionIndex = 5;
                }else if(marioRect.intersect(goomba[row][col].getDrawRect())&& !goomba[row][col].isDead()){
                    if(mario.getStatus()>=2) {
                        goomba[row][col].setDead();
                        collisionIndex = 1;
                    } else {
                        collisionIndex = 6;
                    }
                }else{
                    collisionIndex = 1;
                }
                break;
            case 6:
                if(marioRect.intersect(piranha[row][col].getDrawRect())&& !piranha[row][col].isDead()){
                    collisionIndex = 6;
                }else{
                    collisionIndex = 1;
                }
                break;
            case 7:
                if((mario.getPosY()+scale <= itemTop+scale/2&&mario.getPosY()+scale>=itemTop-5) && (mario.getPosX()>= collisionLeft)&&(mario.getPosX()<=collisionRight)){ //on the block
                    mario.setPosY(itemTop-scale);
                    collisionIndex = 0;
                    mario.setInSpace(false);
                }else if((mario.getPosX()+scale >= itemLeft&& mario.getPosX()+scale<=itemLeft+15)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom)){//touch the left of block
                    mario.setPosX(itemLeft-scale);
                    collisionIndex = 2;
                }else if((mario.getPosX() >= itemRight-15 && mario.getPosX()<=itemRight)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom)){ //touch the right of the block
                    mario.setPosX(itemRight);
                    collisionIndex = 3;
                }else{
                    collisionIndex = 1;
                }
                break;
            case 8:
                if((mario.getPosY()+scale <= itemTop+scale/2&&mario.getPosY()+scale>=itemTop-5) && (mario.getPosX()>= collisionLeft)&&(mario.getPosX()<=collisionRight)){ //on the block
                    mario.setPosY(itemTop-scale);
                    collisionIndex = 0;
                    mario.setInSpace(false);
                }else if((mario.getPosX()+scale >= itemLeft&& mario.getPosX()+scale<=itemLeft+15)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom+scale)){//touch the left of block
                    mario.setPosX(itemLeft-scale);
                    collisionIndex = 2;
                }else if((mario.getPosX() >= itemRight-15 && mario.getPosX()<=itemRight)&&(mario.getPosY()>=collisionTop)&&(mario.getPosY()<=collisionBottom+scale)){ //touch the right of the block
                    mario.setPosX(itemRight);
                    collisionIndex = 3;
                }else{
                    collisionIndex = 1;
                }
                break;
        }
        return collisionIndex;



    }
}
