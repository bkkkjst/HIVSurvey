package me.dev.bkk.hivsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dev.bkk.hivsurvey.views.personalinformation.PersonalInformationActivity;
import me.dev.bkk.hivsurvey.views.question.QuestionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_personal_information)
    Button mBtnPersonalInformation;
    @BindView(R.id.btn_survey)
    Button mBtnSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupListener();

    }

    private void setupListener() {
        mBtnPersonalInformation.setOnClickListener(this);
        mBtnSurvey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_personal_information:
                openPersonalInformation();
                break;
            case R.id.btn_survey:
                openSurvey();
                break;
        }
    }

    private void openPersonalInformation(){
        Intent intent = new Intent(MainActivity.this, PersonalInformationActivity.class);
        startActivity(intent);
    }

    private void openSurvey(){
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}
