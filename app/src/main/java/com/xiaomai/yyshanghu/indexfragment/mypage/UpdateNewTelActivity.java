package com.xiaomai.yyshanghu.indexfragment.mypage;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateNewTelActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.bt_save)
    TextView bt_save;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_new_tel;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back,R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_save://保存
                toClass(UpdateNewTelActivity.this,UpdateSuccessActivity.class);
                break;
        }
    }
}
