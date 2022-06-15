package com.example.a2_haeun_hekim4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a2_haeun_hekim4.databinding.CustomDialogLayoutBinding;

public class CustomLayoutDialogBox extends DialogFragment {

    // binding variable for the custom layout xml file
    private CustomDialogLayoutBinding binding;
    // listener
    private CustomLayoutDialogBoxListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //initialize the binding
        binding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the layout of the alert dialog.
        builder.setView(binding.getRoot());


        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 1. get data from UI
                String nameFromUI = binding.eTName.getText().toString();
                String amount = binding.etAmount.getText().toString();
                boolean statusFromUI = binding.swStatus.isChecked();

                Purchase purchaseToUpdate = new Purchase(nameFromUI, amount, statusFromUI);
                listener.onDialogUpdateBtnPressed(purchaseToUpdate);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }


    // Interface
    public interface CustomLayoutDialogBoxListener {
        // methods
        public void onDialogUpdateBtnPressed(Purchase purchaseToUpdate);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CustomLayoutDialogBoxListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}

