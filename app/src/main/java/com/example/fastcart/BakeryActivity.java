package com.example.fastcart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class BakeryActivity extends AppCompatActivity {

    ListView bakeryListView;

    String[] Names = {"Croissant", "Chocolate Cake", "Donut", "Bagel", "Muffin"};
    String[] Descriptions = {
            "Flaky, buttery French pastry.",
            "Rich and moist chocolate cake slice.",
            "Sweet, round fried dough with icing.",
            "Chewy ring-shaped bread roll.",
            "Soft and fluffy blueberry muffin."
    };
    String[] Prices = {"$4.50", "$7.00", "$5.75", "$4.00", "$6.25"};

    int[] Images = {
            R.drawable.croissant,
            R.drawable.chocolate_cake,
            R.drawable.donut,
            R.drawable.bagel,
            R.drawable.muffin
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery);

        // Initialize the ListView that shows bakery items
        bakeryListView = findViewById(R.id.bakeryListView);

        // These are the items shown in the ListView
        String[] bakeryItems = {
                "Croissant ",
                "Chocolate Cake ",
                "Donut ",
                "Bagel ",
                "Muffin "
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bakeryItems
        );
        bakeryListView.setAdapter(adapter);

        bakeryListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(BakeryActivity.this, activity_detail.class);
            intent.putExtra("name", Names[position]);
            intent.putExtra("description", Descriptions[position]);
            intent.putExtra("price", Prices[position]);
            intent.putExtra("image", Images[position]);
            startActivity(intent);
        });

        // Back button
        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        // Cart button
        findViewById(R.id.openCartButton).setOnClickListener(v -> {
            Intent intent = new Intent(BakeryActivity.this, CartActivity.class);
            startActivity(intent);
        });

        //  search functionality
        SearchView searchView = findViewById(R.id.searchView);
        bakeryListView = findViewById(R.id.bakeryListView); // Rebinding just to be safe

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
}
