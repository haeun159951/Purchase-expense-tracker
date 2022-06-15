package com.example.a2_haeun_hekim4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.a2_haeun_hekim4.databinding.CustomRowLayoutBinding;

import java.util.List;

public class PurchaseObjectAdapter extends ArrayAdapter {

    private List<Purchase> purchaseList;

    public PurchaseObjectAdapter(@NonNull Context context, List<Purchase> purchaseList)
    {
        super(context, 0);
        this.purchaseList = purchaseList;
    }

    @Override
    public int getCount() {
        return purchaseList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_row_layout, parent, false);
        }

        Purchase purchase = purchaseList.get(position);

        CustomRowLayoutBinding binding = CustomRowLayoutBinding.bind(convertView);

        // @TODO: code to update the ui
        binding.tvStoreName.setText(purchase.getNameOfTheStore());
        binding.tvAmountUserPurchase.setText(String.valueOf(purchase.getAmountOfThePurchase()));
        boolean status = purchase.isPaid();

        if(status == true){
            binding.tvPaidStatus.setText("Paid");
        }else{
            binding.tvPaidStatus.setText("Unpaid");
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
