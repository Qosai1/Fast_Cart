package com.example.fastcart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_detail extends AppCompatActivity {
    ImageView Image;
    TextView Name, Description, Price;
    Button backButton;
    Button addToCartButton;
    Button openCartButton;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Image = findViewById(R.id.Image);
        Name = findViewById(R.id.Name);
        Description = findViewById(R.id.Description);
        Price = findViewById(R.id.Price);

        Intent intent = getIntent();
        Name.setText(intent.getStringExtra("name"));
        Description.setText(intent.getStringExtra("description"));
        Price.setText(intent.getStringExtra("price"));
        Image.setImageResource(intent.getIntExtra("image", 0));

        // Back button
        backButton = findViewById(R.id.btback);
        backButton.setOnClickListener(v -> {
            finish();
        });

        // Add to Cart button
        addToCartButton = findViewById(R.id.btcart);
        addToCartButton.setOnClickListener(v -> {
            String name = getIntent().getStringExtra("name");
            String price = getIntent().getStringExtra("price");

            prefs = getSharedPreferences("cart", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String json = prefs.getString("cart_items", "[]");
            try {
                JSONArray array = new JSONArray(json);

                // Create JSON object for the new cart item
                JSONObject item = new JSONObject();
                item.put("name", name);
                item.put("price", price);

                array.put(item);

                // Save updated cart to SharedPreferences
                editor.putString("cart_items", array.toString());
                editor.apply();

                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        // Button to open the Cart screen
        openCartButton = findViewById(R.id.openCartButton);
        openCartButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(activity_detail.this, CartActivity.class);
            startActivity(intent1);
        });
    }
}
