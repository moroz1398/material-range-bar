/*
 * Copyright 2013, Edmodo, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
 * You may obtain a copy of the License in the LICENSE file, or at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.appyvet.materialrangebar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the underlying gray bar in the RangeBar (without the
 * thumbs).
 */
public class Bar {

    // Member Variables ////////////////////////////////////////////////////////

    private final Resources mRes;

    private final Paint mBarPaint;

    private final Paint mTickPaint;
    private Paint mLabelPaint;

    // Left-coordinate of the horizontal bar.
    private final float mLeftX;

    private final float mRightX;

    private final float mY;

    private int mNumSegments;

    private float mTickDistance;

    private final float mTickHeight;

    private int mTickLabelColor;

    private int mTickLabelSelectedColor;

    private CharSequence[] mTickTopLabels;

    private CharSequence[] mTickBottomLabels;

    private String mTickDefaultLabel;

    private float mTickLabelSize;

    private List<Integer> mTickColors = new ArrayList<>();

    // Constructor /////////////////////////////////////////////////////////////

    /**
     * Bar constructor
     *
     * @param ctx          the context
     * @param x            the start x co-ordinate
     * @param y            the y co-ordinate
     * @param length       the length of the bar in px
     * @param tickCount    the number of ticks on the bar
     * @param tickHeight   the height of each tick
     * @param barWeight    the weight of the bar
     * @param barColor     the color of the bar
     * @param isBarRounded if the bar has rounded edges or not
     */
    public Bar(Context ctx,
               float x,
               float y,
               float length,
               int tickCount,
               float tickHeight,
               float barWeight,
               int barColor,
               boolean isBarRounded) {
        mRes = ctx.getResources();

        mLeftX = x;
        mRightX = x + length;
        mY = y;

        mNumSegments = tickCount - 1;
        mTickDistance = length / mNumSegments;
        mTickHeight = tickHeight;
        // Initialize the paint.
        mBarPaint = new Paint();
        mBarPaint.setColor(barColor);
        mBarPaint.setStrokeWidth(barWeight);
        mBarPaint.setAntiAlias(true);
        if (isBarRounded) {
            mBarPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        mTickPaint = new Paint();
        mTickPaint.setStrokeWidth(barWeight);
        mTickPaint.setAntiAlias(true);
    }

    /**
     * Bar constructor
     *
     * @param ctx          the context
     * @param x            the start x co-ordinate
     * @param y            the y co-ordinate
     * @param length       the length of the bar in px
     * @param tickCount    the number of ticks on the bar
     * @param tickHeight   the height of each tick
     * @param tickColor    the color of each tick
     * @param barWeight    the weight of the bar
     * @param barColor     the color of the bar
     * @param isBarRounded if the bar has rounded edges or not
     */
    public Bar(Context ctx,
               float x,
               float y,
               float length,
               int tickCount,
               float tickHeight,
               int tickColor,
               float barWeight,
               int barColor,
               boolean isBarRounded) {
        this(ctx, x, y, length, tickCount, tickHeight, barWeight, barColor, isBarRounded);

        mTickPaint.setColor(tickColor);
    }

    /**
     * Bar constructor
     *
     * @param ctx              the context
     * @param x                the start x co-ordinate
     * @param y                the y co-ordinate
     * @param length           the length of the bar in px
     * @param tickCount        the number of ticks on the bar
     * @param tickHeight       the height of each tick
     * @param barWeight        the weight of the bar
     * @param barColor         the color of the bar
     * @param isBarRounded     if the bar has rounded edges or not
     * @param tickLabelColor   the color of each tick's label
     * @param tickTopLabels    the top label of each tick
     * @param tickBottomLabels the top label of each tick
     */
    public Bar(Context ctx,
               float x,
               float y,
               float length,
               int tickCount,
               float tickHeight,
               float barWeight,
               int barColor,
               boolean isBarRounded,
               int tickLabelColor,
               int tickLabelSelectedColor,
               CharSequence[] tickTopLabels,
               CharSequence[] tickBottomLabels,
               String tickDefaultLabel,
               float tickLabelSize) {
        this(ctx, x, y, length, tickCount, tickHeight, barWeight, barColor, isBarRounded);

        if (tickTopLabels != null || tickBottomLabels != null) {
            // Creates the paint and sets the Paint values
            mLabelPaint = new Paint();
            mLabelPaint.setColor(tickLabelColor);
            mLabelPaint.setAntiAlias(true);
            mTickLabelColor = tickLabelColor;
            mTickLabelSelectedColor = tickLabelSelectedColor;
            mTickTopLabels = tickTopLabels;
            mTickBottomLabels = tickBottomLabels;
            mTickDefaultLabel = tickDefaultLabel;
            mTickLabelSize = tickLabelSize;
        }
    }

    /**
     * Bar constructor
     *
     * @param ctx              the context
     * @param x                the start x co-ordinate
     * @param y                the y co-ordinate
     * @param length           the length of the bar in px
     * @param tickCount        the number of ticks on the bar
     * @param tickHeight       the height of each tick
     * @param tickColor        the color of each tick
     * @param barWeight        the weight of the bar
     * @param barColor         the color of the bar
     * @param isBarRounded     if the bar has rounded edges or not
     * @param tickLabelColor   the color of each tick's label
     * @param tickTopLabels    the top label of each tick
     * @param tickBottomLabels the top label of each tick
     */
    public Bar(Context ctx,
               float x,
               float y,
               float length,
               int tickCount,
               float tickHeight,
               int tickColor,
               float barWeight,
               int barColor,
               boolean isBarRounded,
               int tickLabelColor,
               int tickLabelSelectedColor,
               CharSequence[] tickTopLabels,
               CharSequence[] tickBottomLabels,
               String tickDefaultLabel,
               float tickLabelSize) {
        this(ctx, x, y, length, tickCount, tickHeight, barWeight, barColor, isBarRounded, tickLabelColor, tickLabelSelectedColor, tickTopLabels, tickBottomLabels, tickDefaultLabel, tickLabelSize);
        mTickPaint.setColor(tickColor);
    }

    /**
     * Bar constructor
     *
     * @param ctx              the context
     * @param x                the start x co-ordinate
     * @param y                the y co-ordinate
     * @param length           the length of the bar in px
     * @param tickCount        the number of ticks on the bar
     * @param tickHeight       the height of each tick
     * @param tickColors       the colors of each tick
     * @param barWeight        the weight of the bar
     * @param barColor         the color of the bar
     * @param isBarRounded     if the bar has rounded edges or not
     * @param tickLabelColor   the color of each tick's label
     * @param tickTopLabels    the top label of each tick
     * @param tickBottomLabels the top label of each tick
     */
    public Bar(Context ctx,
               float x,
               float y,
               float length,
               int tickCount,
               float tickHeight,
               List<Integer> tickColors,
               float barWeight,
               int barColor,
               boolean isBarRounded,
               int tickLabelColor,
               int tickLabelSelectedColor,
               CharSequence[] tickTopLabels,
               CharSequence[] tickBottomLabels,
               String tickDefaultLabel,
               float tickLabelSize) {

        this(ctx, x, y, length, tickCount, tickHeight, barWeight, barColor, isBarRounded, tickLabelColor, tickLabelSelectedColor, tickTopLabels, tickBottomLabels, tickDefaultLabel, tickLabelSize);

        if (null != tickColors) {
            mTickColors = tickColors;
        }


    }
    // Package-Private Methods /////////////////////////////////////////////////

    /**
     * Draws the bar on the given Canvas.
     *
     * @param canvas Canvas to draw on; should be the Canvas passed into {#link
     *               View#onDraw()}
     */
    public void draw(Canvas canvas) {
        canvas.drawLine(mLeftX, mY, mRightX, mY, mBarPaint);
    }

    /**
     * Get the x-coordinate of the left edge of the bar.
     *
     * @return x-coordinate of the left edge of the bar
     */
    public float getLeftX() {
        return mLeftX;
    }

    /**
     * Get the x-coordinate of the right edge of the bar.
     *
     * @return x-coordinate of the right edge of the bar
     */
    public float getRightX() {
        return mRightX;
    }

    /**
     * Gets the x-coordinate of the nearest tick to the given x-coordinate.
     *
     * @param thumb the thumb to find the nearest tick for
     * @return the x-coordinate of the nearest tick
     */
    public float getNearestTickCoordinate(PinView thumb) {

        final int nearestTickIndex = getNearestTickIndex(thumb);

        return mLeftX + (nearestTickIndex * mTickDistance);
    }

    /**
     * Gets the zero-based index of the nearest tick to the given thumb.
     *
     * @param thumb the Thumb to find the nearest tick for
     * @return the zero-based index of the nearest tick
     */
    public int getNearestTickIndex(PinView thumb) {

        int tickIndex = (int) ((thumb.getX() - mLeftX + mTickDistance / 2f) / mTickDistance);

        if (tickIndex > mNumSegments) {
            tickIndex = mNumSegments;
        } else if (tickIndex < 0) {
            tickIndex = 0;
        }
        return tickIndex;
    }


    /**
     * Set the number of ticks that will appear in the RangeBar.
     *
     * @param tickCount the number of ticks
     */
    public void setTickCount(int tickCount) {

        final float barLength = mRightX - mLeftX;

        mNumSegments = tickCount - 1;
        mTickDistance = barLength / mNumSegments;
    }

    private String getTickLabel(int index, CharSequence[] labels) {
        if (index >= labels.length) {
            return mTickDefaultLabel;
        }

        return labels[index].toString();
    }

    private String getTickTopLabel(int index) {
        return getTickLabel(index, mTickTopLabels);
    }

    private String getTickBottomLabel(int index) {
        return getTickLabel(index, mTickBottomLabels);
    }

    // Private Methods /////////////////////////////////////////////////////////

    /**
     * Draws the tick marks on the bar.
     *
     * @param canvas Canvas to draw on; should be the Canvas passed into {#link
     *               View#onDraw()}
     */
    public void drawTicks(Canvas canvas, float pinRadius, PinView rightThumb) {
        drawTicks(canvas, pinRadius, rightThumb, null);
    }

    public void drawTicks(Canvas canvas, float pinRadius, PinView rightThumb, @Nullable PinView leftThumb) {
        boolean paintLabel = false;
        if (mLabelPaint != null) {
            paintLabel = true;
            final int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTickLabelSize,
                    mRes.getDisplayMetrics());
            mLabelPaint.setTextSize(textSize);
        }

        // Loop through and draw each tick (except final tick).
        for (int i = 0; i < mNumSegments; i++) {
            final float x = i * mTickDistance + mLeftX;
            canvas.drawCircle(x, mY, mTickHeight, getTick(i));

            if (paintLabel) {
                if (mTickTopLabels != null)
                    drawTickLabel(canvas, getTickTopLabel(i), x, pinRadius, i == 0, false, true, rightThumb, leftThumb);

                if (mTickBottomLabels != null)
                    drawTickLabel(canvas, getTickBottomLabel(i), x, pinRadius, i == 0, false, false, rightThumb, leftThumb);
            }
        }
        // Draw final tick. We draw the final tick outside the loop to avoid any
        // rounding discrepancies.
        canvas.drawCircle(mRightX, mY, mTickHeight, getTick(mNumSegments-1));

        // Draw final tick's label outside the loop
        if (paintLabel) {
            if (mTickTopLabels != null)
                drawTickLabel(canvas, getTickTopLabel(mNumSegments), mRightX, pinRadius, false, true, true, rightThumb, leftThumb);

            if (mTickBottomLabels != null)
                drawTickLabel(canvas, getTickBottomLabel(mNumSegments), mRightX, pinRadius, false, true, false, rightThumb, leftThumb);
        }
    }

