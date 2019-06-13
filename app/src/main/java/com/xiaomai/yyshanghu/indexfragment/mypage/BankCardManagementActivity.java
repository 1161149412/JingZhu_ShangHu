package com.xiaomai.yyshanghu.indexfragment.mypage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.tixian.AddBankCardActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BankCardManagementActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.btn_add_back_card)
    Button btn_add_back_card;
    @BindView(R.id.tv_update_info)
    TextView tv_update_info;


    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card_management;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back,R.id.btn_add_back_card,R.id.tv_update_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_add_back_card:
                //添加银行卡
                Intent intent = new Intent(this,AddBankCardActivity.class);
                intent.putExtra("title","添加银行卡");
                startActivity(intent);
                break;
            case R.id.tv_update_info:
                //修改银行卡
                Intent intent1 = new Intent(this,AddBankCardActivity.class);
                intent1.putExtra("title","修改银行卡");
                startActivity(intent1);
                break;
        }
    }
}
