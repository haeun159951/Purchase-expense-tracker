package com.example.a2_haeun_hekim4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2_haeun_hekim4.databinding.ActivityScreen2Binding;

import java.util.ArrayList;

public class Screen2Activity extends AppCompatActivity implements CustomLayoutDialogBox.CustomLayoutDialogBoxListener {

    private ActivityScreen2Binding binding;
    private ArrayList<Purchase> purchaseListFromPrevScreen;
    private PurchaseObjectAdapter purchaseAdapter;
    private int row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            purchaseListFromPrevScreen = (ArrayList<Purchase>) intent.getSerializableExtra("EXTRA_PURCHASE_LIST");
            for (Purchase curr : purchaseListFromPrevScreen) {
                Log.d("ABC", curr.toString());
            }
            //2. create the adopter
            purchaseAdapter = new PurchaseObjectAdapter(this, purchaseListFromPrevScreen);

            //3. configure the listview to use the adopter
            binding.lvPurchases.setAdapter(purchaseAdapter);

            //4. detect when user click or tap the row
            binding.lvPurchases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CustomLayoutDialogBox myCustomBox = new CustomLayoutDialogBox();
                    myCustomBox.show(getSupportFragmentManager(), "custom layout box");
                    row = i;
                    Log.d("ABC", "which row? " + row);
                }
            });
        }
    }

    @Override
    public void onDialogUpdateBtnPressed(Purchase tobeUpdated) {
        if (tobeUpdated.getNameOfTheStore() == "N/A") {
            Toast.makeText(getApplicationContext(), "Store name is required", Toast.LENGTH_SHORT).show();
        } else if (tobeUpdated.getAmountOfThePurchase() == -1) {
            Toast.makeText(getApplicationContext(), "Purchase Amount is required", Toast.LENGTH_SHORT).show();
        } else {
            purchaseListFromPrevScreen.set(row, tobeUpdated);
            purchaseAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Data has been updated", Toast.LENGTH_SHORT).show();
        }
    }
}