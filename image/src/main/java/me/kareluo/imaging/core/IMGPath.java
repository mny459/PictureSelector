package me.kareluo.imaging.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by felix on 2017/11/22 下午6:13.
 */

public class IMGPath {

    protected Path path;

    private int color = Color.RED;

    private float width = BASE_MOSAIC_WIDTH;

    private IMGMode mode = IMGMode.DOODLE;
    private IMGMosaicMode mosaicMode = IMGMosaicMode.GRID;

    public static final float BASE_DOODLE_WIDTH = 20f;

    public static final float BASE_MOSAIC_WIDTH = 72f;

    public IMGPath() {
        this(new Path());
    }

    public IMGPath(Path path) {
        this(path, IMGMode.DOODLE);
    }

    public IMGPath(Path path, IMGMode mode) {
        this(path, mode, Color.RED);
    }

    public IMGPath(Path path, IMGMode mode, int color) {
        this(path, mode, color, BASE_MOSAIC_WIDTH);
    }

    public IMGPath(Path path, IMGMode mode, int color, float width) {
        this(path, mode, color, BASE_MOSAIC_WIDTH, IMGMosaicMode.GRID);
    }

    public IMGPath(Path path, IMGMode mode, int color, float width, IMGMosaicMode mosaicMode) {
        this.path = path;
        this.mode = mode;
        this.color = color;
        this.width = width;
        this.mosaicMode = mosaicMode;
        if (mode == IMGMode.MOSAIC) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public IMGMode getMode() {
        return mode;
    }

    public void setMode(IMGMode mode) {
        this.mode = mode;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public IMGMosaicMode getMosaicMode() {
        return mosaicMode;
    }

    public void setMosaicMode(IMGMosaicMode mosaicMode) {
        this.mosaicMode = mosaicMode;
    }

    public void onDrawDoodle(Canvas canvas, Paint paint) {
        if (mode == IMGMode.DOODLE) {
            paint.setColor(color);
            // rewind
            canvas.drawPath(path, paint);
        }
    }

    public void onDrawMosaic(Canvas canvas, Paint paint) {
        if (mode == IMGMode.MOSAIC) {
            paint.setStrokeWidth(width);
            canvas.drawPath(path, paint);
        }
    }

    public void transform(Matrix matrix) {
        path.transform(matrix);
    }

    @Override
    public String toString() {
        return "IMGPath{" +
                "path=" + path +
                ", color=" + color +
                ", width=" + width +
                ", mode=" + mode +
                ", mosaicMode=" + mosaicMode +
                '}';
    }
}
