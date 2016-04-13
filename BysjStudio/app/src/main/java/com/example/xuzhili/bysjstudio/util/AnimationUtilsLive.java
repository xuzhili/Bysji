package com.example.xuzhili.bysjstudio.util;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by xuzhili on 16/3/17.
 */
public class AnimationUtilsLive {

    public static class TranslationClass extends Animation {

        private Point fromPoint, toPoint;
        private int duration;
        private PathMeasure pathMeasure;
        private boolean isInQuartInterapter;

        public TranslationClass(Point fromPoint, Point toPoint, int duration) {
            this.fromPoint = fromPoint;
            this.toPoint = toPoint;
            this.duration = duration;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(duration);
            setFillAfter(true);
            Path path = new Path();
            path.moveTo(fromPoint.x, fromPoint.y);
            path.lineTo(toPoint.x, toPoint.y);
            pathMeasure = new PathMeasure(path, false);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            Matrix matrix = t.getMatrix();
            pathMeasure.getMatrix(pathMeasure.getLength() * interpolatedTime, matrix, PathMeasure.POSITION_MATRIX_FLAG);
        }

    }

}
