package com.xiaomai.yyshanghu.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;


/**
 * Created by Administrator on 2016/12/4 0004.
 */
public class CountDownTimerReductionUtils extends CountDownTimer {
    private TextView mTextView;
    /**
     *60s后获取验证码
     */
    public CountDownTimerReductionUtils(TextView mTextView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView=mTextView;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText( millisUntilFinished / 1000+"s");  //设置倒计时时间
        mTextView.setTextColor(Color.parseColor("#E1B872"));
        //mTextView.setBackgroundResource(R.drawable.vercode); //设置按钮为灰色，这时是不能点击的
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
       // ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
        //spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setBackgroundResource(R.drawable.button_kong);//设置为空背景
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setTextColor(Color.parseColor("#E1B872"));
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.login_button_true);  //还原背景色
    }
}