    private void drawTickLabel(Canvas canvas, final String label, float x, float pinRadius,
                               boolean first, boolean last, boolean isTop, PinView rightThumb, @Nullable PinView leftThumb) {

        Rect labelBounds = new Rect();
        mLabelPaint.getTextBounds(label, 0, label.length(), labelBounds);
        float xPos = x - labelBounds.width()/2;

        if (first) {
            xPos += mTickHeight;
        } else if (last) {
            xPos -= mTickHeight;
        }

        boolean isSelected = rightThumb.isInTargetZone(x, mY);

        if (!isSelected && leftThumb != null) {
            isSelected = leftThumb.isInTargetZone(x, mY);
        }

        if (isSelected) {
            mLabelPaint.setColor(mTickLabelSelectedColor);
        } else {
            mLabelPaint.setColor(mTickLabelColor);
        }

        float yPos;
        if (isTop) {
            yPos = mY - labelBounds.height() - pinRadius;
        } else {
            yPos = mY + labelBounds.height() + pinRadius;
        }

        canvas.drawText(label, xPos, yPos, mLabelPaint);
    }

    private Paint getTick(int index) {

        if (index < mTickColors.size()) {
            mTickPaint.setColor(mTickColors.get(index));
        }

        return mTickPaint;
    }
}
