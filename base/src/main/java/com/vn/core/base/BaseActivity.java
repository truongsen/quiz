package com.vn.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vn.core.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SenTH on 5/24/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        setUpToolbar();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    public void setUpToolbar() {
        Toolbar toolBar = findViewById(R.id.toolbar);
        if (toolBar != null) {
            setSupportActionBar(toolBar);
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
}
