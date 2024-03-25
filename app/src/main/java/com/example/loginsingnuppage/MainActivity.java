package com.example.loginsingnuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText userName, password, cnfPassword;
    String selectedDate;
    Button result, loginPage;
    ArrayList<String> storeUsers = new ArrayList<>();
    ArrayList<String> storePasswords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.getuserName);
        password = findViewById((R.id.getpass));
        cnfPassword = findViewById(R.id.getCnfPass);
        result = findViewById(R.id.submit);
        loginPage =findViewById(R.id.nextPage);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String cnfPass = cnfPassword.getText().toString();
                if(!(isThere(user))) {
                    if(pass.equals(cnfPass)) {
                        storeUsers.add(user);
                        storePasswords.add(pass);
                        // textView banakr show krdete ha
                        Toast.makeText(MainActivity.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Passwords Mismatch!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_LONG).show();
                }
                // Printing elements to logcat
                for (String element : storeUsers) {
                    Log.d("ArrayList", element);
                }
            }
        });

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginPage.class);
                intent.putStringArrayListExtra("USER", storeUsers);
                intent.putStringArrayListExtra("PASS", storePasswords);
                startActivity(intent);
            }
        });

    }
    private boolean isThere(String user) {
        for(String val : storeUsers) {
            if(user.equals(val))
                return true;
        }
        return false;
    }

    public void selectTime(View v) {
        Calendar c = Calendar.getInstance();
        int pMonth = c.get(Calendar.MONTH);
        int pYear = c.get(Calendar.YEAR);
        int pDate = c.get(Calendar.DATE);
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_DialogWhenLarge,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    }
                }, pMonth, pYear, pDate);
        dialog.show();
    }
}