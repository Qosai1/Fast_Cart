package com.example.fastcart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodActivity extends AppCompatActivity {
    ListView foodListView;
    Button backButton;
    Button openCartButton;
    String[] foodNames = {"Burger", "Pizza", "Fries", "Hot Dog", "Chicken Wings", "Salad"};
    String[] foodDescriptions = {
            "Juicy beef burger with cheese, lettuce, and tomatoes.",
            "Classic pizza with pepperoni, cheese, and tomato sauce.",
            "Crispy golden fries, perfect with any meal.",
            "Grilled hot dog with your choice of toppings.",
            "Crispy chicken wings, served with a spicy dipping sauce.",
            "Fresh salad with mixed greens, tomatoes, and a light dressing."
    };
    String[] foodPrices = {
            "$25.00", "$18.00", "$11.50", "$9.00", "$12.50", "$9.00"
    };
    int[] foodImages = {
            R.drawable.burger, R.drawable.pizza, R.drawable.fries,
            R.drawable.hot_dog, R.drawable.chicken_wings, R.drawable.salad
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        foodListView();

        //  open detail screen with food info
        foodListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(FoodActivity.this, activity_detail.class);
            intent.putExtra("name", foodNames[position]);
            intent.putExtra("description", foodDescriptions[position]);
            intent.putExtra("price", foodPrices[position]);
            intent.putExtra("image", foodImages[position]);
            startActivity(intent);
        });

        // Back button:
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        // Open Cart button:
        openCartButton = findViewById(R.id.openCartButton);
        openCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(FoodActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // SearchView
        SearchView searchView = findViewById(R.id.searchView);
        foodListView = findViewById(R.id.foodListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodNames);
        foodListView.setAdapter(adapter);

        // Filter food list as user types in the SearchView
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

    //  simple food list
    private void foodListView() {
        foodListView = findViewById(R.id.foodListView);

        String[] foods = {
                "Burger ", "Pizza ", "Fries ",
                "Hot Dog ", "Chicken Wings ", "Salad "
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                foods
        );

        foodListView.setAdapter(adapter);
    }
}
