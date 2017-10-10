package me.dev.bkk.hivsurvey.views.personalinformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dev.bkk.hivsurvey.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PersonalInformationActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);

        setupFragment(savedInstanceState);
    }

    private void setupFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, PersonalInformationPageOneFragment.newInstance())
                    .commit();

        }
    }
}
