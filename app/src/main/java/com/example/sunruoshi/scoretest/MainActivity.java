package com.example.sunruoshi.scoretest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "FLAG_HW3";
    private static int eventCount = 0;

    private
    TextView tv_num1;
    TextView tv_num2;
    TextView symbol;
    Button btn_submit;
    Compute compute;
    TextView tv_userAn;

    private FirebaseAuth auth;

    private int total_score = 0;
    private int count = 0;

    static final String STATE_SCORE = "user_score";
    static final String STATE_COUNT = "user_count";
    static final String SUBMIT_STATE = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount)+":Activity onCreate State Transition");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        compute = new Compute();
        symbol= (TextView) findViewById(R.id.symbol);
        tv_num1 = (TextView) findViewById(R.id.tv_num1);
        tv_num2 = (TextView) findViewById(R.id.tv_num2);
        tv_userAn = (TextView) findViewById(R.id.tv_userAn);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setEnabled(false);

        if (savedInstanceState != null){
            tv_num1.setText(savedInstanceState.getString("num1"));
            tv_num2.setText(savedInstanceState.getString("num2"));
            total_score = savedInstanceState.getInt(STATE_SCORE);
            count = savedInstanceState.getInt(STATE_COUNT);
            compute.setNum1(Integer.parseInt(savedInstanceState.getString("num1")));
            compute.setNum2(Integer.parseInt(savedInstanceState.getString("num2")));
            btn_submit.setEnabled(savedInstanceState.getBoolean(SUBMIT_STATE));
        }
        else {
            String name;
            Bundle bundle = getIntent().getExtras();
            name = bundle.getString("name");
            name = "Welcome " + name;
            Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSubmit();
            }
        });
    }

    @Override
    protected void onStart(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStart State Transition");
        super.onStart();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        Log.i(MyFlag, "Retrieving our saved state from before...");
        super.onRestoreInstanceState(savedInstanceState);

        tv_num1.setText(savedInstanceState.getString("num1"));
        tv_num2.setText(savedInstanceState.getString("num2"));
        total_score = savedInstanceState.getInt(STATE_SCORE); //saves the total score so far
        count = savedInstanceState.getInt(STATE_COUNT); // saves the number of questions answered so far
        compute.setNum1(Integer.parseInt(savedInstanceState.getString("num1")));
        compute.setNum2(Integer.parseInt(savedInstanceState.getString("num2")));
        btn_submit.setEnabled(savedInstanceState.getBoolean(SUBMIT_STATE));
    }

    @Override
    protected void onResume(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onResume State Transition");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
        Log.i(MyFlag, "Bundling State of our views before they get destroyed");

        outState.putString("num1", tv_num1.getText().toString());
        outState.putString("num2", tv_num2.getText().toString());
        outState.putInt(STATE_SCORE, total_score);
        outState.putInt(STATE_COUNT, count);
        outState.putBoolean(SUBMIT_STATE, btn_submit.isEnabled());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onPause State Transition");
        super.onPause();
    }

    @Override
    protected void onStop(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStop State Transition");
        super.onStop();
    }

    @Override
    protected void onRestart(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestart State Transition");
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onDestroy State Transition");
        super.onDestroy();
    }

    /**
     * Created by sophiahon on 2018/2/1
     */

    // Start the event when click the generate button
    public void goGenerateQuestions(View view) {
        btn_submit.setEnabled(true);  //Enable the submit button
        //submit = true;
        //reset the textfield
        tv_userAn.setText("");
        //compute.setScore(0);  //Reset the score
        total_score = 0;
        //compute.setCount(0);  //Reset the questions count
        count = 0;
        Integer op2 = (int) (Math.random()*10 + 1); //Generate randomly number
        Integer op1 = (int) (Math.random()*10)*op2;
        tv_num1.setText(op1.toString());
        tv_num2.setText(op2.toString());
        compute.setNum1(op1);
        compute.setNum2(op2);

    }


    // Submit the answer and generate the next question or show the final score
//    public void goSubmit(View view) {
    public void goSubmit() {

        if (!tv_userAn.getText().toString().equals("")) {  //Check is the input is empty
            String yourAns =  tv_userAn.getText().toString();
            //compute.addCount();
            count++;

            // Check if it is the correct answer
            // Count the score
            if (compute.getRightAns() == Integer.parseInt(yourAns)) {
                //compute.addScore();
                total_score++;
            }

            // If we finish all 10 questions, show the final score
            //if (compute.getCount() == 10) {
            if (count == 10){
                //Toast.makeText(getApplicationContext(), "Your final score is: " + compute.getScore(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Your final score is: " + total_score, Toast.LENGTH_LONG).show();
                //compute.setCount(0);
                count = 0;
                //compute.setScore(0);
                total_score = 0;
                btn_submit.setEnabled(false);
                //submit = false;
                return;
            }


            //reset the textfield
            tv_userAn.setText("");

            //randomly generate the next question
            Integer op2 = (int) (Math.random() * 10 + 1);
            Integer op1 = (int) (Math.random() * 10)*op2;
            tv_num1.setText(op1.toString());
            tv_num2.setText(op2.toString());
            compute.setNum1(op1);
            compute.setNum2(op2);


            // If it is empty input, warning the player!!
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid answer!!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    /**
     * Created by sunruoshi on 2018/2/9.
     */



    public static class Compute {

        private Integer Num1;
        private Integer Num2;
        private Integer RightAns;
        private Integer Score;
        private Integer count;

        public Compute() {
            this.Num1 = 0;
            this.Num2 = 0;
            this.RightAns = 0;
            this.count = 0;
            this.Score = 0;
        }

        public void setNum1(int v) {
            Num1 = v;
        }


        public void setNum2(int v) {
            Num2 = v;
        }


        public Integer getRightAns() {
            RightAnswer();
            return RightAns;
        }

        public void setCount(int v) {
            count = v;
        }

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }

        public void setScore(int v) {
            Score = v;
        }

        public int getScore() {
            return Score;
        }

        public void addScore() {
            Score += 1;
        }


        private void RightAnswer() {
            System.out.println("HERE: " + Num1 + " , " + Num2);
            RightAns = Num1 / Num2;
        }
    }

    //helpers
    public String intToStr(Integer i) { return i.toString();}

}

