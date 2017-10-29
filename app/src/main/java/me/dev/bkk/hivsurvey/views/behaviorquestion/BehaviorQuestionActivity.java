package me.dev.bkk.hivsurvey.views.behaviorquestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.dev.bkk.hivsurvey.R;

public class BehaviorQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_question);

        setupFragment(savedInstanceState);
    }

    private void setupFragment(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, BehaviorQuestionFragment.newInstance())
                    .commit();
        }

    }
}
