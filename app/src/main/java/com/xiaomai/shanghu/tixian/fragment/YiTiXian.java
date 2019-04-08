package com.xiaomai.shanghu.tixian.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Yitixian;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.DepositsBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.MaptoJson;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class YiTiXian extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    private List<DepositsBean.DataBean.ListBean> list;
    private Adapter_Yitixian adapter;

    @Override
    protected void initView(View view) {


        getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.yitixian_fragment;
    }

    private void getData() {
        //body传参
        List<String> keylist = new ArrayList<>();
        List<String> valulist = new ArrayList<>();
        keylist.add("moneyMax");
        keylist.add("moneyMin");
        keylist.add("time");
        keylist.add("state");
        valulist.add("");
        valulist.add("");
        valulist.add("");
        valulist.add("1");
        String jsonStr = MaptoJson.toJson("condition", keylist, valulist);
        Log.d("tag", "json----" + jsonStr);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        Subscription subscription = (Subscription) RetrofitClient.getInstance().getApi().getDepositBean(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DepositsBean>() {
                    @Override
                    public void accept(DepositsBean bean) throws Exception {
                        if (bean.getCode() == 1) {
                            list = bean.getData().getList();
                            recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recycler.addItemDecoration(new SpacesItemDecoration(20));

                            adapter = new Adapter_Yitixian(R.layout.tixianzhong_item, list);
                            recycler.setAdapter(adapter);
                            adapter.openLoadAnimation();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                    }
                });


    }

//    private String toJson() {
//        Map<String, String> map1 = new HashMap<>();
//        map1.put("moneyMax", "100");
//        map1.put("moneyMin", "2");
//        map1.put("time", "2011-02-09");
//        map1.put("state", "1");
//
//        Map<String, Map<String, String>> map = new HashMap();
//        map.put("condition", map1);
//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(map);
//        return jsonStr;
//    }
}


