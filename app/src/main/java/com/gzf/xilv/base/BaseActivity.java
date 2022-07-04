package com.gzf.xilv.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    public ViewDataBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        dataBinding.setLifecycleOwner(this);
        context = getApplicationContext();
        initStatusBar();
        initView();
        initData();
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initData();


    protected void initStatusBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .keyboardEnable(true)
                .fitsSystemWindows(true)
                .init();
    }
}
