package uci.edu.mariobro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class CanvasViewer extends SurfaceView implements SurfaceHolder.Callback {
    private MainActivity mainActivity;
    private SurfaceHolder holder;
    private UIThread uiThread;
    private GameBoard gameBoard;

    // Background
    Bitmap background= BitmapFactory.decodeResource(getResources(), R.drawable.background1);
    Bitmap background2= BitmapFactory.decodeResource(getResources(), R.drawable.background2);
    Bitmap background3= BitmapFactory.decodeResource(getResources(), R.drawable.background3);

    Bitmap marioSL=BitmapFactory.decodeResource(getResources(), R.drawable.mario_stand_left);
    Bitmap marioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rl1);
    Bitmap marioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rl2);
    Bitmap marioRL3=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rl3);
    Bitmap marioJL = BitmapFactory.decodeResource(getResources(), R.drawable.mario_jump_left);

    Bitmap marioSR=BitmapFactory.decodeResource(getResources(), R.drawable.mario_stand_right);
    Bitmap marioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rr1);
    Bitmap marioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rr2);
    Bitmap marioRR3=BitmapFactory.decodeResource(getResources(), R.drawable.mario_rr3);
    Bitmap marioJR = BitmapFactory.decodeResource(getResources(), R.drawable.mario_jump_right);

    Bitmap marioDead = BitmapFactory.decodeResource(getResources(), R.drawable.mario_dead);

    Bitmap lmarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_stand_left);
    Bitmap lmarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rl1);
    Bitmap lmarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rl2);
    Bitmap lmarioRL3=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rl3);
    Bitmap lmarioJL = BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_jump_left);

    Bitmap lmarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_stand_right);
    Bitmap lmarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rr1);
    Bitmap lmarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rr2);
    Bitmap lmarioRR3=BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_rr3);
    Bitmap lmarioJR = BitmapFactory.decodeResource(getResources(), R.drawable.large_mario_jump_right);

    Bitmap fmarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_stand_left);
    Bitmap fmarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rl1);
    Bitmap fmarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rl2);
    Bitmap fmarioRL3=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rl3);
    Bitmap fmarioJL = BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_jump_left);

    Bitmap fmarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_stand_right);
    Bitmap fmarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rr1);
    Bitmap fmarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rr2);
    Bitmap fmarioRR3=BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_rr3);
    Bitmap fmarioJR = BitmapFactory.decodeResource(getResources(), R.drawable.flash_mario_jump_right);

    Bitmap flmarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_stand_left);
    Bitmap flmarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rl1);
    Bitmap flmarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rl2);
    Bitmap flmarioRL3=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rl3);
    Bitmap flmarioJL = BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_jump_left);

    Bitmap flmarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_stand_right);
    Bitmap flmarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rr1);
    Bitmap flmarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rr2);
    Bitmap flmarioRR3=BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_rr3);
    Bitmap flmarioJR = BitmapFactory.decodeResource(getResources(), R.drawable.large_flash_mario_jump_right);

    Bitmap bblock=BitmapFactory.decodeResource(getResources(), R.drawable.breakable_bloack);
    Bitmap ubblock=BitmapFactory.decodeResource(getResources(), R.drawable.unbreake_block);
    Bitmap qblock=BitmapFactory.decodeResource(getResources(), R.drawable.question);
    Bitmap coin=BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    Bitmap star=BitmapFactory.decodeResource(getResources(), R.drawable.star);
    Bitmap mushroom = BitmapFactory.decodeResource(getResources(), R.drawable.mushroom);
    Bitmap goombad=BitmapFactory.decodeResource(getResources(), R.drawable.goomba_dead);
    Bitmap goombar1=BitmapFactory.decodeResource(getResources(), R.drawable.goomba_run1);
    Bitmap goombar2=BitmapFactory.decodeResource(getResources(), R.drawable.goomba_run2);
    Bitmap piranhac=BitmapFactory.decodeResource(getResources(), R.drawable.piranha_close);
    Bitmap piranhao = BitmapFactory.decodeResource(getResources(), R.drawable.piranha_open);
    Bitmap pipe = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
    public CanvasViewer(Context context) {
        super(context);
        this.mainActivity = (MainActivity)context;
        holder = this.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
//        System.out.println("Surface Changed!");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //        setOnTouchListener(gameBoard);
        gameBoard = new GameBoard(this,mainActivity.getBoardViewHeight(),mainActivity.getBoardViewWidth());
        setOnTouchListener(gameBoard);
        uiThread = new UIThread(holder, gameBoard, this);
        uiThread.startGame();
        uiThread.start();
    }

    public GameBoard getGameBoard(Mario mario){
        gameBoard = new GameBoard(this,mario,mainActivity.getBoardViewHeight(),mainActivity.getBoardViewWidth());
        setOnTouchListener(gameBoard);
        return gameBoard;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        uiThread.stopGame();
    }
}
