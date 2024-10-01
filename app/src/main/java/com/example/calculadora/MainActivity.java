package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

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

        TextView calScreen = findViewById(R.id.screen);

        //creamos los botones de numeros y operaciones y les asignamos su funcion de concatenación

        Button btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = calScreen.getText().toString();
                if (screen.equals("0")) {
                    Toast.makeText(MainActivity.this, "No se puede poner dos ceros seguidos", Toast.LENGTH_SHORT).show();
                } else {
                    calScreen.append("0");
                }
            }
        });
        //si pulsamos el 0 la primera vez y lo sigue otro cero que nos informe
        // que no se puede poner dos ceros seguidos


        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(v -> calScreen.append("1"));

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> calScreen.append("2"));

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> calScreen.append("3"));

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(v -> calScreen.append("4"));

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(v -> calScreen.append("5"));

        Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(v -> calScreen.append("6"));

        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(v -> calScreen.append("7"));

        Button btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(v -> calScreen.append("8"));

        Button btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(v -> calScreen.append("9"));

        Button btnSum = findViewById(R.id.btnSuma);
        btnSum.setOnClickListener(v -> calScreen.append("+"));

        Button btnRes = findViewById(R.id.btnResta);
        btnRes.setOnClickListener(v -> calScreen.append("-"));

        Button btnMul = findViewById(R.id.btnMultiplicacion);
        btnMul.setOnClickListener(v -> calScreen.append("*"));

        Button btnDiv = findViewById(R.id.btnDivision);
        btnDiv.setOnClickListener(v -> calScreen.append("÷"));

        Button btnPunto = findViewById(R.id.btnPunto);
        btnPunto.setOnClickListener(v -> calScreen.append("."));

        Button btnClear = findViewById(R.id.btnC);
        btnClear.setOnClickListener(v -> calScreen.setText(""));


        //boton de igual

        Button btnIgu = findViewById(R.id.btnIgual);

        btnIgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Obtenemos el texto de la pantalla
                String screen = calScreen.getText().toString();

                // Validamos si el string contiene una operación válida (+, -, *, ÷)
                if (!screen.matches(".*[+÷*-].*")) {
                    Toast.makeText(MainActivity.this, "Formato incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Creamos un array para separar los números y la operación
                String[] parts = screen.split("[+÷*-]");

                // Verificamos que tengamos exactamente dos números
                if (parts.length != 2) {
                    Toast.makeText(MainActivity.this, "Formato incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double num1 = Double.parseDouble(parts[0]);
                    double num2 = Double.parseDouble(parts[1]);
                    double result = 0;

                    if (screen.contains("+")) {
                        result = num1 + num2;
                    } else if (screen.contains("-")) {
                        result = num1 - num2;
                    } else if (screen.contains("÷")) {
                        if (num2 == 0) {
                            Toast.makeText(MainActivity.this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        result = num1 / num2;
                    } else if (screen.contains("*")) {
                        result = num1 * num2;
                    }

                    // Mostramos el resultado
                    calScreen.setText(String.valueOf(result));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Error de formato", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}