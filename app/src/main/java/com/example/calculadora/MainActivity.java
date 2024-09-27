package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        btn0.setOnClickListener(v -> calScreen.append("0"));

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

                String screen = calScreen.getText().toString();

                //creamos un array para separar los numeros y la operacion

                String[] parts = screen.split("[+÷*-]");

                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[1]);
                double result = 0;
                if(screen.contains("+")){
                    result = num1 + num2;
                }else if(screen.contains("-")){
                    result = num1 - num2;
                }else if(screen.contains("÷")){
                    result = num1 / num2;
                }else if(screen.contains("*")){
                    result = num1 * num2;
                }
              // hacemos un if para que si el resultado es un numero entero no muestre decimales

                int r = (int) result;
                if (result % 1 == 0){

                    calScreen.setText(String.valueOf(r));
                }else if (result % 1 != 0){


                    calScreen.setText(String.valueOf(r));

                }

            }
        });








    }
}