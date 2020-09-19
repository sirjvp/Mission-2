package com.uc.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.uts.model.DataUser;
import com.uc.uts.model.User;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity implements TextWatcher {
    Toolbar toolbar;
    TextInputLayout input_fname, input_age, input_address;
    TextInputEditText text_name, text_age, text_address;
    Button button_save;
    String fname, age, address;
    int position;
    User user;
    ArrayList<User> AdapterList = DataUser.userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        input_fname = findViewById(R.id.input_fnama);
        input_age = findViewById(R.id.input_age);
        input_address = findViewById(R.id.input_address);
        button_save = findViewById(R.id.button_add);
        text_name = findViewById(R.id.iname);
        text_age = findViewById(R.id.iage);
        text_address = findViewById(R.id.iaddress);

        getIncomingIntent();

        input_fname.getEditText().addTextChangedListener(this);
        input_age.getEditText().addTextChangedListener(this);
        input_address.getEditText().addTextChangedListener(this);


        toolbar = findViewById(R.id.toolbar2);
        if (user == null) {
            toolbar.setTitle("Add User");
            button_save.setText("Save Data");
        }
        else{
            toolbar.setTitle("Edit User");
            button_save.setText("Update Data");
            text_name.setText(user.getName());
            text_age.setText(user.getAge());
            text_address.setText(user.getAddress());
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(AddUserActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.screen_loading);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                User orang = new User(fname, age, address);

                if (user == null) {
                    DataUser.userdata.add(new User(fname, age, address));
                }
                else {
                    DataUser.userdata.get(position).setName(fname);
                    DataUser.userdata.get(position).setAge(age);
                    DataUser.userdata.get(position).setAddress(address);
                }
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                intent.putExtra("dataUser", orang);
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), UserDetail.class);
                    intent.putExtra("data", AdapterList.get(position));
                    intent.putExtra("position", position);
                    startActivity(new Intent(intent));
                }
            }
        });
    }

    private void getIncomingIntent() {
        final Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        user = intent.getParcelableExtra("ArrayList");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        fname = input_fname.getEditText().getText().toString().trim();
        age = input_age.getEditText().getText().toString().trim();
        address = input_address.getEditText().getText().toString().trim();

        if (!fname.isEmpty() && !age.isEmpty() && !address.isEmpty()) {
            button_save.setEnabled(true);
        }
        else{
            button_save.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
