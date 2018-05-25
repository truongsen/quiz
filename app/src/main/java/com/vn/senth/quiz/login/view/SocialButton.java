package com.vn.senth.quiz.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.vn.core.utils.StringUtils;
import com.vn.senth.quiz.R;

/**
 * Created by SenTH on 5/25/2018.
 */

public class SocialButton extends FrameLayout {
    private ImageView icon;
    private TextView title;

    public SocialButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SocialButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SocialButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SocialButton, 0, 0);
        int type = a.getInt(R.styleable.SocialButton_style, 0);
        if (type == context.getResources().getInteger(R.integer.SocialFacebook)) {
            LayoutInflater.from(context).inflate(R.layout.btn_facebook, this, true);
        } else if (type == context.getResources().getInteger(R.integer.SocialGoogle)) {
            LayoutInflater.from(context).inflate(R.layout.btn_google, this, true);
        }
        // icon
        icon = findViewById(R.id.ic_social);
        if (icon != null) {
            int icRes = a.getInt(R.styleable.SocialButton_ic, 0);
            if (icRes != 0) {
                icon.setImageResource(icRes);
            }
        }
        // title
        title = findViewById(R.id.title);
        if (title != null) {
            String text = a.getString(R.styleable.SocialButton_text);
            if (!StringUtils.isEmpty(text))
                title.setText(text);
        }
        a.recycle();
    }

    public void setAlpha(float alpha) {
        if (icon != null)
            icon.setAlpha(alpha);
        if (title != null)
            title.setAlpha(alpha);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.3f);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                setAlpha(1f);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}