package com.uc.uts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.uc.uts.model.DataUser;
import com.uc.uts.model.User;
import com.uc.uts.utils.ItemClickSupport;

import java.util.ArrayList;

public class ExampleDialog extends AppCompatDialogFragment {

    ArrayList<User> AdapterList = DataUser.userdata;
    private ItemClickSupport.OnItemClickListener mListener;
    private int mPosition;

    public ExampleDialog(int position) {
        mPosition = position;
    }

    public ExampleDialog() {

    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        AdapterList.remove(mPosition);
//                        transaksiHelper = new TransaksiHelper(getActivity());
//                        listsave = transaksiHelper.allData();
//                        id = User.getid;
//                        transaksiHelper.delete(id);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                    }
                });
            return builder.create();

    }
}
