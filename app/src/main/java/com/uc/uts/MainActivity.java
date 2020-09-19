package com.uc.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.uts.model.DataUser;
import com.uc.uts.model.User;
import com.uc.uts.utils.ItemClickSupport;
//import com.uc.uts.utils.ItemClickSupport;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button_tambah;

    private RecyclerView mRecyclerView;
    private PrototypeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> AdapterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_tambah = findViewById(R.id.button_add);
        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        AdapterList = DataUser.userdata;
        mRecyclerView = findViewById(R.id.RecyclerView);
        showAdapter(AdapterList);


//        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                User currentItem = AdapterList.get(position);
//                Intent intent = new Intent(MainActivity.this, UserDetail.class);
//                intent.putExtra("name", currentItem.getName());
//                intent.putExtra("age", currentItem.getAge());
//                intent.putExtra("address", currentItem.getAddress());
//                intent.putExtra("data", position);
//                startActivity(intent);
//            }
//        });

    }

    private void showAdapter(final ArrayList<User>list) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new PrototypeAdapter(MainActivity.this);
        mAdapter.setmAdapterList(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    public boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(a);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(MainActivity.this, "Press back once more to close the apps!", Toast.LENGTH_SHORT).show();
    }
}
