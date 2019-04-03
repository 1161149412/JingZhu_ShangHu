package com.xiaomai.shanghu.tixian;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.shanghu.MainActivity;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianShowActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.bt_back_index)
    TextView btBackIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian_show;
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.back, R.id.bt_back_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_back_index:
                toClass_Empty(this, MainActivity.class);
                break;
        }
    }
}
