package com.example.rbates.game_of_life;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {
    private boolean[][] board;
    private int cellWidth;
    private Paint live;
    private Paint dead;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BoardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBoard(boolean[][] board){
        this.board = board;
        cellWidth = getWidth() / board.length;
        live = new Paint();
        live.setColor(Color.RED);
        dead = new Paint();
        dead.setColor(Color.BLACK);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int left = i * cellWidth;
                int top = j * cellWidth;
                boolean state = board[i][j];
                canvas.drawRect(left, top, left + cellWidth, top + cellWidth, state ? live : dead);
            }
        }
    }
}
