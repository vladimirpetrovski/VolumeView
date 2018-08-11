package com.vladimirpetrovski.volumeview;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class VolumeView extends View {

  private static final String TAG = VolumeView.class.getSimpleName();

  private int numOfLines;
  private int volume;
  private int colorLines;

  public VolumeView(Context context) {
    super(context);
  }

  public VolumeView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setNumOfLines(@IntRange(from = 1) int numOfLines) {
    this.numOfLines = numOfLines;
    Log.v(TAG, "Setting lines: " + numOfLines);
    // TODO implement
  }

  public int getNumOfLines() {
    return numOfLines;
  }

  public void setVolume(@IntRange(from = 1, to = 100) int volume) {
    this.volume = volume;
    Log.v(TAG, "Setting volume: " + volume);
    // TODO implement
  }

  public int getVolume() {
    return volume;
  }

  public void setColorLines(@ColorInt int colorLines) {
    this.colorLines = colorLines;
    Log.v(TAG, "Setting lines color: " + Integer.toHexString(colorLines));
    // TODO implement
  }

  public int getColorLines() {
    return colorLines;
  }
}
