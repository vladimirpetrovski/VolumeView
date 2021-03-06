package com.vladimirpetrovski.volumeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class VolumeView extends View {

  private int numOfLines;
  private int volume;
  private int selectedColorLines;
  private int unSelectedColorLines;

  private Paint selectedLinePaint;
  private Paint unselectedLinePaint;

  private ArrayList<OnVolumeChangedListener> listeners;

  public VolumeView(Context context) {
    this(context, null);
  }

  public VolumeView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initAttributes(context, attrs, defStyleAttr);
    selectedLinePaint = new Paint();
    selectedLinePaint.setStyle(Style.FILL);
    unselectedLinePaint = new Paint();
    unselectedLinePaint.setStyle(Style.FILL);
  }

  private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VolumeView, defStyleAttr, 0);
    numOfLines = a.getInt(R.styleable.VolumeView_volumeView_numOfLines, 0);
    volume = a.getInt(R.styleable.VolumeView_volumeView_volume, 0);
    selectedColorLines = a.getColor(R.styleable.VolumeView_volumeView_selectedColorLines,
        getResources().getColor(android.R.color.black));
    unSelectedColorLines = a.getColor(R.styleable.VolumeView_volumeView_unselectedColorLines,
        getResources().getColor(android.R.color.darker_gray));
    a.recycle();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        refreshVolume(event);
        break;
      case MotionEvent.ACTION_MOVE:
        refreshVolume(event);
        break;

    }
    return true;
  }

  private void refreshVolume(MotionEvent event) {
    int vol = Math.round((100 - ((event.getY() / getHeight()) * 100)));
    if (vol > 0 && vol <= 100) {
      setVolume(vol);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    selectedLinePaint.setColor(selectedColorLines);
    unselectedLinePaint.setColor(unSelectedColorLines);

    int viewHeight = getHeight();
    int viewWidth = getWidth();
    int lineHeight = viewHeight / (2 * numOfLines);

    double volumeValue = (double) numOfLines / 100 * (100 - volume);

    for (int i = 0; i < numOfLines * 2; i = i + 2) {
      if (volumeValue > 0 && i / 2 < volumeValue) {
        drawRect(canvas, viewWidth, lineHeight, i, unselectedLinePaint);
      } else {
        drawRect(canvas, viewWidth, lineHeight, i, selectedLinePaint);
      }
    }
  }

  private void drawRect(Canvas canvas, int viewWidth, int lineHeight, int i, Paint paint) {
    canvas.drawRect(0,
        lineHeight * i,
        viewWidth,
        lineHeight * i + lineHeight, paint);
  }

  /**
   * Adds given event listener.
   *
   * @param listener a listener you want to add
   */
  public void addEventListener(OnVolumeChangedListener listener) {
    if (listeners == null) {
      listeners = new ArrayList<>();
    }
    listeners.add(listener);
  }

  /**
   * Removes given event listener.
   *
   * @param listener a listener you want to remove
   */
  public void removeEventListener(OnVolumeChangedListener listener) {
    if (listeners != null) {
      int i = listeners.indexOf(listener);
      if (i >= 0) {
        listeners.remove(i);
      }
    }
  }

  /**
   * Sets the number of lines for the volume view.
   *
   * @param numOfLines new number of lines
   */
  public void setNumOfLines(@IntRange(from = 1) int numOfLines) {
    this.numOfLines = numOfLines;
    invalidate();
  }

  /**
   * Get current number of lines.
   *
   * @return current number of lines
   */
  public int getNumOfLines() {
    return numOfLines;
  }

  /**
   * Sets current volume in percents. Must be between 1 and 100.
   *
   * @param volume percentage value
   */
  public void setVolume(@IntRange(from = 1, to = 100) int volume) {
    this.volume = volume;
    invalidate();
    for (OnVolumeChangedListener listener : listeners) {
      listener.onVolumeChanged(volume);
    }
  }

  /**
   * Get current volume in percents.
   *
   * @return percentage value
   */
  public int getVolume() {
    return volume;
  }

  /**
   * Sets current color for selected lines.
   *
   * @param selectedColorLines color int
   */
  public void setSelectedColorLines(@ColorInt int selectedColorLines) {
    this.selectedColorLines = selectedColorLines;
    invalidate();
  }

  /**
   * Gets current color for selected lines.
   *
   * @return color int
   */
  public int getSelectedColorLines() {
    return selectedColorLines;
  }

  /**
   * Sets current color for unselected lines.
   *
   * @param unSelectedColorLines color int
   */
  public void setUnselectedColorLines(@ColorInt int unSelectedColorLines) {
    this.unSelectedColorLines = unSelectedColorLines;
    invalidate();
  }

  /**
   * Gets current color for unselected lines.
   *
   * @return color int
   */
  public int getUnselectedColorLines() {
    return unSelectedColorLines;
  }
}
