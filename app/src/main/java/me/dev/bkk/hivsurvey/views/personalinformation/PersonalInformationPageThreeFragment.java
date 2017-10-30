package me.dev.bkk.hivsurvey.views.personalinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.pixplicity.easyprefs.library.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dev.bkk.hivsurvey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationPageThreeFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.tv_question)
    AppCompatTextView mTvQuestion;
    @BindView(R.id.rbn_yes)
    AppCompatRadioButton mRbnYes;
    @BindView(R.id.rbn_no)
    AppCompatRadioButton mRbnNo;
    @BindView(R.id.rg_answer)
    RadioGroup mRgAnswer;
    @BindView(R.id.btn_done)
    AppCompatButton mBtnDone;
    private Unbinder unbinder;

    public PersonalInformationPageThreeFragment() {
        // Required empty public constructor
    }

    public static PersonalInformationPageThreeFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInformationPageThreeFragment fragment = new PersonalInformationPageThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_information_page_three, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupListener();

        return view;
    }

    private void setupListener() {
        mBtnDone.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
                saveData();
                closePage();
                break;
        }
    }

    private void closePage() {
        getActivity().finish();
    }

    private void saveData(){
        Prefs.putString("ever_been", getAnswer());
    }

    private String getAnswer(){
        String answer = "";
        switch (mRgAnswer.getCheckedRadioButtonId()){
            case R.id.rbn_yes:
                answer = mRbnYes.getText().toString();
                break;
            case R.id.rbn_no:
                answer = mRbnNo.getText().toString();
                break;
        }

        return answer;
    }
}
