package com.app.consultarcep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnSeachCep;
    EditText edtCepInserted;
    TextView txtResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSeachCep = findViewById(R.id.btnSearch);
        edtCepInserted = findViewById(R.id.edtCepInserted);
        txtResult = findViewById(R.id.txtResult);

        btnSeachCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cepInserted = edtCepInserted.getText().toString();

                try {
                    CEP result = new HttpService(cepInserted.trim()).execute().get();
                    txtResult.setText(result.toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }


}