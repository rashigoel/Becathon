package com.street35.beacon.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.street35.beacon.Information.Information;
import com.street35.beacon.R;

public class Login extends AppCompatActivity {

    private EditText name;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.editText_name);
        submit = (Button) findViewById(R.id.button_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Enter your name here");
                }
                else{
                    Intent i =new Intent(Login.this, Information.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("Login" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",name.getText().toString());
                    editor.apply();
                    startActivity(i);
                }

            }
        });





    }
}
