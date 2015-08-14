package info.nukoneko.game.lifegame.controller;

import android.graphics.Rect;

public class Cell {

    final CellController controller;

    final int x;
    final int y;
    public final Rect rect;
    private boolean liveState = true;
    public Cell(CellController controller, Rect rect, int x, int y){
        this(controller, rect, x, y, false);
    }
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
            if(liveCells == 2 || liveCells == 3) {
                liveState = true;
                return;
            }
        }else {
            if(liveCells == 3) {
                liveState = true;
                return;
            }else {
                liveState = false;
                return;
            }
        }
        liveState = false;
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
        int targetX = 0;
        int targetY = 0;
        try{
            final int rowMax = controller.rowNums -1;
            if (_x == -1){
                targetX = rowMax;
            }else if (_x == controller.rowNums){
                targetX = 0;
            }else {
                targetX = _x;
            }
            if (_y == -1){
                targetY = rowMax;
            }else if (_y == controller.rowNums){
                targetY = 0;
            }else {
                targetY = _y;
            }
            return controller.cells[targetX][targetY].isLive();
        }
        catch (Exception e){
            System.out.println("おかしい " + e.getLocalizedMessage());
            System.out.println(String.format("%d %d", targetX, targetY));
            return false;
        }
    }

    private void Log(int cells, String comment){
        if(!comment.equals("bug")) return;
        System.out.println(String.format("[LIVE-CHECK] x:%d y:%d cnt:%d live:%b %s",x, y, cells, liveState, comment));
    }
}
