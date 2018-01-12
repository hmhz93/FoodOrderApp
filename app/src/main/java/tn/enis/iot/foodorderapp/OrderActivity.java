package tn.enis.iot.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    TextView orderTotal, order, order2, userTV;
    Button add, logout;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderTotal = (TextView) findViewById(R.id.textView_ordertotal);
        order = (TextView) findViewById(R.id.tv_order2);
        order2 = (TextView) findViewById(R.id.tv_order3);

        add = (Button) findViewById(R.id.button_add_new_item);
        add.setOnClickListener(this);

        logout = (Button) findViewById(R.id.button_logout);
        logout.setOnClickListener(this);

        userTV = (TextView) findViewById(R.id.textView1);
        //1
       /* Bundle bundle= getIntent().getExtras();
        String user= bundle.getString("user");*/

        //2
        String user = (String) getIntent().getStringExtra("user");


        userTV.setText("Hello " + user);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_add_new_item:
                Intent i = new Intent(this, OverViewActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.button_logout:
                Intent i2 = new Intent(this, LoginActivity.class);
                startActivity(i2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();
            int itemQty = bundle.getInt("qty");
            String itemSize = bundle.getString("size");
            double itemTotal = bundle.getDouble("total");

            order.setText(order.getText() + "\n" + itemQty + " " + itemSize);
            order2.setText(order2.getText() + "\n" + itemTotal + "$");

            total += itemTotal;
            orderTotal.setText(total + "$");
        }
    }
}
