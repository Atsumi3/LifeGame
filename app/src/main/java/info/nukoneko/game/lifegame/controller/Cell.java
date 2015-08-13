package info.nukoneko.game.lifegame.controller;

import android.graphics.Rect;

public class Cell {

    final CellController controller;

    final int x;
    final int y;
    public final Rect rect;
    private boolean liveState = true;
    public Cell(CellController controller, Rect rect, int x, int y, boolean defaultLive){
        this.controller = controller;
        this.rect = rect;
        this.x = x;
        this.y = y;
        this.liveState = defaultLive;
    }
    public boolean isLive(){
        return liveState;
    }

    public void liveCheck(){
        int liveCells = getLiveCellCount();
        if (liveState){
            if(2 > liveCells) {
                liveState = false;
                return;
            }
            if(liveCells > 3) {
                liveState = false;
                return;
            }
        }else {
            if(liveCells == 3) {
                liveState = true;
                return;
            }
        }
    }

    private int getLiveCellCount() {
        int liveCell = 0;
        // left bottom
        if (isLive(x - 1, y + 1)) liveCell++;
        // left
        if (isLive(x - 1, y)) liveCell++;
        // left top
        if (isLive(x - 1, y - 1)) liveCell++;
        // top
        if (isLive(x, y - 1)) liveCell++;
        // right top
        if (isLive(x + 1, y - 1)) liveCell++;
        // right
        if (isLive(x + 1, y)) liveCell++;
        // right bottom
        if (isLive(x + 1, y + 1)) liveCell++;
        // bottom
        if (isLive(x, y + 1)) liveCell++;
        return liveCell;
    }

    private boolean isLive(int _x, int _y){
        try{
            return controller.cells[_x][_y].isLive();
        }
        catch (Exception e){
            return false;
        }
    }
}
