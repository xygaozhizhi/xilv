package com.gzf.xilv.ui.personal;

import android.view.View;

import com.gzf.xilv.R;
import com.gzf.xilv.base.mvp.BaseMvpActivity;
import com.gzf.xilv.databinding.ActivityPersonalBinding;

public class PersonalActivity extends BaseMvpActivity<PersonalPresenter> implements PersonalContract.View {
    private ActivityPersonalBinding binding;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        binding = (ActivityPersonalBinding) dataBinding;
    }

    @Override
    protected PersonalPresenter createPresenter() {
        return new PersonalPresenter();
    }

    public void onPersonalClick(View view) {
        if (view == binding.ivReturn) {
            finish();
        }

    }
}