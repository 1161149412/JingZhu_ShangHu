package com.xiaomai.shanghu.filter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Filter_TixianActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.filter_time)
    TextView filterTime;
    @BindView(R.id.filter_bt_reset)
    TextView filterBtReset;
    @BindView(R.id.filter_bt_submit)
    TextView filterBtSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter__tixian;
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.back, R.id.filter_time, R.id.filter_bt_reset, R.id.filter_bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.filter_time:
                TimePickerView pvTime = new TimePickerBuilder(Filter_TixianActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        filterTime.setText(DateUtils.getTodayDateTime(date));
                    }
                }).build();
                pvTime.show();

                break;
            case R.id.filter_bt_reset:
                break;
            case R.id.filter_bt_submit:
                break;
        }
    }
}
