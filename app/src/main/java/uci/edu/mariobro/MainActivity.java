package uci.edu.mariobro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Button start;
    static int level;
    private int boardViewHeight;
    private int boardViewWidth;
    private MainActivity m = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startButton);

        final RelativeLayout startLayout = findViewById(R.id.background);

        startLayout.post(new Runnable() { // get width and height of board view dynamically

            @Override
            public void run() {
                boardViewHeight = startLayout.getHeight();
                boardViewWidth = startLayout.getWidth();
            }

        });

        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                level = 1;
                setContentView(R.layout.layout1);

                final RelativeLayout canvasLayout = findViewById(R.id.gameCanvas);

                CanvasViewer canvas = new CanvasViewer(m);
                RelativeLayout.LayoutParams canvasParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                canvas.setLayoutParams(canvasParams);

                canvasLayout.addView(canvas);

            }

        });

    }

    public int getBoardViewHeight(){
        return this.boardViewHeight;
    }



    public int getBoardViewWidth(){
        return this.boardViewWidth;
    }

    static void increaseLevel(){
        level+=1;
    }
}
