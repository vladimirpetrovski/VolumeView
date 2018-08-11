package com.vladimirpetrovski.volumeview.sample;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.vladimirpetrovski.volumeview.VolumeView;

public class SampleActivity extends AppCompatActivity {

  private EditText percentageEdit;
  private EditText linesEdit;
  private VolumeView volumeView;
  private View colorView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
    percentageEdit = findViewById(R.id.volume_edit_percentage);
    linesEdit = findViewById(R.id.volume_edit_lines);
    volumeView = findViewById(R.id.volume_view);
    colorView = findViewById(R.id.volume_view_color);
  }

  public void setVolume(View view) {
    if (validatePercentageValue()) {
      return;
    }
    int percentage = Integer.parseInt(percentageEdit.getText().toString());
    volumeView.setVolume(percentage);
  }

  public void setLines(View view) {
    if (validateLinesValue()) {
      return;
    }
    int lines = Integer.parseInt(linesEdit.getText().toString());
    volumeView.setNumOfLines(lines);
  }

  public void setColor(View view) {
    volumeView.setColorLines(((ColorDrawable) colorView.getBackground()).getColor());
  }

  public void openColorPicker(View view) {
    ColorPickerDialog colorPickerDialog = ColorPickerDialog.newBuilder()
        .setColor(((ColorDrawable) colorView.getBackground()).getColor()).create();
    colorPickerDialog.setColorPickerDialogListener(new ColorPickerDialogListener() {
      @Override
      public void onColorSelected(int dialogId, int color) {
        colorView.setBackgroundColor(color);
      }

      @Override
      public void onDialogDismissed(int dialogId) {
      }
    });
    colorPickerDialog.show(getFragmentManager(), "color_picker");
  }

  private boolean validatePercentageValue() {
    if (TextUtils.isEmpty(percentageEdit.getText().toString()) ||
        !TextUtils.isEmpty(percentageEdit.getText().toString())
            && (Integer.parseInt(percentageEdit.getText().toString()) < 1
            || Integer.parseInt(percentageEdit.getText().toString()) > 100)) {
      showError(R.string.error_percentage);
      return true;
    }
    return false;
  }

  private boolean validateLinesValue() {
    if (TextUtils.isEmpty(linesEdit.getText().toString())
        || Integer.parseInt(linesEdit.getText().toString()) < 1) {
      showError(R.string.error_lines);
      return true;
    }
    return false;
  }

  private void showError(@StringRes int errorMessage) {
    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
    toast.setGravity(Gravity.TOP, 0, 0);
    toast.show();
  }
}
