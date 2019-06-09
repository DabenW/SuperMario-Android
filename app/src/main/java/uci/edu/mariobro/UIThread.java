package uci.edu.mariobro;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class UIThread extends Thread {
    private GameBoard gameBoard;
    final SurfaceHolder holder;
    boolean isRun = false;
    CanvasViewer canvasViewer;

    public UIThread(SurfaceHolder holder, GameBoard gameBoard,CanvasViewer canvasViewer){
        this.gameBoard = gameBoard;
        this.holder = holder;
        this.canvasViewer = canvasViewer;
    }

    @Override
    public void run() {
        long t1,t2,dt,sleepTime;
        long period = 1000 / 20;

        Canvas c = null;
        while(isRun) {
            t1 = System.nanoTime();
            try{
                synchronized (holder){
                    gameBoard.updateCanvas();
                    c = holder.lockCanvas();

                    gameBoard.draw(c);
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);// commit change
                }
            }

            t2 = System.nanoTime();
            dt = (t2-t1)/1000000L;
            sleepTime = period - dt;
            if(sleepTime<=0){
                sleepTime = 0;
            }
            try{
                Thread.sleep(sleepTime);
            }catch(InterruptedException ex){
                t1 = System.nanoTime();
            }


            if(gameBoard.isWin()&&MainActivity.level<3){
                MainActivity.increaseLevel();
                Mario mario = this.gameBoard.getMario();
                this.gameBoard = null;
                this.gameBoard = canvasViewer.getGameBoard(mario);
            }
        }
    }



    public synchronized void startGame(){
        isRun = true;
    }

    public synchronized void stopGame(){
        isRun = false;
    }

}
