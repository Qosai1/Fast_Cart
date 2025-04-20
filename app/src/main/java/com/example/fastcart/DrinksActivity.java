package com.example.fastcart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DrinksActivity extends AppCompatActivity {

    ListView drinksListView;
    String[] drinkNames = {"Pepsi", "Orange Juice", "Iced Coffee", "Mineral Water", "Lemonade", "Green Tea"};
    String[] drinkDescriptions = {
            "Classic fizzy cola drink, served chilled.",
            "Freshly squeezed orange juice rich in Vitamin C.",
            "Cool and energizing coffee served with ice.",
            "Pure and refreshing bottled mineral water.",
            "Sweet and tangy lemonade with a hint of mint.",
            "Soothing green tea, served warm or cold."
    };

    String[] drinkPrices = {
            "$3.50", // Pepsi
            "$6.00", // Orange Juice
            "$4.00", // Iced Coffee
            "$2.00", // Mineral Water
            "$6.75", // Lemonade
            "$4.25"  // Green Tea
    };

    int[] drinkImages = {
            R.drawable.pepsi, R.drawable.orange_juice,
            R.drawable.iced_coffee, R.drawable.mineral_water,
            R.drawable.lemonade, R.drawable.green_tea
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drinks);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drinksListView();
        //  open detail screen with food info
        drinksListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DrinksActivity.this, activity_detail.class);
            intent.putExtra("name", drinkNames[position]);
            intent.putExtra("description", drinkDescriptions[position]);
            intent.putExtra("price", drinkPrices[position]);
            intent.putExtra("image", drinkImages[position]);
            startActivity(intent);
        });

        // Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Cart button
        Button openCartButton = findViewById(R.id.openCartButton);
        openCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(DrinksActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // SearchView setup
        SearchView searchView = findViewById(R.id.searchView);
        drinksListView = findViewById(R.id.drinksListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drinkNames);
        drinksListView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    // Helper method to initialize the list view
    private void drinksListView() {
        drinksListView = findViewById(R.id.drinksListView);

        String[] drinks = {
                "Pepsi ",
                "Orange Juice ",
                "Iced Coffee ",
                "Mineral Water ",
                "Lemonade ",
                "Green Tea "
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                drinks
        );

        drinksListView.setAdapter(adapter);
    }
}
