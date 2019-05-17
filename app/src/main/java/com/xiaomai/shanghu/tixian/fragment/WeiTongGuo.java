package com.xiaomai.shanghu.tixian.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Weitongguo;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.Condition;
import com.xiaomai.shanghu.bean.DepositsBean;
import com.xiaomai.shanghu.bean.Info;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WeiTongGuo extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    private List<DepositsBean.DataBean.ListBean> list;
    private Adapter_Weitongguo adapter;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    protected void initView(View view) {

        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        list = new ArrayList<>();

        adapter=new Adapter_Weitongguo(R.layout.weitongguo_item,list);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        recycler.addItemDecoration(new SpacesItemDecoration(12));
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();
        getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.weitongguo_fragment;
    }

    private void getData() {
        dialog = DialogUtils.showDialog_progressbar(getContext());
        //body传参
        Condition condition = new Condition("","","","-1",null);
        Info info = new Info(1+"",30+"",condition);
        Gson gson = new Gson();
        String strJson = gson.toJson(info);
        Log.d("tag", "json----" + strJson);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getDepositBean(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<DepositsBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<DepositsBean>() {
                    @Override
                    public void accept(DepositsBean bean) throws Exception {
                        if (bean.getCode() == 1) {
                            for (int i = 0;i<bean.getData().getList().size();i++){
                                list.add(bean.getData().getList().get(i));
                            }
                        }
                        adapter.notifyDataSetChanged();
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
}
