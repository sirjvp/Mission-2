package com.uc.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uc.uts.model.DataUser;
import com.uc.uts.model.User;
import com.uc.uts.ExampleDialog;
import com.uc.uts.utils.ItemClickSupport;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserDetail extends AppCompatActivity {
    ImageButton button_edit, button_delete;
    TextView named, aged, addressed;
    String namee;
    String agee;
    String addresss;
    int position;
    ArrayList<User> AdapterList = DataUser.userdata;
    User user;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        button_edit = findViewById(R.id.editbut);
        button_delete = findViewById(R.id.deletebut);

        getIncomingIntent();

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetail.this, AddUserActivity.class);
                intent.putExtra("ArrayList", AdapterList.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
                finish();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        toolbar = findViewById(R.id.toolbar3);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog(position);
        exampleDialog.show(getSupportFragmentManager(), "warning");
    }

//    private void getIncomingIntent(){
//        if(getIntent().hasExtra("name") && getIntent().hasExtra("age") && getIntent().hasExtra("address")){
//            namee = getIntent().getStringExtra("name");
//            agee = getIntent().getStringExtra("age");
//            addresss = getIntent().getStringExtra("address");
//            position = getIntent().getIntExtra("position", 0);
//            setDetail(namee, agee, position);
//        }
//    }
//
//    private void setDetail(String namee, String agee, int position){
//        named = findViewById(R.id.dname);
//        aged = findViewById(R.id.dage);
//        addressed = findViewById(R.id.daddress);
//
//        named.setText(namee);
//        aged.setText(agee);
////        addressed.setText(position);
//    }

    private void getIncomingIntent(){
        final Intent intent = getIntent();
        user = intent.getParcelableExtra("data");
        position = intent.getIntExtra("position", 0);

        named = findViewById(R.id.dname);
        aged = findViewById(R.id.dage);
        addressed = findViewById(R.id.daddress);

        named.setText(user.getName());
        aged.setText(user.getAge());
        addressed.setText(user.getAddress());
    }
}