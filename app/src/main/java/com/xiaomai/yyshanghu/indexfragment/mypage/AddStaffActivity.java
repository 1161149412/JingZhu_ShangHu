package com.xiaomai.yyshanghu.indexfragment.mypage;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddStaffActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.edit_put_name)
    EditText edit_put_name;
    @BindView(R.id.edit_put_vip_phone)
    EditText edit_put_vip_phone;
    @BindView(R.id.edit_put_phone_code)
    EditText edit_put_phone_code;
    @BindView(R.id.tv_get_code)
    TextView tv_get_code;
    @BindView(R.id.bt_save)
    TextView bt_save;

    private String strTel,name,code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_staff;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.back,R.id.tv_get_code,R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_get_code://获取验证码
                strTel = edit_put_vip_phone.getText().toString();
                Log.d("tag", ""+strTel);
                if (TextUtils.isEmpty(strTel)) {
                    ToastUtil.showShortToast("电话号码不能为空");
                } else {
                    //TODO 获取验证码网络操作
                }
                break;
            case R.id.bt_save://保存
                strTel = edit_put_vip_phone.getText().toString();
                name = edit_put_name.getText().toString();
                code  = edit_put_phone_code.getText().toString();
                if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {
                    ToastUtil.showShortToast("任何一项都不能为空");
                } else {
                    //TODO 添加员工网络操作
                }

                break;
        }
    }

    @OnTextChanged({R.id.edit_put_name, R.id.edit_put_vip_phone,R.id.edit_put_phone_code})
    public void onTextChanged(CharSequence text) {
        strTel = edit_put_name.getText().toString().trim();
        name = edit_put_vip_phone.getText().toString().trim();
        code = edit_put_phone_code.getText().toString().trim();
        if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(name)||TextUtils.isEmpty(code)) {
            bt_save.setBackgroundResource(R.drawable.login_button);
            bt_save.setEnabled(false);
        } else {
            bt_save.setBackgroundResource(R.drawable.login_button_true);
            bt_save.setEnabled(true);
        }
    }
}
