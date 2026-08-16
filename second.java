package com.example.s23498498;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class second extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText tnPeople, tnBill;
    private TextView tvTip, tvPerPerson;
    private Button submit;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.qualityofservice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        tnPeople = findViewById(R.id.tnEnterNumberOfPeople);
        tnBill = findViewById(R.id.tnEnterBillAmount);
        submit = findViewById(R.id.btnSubmit);
        tvTip = findViewById(R.id.tvTotalTipDisplay);
        tvPerPerson = findViewById(R.id.tvSharePerPerson);
        btnExit = findViewById(R.id.btnExit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String peopleText = tnPeople.getText().toString();
                String billText = tnBill.getText().toString();

                if (peopleText.isEmpty() || billText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double value1 = Double.parseDouble(peopleText); // people
                    double value2 = Double.parseDouble(billText);   // bill

                    if (value1 == 0) {
                        Toast.makeText(getApplicationContext(), "People cannot be 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double tip = value2 * 0.18;
                    double total = value2 + tip;
                    double perPerson = total / value1;


                    tvTip.setText("Total Tip on the Entire Meal: R " + String.format("%.2f", tip));
                    tvPerPerson.setText("Share of the bill Per Person: R " + String.format("%.2f", perPerson));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
