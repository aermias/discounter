package com.aermias.discounter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private final RelativeLayout container = findViewById(R.id.container);
    private final Button calcDiscount = findViewById(R.id.calcDiscButton);
    private final TextView display = findViewById(R.id.finalPrice);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // on "ready"
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcDiscount.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                calculateDiscount(container, display);
            }
        });

        calcDiscount.setOnLongClickListener(new Button.OnLongClickListener(){
            public boolean onLongClick(View view) {
                calculateDiscount(container, display);
                return true;
            }
        });
    }

    public void calculateDiscount(RelativeLayout layout, TextView view) {
        // decimal format 0.00
        DecimalFormat df = new DecimalFormat("#.00");

        EditText costPriceTV = findViewById(R.id.costPrice);
        EditText discountTV = findViewById(R.id.discount);
        TextView finalPriceTV = findViewById(R.id.finalPrice);

        try {
            // tries to pull string values from textviews and turns them into doubles
            Double costPrice = Double.valueOf(costPriceTV.getText().toString());
            Double discount = Double.valueOf(discountTV.getText().toString());

            // divided discount by 100 to get the decimal value (user will enter a percent)
            Double finalPrice = costPrice - (costPrice * (discount / 100));

            // throws exception if under 0
            if (finalPrice < 0) throw new Exception();

            // formats final price 0.00
            finalPrice = Double.valueOf(df.format(finalPrice));

            // sets text
            view.setText("$" + String.format("%.2f", finalPrice));
            layout.setBackgroundColor(Color.GREEN);
        } catch (Exception e) {
            // sets text
            view.setText(getResources().getString(R.string.invalid_entry));
            layout.setBackgroundColor(Color.RED);
        }
    }

    public String appendZeros(String value) {

        return "";
    }
}
