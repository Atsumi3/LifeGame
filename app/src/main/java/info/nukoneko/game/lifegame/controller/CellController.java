package info.nukoneko.game.lifegame.controller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;

import info.nukoneko.game.lifegame.BaseController;
import info.nukoneko.game.lifegame.MainSurface;

public class CellController extends BaseController {
    final int tick = 60;
    final int margin = 0;
    public final int rowNums = 20;
    int cellSize = 0;
    int maxCellSize = 0;

    int relativeMargin = 0;

    public Cell[][] tempCells;

    public Cell[][] cells;

    private Paint _line = new Paint();

    public CellController(MainSurface holder) {
        super(holder);
        init();
    }

    public void init(){
        System.out.println("Cell Initialize!");

        tempCells = new Cell[rowNums][rowNums];
        cells = new Cell[rowNums][rowNums];
        Display disp = ((AppCompatActivity)holder.getContext()).getWindowManager().getDefaultDisplay();
        int size = (disp.getHeight() > disp.getWidth()) ? disp.getWidth() : disp.getHeight();
        cellSize = (size / rowNums) - margin;
        maxCellSize = size / rowNums;
        relativeMargin = (maxCellSize - cellSize) / 2;
        _line.setARGB(255, 127, 255, 127);
        _line.setAntiAlias(true);
        for (int i = 0 ; i < rowNums; i++){
            for (int j = 0 ; j < rowNums; j++) {
                int maxLeft = i + ( i * maxCellSize);
                int maxRight = maxLeft + maxCellSize;
                int maxTop = j + (j * maxCellSize);
                int maxBottom = maxTop + maxCellSize;
                cells[i][j] = new Cell(this, new Rect(
                        maxLeft + relativeMargin,
                        maxTop + relativeMargin,
                        maxRight - relativeMargin,
                        maxBottom - relativeMargin),
                        i,
                        j);
            }
        }

        new FirstPattern().pentadecathlon();
    }

    @Override
    public void onDraw(Canvas c) {
        for (int i = 0; i < rowNums; i++){
            for (int j = 0 ; j < rowNums; j++){
                Cell cell = cells[i][j];
                if( cell.isLive()){
                    c.drawRect(cell.rect, _line);
                }
            }
        }
    }

    int count = 0;
    @Override
    public boolean onUpdate() {
        /*
        if (count % tick == 0) {
            tempCells = cells;
            for (int i = 0; i < rowNums; i++) {
                for (int j = 0; j < rowNums; j++) {
                    tempCells[i][j].liveCheck();
                }
            }
            cells = tempCells;
        }
        if(count == 60) count = 0;
        count++;
        */
        return super.onUpdate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (count % 60 == 0) {
            tempCells = cells;
            for (int i = 0; i < rowNums; i++) {
                for (int j = 0; j < rowNums; j++) {
                    tempCells[i][j].liveCheck();
                }
            }
            cells = tempCells;
            count = 0;
        }
        count++;
        return super.onTouchEvent(event);
    }

    public class FirstPattern{
        /**
         * 8
         * ■■■□□□
         * ■■■□□□
         * ■■■□□□
         * □□□■■■
         * □□□■■■
         * □□□■■■
         */
        public void Eight(){
            if(12 > rowNums){
                throw new IllegalArgumentException("rowNums is need upper 12");
            }

            int os = (rowNums / 4);

            int offsetX = os;
            int offsetY = os;

            cells[offsetX + 0][offsetY + 0].setLive(true);
            cells[offsetX + 1][offsetY + 0].setLive(true);
            cells[offsetX + 2][offsetY + 0].setLive(true);

            cells[offsetX + 0][offsetY + 1].setLive(true);
            cells[offsetX + 1][offsetY + 1].setLive(true);
            cells[offsetX + 2][offsetY + 1].setLive(true);

            cells[offsetX + 0][offsetY + 2].setLive(true);
            cells[offsetX + 1][offsetY + 2].setLive(true);
            cells[offsetX + 2][offsetY + 2].setLive(true);


            cells[offsetX + 3][offsetY + 3].setLive(true);
            cells[offsetX + 4][offsetY + 3].setLive(true);
            cells[offsetX + 5][offsetY + 3].setLive(true);

            cells[offsetX + 3][offsetY + 4].setLive(true);
            cells[offsetX + 4][offsetY + 4].setLive(true);
            cells[offsetX + 5][offsetY + 4].setLive(true);

            cells[offsetX + 3][offsetY + 5].setLive(true);
            cells[offsetX + 4][offsetY + 5].setLive(true);
            cells[offsetX + 5][offsetY + 5].setLive(true);
        }

        public void pentadecathlon(){
            int os = (rowNums / 4);

            cells[os + 2][os + 0].setLive(true);
            cells[os + 7][os + 0].setLive(true);

            cells[os + 0][os + 1].setLive(true);
            cells[os + 1][os + 1].setLive(true);

            cells[os + 3][os + 1].setLive(true);
            cells[os + 4][os + 1].setLive(true);
            cells[os + 5][os + 1].setLive(true);
            cells[os + 6][os + 1].setLive(true);


            cells[os + 8][os + 1].setLive(true);
            cells[os + 9][os + 1].setLive(true);

            cells[os + 2][os + 2].setLive(true);
            cells[os + 7][os + 2].setLive(true);
        }
    }
}
