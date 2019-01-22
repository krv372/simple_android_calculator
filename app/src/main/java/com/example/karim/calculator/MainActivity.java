package com.example.karim.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.karim.calculator.CalculatorUtil.calculate;

public class MainActivity extends AppCompatActivity {

    private Double number, number2;
    private char operator = 'n';
    private boolean resetStartingNumber = false;
    private TextView monitorTextView;
    private TextView statementTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.monitorTextView = findViewById(R.id.monitor_text_view);
        this.statementTextView = findViewById(R.id.statement_text_view);
    }

    public void numberActions(View view) {
        checkForResettingNumber();
        int input = (CalculatorUtil.mapButtonToNumbers.get(view.getId()));
        setMonitorValue(StringUtil.addNumberToRightOfNumber(getCurrentMonitorText(), input));
    }

    private void checkForResettingNumber() {
        if (resetStartingNumber) {
            number = getCurrentMonitorNumber();
            number2 = null;
            resetMonitorText();
        }
    }

    public void decimalButtonAction(View view) {
        checkForResettingNumber();
        if (!getCurrentMonitorText().contains(".")) {
            monitorTextView.append(".");
        }
    }

    public void resetCalculator(View view) {
        statementTextView.setText("");
        number = number2 = null;
        operator = 'n';
        resetMonitorText();
    }

    public void cleanCalculator(View view) {
        String currentText = getCurrentMonitorText();
        monitorTextView.setText(StringUtil.remveLastIndexOfNumberString(currentText));
    }

    public void closeCalculator(View view) {
        finish();
    }

    public void setStatementTextViewText(Double n1, Double n2, char operator) {
        if (n1 == null) {
            statementTextView.setText("");
        } else {
            String n2Text = n2 == null ? "" : n2 + ":";
            statementTextView.setText(n1 + " " + operator + " " + n2Text);
        }
    }

    public void operatorAction(View view) {
        double number2 = getCurrentMonitorNumber();
        if (number != null) {
            setStatementTextViewText(number, number2, operator);
            number = calculate(number, number2, operator);
            setMonitorValue(number);
        }
        number = null;
        operator = CalculatorUtil.mapButtonToOperator.get(view.getId());
        resetStartingNumber = true;
    }

    public void addDoubleZero(View view) {
        checkForResettingNumber();
        double currentMonitorNumber = getCurrentMonitorNumber();

        if (currentMonitorNumber == 0) {
            return;
        } else if ((currentMonitorNumber < 1 && currentMonitorNumber > 0) || (currentMonitorNumber > -1 && currentMonitorNumber < 0)) {
            setMonitorValue(currentMonitorNumber / 100);
        } else {
            setMonitorValue(currentMonitorNumber * 100);
        }
    }

    public double getCurrentMonitorNumber() {
        return Double.parseDouble(getCurrentMonitorText());
    }

    private String getCurrentMonitorText() {
        return monitorTextView.getText().toString();
    }

    private void resetMonitorText() {
        resetStartingNumber = false;
        setMonitorValue("0");
    }

    public void equalOperator(View view) {
        if (this.number == null && this.number2 == null || operator == 'n') {
            return;
        }

        if (this.number2 == null) {
            number2 = getCurrentMonitorNumber();
        }
        if (number == null) {
            number = getCurrentMonitorNumber();
        }
        setStatementTextViewText(number, number2, operator);
        setMonitorValue(calculate(number, number2, operator));
        number = null;
    }

    private void setMonitorValue(double input) {
        if (Double.isInfinite(input)) {
            resetCalculator(null);
            statementTextView.setText(String.valueOf(input));
        } else {
            setMonitorValue(String.valueOf(input));

        }
    }

    private void setMonitorValue(String input) {
        if (input.endsWith(".0")) {
            input = input.replace(".0", "");
        }
        monitorTextView.setText(input);
    }

}
