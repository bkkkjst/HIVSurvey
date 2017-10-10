package me.dev.bkk.hivsurvey.views.personalinformation;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dev.bkk.hivsurvey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationPageOneFragment extends Fragment implements
        View.OnClickListener{


    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.tie_name)
    TextInputEditText mTieName;
    @BindView(R.id.til_name)
    TextInputLayout mTilName;
    @BindView(R.id.tie_id_card)
    TextInputEditText mTieIdCard;
    @BindView(R.id.til_id_card)
    TextInputLayout mTilIdCard;
    @BindView(R.id.tie_tel)
    TextInputEditText mTieTel;
    @BindView(R.id.til_tel)
    TextInputLayout mTilTel;
    @BindView(R.id.btn_next)
    AppCompatButton mBtnNext;
    Unbinder unbinder;

    public PersonalInformationPageOneFragment() {
        // Required empty public constructor
    }

    public static PersonalInformationPageOneFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInformationPageOneFragment fragment = new PersonalInformationPageOneFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_information_page_one, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupListener();

        return view;
    }

    private void setupListener() {
        mBtnNext.setOnClickListener(this);
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
                openPageTwo();
                break;
        }
    }

    private void openPageTwo(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PersonalInformationPageTwoFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
