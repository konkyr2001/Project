package com.example.alarmmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class DestinationActivity extends AppCompatActivity {

    private Random random;
    private int randomNumber;
    private ArrayList<Integer> differentRandomNumber;
    private TextView counterWins;
    private TextView mathEquation;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;
    private int counterWin = 0;
    private ArrayList<String> equation;
    private ArrayList<String> option1;
    private ArrayList<String> option2;
    private ArrayList<String> option3;
    private ArrayList<String> option4;
    private ArrayList<Integer> correctOption;
    private EditText editText;
    private Button submit;
    private Toast toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        random = new Random();
        differentRandomNumber = new ArrayList<>();
        initializeArrays();

        counterWins = (TextView) findViewById(R.id.counterWins);
        mathEquation = (TextView) findViewById(R.id.mathEquation);
        option1Button = (Button) findViewById(R.id.option1);
        option2Button = (Button) findViewById(R.id.option2);
        option3Button = (Button) findViewById(R.id.option3);
        option4Button = (Button) findViewById(R.id.option4);
        editText = (EditText) findViewById(R.id.edittext);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = editText.getText().toString();
                if (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")) {
                    if (toastMessage != null) {
                        toastMessage.cancel();
                    }
                    toastMessage = Toast.makeText(getApplicationContext(), "Type only a number between 1-4", Toast.LENGTH_SHORT);
                    toastMessage.show();

                    return;
                }
                if (correctOption.get(randomNumber) == Integer.parseInt(answer))
                    correctAnswer();
                else
                    wrongAnswer();
            }
        });

        setTexts();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void wrongAnswer() {
        counterWin = 0;
        if (toastMessage != null) {
            toastMessage.cancel();
        }
        toastMessage = Toast.makeText(this, "Wrong answer... You need 3 correct answer in a row", Toast.LENGTH_SHORT);
        toastMessage.show();

        counterWins.setText("Correct: 0/3");
        differentRandomNumber.removeAll(differentRandomNumber);
        setTexts();
    }

    private void correctAnswer() {
        counterWin++;
        if (toastMessage != null) {
            toastMessage.cancel();
        }
        if (counterWin == 3) {
            toastMessage = Toast.makeText(this, "Congrats! You alarm is done", Toast.LENGTH_SHORT);
            finish();
            System.exit(0);
            // turn off alarm
        } else
            toastMessage = Toast.makeText(this, "Correct answer! " + (3 - counterWin) + " more left", Toast.LENGTH_SHORT);
        toastMessage.show();

        counterWins.setText("Correct: " + counterWin + "/3");
        setTexts();
    }

    private void setTexts() {
        randomNumber = random.nextInt(10);
        if (!differentRandomNumber.isEmpty()) {
            int i=0;
            while (differentRandomNumber.get(i) == randomNumber && i < differentRandomNumber.size()) {
                randomNumber = random.nextInt(10);
            }
            differentRandomNumber.add(randomNumber);
        } else
            differentRandomNumber.add(randomNumber);

        mathEquation.setText(equation.get(randomNumber) + "");
        option1Button.setText(option1.get(randomNumber) + "");
        option2Button.setText(option2.get(randomNumber) + "");
        option3Button.setText(option3.get(randomNumber) + "");
        option4Button.setText(option4.get(randomNumber) + "");
    }

    private void initializeArrays() {
        equation = new ArrayList<String>();
        option1 = new ArrayList<String>();
        option2 = new ArrayList<String>();
        option3 = new ArrayList<String>();
        option4 = new ArrayList<String>();
        correctOption = new ArrayList<Integer>();

        // index 0
        equation.add("-5x = 25");
        option1.add("1)x = 20");
        option2.add("2)x = -20");
        option3.add("3)x = -5");
        option4.add("4)x = 5");
        correctOption.add(3);

        // index 1
        equation.add("19 + x = -50");
        option1.add("1)x = -950");
        option2.add("2)x = -69");
        option3.add("3)x = -31");
        option4.add("4)x = -50/19");
        correctOption.add(2);

        // index 2
        equation.add("x - 8 = -5");
        option1.add("1)x = 13");
        option2.add("2)x = -3");
        option3.add("3)x = -13");
        option4.add("4)x = 3");
        correctOption.add(3);

        // index 3
        equation.add("x + 15 = 3");
        option1.add("1)x = -18");
        option2.add("2)x = 12");
        option3.add("3)x = -12");
        option4.add("4)x = 18");
        correctOption.add(3);

        // index 4
        equation.add("x + 12 = 20");
        option1.add("1)x = 32");
        option2.add("2)x = -8");
        option3.add("3)x = -32");
        option4.add("4)x = 8");
        correctOption.add(4);

        // index 5
        equation.add("16 = x/11");
        option1.add("1)x = 176");
        option2.add("2)x = 5");
        option3.add("3)x = 152");
        option4.add("4)x = 27");
        correctOption.add(1);

        // index 6
        equation.add("x - 33 = 147");
        option1.add("1)x = 114");
        option2.add("2)x = 180");
        option3.add("3)x = 190");
        option4.add("4)x = 170");
        correctOption.add(2);

        // index 7
        equation.add("2x + 5 = 39");
        option1.add("1)x = 15");
        option2.add("2)x = -20");
        option3.add("3)x = -12");
        option4.add("4)x = 17");
        correctOption.add(3);

        // index 8
        equation.add("4x - 6 = 30");
        option1.add("1)x = 9");
        option2.add("2)x = 13");
        option3.add("3)x = 36");
        option4.add("4)x = 4");
        correctOption.add(1);

        // index 9
        equation.add("2x - 3 + 4x = 27");
        option1.add("1)x = 15");
        option2.add("2)x = 12");
        option3.add("3)x = 4");
        option4.add("4)x = 5");
        correctOption.add(4);

    }
}