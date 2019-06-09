package uci.edu.mariobro;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameBoard implements View.OnTouchListener {
    private CanvasViewer canvasView;
    final static int Maxwidth = 40;
    final static int MaxHeight = 10;
    static int canvasH;
    static int canvasW;
    final int boardWidth;
    final int boardHeight;
    static int scale;
    static int offsetx;
    Mario mario;
    Paint textPaint = new Paint();
    private int marioStatus = 1;  //the status of mario to other placement
    private int touchx = 0;  //the position of x of touching screen
    private int touchy = 0;  //the position of y of touching screen
    private boolean goRight = false;
    private boolean goLeft = false;
    private boolean sync = false;
    private boolean touchJump = false;
    private boolean jump = false;
    private int count;
    private boolean win = false;
    Level level;
    static int peekCounter = 0;
    private boolean jumpFlag = true;
    static int score = 0;
    private int marioCounter = 0;


    private static Rect backgroundRect = new Rect();
    private static Rect marioRect = new Rect();
    private Paint gameover = new Paint();

    public GameBoard(CanvasViewer canvasView,int h, int w){
        this.canvasView = canvasView;
        canvasH = h;  // get the height of the screen
        canvasW = w;  // get width of the screen
//        System.out.println(canvasH);
        scale = canvasH / MaxHeight;   //scale the board to screen
        boardHeight = scale * MaxHeight;
        boardWidth = scale * Maxwidth;

        offsetx = 0;
        mario = new Mario();
        count = 12;
        this.level = new Level(scale);
        this.level.initialMap();

        mario.setOffy(4*scale/78);
    }

    public GameBoard(CanvasViewer canvasView,Mario mario,int h, int w){
        this.canvasView = canvasView;
        canvasH = h;  // get the height of the screen
        canvasW = w;  // get width of the screen
//        System.out.println(canvasH);
        scale = canvasH / MaxHeight;   //scale the board to screen
        boardHeight = scale * MaxHeight;
        boardWidth = scale * Maxwidth;

        offsetx = 0;
        this.mario = mario;
        count = 12;
        this.level = new Level(scale);
        this.level.initialMap();

        mario.setOffy(4*scale/78);
        mario.setPosX(2*scale);
        mario.setPosY(8*scale);
    }

    public void draw(Canvas canvas){
        //draw background
        canvas.drawColor(Color.BLACK);
        Paint p = new Paint(); // 创建画笔
        backgroundRect.set(0-offsetx, 0, boardWidth-offsetx, boardHeight);
        if (MainActivity.level == 1){
            canvas.drawBitmap(canvasView.background, null, backgroundRect, null);
        }else if(MainActivity.level == 2){
            canvas.drawBitmap(canvasView.background2, null, backgroundRect, null);
        }else if(MainActivity.level == 3){
            canvas.drawBitmap(canvasView.background3, null, backgroundRect, null);
        }



        textPaint.setColor(Color.YELLOW);
        textPaint.setTextSize(50);
        canvas.drawText("Lives:"+mario.getLives(),3*scale, scale*2/3,textPaint);
        canvas.drawText("Score:"+score,7*scale, scale*2/3,textPaint);
        canvas.drawText("Level : "+ MainActivity.level, 13*scale, scale*2/3, textPaint);

        level.draw(canvas, canvasView, offsetx);
        //draw mario
        mario.draw(marioRect, scale, canvas, canvasView,goRight,goLeft, jump);

        gameover.setColor(Color.RED);
        gameover.setTextSize(100);
        if (mario.getLives() == 0 && mario.isDead()) {
            canvas.drawText("Game Over", 8 * scale, 5 * scale, gameover);
        }
    }

    public void updateCanvas(){
        synchronized (this){
            checkStatus();
        }

        if(mario.isMove()&&!mario.isDead()){
            if(goRight){
                if(mario.isRight()){
                    System.out.println(mario.getPosX());
                    System.out.println(canvasW-scale);
                    if(mario.getPosX()<canvasW*4/7){
                        mario.moveRight();
                    }else if(offsetx<(boardWidth-canvasW-mario.getOffx())) {
                        offsetx = offsetx + mario.getOffx();
                    }else if(mario.getPosX()<(canvasW-scale)){
                        mario.moveRight();
                    }else if(mario.getPosX()>=(canvasW-scale)){
                        System.out.println("win the lvele");
                        this.win = true; // win this level
                    }
                }
            }
            if(goLeft){
                if(mario.isLeft()){
                    if(mario.getPosX()>canvasW*3/7){
                        mario.moveLeft();
                    }else if(offsetx>0) {
                        offsetx = offsetx - mario.getOffx();
                    }else if(mario.getPosX()>0){
                        mario.moveLeft();
                    }
                }
            }

        }
        if(touchJump && jumpFlag){
            jump = true;
            jumpFlag = false;
        }else if(!touchJump){
            jump = false;
        }

//        System .out.println(marioStatus);

        if(jump &&mario.isJump()){

            jump = mario.jump(count+1);
            System.out.println("goJump");
            count--;
        }else if(mario.isFall()){
            jump = false;
            if(peekCounter<=0){
                mario.gravity(count);
                mario.setRise(false);
                System.out.println("fall down");
                count++;
            }
            peekCounter--;
        }else{
            jumpFlag = true;
            count = 12;
            peekCounter = 0;
        }

        marioStatus = 1;
//        level.moveItem(offsetx);
        for (int row = 0; row < MaxHeight; row++) {
            for (int col = 0; col < Maxwidth; col++) {
                level.moveItem(row,col,offsetx);
                if (marioStatus == 1) {
                    marioStatus = level.isCollided(row,col,mario, marioRect, offsetx);
                }
            }
        }

    }

    public void checkStatus(){
        switch(marioStatus){
            case 0: // on the ground
                mario.setLeft(true);
                mario.setRight(true);
                mario.setJump(true);
                mario.setFall(false);
                break;
            case 1: // in the air
                mario.setLeft(true);
                mario.setRight(true);
                mario.setJump(true);
                mario.setFall(true);
                break;
            case 2: //block ia at mario's right
                mario.setLeft(true);
                mario.setRight(false);
                mario.setJump(true);
                mario.setFall(true);
                break;
            case 3: //block is at mario's left;
                mario.setLeft(false);
                mario.setRight(true);
                mario.setJump(true);
                mario.setFall(true);
                break;
            case 4: //mario's head touch the block
                mario.setLeft(true);
                mario.setRight(true);
                mario.setJump(false);
                mario.setFall(true);
                count = 2;
                break;
            case 5:  //jump on the enemy
                mario.setLeft(true);
                mario.setRight(true);
                mario.setJump(false);
                mario.setFall(true);
                jump = true;
                count = 3;
                score += 100;
                break;
            case 6:  //touch the enemy
                mario.setLeft(false);
                mario.setRight(false);
                mario.setJump(false);
                if (marioCounter%2 == 1) {
                    if (mario.getStatus() >= 2) {

                    }else if (mario.getStatus() > 1) {
                        mario.setStatus(0);
                    }else if (mario.getStatus() == 0) {
                        int life = mario.decreaseLives();
                        if (mario.getLives() > 0) {
                            //mario = new Mario(life);
                            mario.setPosX(2*scale);
                            mario.setPosY(8*scale);
                        }
                        else {
                            mario.setStatus(4);
                            jump = true;
                            count = 1;
                        }
                    }
                }
                marioCounter++;
                break;
        }
        if(marioCounter==100){
            marioCounter=0;
        }
        if(mario.getLives()==0){
            mario.setDead(true);
            mario.setStatus(4);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int touchx = 0;  //the position of x of touching screen
        int touchy = 0;  //the position of y of touching screen
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                touchx = (int)event.getX(0);
                touchy = (int)event.getY(0);
                mario.setMove(true);
                if(touchx>(canvasW*2/3)&& touchy<=(canvasH*4/5)){
                    goRight = true;
                    goLeft = false;
                }else if(touchx<(canvasW/3)&& touchy<=(canvasH*4/5)){
                    goLeft = true;
                    goRight = false;
                }else if(touchx>(canvasW/3)&&touchx<(canvasW*2/3)&&touchy>(canvasH/2)){
                    touchJump = true;
                }
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                touchx = (int)event.getX(1);
                touchy = (int)event.getY(1);


                if(goLeft || goRight){
                    touchJump = true;
                }
                if(touchJump){
                    mario.setMove(true);
                    if(touchx>(canvasW*2/3)&& touchy<=(canvasH*4/5)){
                        goRight = true;
                        goLeft = false;
                    }else if(touchx<(canvasW/3)&& touchy<=(canvasH*4/5)){
                        goLeft = true;
                        goRight = false;
                    }
                }
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                touchy = (int)event.getY(0);
                if(touchy<=(canvasH*4/5)){
                    touchJump = false;
                    count = 0;
                }

                touchJump = false;
                count = 0;

                return true;
            case MotionEvent.ACTION_UP:
                mario.setMove(false);


                touchJump = false;
                count = 0;

                goLeft = false;
                goRight = false;
                return true;
        }
        return false;
    }

    public boolean isWin(){
        return this.win;
    }

    public Mario getMario() {
        return mario;
    }
}
