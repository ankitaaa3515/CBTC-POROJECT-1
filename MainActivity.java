package com.example.converter;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValueEditText;
    private Spinner unitFromSpinner, unitToSpinner;
    private Button convertBtn;
    private TextView resultTextView;

    private String[] units = {"Centimeters", "Meters", "Grams", "Kilograms"};
    private double conversionFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValueEditText = findViewById(R.id.inputValueEditText);
        unitFromSpinner = findViewById(R.id.unitFromSpinner);
        unitToSpinner = findViewById(R.id.unitToSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitFromSpinner.setAdapter(adapter);
        unitToSpinner.setAdapter(adapter);

        unitFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionFactor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        unitToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionFactor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void updateConversionFactor() {
        String fromUnit = unitFromSpinner.getSelectedItem().toString();
        String toUnit = unitToSpinner.getSelectedItem().toString();

        if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
            conversionFactor = 0.01;
        } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
            conversionFactor = 100;
        } else if (fromUnit.equals("Grams") && toUnit.equals("Kilograms")) {
            conversionFactor = 0.001;
        } else if (fromUnit.equals("Kilograms") && toUnit.equals("Grams")) {
            conversionFactor = 1000;
        } else {
            conversionFactor = 1;
        }
    }

    private void performConversion() {
        String inputValueString = inputValueEditText.getText().toString();
        if (!inputValueString.isEmpty()) {
            double inputValue = Double.parseDouble(inputValueString);
            double resultValue = inputValue * conversionFactor;
            resultTextView.setText(String.valueOf(resultValue));
        } else {
            resultTextView.setText("Please enter a value");
        }
    }
}
