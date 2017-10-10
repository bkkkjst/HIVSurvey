package me.dev.bkk.hivsurvey.views.question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dev.bkk.hivsurvey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.tv_question)
    AppCompatTextView mTvQuestion;
    @BindView(R.id.btn_yes)
    Button mBtnYes;
    @BindView(R.id.btn_no)
    Button mBtnNo;
    Unbinder unbinder;
    @BindView(R.id.et_another)
    EditText mEtAnother;
    @BindView(R.id.btn_done)
    AppCompatButton mBtnDone;

    private String question[];
    private int questionNo = 0;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        unbinder = ButterKnife.bind(this, view);

        question = getResources().getStringArray(R.array.question);
        ;

        setupQuestion(questionNo);
        setupListener();

        return view;
    }

    private void setupListener() {
        mBtnYes.setOnClickListener(this);
        mBtnNo.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
    }

    private void setupQuestion(int questionNo) {
        mTvQuestion.setText(question[questionNo]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                nextQuestion();
                break;
            case R.id.btn_no:
                nextQuestion();
                break;
            case R.id.btn_done:
                closePage();
        }
    }

    private void closePage() {
        getActivity().finish();
    }

    private void previousQuestion() {
        if (questionNo > 0) {
            mBtnNo.setVisibility(View.VISIBLE);
            mBtnYes.setVisibility(View.VISIBLE);
            mBtnDone.setVisibility(View.GONE);
            questionNo--;
            mTvQuestion.setText(question[questionNo]);
        }
    }

    private void nextQuestion() {
        if (questionNo == 2) {
            lastQuestion();
            return;
        }
        if (questionNo < question.length - 1) {
            mBtnNo.setVisibility(View.VISIBLE);
            mBtnYes.setVisibility(View.VISIBLE);
            mBtnDone.setVisibility(View.GONE);
            questionNo++;
            mTvQuestion.setText(question[questionNo]);
        }
    }

    private void lastQuestion() {
        mBtnNo.setVisibility(View.GONE);
        mBtnYes.setVisibility(View.GONE);
        mBtnDone.setVisibility(View.VISIBLE);
        mEtAnother.setVisibility(View.VISIBLE);
        questionNo++;
        mTvQuestion.setText(question[questionNo]);
    }

}
