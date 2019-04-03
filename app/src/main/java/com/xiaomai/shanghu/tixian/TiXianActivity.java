package com.xiaomai.shanghu.tixian;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_tixian_money)
    TextView tvTixianMoney;
    @BindView(R.id.tv_tixian_dongjie)
    TextView tvTixianDongjie;
    @BindView(R.id.view_bank)
    LinearLayout viewBank;
    @BindView(R.id.bt_addbank)
    TextView btAddbank;
    @BindView(R.id.view_addbank)
    LinearLayout viewAddbank;
    @BindView(R.id.tx_tixian_yuan)
    TextView txTixianYuan;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.bt_sure)
    TextView btSure;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.back, R.id.bt_addbank, R.id.bt_sure,R.id.view_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_addbank:
                //添加银行卡
                toClass(this, AddBankCardActivity.class);
                break;
            case R.id.view_bank:
                //修改信息
                toClass(this, AddBankCardActivity.class);
                break;
            case R.id.bt_sure:
                toClass(this,TiXianShowActivity.class);
                break;
        }
    }
}
