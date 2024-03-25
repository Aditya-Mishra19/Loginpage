package com.example.loginsingnuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class loginPage extends AppCompatActivity {
    EditText nameInput, passInput;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        nameInput = findViewById(R.id.getuserName);
        passInput = findViewById(R.id.getpass);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameInput.getText().toString();
                String password = passInput.getText().toString();
                Intent intent = getIntent();
                ArrayList<String> storeUsers = intent.getStringArrayListExtra("USER");
                ArrayList<String> storePass = intent.getStringArrayListExtra("PASS");
//                if(checkUser(userName) && checkPass(password)) {
//                    Toast.makeText(loginPage.this, "Successfully Logged IN!", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(loginPage.this, "Check Credentials!", Toast.LENGTH_LONG).show();
//                }
                // Printing elements to logcat
                for (String element : storeUsers) {
                    Log.d("ArrayList", element);
                }
                int flag1 = 0;
                int flag2 = 0;
                for(String val : storeUsers) {
                    if(val.equals(userName)){
                       flag1 = 1;
                       break;
                    }
                }
                for(String val : storePass) {
                    if(val.equals(password)){
                        flag2 = 1;
                        break;
                    }
                }
                if(flag1 == flag2) {
                    Toast.makeText(loginPage.this, "Successfully Logged IN!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(loginPage.this, "Not Registered", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean checkUser(String userName) {
        Intent intent = getIntent();
        ArrayList<String> storeUsers = intent.getStringArrayListExtra("USER");
        for(String vals : storeUsers) {
            if(vals.equals(userName))
                return true;
        }
        return false;
    }
    private boolean checkPass(String password) {
        Intent intent = getIntent();
        ArrayList<String> storePass = intent.getStringArrayListExtra("PASS");
        for(String vals : storePass) {
            if(vals.equals(password))
                return true;
        }
        return false;
    }
}