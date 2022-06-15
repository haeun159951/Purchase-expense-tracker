package com.example.a2_haeun_hekim4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2_haeun_hekim4.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ABC", "Submit button was pressed");
                // get the value from input box
                String nameFromUI = binding.eTName.getText().toString();
                String amountFromUI = binding.etAmount.getText().toString();

                if (nameFromUI.isEmpty() || amountFromUI.isEmpty()) {
                    Log.d("ABC", "It's required field");
                    binding.tvError.setText("It's required field");
                    binding.tvError.setTextColor(Color.parseColor("#FF00FF"));
                    return;
                }
                // once you input, the error should be gone
                binding.tvError.setVisibility(View.GONE);

                boolean statusFromUI = binding.swStatus.isChecked();
                double doubleAmountFromUI = Double.parseDouble(amountFromUI);

                purchaseList.add(new Purchase(nameFromUI, doubleAmountFromUI, statusFromUI));
                // 1. create a toast, using the built in Android sdk functions & show a toast
                Toast myToast = Toast.makeText(getApplicationContext(), "Form has been submitted!", Toast.LENGTH_SHORT);
                // 2. show a toast
                myToast.show();
                Log.d("ABC", purchaseList.toString());
            }
        });

        binding.btnLoadDummyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ABC", "Load button pressed");
                // pre-defined purchase lists
                purchaseList.add(new Purchase("Sunny Market", 13.42, false));
                purchaseList.add(new Purchase("Bubble Tea Cafe", 5.48, true));
                purchaseList.add(new Purchase("Galleria Market", 55.12, true));
                purchaseList.add(new Purchase("Flower shop", 22.12, true));
                purchaseList.add(new Purchase("Korean BBQ Restaurant", 80.51, false));
                // 1. create a toast, using the built in Android sdk functions & show a toast
                Toast myToast = Toast.makeText(getApplicationContext(), "Dummy data has been added!", Toast.LENGTH_SHORT);
                // 2. show a toast
                myToast.show();
            }
        });

        binding.btnShowPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an explicit intent
                // -- starting screen & the destination screen
                Intent intent = new Intent(getApplicationContext(), Screen2Activity.class);
                intent.putExtra("EXTRA_PURCHASE_LIST", purchaseList);
                // 2. use the startActivity()
                startActivity(intent);
            }
        });
    }
}