package tn.enis.iot.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OverViewActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, NumberPicker.OnValueChangeListener {

    RadioGroup size;
    TextView itemTotal;
    double total = 0;
    Button add, cancel;
    NumberPicker qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        size = (RadioGroup) findViewById(R.id.radioGroup_size);
        itemTotal = (TextView) findViewById(R.id.textView_itemtotat);
        add = (Button) findViewById(R.id.button_add);
        cancel = (Button) findViewById(R.id.button_cancel);
        qty = (NumberPicker) findViewById(R.id.numberPicker);
        qty.setMinValue(1);
        qty.setMaxValue(10);
        qty.setOnValueChangedListener(this);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
        size.setOnCheckedChangeListener(this);
    }

    public void computeSum(int checkedId, int newVal) {
        switch (checkedId) {
            case R.id.radio_small:
                total = 10.5 * newVal;
                break;
            case R.id.radio_medium:
                total = 13.5 * newVal;
                break;
            case R.id.radio_large:
                total = 16.5 * newVal;
                break;
        }
        itemTotal.setText(total + "$");
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(OverViewActivity.this, OrderActivity.class);

        switch (v.getId()) {
            case R.id.button_add:

                Bundle bundle = new Bundle();
                bundle.putString("size", ((RadioButton) findViewById(size.getCheckedRadioButtonId())).getText().toString());
                bundle.putInt("qty", qty.getValue());
                bundle.putDouble("total", total);
                i.putExtras(bundle);
                setResult(RESULT_OK, i);
                finish();
                break;
            case R.id.button_cancel:
                setResult(RESULT_CANCELED, i);
                finish();
                break;

        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        computeSum(size.getCheckedRadioButtonId(), newVal);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        computeSum(checkedId, qty.getValue());
    }
}
