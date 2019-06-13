package com.xiaomai.yyshanghu.tixian;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.xiaomai.yyshanghu.bean.GetCodeBean;
import com.xiaomai.yyshanghu.bean.GetMessageBean;
import com.xiaomai.yyshanghu.bean.UserUpDate;
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

public class EditProfileActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.edit_put_name)
    EditText edit_put_name;
    @BindView(R.id.bt_save)
    TextView bt_save;

    String address,linkman,phone,name;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();
        Intent intent =getIntent();
        String name =intent.getStringExtra("name");
        edit_put_name.setText(name);
        getMessage();
    }

    @OnClick({R.id.back,R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_save:
                //TODO 保存网络操作
                name = edit_put_name.getText().toString();
                updateMessage(address,linkman,name,phone);
                break;
        }
    }

    private void updateMessage(String address, String linkman, String name, String phone){
        dialog = DialogUtils.showDialog_progressbar(EditProfileActivity.this);
        UserUpDate userUpDate = new UserUpDate(address,linkman,name,phone);
        Gson gson = new Gson();
        String strJson = gson.toJson(userUpDate);

        Log.d("tag", "22222"+strJson.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).updateMessage(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean getCodeBean) throws Exception {
                        if(getCodeBean.getCode()==1){
                            ToastUtil.showShortToast("修改成功");
                        }
                        DialogUtils.closeDialog(dialog);
                        toClass(EditProfileActivity.this,PersonalInformationaActivity.class);
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        DialogUtils.closeDialog(dialog);
                    }
                });
    }

    public void getMessage(){
        dialog = DialogUtils.showDialog_progressbar(EditProfileActivity.this);
        RetrofitClient.getInstance().getApi(token).getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetMessageBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetMessageBean>() {
                               @Override
                               public void accept(GetMessageBean getMessageBean) throws Exception {
                                   address = getMessageBean.getData().getAddress();
                                   linkman = getMessageBean.getData().getLinkman();
                                   phone = getMessageBean.getData().getLinktel();
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
