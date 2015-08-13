package info.nukoneko.game.lifegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class BaseController {
    final public MainSurface holder;

    public BaseController(MainSurface holder) {
        this.holder = holder;
    }

    public boolean onUpdate() {
        return true;
    }

    abstract public void onDraw(Canvas c);

    public boolean onTouchEvent(MotionEvent event){
        return true;
    }
}
