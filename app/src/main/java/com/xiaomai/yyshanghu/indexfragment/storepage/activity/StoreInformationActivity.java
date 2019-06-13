package com.xiaomai.yyshanghu.indexfragment.storepage.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreInformationActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_information;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
