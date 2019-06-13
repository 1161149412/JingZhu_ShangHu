package com.xiaomai.yyshanghu.tixian;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.yyshanghu.MainActivity;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;

import butterknife.BindView;
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
