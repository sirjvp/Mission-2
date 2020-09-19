package com.uc.uts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.uts.model.User;

import java.util.ArrayList;

public class PrototypeAdapter extends RecyclerView.Adapter<PrototypeAdapter.ExampleViewHolder> {
    private ArrayList<User> mAdapterList;
    private Context context;

    public PrototypeAdapter(Context context){
        this.context = context;
    }

    public ArrayList<User> getmAdapterList() {
        return mAdapterList;
    }

    public void setmAdapterList(ArrayList<User> mAdapterList) {
        this.mAdapterList = mAdapterList;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        CardView cardview;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.name);
            mTextView2 = itemView.findViewById(R.id.age);
            mTextView3 = itemView.findViewById(R.id.address);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }

//    public void PrototypeAdapter(ArrayList<User> mAdapterList) {
//        this.mAdapterList = mAdapterList;
//    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_prototype_adapter, parent, false);
//        ExampleViewHolder evh = new ExampleViewHolder(v);
//        return evh;
        return new PrototypeAdapter.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, final int position) {
//        User currentItem = mAdapterList.get(position);
        final User currentItem = getmAdapterList().get(position);
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getAge());
        holder.mTextView3.setText(currentItem.getAddress());
        //biar kepencet semua
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetail.class);
                intent.putExtra("data", mAdapterList.get(position));
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return mAdapterList.size();
        return getmAdapterList().size();
    }
}
