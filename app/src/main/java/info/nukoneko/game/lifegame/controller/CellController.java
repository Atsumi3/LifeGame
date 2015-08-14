package info.nukoneko.game.lifegame.controller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import java.util.Random;

import info.nukoneko.game.lifegame.BaseController;
import info.nukoneko.game.lifegame.MainSurface;

public class CellController extends BaseController {
    final int tick = 2;
    final int margin = 0;
    public final int rowNums = 100;
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
        Random random = new Random();
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
                        j,
                        random.nextInt(100) > 95);
            }
        }
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
        return super.onUpdate();
    }
}
