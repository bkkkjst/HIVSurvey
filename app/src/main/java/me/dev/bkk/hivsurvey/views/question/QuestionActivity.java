package me.dev.bkk.hivsurvey.views.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.dev.bkk.hivsurvey.R;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setupFragment(savedInstanceState);
    }

    private void setupFragment(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, QuestionFragment.newInstance())
                    .commit();
        }

    }
}
