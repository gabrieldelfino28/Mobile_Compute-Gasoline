package br.edu.fateczl.calculocombustivel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputGasolina;
    private EditText inputEtanol;
    private TextView output;

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

        inputEtanol = findViewById(R.id.inputEtanol);
        inputGasolina = findViewById(R.id.inputGasolina);
        output = findViewById(R.id.tvAnswer);

        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> CalcCombustivel());
    }


    @SuppressWarnings("all")
    private void CalcCombustivel() {
        output.setText(" ");

        float valGasolina = Float.parseFloat(inputGasolina.getText().toString());
        float valEtanol = Float.parseFloat(inputEtanol.getText().toString());

        if (valEtanol < (valGasolina * 0.70f)) {
            String diffPorcentagem = " " + String.format("%.2f", (valEtanol / valGasolina) * 100f);
            output.setText(getString(R.string.respostaUm) + diffPorcentagem);
        } else if (valEtanol > (valGasolina * 0.70f)) {
            if (valEtanol <= valGasolina) { //Maior que 70% mas ainda menor que o valor da gasolina
                String diffPorcentagem = " " + String.format("%.2f", (valEtanol / valGasolina) * 100f);
                output.setText(getString(R.string.respostaDois) + diffPorcentagem);
            }else { //Caso etanol seja mais caro que gasolina.
                output.setText(getString(R.string.respostaTres));
            }
        }
    }
}