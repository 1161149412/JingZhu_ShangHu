package com.xiaomai.shanghu.tixian;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.Condition;
import com.xiaomai.shanghu.bean.GetBalanceBean;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.GetCreditCardBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.HideUtil;
import com.xiaomai.shanghu.utils.StringToIntUtils;
import com.xiaomai.shanghu.utils.ToastUtil;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_back_card_name)
    TextView tv_back_card_name;
    @BindView(R.id.tv_back_card)
    TextView tv_back_card;
    @BindView(R.id.view_bank)
    LinearLayout view_bank;
    @BindView(R.id.bt_addbank)
    TextView btAddbank;
    @BindView(R.id.view_addbank)
    LinearLayout viewAddbank;
    @BindView(R.id.tv_back_name)
    TextView tv_back_name;
    @BindView(R.id.tv_back_tle)
    TextView tv_back_tle;
    @BindView(R.id.bt_sure)
    TextView btSure;
    @BindView(R.id.edit_money)
    EditText edit_money;
    @BindView(R.id.tv_ke_tixian_money)
    TextView tv_ke_tixian_money;
    @BindView(R.id.tv_dongjie_tixian)
    TextView tv_dongjie_tixian;
    @BindView(R.id.linearLayou_main)
    LinearLayout linearLayou_main;
    @BindView(R.id.tv_tixian_sxf)
    TextView tv_tixian_sxf;

    private String put_money;
    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;
    private int ke_tixian;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        dialog = DialogUtils.showDialog_progressbar(TiXianActivity.this);

        SpannableString ss = new SpannableString("请输入金额");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(22,true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit_money.setHint(new SpannedString(ss));

        getCreditCard();
        getBalance();
    }

    @OnClick({R.id.back, R.id.bt_addbank,R.id.bt_sure,R.id.view_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_addbank:
                //添加银行卡
                Intent intent = new Intent(this,AddBankCardActivity.class);
                intent.putExtra("title","添加银行卡");
                startActivity(intent);
                break;
            case R.id.view_bank:
                //修改信息
                Intent intent1 = new Intent(this,AddBankCardActivity.class);
                intent1.putExtra("title","修改银行卡");
                startActivity(intent1);
                break;
            case R.id.bt_sure:
                put_money = edit_money.getText().toString();

                if (TextUtils.isEmpty(put_money)) {
                    ToastUtil.showShortToast("提现金额不能为空");
                }else {
                    try{
                        int money = Integer.parseInt(put_money);
                        if(money<20 && ke_tixian<20){
                            ToastUtil.showShortToast("可提现余额不足");
                        }else if(money<20 && ke_tixian>=20){
                            ToastUtil.showShortToast("提现金额最低为20元");
                        }else if(money>20 && ke_tixian<money){
                            ToastUtil.showShortToast("输入金额的最大值不能超过可提现余额");
                        }else {
                            addWithdraw(put_money);
                        }
                    }catch(Exception e){

                    }
                }
                break;
        }
    }

    /**
     * 获取用户银行卡
     * */
    private void getCreditCard(){
        RetrofitClient.getInstance().getApi(token).getCreditCard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCreditCardBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCreditCardBean>() {
                               @Override
                               public void accept(GetCreditCardBean getCreditCardBean) throws Exception {

                                   if(getCreditCardBean.getData().equals("null")){
                                       view_bank.setVisibility(View.GONE);
                                       viewAddbank.setVisibility(View.VISIBLE);
                                   }else {
                                       viewAddbank.setVisibility(View.GONE);
                                       view_bank.setVisibility(View.VISIBLE);
                                       tv_back_card_name.setText(getCreditCardBean.getData().getBank());
                                       tv_back_card.setText(HideUtil.hideCardNo(getCreditCardBean.getData().getCreditCard()));
                                       tv_back_name.setText(getCreditCardBean.getData().getRealName());
                                       tv_back_tle.setText(getCreditCardBean.getData().getMobile());
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误");
                               }
                           }
                );
    }

    /**
     * 获取商家提现中金额和可提现金额
     * */
    private void getBalance(){
//        dialog = DialogUtils.showDialog_progressbar(TiXianActivity.this);
        RetrofitClient.getInstance().getApi(token).getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetBalanceBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetBalanceBean>() {
                               @Override
                               public void accept(GetBalanceBean getBalanceBean) throws Exception {
                                   if (getBalanceBean.getCode()==1){
                                       ke_tixian = getBalanceBean.getData().getCanWithdraw();
                                       tv_ke_tixian_money.setText(StringToIntUtils.StringToInt(getBalanceBean.getData().getCanWithdraw()+""));
                                       tv_dongjie_tixian.setText(StringToIntUtils.StringToInt(getBalanceBean.getData().getWithdrawIng()+""));
                                   }else if(getBalanceBean.getCode()==-10 ||getBalanceBean.getMsg().equals("您的账户已在其他设备上登录")){
                                       signOutDialog(TiXianActivity.this);
                                       usertoken.edit().clear().commit();
                                   }
                                   DialogUtils.closeDialog(dialog);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误");
                                   DialogUtils.closeDialog(dialog);
                                   if (throwable.getMessage().equals("HTTP 401 ")){
                                       signOutDialog(TiXianActivity.this);
                                       usertoken.edit().clear().commit();
                                   }
                               }
                           }
                );
    }

    /**
     * 发起提现申请
     * */
    private void addWithdraw(String put_money){
//        dialog = DialogUtils.showDialog_progressbar(TiXianActivity.this);
        Condition condition = new Condition(put_money);

        Gson gson = new Gson();
        String strJson = gson.toJson(condition);

        Log.d("tag", "22222"+strJson.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).addWithdraw(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean getCodeBean) throws Exception {
                      if(getCodeBean.getCode()==1){
                          toClass(TiXianActivity.this,TiXianShowActivity.class);
                      }else if(getCodeBean.getCode()==-1){
                          ToastUtil.showShortToast(""+getCodeBean.getMsg());
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

    @OnTextChanged({R.id.edit_money})
    public void onTextChanged(CharSequence text) {
        if(TextUtils.isEmpty(text)){
            tv_tixian_sxf.setText("0.00");
        }
        try {
            put_money = edit_money.getText().toString();
            Log.d("money", ""+put_money);
            int get_money = Integer.parseInt(put_money);
            if(get_money==0){
                tv_tixian_sxf.setText("0.00");
            }else if(get_money<=1000){
                tv_tixian_sxf.setText("1.00");
            }else if(get_money>1000){
                double   f   =  get_money * 0.001;
                tv_tixian_sxf.setText(""+doubleToString(f));
                Log.d("计算结果", ""+f);
                Log.d("计算结果", ""+doubleToString(f));
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getCreditCard();
    }

    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

}
