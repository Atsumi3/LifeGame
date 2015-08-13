package info.nukoneko.game.lifegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.LinkedList;

import info.nukoneko.game.lifegame.controller.CellController;

public class GameManager {
    final MainSurface parent;
    private LinkedList<BaseController> mTaskList = new LinkedList<>();
    public GameManager(MainSurface surface){
        this.parent = surface;
        mTaskList.add(new CellController(parent));
    }
    public boolean onUpdate(){
        for(BaseController t : this.mTaskList){
            if(!t.onUpdate()) this.mTaskList.remove(t);
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent event){
        for(BaseController t : this.mTaskList){
            t.onTouchEvent(event);
        }
        return true;
    }

    public void onDraw(Canvas c){
        c.drawARGB(255, 0,0,0);
        for(BaseController t : this.mTaskList){
            t.onDraw(c);
        }
    }
}
