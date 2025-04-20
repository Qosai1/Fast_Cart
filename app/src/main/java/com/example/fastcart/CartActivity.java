package com.example.fastcart;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button confirmButton;
    CartAdapter adapter;
    List<CartItem> cartItems = new ArrayList<>();
    SharedPreferences prefs;
    Button backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        confirmButton = findViewById(R.id.confirmButton);
        backButton = findViewById(R.id.backButton);
        TextView totalPriceText = findViewById(R.id.totalPriceText);

        // Load saved cart data from SharedPreferences
        prefs = getSharedPreferences("cart", MODE_PRIVATE);
        loadCartItems();

        // Set up the RecyclerView with the cart items
        adapter = new CartAdapter(cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Update total price
        calculateTotal(cartItems, totalPriceText);
        adapter.setOnCartChangeListener(() -> calculateTotal(cartItems, totalPriceText));

        // Handle order confirmation
        confirmButton.setOnClickListener(v -> {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_SHORT).show();

            // Clear the cart
            prefs.edit().clear().apply();

            finish();
        });

        // Back button
        backButton.setOnClickListener(v -> finish());
    }

    /**
     * Loads cart items from SharedPreferences.
     */
    private void loadCartItems() {
        String json = prefs.getString("cart_items", null);
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    cartItems.add(new CartItem(obj.getString("name"), obj.getString("price")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculates total price and updates the total price TextView.
     */
    private void calculateTotal(List<CartItem> cartItems, TextView totalPriceText) {
        double totalPrice = 0.0;

        for (CartItem item : cartItems) {
            String priceStr = item.getPrice().replace("$", "").trim();
            try {
                double price = Double.parseDouble(priceStr);
                totalPrice += price;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        totalPriceText.setText("Total: $" + String.format("%.2f", totalPrice));
    }
}
