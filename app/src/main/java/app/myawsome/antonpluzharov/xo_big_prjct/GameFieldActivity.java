package app.myawsome.antonpluzharov.xo_big_prjct;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Anton Pluzharov on 10/6/2017.
 */

public class GameFieldActivity extends AppCompatActivity {    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GameFieldActivity.PixelGridView pixelGrid = new GameFieldActivity.PixelGridView(this);
    pixelGrid.setNumColumns(10);
    pixelGrid.setNumRows(10);
    setContentView(pixelGrid);
}

    public class PixelGridView extends View {
        private int numColumns, numRows;
        private int cellWidth;
        private Paint cellFillPaint = new Paint();
        private int[][] field;
        private final int empty=2, cross=1, zero=0;
        private int moveCounter=0;

        public PixelGridView(Context context) {
            this(context, null);
        }

        public PixelGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
            cellFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        public void setCell(int x, int y,int val) {
            if(x < numColumns && y < numRows) {
                field[x][y] = val;
            }
        }

        public void setNumColumns(int numColumns) {
            this.numColumns = numColumns;
            calculateDimensions();
        }

        public int getNumColumns() {
            return numColumns;
        }

        public void setNumRows(int numRows) {
            this.numRows = numRows;
            calculateDimensions();
        }

        public int getNumRows() {
            return numRows;
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            calculateDimensions();
        }

        private void calculateDimensions() {
            if (numColumns < 1 || numRows < 1) {
                return;
            }

            cellWidth = getWidth() / numColumns;

            field = new int[numColumns][numRows];
            for (int i=0; i<numColumns;i++)
                for (int j=0;j<numRows;j++)
                {
                    setCell(i,j,empty);//set sell empty
                }
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            if (numColumns == 0 || numRows == 0) {
                return;
            }

            int width = getWidth();
            int height = getHeight();

            for (int i = 0; i < numColumns; i++) {
                for (int j = 0; j < numRows; j++) {
                    if (field[i][j]==empty)
                    {
                        cellFillPaint.setColor(Color.WHITE);
                        canvas.drawRect(i * cellWidth, j * cellWidth,
                                (i + 1) * cellWidth, (j + 1) * cellWidth,
                                cellFillPaint);
                    }
                    else if(field[i][j]==cross)
                    {
                        cellFillPaint.setColor(Color.RED);
                        canvas.drawRect(i * cellWidth, j * cellWidth,
                                (i + 1) * cellWidth, (j + 1) * cellWidth,
                                cellFillPaint);
                    }
                    else
                    {
                        cellFillPaint.setColor(Color.GREEN);
                        canvas.drawRect(i * cellWidth, j * cellWidth,
                                (i + 1) * cellWidth, (j + 1) * cellWidth,
                                cellFillPaint);
                    }
                }
            }

            for (int i = 0; i <= numColumns; i++) {
                cellFillPaint.setColor(Color.BLACK);
                canvas.drawLine(i * cellWidth, 0, i * cellWidth, numColumns*cellWidth, cellFillPaint);
            }

            for (int i = 0; i <= numRows; i++) {
                cellFillPaint.setColor(Color.BLACK);
                canvas.drawLine(0, i * cellWidth, width, i * cellWidth, cellFillPaint);
            }
            cellFillPaint.setColor(Color.CYAN);
            canvas.drawText("Good luck!",cellWidth*getNumColumns(),cellWidth*getNumRows(),cellFillPaint);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int column = (int)(event.getX() / cellWidth);
                int row = (int)(event.getY() / cellWidth);
                if(column<numColumns&&row<numRows)
                if(field[column][row]==empty)
                {
                    if (moveCounter % 2 == 0) {
                        field[column][row] = cross;
                        moveCounter++;
                    } else {
                        field[column][row] = zero;
                        moveCounter++;
                    }
                }
                invalidate();

            }

            return true;
        }
    }
    // from here AI logic
public int winChecker(int [][] field)
{
    int strike=0, i=0, j=0;
    int winnerIndicator=field[i][j];
    //check if sell is unchecked
  /*  if(field[i][j]==2)
    {
        strike=0;
        winnerIndicator=2;
    }else
    {
        winnerIndicator=field[i][j];//set current winner ID
     //run test to right side
        if (field[i + 1][j] == field[i][j])
        {
            return rightWinChecker(i + 1, j, field, strike + 1, winnerIndicator);
        }
     //run test to down side
        if(field[i][j+1]==field[i][j])
        {
            return downWinChecker(i,j+1,field,strike+1,winnerIndicator);
        }
      //run test on diagonal
        if(field[i+1][j+1]==field[i][j])
        {
            return diagonalWinChecker(i+1,j+1,field,strike+1,winnerIndicator);
        }
    }*/
    return 2;
}
/*
public int diagonalWinChecker(int i, int j, int [][] field, int strike, int winnerIndicator)
{
    //check if there is a strike
    if(strike==5)
        return winnerIndicator;
    return 2;
}
    public int rightWinChecker(int i, int j, int [][] field, int strike, int winnerIndicator)
    {
        //check if there is a strike
        if(strike==5)
            return winnerIndicator;
        return 2;
    }
    public int downWinChecker(int i, int j, int [][] field, int strike, int winnerIndicator)
    {
        //check if there is a strike
        if(strike==5)
            return winnerIndicator;
        return 2;
    }
    */
}
