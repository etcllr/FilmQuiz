package com.webtech.filmquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webtech.filmquiz.pojo.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView scoreText;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnRestart;
    private Integer currentQuestionIndex = 0;
    private Integer score = 0;
    private List<Question> questions = new ArrayList<>();
    private Resources res;
    private MainActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère les vues
        findViews();
        btnRestart.setVisibility(View.GONE);

        // On récupère les questions
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex");
            score = savedInstanceState.getInt("score");
            scoreText.setText("Score : " + score);
        }

        // On initialise les questions
        setQuestions();
        tvQuestion.setText(questions.get(currentQuestionIndex).getQuestion());

        btnTrue.setOnClickListener(v -> {
            checkAnswer(true);
            currentQuestionIndex++;
            updateQuestion();
        });

        btnFalse.setOnClickListener(v -> {
            checkAnswer(false);
            currentQuestionIndex++;
            updateQuestion();
        });

        btnRestart.setOnClickListener(v -> {
            reset();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentQuestionIndex", currentQuestionIndex);
        outState.putInt("score", score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cheat:
                Intent intent = new Intent(context, CheatActivity.class);
                intent.putExtra("answer", questions.get(currentQuestionIndex).isAnswer());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void findViews() {
        tvQuestion = findViewById(R.id.tv_question);
        scoreText = findViewById(R.id.score);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnRestart = findViewById(R.id.restart_game);
    }

    protected void setQuestions() {
        res = getResources();
        questions.add(new Question(1, res.getString(R.string.question_1), true));
        questions.add(new Question(2, res.getString(R.string.question_2), false));
        questions.add(new Question(3, res.getString(R.string.question_3), true));
        questions.add(new Question(4, res.getString(R.string.question_4), false));
        questions.add(new Question(5, res.getString(R.string.question_5), true));
        questions.add(new Question(6, res.getString(R.string.question_6), false));
        questions.add(new Question(7, res.getString(R.string.question_7), true));
        questions.add(new Question(8, res.getString(R.string.question_8), false));
        questions.add(new Question(9, res.getString(R.string.question_9), true));
        questions.add(new Question(10, res.getString(R.string.question_10), false));
    }

    protected void updateQuestion() {
        if (currentQuestionIndex < questions.size()) {
            tvQuestion.setText(questions.get(currentQuestionIndex).getQuestion());
        } else {
            tvQuestion.setText("Fin du quiz");
        }
        if (currentQuestionIndex == questions.size()) {
            displayFinish();
        }
    }

    protected void checkAnswer(boolean userChoice) {
        if (currentQuestionIndex < questions.size()) {
            if (questions.get(currentQuestionIndex).isAnswer() == userChoice) {
                score++;
            }
            scoreText.setText("Score : " + score);
        }
    }

    protected void reset() {
        currentQuestionIndex = 0;
        score = 0;
        scoreText.setText("Score : " + score);
        updateQuestion();
        btnRestart.setVisibility(View.GONE);
        btnTrue.setVisibility(View.VISIBLE);
        btnFalse.setVisibility(View.VISIBLE);
    }

    protected void displayFinish() {
        tvQuestion.setText("Fin du quiz");
        btnTrue.setVisibility(View.INVISIBLE);
        btnFalse.setVisibility(View.INVISIBLE);
        btnRestart.setVisibility(View.VISIBLE);
    }
}