package com.xiaomai.yyshanghu.tixian;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.bean.AddBack;
import com.xiaomai.yyshanghu.bean.GetCodeBean;
import com.xiaomai.yyshanghu.bean.GetCreditCardBean;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.utils.DialogUtils;
import com.xiaomai.yyshanghu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddBankCardActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.edit_put_name)
    EditText edit_put_name;
    @BindView(R.id.edit_put_kaihu_back_name)
    EditText edit_put_kaihu_back_name;
    @BindView(R.id.edit_put_back_card)
    EditText edit_put_back_card;
    @BindView(R.id.edit_put_phone)
    EditText edit_put_phone;
    @BindView(R.id.bt_save)
    TextView bt_save;
    @BindView(R.id.tv_title)
    TextView tv_title;

    String name,kaihu_bank_name,bank_card,phone;

    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;
    private String title;
    private int card_id;
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        Intent intent =getIntent();
        title=intent.getStringExtra("title");

        tv_title.setText(title);

        if(title.equals("修改银行卡")){
            getCreditCard();
        }else if(title.equals("添加银行卡")){

        }

        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();
    }

    @OnClick({R.id.back, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_save:
                name = edit_put_name.getText().toString();
                kaihu_bank_name = edit_put_kaihu_back_name.getText().toString();
                bank_card = edit_put_back_card.getText().toString();
                phone = edit_put_phone.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(kaihu_bank_name) || TextUtils.isEmpty(bank_card) || TextUtils.isEmpty(phone)) {
                    ToastUtil.showShortToast("任何一项都不能为空！");
                }else {
                    addOrUpdateCreditCard(name,kaihu_bank_name,bank_card,phone);
                }
                break;
        }
    }

    /**
     * 更新修改银行卡
     * */
    private void addOrUpdateCreditCard(String name,String kaihu_bank_name,String bank_card,String phone){

        AddBack addBack = null;
        dialog = DialogUtils.showDialog_progressbar(AddBankCardActivity.this);
        if(title.equals("修改银行卡")){
            addBack = new AddBack(kaihu_bank_name,bank_card,card_id+"",phone,name);
        }else if(title.equals("添加银行卡")){
            addBack = new AddBack(kaihu_bank_name,bank_card,"",phone,name);
        }

        Gson gson = new Gson();
        String strJson = gson.toJson(addBack);

        Log.d("tag", "22222"+strJson.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).addOrUpdateCreditCard(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean getCodeBean) throws Exception {
                        if(getCodeBean.getCode()==1){
                            ToastUtil.showShortToast("操作成功");
                            finish();
                        }else if(getCodeBean.getCode() == -1){
                            ToastUtil.showShortToast(getCodeBean.getMsg());
                        }
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        DialogUtils.closeDialog(dialog);
                    }
                });

    }

    /**
     * 获取用户银行卡
     * */
    private void getCreditCard(){
        dialog = DialogUtils.showDialog_progressbar(AddBankCardActivity.this);
        RetrofitClient.getInstance().getApi(token).getCreditCard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCreditCardBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCreditCardBean>() {
                               @Override
                               public void accept(GetCreditCardBean getCreditCardBean) throws Exception {
                                   edit_put_name.setText(getCreditCardBean.getData().getRealName());
                                   edit_put_kaihu_back_name.setText(getCreditCardBean.getData().getBank());
                                   edit_put_back_card.setText(getCreditCardBean.getData().getCreditCard());
                                   edit_put_phone.setText(getCreditCardBean.getData().getMobile());
                                   card_id = getCreditCardBean.getData().getId();
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


}
