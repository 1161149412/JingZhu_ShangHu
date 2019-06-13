package com.xiaomai.yyshanghu.staff;

import android.view.View;
import android.widget.RelativeLayout;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StaffStoreDetailsActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_staff_store_details;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {  //item_staff_store_details 列表item
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
