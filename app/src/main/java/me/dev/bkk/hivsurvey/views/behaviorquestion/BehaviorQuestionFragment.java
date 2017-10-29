package me.dev.bkk.hivsurvey.views.behaviorquestion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dev.bkk.hivsurvey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BehaviorQuestionFragment extends Fragment implements
        View.OnClickListener {


    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.tv_question)
    AppCompatTextView mTvQuestion;
    @BindView(R.id.tv_yourself)
    AppCompatTextView mTvYourself;
    @BindView(R.id.rbn_yourself_answer_one)
    AppCompatRadioButton mRbnYourselfAnswerOne;
    @BindView(R.id.rbn_yourself_answer_two)
    AppCompatRadioButton mRbnYourselfAnswerTwo;
    @BindView(R.id.rbn_yourself_answer_three)
    AppCompatRadioButton mRbnYourselfAnswerThree;
    @BindView(R.id.rg_answer_yourself)
    RadioGroup mRgAnswerYourself;
    @BindView(R.id.tv_lover)
    AppCompatTextView mTvLover;
    @BindView(R.id.rbn_lover_answer_one)
    AppCompatRadioButton mRbnLoverAnswerOne;
    @BindView(R.id.rbn_lover_answer_two)
    AppCompatRadioButton mRbnLoverAnswerTwo;
    @BindView(R.id.rbn_lover_answer_three)
    AppCompatRadioButton mRbnLoverAnswerThree;
    @BindView(R.id.rg_answer_lover)
    RadioGroup mRgAnswerLover;
    Unbinder unbinder;
    @BindView(R.id.btn_next)
    AppCompatButton mBtnNext;
    @BindView(R.id.btn_previous)
    AppCompatButton mBtnPrevious;
    @BindView(R.id.btn_done)
    AppCompatButton mBtnDone;

    private String question[];
    private int questionNo = 0;
    private List<int[]> answer = new ArrayList<int[]>();
    private int yourselfAnswer[];
    private int loverAnswer[];

    public BehaviorQuestionFragment() {
        // Required empty public constructor
    }

    public static BehaviorQuestionFragment newInstance() {
        Bundle args = new Bundle();
        BehaviorQuestionFragment behaviorQuestionFragment = new BehaviorQuestionFragment();
        behaviorQuestionFragment.setArguments(args);
        return behaviorQuestionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_behavior_question, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupQuestion();
        setupAnswer();
        setupListener();

        return view;
    }

    private void setupAnswer() {
        yourselfAnswer = new int[question.length];
        loverAnswer = new int[question.length];

        answer.add(yourselfAnswer);
        answer.add(loverAnswer);
    }

    private void setupListener() {
        mBtnNext.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
    }

    private void setupQuestion() {
        question = getResources().getStringArray(R.array.behavior_question);
        mTvQuestion.setText(question[questionNo]);
        showNextButton();
    }

    private void nextQuestion() {
        if (questionNo == 3) {
            lastQuestion();
            return;
        }
        if (questionNo < question.length - 1) {
            setupAnswerTwoChoice();
            questionNo++;
            mTvQuestion.setText(question[questionNo]);
            showPreviousAndNextButton();
        }
    }

    private void previousQuestion() {
        if (questionNo > 0) {
            setupAnswerTwoChoice();
            questionNo--;
            mTvQuestion.setText(question[questionNo]);

            if (questionNo == 0) {
                showNextButton();
            } else {
                showPreviousAndNextButton();
            }
        }
    }

    private void lastQuestion() {
        mBtnPrevious.setVisibility(View.GONE);
        mBtnNext.setVisibility(View.GONE);
        mBtnDone.setVisibility(View.VISIBLE);

        setupAnswerThreeChoice();

        questionNo++;
        mTvQuestion.setText(question[questionNo]);

        showPreviousAndDoneButton();
    }

    private void showTwoChoice() {
        mRbnYourselfAnswerOne.setVisibility(View.VISIBLE);
        mRbnYourselfAnswerTwo.setVisibility(View.VISIBLE);
        mRbnYourselfAnswerThree.setVisibility(View.GONE);

        mRbnLoverAnswerOne.setVisibility(View.VISIBLE);
        mRbnLoverAnswerTwo.setVisibility(View.VISIBLE);
        mRbnLoverAnswerThree.setVisibility(View.GONE);

    }

    private void showThreeChoice() {
        mRbnYourselfAnswerOne.setVisibility(View.VISIBLE);
        mRbnYourselfAnswerTwo.setVisibility(View.VISIBLE);
        mRbnYourselfAnswerThree.setVisibility(View.VISIBLE);

        mRbnLoverAnswerOne.setVisibility(View.VISIBLE);
        mRbnLoverAnswerTwo.setVisibility(View.VISIBLE);
        mRbnLoverAnswerThree.setVisibility(View.VISIBLE);
    }

    private void setupAnswerTwoChoice() {
        mRbnYourselfAnswerOne.setText(R.string.text_answer_ever);
        mRbnYourselfAnswerTwo.setText(R.string.text_answer_never);

        mRbnLoverAnswerOne.setText(R.string.text_answer_ever);
        mRbnLoverAnswerTwo.setText(R.string.text_answer_never);

        showTwoChoice();
    }

    private void setupAnswerThreeChoice() {
        mRbnYourselfAnswerOne.setText(R.string.text_answer_risk);
        mRbnYourselfAnswerTwo.setText(R.string.text_answer_no_risk);
        mRbnYourselfAnswerThree.setText(R.string.text_answer_not_sure);

        mRbnLoverAnswerOne.setText(R.string.text_answer_risk);
        mRbnLoverAnswerTwo.setText(R.string.text_answer_no_risk);
        mRbnLoverAnswerThree.setText(R.string.text_answer_not_sure);

        showThreeChoice();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        saveAnswer(questionNo);

        switch (v.getId()) {
            case R.id.btn_next:
                nextQuestion();
                break;
            case R.id.btn_previous:
                previousQuestion();
                break;
            case R.id.btn_done:
                closePage();
                break;
        }

        clearAnswerSelect();
    }

    private void showPreviousAndNextButton() {
        mBtnPrevious.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.VISIBLE);
        mBtnDone.setVisibility(View.GONE);
    }

    private void showNextButton() {
        mBtnPrevious.setVisibility(View.GONE);
        mBtnNext.setVisibility(View.VISIBLE);
        mBtnDone.setVisibility(View.GONE);
    }

    private void showPreviousAndDoneButton() {
        mBtnPrevious.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.GONE);
        mBtnDone.setVisibility(View.VISIBLE);
    }

    private void closePage() {
        getActivity().finish();
    }

    private void saveAnswer(int questionNo) {
        switch (mRgAnswerYourself.getCheckedRadioButtonId()) {
            case R.id.rbn_yourself_answer_one:
                yourselfAnswer[questionNo] = 1;
                break;
            case R.id.rbn_yourself_answer_two:
                yourselfAnswer[questionNo] =0;
                break;
            case R.id.rbn_yourself_answer_three:
                yourselfAnswer[questionNo] = 0;
                break;
            default:
                yourselfAnswer[questionNo] = 0;
                break;
        }

        switch (mRgAnswerLover.getCheckedRadioButtonId()){
            case R.id.rbn_lover_answer_one:
                loverAnswer[questionNo] = 1;
                break;
            case R.id.rbn_lover_answer_two:
                loverAnswer[questionNo] = 0;
                break;
            case R.id.rbn_lover_answer_three:
                loverAnswer[questionNo] = 0;
                break;
            default:
                loverAnswer[questionNo] = 0;
                break;
        }
    }

    private void clearAnswerSelect() {
        mRgAnswerYourself.clearCheck();
        mRgAnswerLover.clearCheck();
    }
}
