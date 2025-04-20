package com.example.fastcart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetail extends AppCompatActivity {
    ImageView foodImage;
    TextView foodName, foodDescription, foodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodImage = findViewById(R.id.Image);
        foodName = findViewById(R.id.Name);
        foodDescription = findViewById(R.id.Description);
        foodPrice = findViewById(R.id.Price);

        Intent intent = getIntent();
        foodName.setText(intent.getStringExtra("name"));
        foodDescription.setText(intent.getStringExtra("description"));
        foodPrice.setText(intent.getStringExtra("price"));
        foodImage.setImageResource(intent.getIntExtra("image", 0));



    }
}
