package com.xiaomai.yyshanghu.indexfragment.mypage;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class Staff_MangementActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.btn_add_staff)
    Button btn_add_staff;

    @Override
    public int getLayoutId() {
        return R.layout.activity_staff__mangement;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back,R.id.btn_add_staff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_add_staff:
                toClass(Staff_MangementActivity.this,AddStaffActivity.class);
                finish();
                break;
        }
    }

}
