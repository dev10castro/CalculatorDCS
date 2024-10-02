package com.example.calculadora;

import android.content.res.Configuration;
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

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnSum, btnRes, btnMul, btnDiv, btnPunto, btnClear;
    private TextView calScreen;

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

        calScreen = findViewById(R.id.screen);

        //creamos los botones de numeros y operaciones y les asignamos su funcion de concatenación

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(v -> onClick(v));

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(v -> onClick(v));

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> onClick(v));

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> onClick(v));

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(v -> onClick(v));

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(v -> onClick(v));

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(v -> onClick(v));

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(v -> onClick(v));

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(v -> onClick(v));

        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(v -> onClick(v));

        btnSum = findViewById(R.id.btnSuma);
        btnSum.setOnClickListener(v -> onClick(v));

        btnRes = findViewById(R.id.btnResta);
        btnRes.setOnClickListener(v -> onClick(v));

        btnMul = findViewById(R.id.btnMultiplicacion);
        btnMul.setOnClickListener(v -> onClick(v));

        btnDiv = findViewById(R.id.btnDivision);
        btnDiv.setOnClickListener(v -> onClick(v));

        btnPunto = findViewById(R.id.btnPunto);
        btnPunto.setOnClickListener(v -> onClick(v));

        btnClear = findViewById(R.id.btnC);
        btnClear.setOnClickListener(v -> onClick(v));


        Button btnIgu = findViewById(R.id.btnIgual);
        btnIgu.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                // Obtengo el texto de la pantalla
                String screen = calScreen.getText().toString();

// Valido si el string contiene una operación válida (+, -, *, ÷) usando esta expresión regular
                if (!screen.matches(".*[+÷*-].*")) {
                    Toast.makeText(MainActivity.this, "Formato incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Creo un array para separar los números y las operaciones
                String[] numbers = screen.split("[+÷*-]");
                String[] operators = screen.split("[0-9.]+");

                // Verifico que tengamos al menos dos números y un operador
                if (numbers.length < 2 || operators.length < 2) {
                    Toast.makeText(MainActivity.this, "Formato incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Primero realizo las multiplicaciones y divisiones
                    for (int i = 1; i < operators.length; i++) {
                        if (operators[i].equals("*") || operators[i].equals("÷")) {
                            double num1 = Double.parseDouble(numbers[i - 1]);
                            double num2 = Double.parseDouble(numbers[i]);
                            double result = 0;

                            if (operators[i].equals("*")) {
                                result = num1 * num2;
                            } else if (operators[i].equals("÷")) {
                                if (num2 == 0) {
                                    Toast.makeText(MainActivity.this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                result = num1 / num2;
                            }

                            numbers[i - 1] = String.valueOf(result);
                            numbers[i] = "1"; // Marco el número como procesado
                            operators[i] = "*"; // Cambio el operador a multiplicación para que no afecte el resultado final
                        }
                    }

                    // Luego realizo las sumas y restas
                    double finalResult = Double.parseDouble(numbers[0]);
                    for (int i = 1; i < numbers.length; i++) {
                        double num = Double.parseDouble(numbers[i]);
                        char operator = operators[i].charAt(0);

                        switch (operator) {
                            case '+':
                                finalResult += num;
                                break;
                            case '-':
                                finalResult -= num;
                                break;
                            case '*':
                                finalResult *= num;
                                break;
                        }
                    }

                    // Muestro el resultado
                    calScreen.setText(String.valueOf(finalResult));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Formato incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    /*
     * Método que se ejecuta al hacer click en un botón de numeros y operaciones
     */
    public void onClick(View v) {

        if (calScreen.getText().length() >= 14) {
            Toast.makeText(this, "Máximo de 14 caracteres alcanzado", Toast.LENGTH_SHORT).show();
            return;
        }

        if (v.getId() == btn1.getId()) {
            calScreen.append("1");
        } else if (v.getId() == btn2.getId()) {
            calScreen.append("2");
        } else if (v.getId() == btn3.getId()) {
            calScreen.append("3");
        } else if (v.getId() == btn4.getId()) {
            calScreen.append("4");
        } else if (v.getId() == btn5.getId()) {
            calScreen.append("5");
        } else if (v.getId() == btn6.getId()) {
            calScreen.append("6");
        } else if (v.getId() == btn7.getId()) {
            calScreen.append("7");
        } else if (v.getId() == btn8.getId()) {
            calScreen.append("8");
        } else if (v.getId() == btn9.getId()) {
            calScreen.append("9");
        } else if (v.getId() == btn0.getId()) {
            if (calScreen.getText().toString().equals("0")) {
                Toast.makeText(this, "No se pueden poner dos ceros seguidos", Toast.LENGTH_SHORT).show();
            } else {
                calScreen.append("0");
            }
        } else if (v.getId() == btnSum.getId()) {
            calScreen.append("+");
        } else if (v.getId() == btnRes.getId()) {
            calScreen.append("-");
        } else if (v.getId() == btnMul.getId()) {
            calScreen.append("*");
        } else if (v.getId() == btnDiv.getId()) {
            calScreen.append("÷");
        } else if (v.getId() == btnPunto.getId()) {
            calScreen.append(".");
        }
        if (v.getId() == btnClear.getId()) {
            calScreen.setText("");
        }
    }


        }
