package me.dev.bkk.hivsurvey.views.personalinformation;


import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixplicity.easyprefs.library.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dev.bkk.hivsurvey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationPageTwoFragment extends Fragment implements
        View.OnClickListener {


    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.img_men)
    CheckableImageButton mImgMen;
    @BindView(R.id.img_women)
    CheckableImageButton mImgWomen;
    @BindView(R.id.tie_age)
    TextInputEditText mTieAge;
    @BindView(R.id.til_age)
    TextInputLayout mTilAge;
    @BindView(R.id.btn_next)
    AppCompatButton mBtnNext;
    Unbinder unbinder;

    private boolean isMen, isWomen = false;

    public PersonalInformationPageTwoFragment() {
        // Required empty public constructor
    }

    public static PersonalInformationPageTwoFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInformationPageTwoFragment fragment = new PersonalInformationPageTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_information_page_two, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupListener();

        return view;
    }

    private void setupListener() {
        mBtnNext.setOnClickListener(this);
        mImgMen.setOnClickListener(this);
        mImgWomen.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                saveData();
                openPageThree();
                break;
            case R.id.img_men:
                mImgMen.setImageResource(R.drawable.ic_male);
                mImgWomen.setImageResource(R.drawable.ic_female_gray);
                isMen = true;
                isWomen = false;
                break;
            case R.id.img_women:
                mImgMen.setImageResource(R.drawable.ic_male_gray);
                mImgWomen.setImageResource(R.drawable.ic_female);
                isWomen = true;
                isMen = false;
                break;
        }
    }

    private void openPageThree(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PersonalInformationPageThreeFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    private void saveData(){
        Prefs.putString("gender", getGender());
        Prefs.putString("ages", mTieAge.getText().toString());
    }

    private String getGender(){

        String gender = "";
        if(isMen && !isWomen){
            gender = "ชาย";
        }else if(isWomen && !isMen){
            gender = "หญิง";
        }

        return gender;
    }
}
