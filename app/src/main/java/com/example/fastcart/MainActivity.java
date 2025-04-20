package com.example.fastcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize spinner
        spinner = findViewById(R.id.spinner);
        bindSpinner(); // optional - sets up spinner with predefined values

        String[] categories = {"Select an Option", "Food", "Drinks", "Bakery"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Spinner selection listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();

                //  "Select an Option"
                if (position == 0) return;

                // Open the corresponding activity
                switch (selected) {
                    case "Drinks":
                        startActivity(new Intent(MainActivity.this, DrinksActivity.class));
                        break;

                    case "Food":
                        startActivity(new Intent(MainActivity.this, FoodActivity.class));
                        break;

                    case "Bakery":
                        startActivity(new Intent(MainActivity.this, BakeryActivity.class));
                        break;
                }
                spinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void bindSpinner() {
        String[] arr = {"Select an Option", "Food", "Drinks", "Bakery"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
        spinner.setAdapter(spnAdapter);
    }
}
