package com.xiaomai.shanghu.vipconfig;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_AddVip;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.AddVipBean;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.SwipeItemLayout;
import com.xiaomai.shanghu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class VipConfigActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.btn_add_vip)
    Button btn_add_vip;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.pullToRefresh)
    PullToRefreshLayout pullToRefresh;

    private List<AddVipBean.DataBean.ListBean> list;
    private Adapter_AddVip adapter;

    private SharedPreferences usertoken;
    private String token;
    private int pageIndex = 1;
    final private int pageSize =10;
    private int total,limit;
    @Override
    public int getLayoutId() {
        return R.layout.activity_vip_config;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        getPageWhiteList(1,pageSize);

        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        list = new ArrayList<>();

        adapter=new Adapter_AddVip(R.layout.item_vip,list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.empty_view, null, false);
        recycler.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        recycler.setNestedScrollingEnabled(false);
        adapter.setEmptyView(inflate);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseadapter, View view, int position) {
                getDeviceFreezeDetail(list.get(position).getId()+"");
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();

        pullToRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                list.clear();
                getPageWhiteList(1,pageSize);
                pageIndex =1;
            }

            @Override
            public void loadMore() {
                pageIndex++;
                getPageWhiteList(pageIndex,pageSize);
            }
        });
    }


    @OnClick({R.id.back, R.id.btn_add_vip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_add_vip://添加VIP
                if(total < limit){
                    toClass(this,AddVipActivity.class);
                }else if(total > limit){
                    ToastUtil.showShortToast("您最多只能添加"+limit+"个VIP");
                }
                break;
        }
    }


    /***
     * 分页获取白名单列表
     * */
    public void getPageWhiteList(int pageIndex ,int pageSize){

        Condition condition = new Condition("");
        Info info = new Info(pageIndex+"",pageSize+"",condition);
        Gson gson = new Gson();
        String strJson = gson.toJson(info);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getPageWhiteList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<AddVipBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<AddVipBean>() {
                               @Override
                               public void accept(AddVipBean addVipBean) throws Exception {
                                   if(addVipBean.getCode()==1){
                                       pullToRefresh.finishRefresh();
                                       pullToRefresh.finishLoadMore();
                                       for (int i = 0; i < addVipBean.getData().getList().size(); i++) {
                                           list.add(addVipBean.getData().getList().get(i));
                                       }
                                   }
                                   total = addVipBean.getData().getTotal();
                                   limit = addVipBean.getData().getLimit();
                                   adapter.notifyDataSetChanged();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   pullToRefresh.finishRefresh();
                                   pullToRefresh.finishLoadMore();
                                   Log.d("tag", "请求错误");
                               }
                           }
                );
    }

    /**
     * 删除VIP
     * */
    private void getDeviceFreezeDetail(final String id) {
        RetrofitClient.getInstance().getApi(token).delete(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
                        if(bean.getCode()==1){
                            ToastUtil.showShortToast(""+bean.getMsg());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        getPageWhiteList(1,pageSize);
    }
}
