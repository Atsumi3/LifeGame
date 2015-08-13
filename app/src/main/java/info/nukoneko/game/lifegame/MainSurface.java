package info.nukoneko.game.lifegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainSurface
        extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {

    GameManager mGameManager = new GameManager(this);
    Thread mThread;

    public MainSurface(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.mThread = new Thread(this);
        this.mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread = null;
    }

    @Override
    public void run() {
        while (mThread != null){

            this.mGameManager.onUpdate();

            this.draw(getHolder());
        }
    }
    private void draw(SurfaceHolder holder){
        Canvas c = holder.lockCanvas();
        if ( c == null){
            return;
        }
        this.mGameManager.onDraw(c);
        holder.unlockCanvasAndPost(c);
    }
}
