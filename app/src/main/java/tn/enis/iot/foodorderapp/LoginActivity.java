package tn.enis.iot.foodorderapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText userName, password;
    ToggleButton passTogg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(this);


        passTogg = (ToggleButton) findViewById(R.id.toggleButton);
        passTogg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:

                Resources res = getResources();
                String user = res.getString(R.string.user_name);
                String pass = res.getString(R.string.password);

                if ((userName.getText().toString().equals(user)) && (password.getText().toString().equals(pass))) {

                    Intent i = new Intent(LoginActivity.this, OrderActivity.class);
                    //1
                    /*Bundle bundle= new Bundle();
                    bundle.putString("user", userName.getText().toString());
            		i.putExtras(bundle);*/

                    //2
                    i.putExtra("user", userName.getText().toString());

                    startActivity(i);
                    finish();

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.toggleButton:
                if (!passTogg.isChecked()) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                break;

        }
    }
}
