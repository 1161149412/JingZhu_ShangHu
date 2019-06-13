package com.xiaomai.yyshanghu.tixian;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.bean.GetMessageBean;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.utils.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PersonalInformationaActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.merchant_name)
    TextView merchant_name;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.contact_information)
    TextView contact_information;
    @BindView(R.id.address)
    TextView address;

    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;
    private String name;
    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_informationa;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        dialog = DialogUtils.showDialog_progressbar(PersonalInformationaActivity.this);
        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();

        getMessage();
    }

    @OnClick({R.id.merchant_name,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.merchant_name:
                Intent intent = new Intent(this,EditProfileActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
        }
    }

    public void getMessage(){
        RetrofitClient.getInstance().getApi(token).getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetMessageBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetMessageBean>() {
                               @Override
                               public void accept(GetMessageBean getMessageBean) throws Exception {
                                   name = getMessageBean.getData().getName();
                                   merchant_name.setText(getMessageBean.getData().getName());
                                   contact.setText(getMessageBean.getData().getLinkman());
                                   contact_information.setText(getMessageBean.getData().getLinktel());
                                   address.setText(getMessageBean.getData().getAddress());
                                   DialogUtils.closeDialog(dialog);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误");
                                   DialogUtils.closeDialog(dialog);
                               }
                           }
                );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMessage();
    }
}
