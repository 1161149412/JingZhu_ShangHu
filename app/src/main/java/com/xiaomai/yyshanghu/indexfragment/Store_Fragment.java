package com.xiaomai.yyshanghu.indexfragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.adapter.Adapter_Store;
import com.xiaomai.yyshanghu.base.BaseFragment;
import com.xiaomai.yyshanghu.bean.GetPageSellerBillBean;
import com.xiaomai.yyshanghu.indexfragment.storepage.DataBean;
import com.xiaomai.yyshanghu.indexfragment.storepage.TreeItemAnimator;
import com.xiaomai.yyshanghu.indexfragment.storepage.activity.StoreDetailsActivity;
import com.xiaomai.yyshanghu.indexfragment.storepage.adapter.ExpandableAdapter;
import com.xiaomai.yyshanghu.indexfragment.storepage.method.MeOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Store_Fragment extends BaseFragment {
    @BindView(R.id.tv_tixian_money)
    TextView tvTixianMoney;
    @BindView(R.id.tv_tixian_dongjie)
    TextView tvTixianDongjie;
    @BindView(R.id.recycler)
    RecyclerView recycler;

//    private List<String> list;
    private Adapter_Store adapter;
    private List<GetPageSellerBillBean.DataBean.ListBean> bean;

    private ExpandableAdapter expandableAdapter;
    private List<String> list;
    private List<DataBean> lists;

    @Override
    protected void initView(View view) {
        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        bean = new ArrayList<>();
        list = new ArrayList<>();

        list.add("测试2");
        list.add("测试2");
        list.add("测试3");
        list.add("测试4");
        list.add("测试5");

        adapter = new Adapter_Store(R.layout.item_store, list);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toClass(getContext(), StoreDetailsActivity.class);
            }
        });

//        list = new ArrayList<>();
//        initRecyclerView();
//        initData();
//        initListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecyclerView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        TreeItemAnimator defaultItemAnimator = new TreeItemAnimator();
        defaultItemAnimator.setMoveDuration(100);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(250);
        animator.setMoveDuration(120);
        // recyclerView.setItemAnimator(defaultItemAnimator);
        recycler.setItemAnimator(animator);
        expandableAdapter = new ExpandableAdapter(getContext());
        recycler.setAdapter(expandableAdapter);
    }

    private void initData() {
        List<DataBean> childBeanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DataBean dataBean = new DataBean("ParentItem" + i, false, false, false);
            dataBean.setChildBean(new DataBean.ChildBean("JZCB019100028"+i,"￥520.6"+i,"￥156.5"+i,"在线"+i,i));
            childBeanList.add(dataBean);
        }
        for (int i = 0; i < 5; i++) {
            DataBean dataBean = new DataBean("ParentItem" + i, false, true, false);
            dataBean.setDataBeanList(childBeanList);
            lists.add(dataBean);
        }
        expandableAdapter.setRefreshData(lists);

    }

    private void initListener() {
        expandableAdapter.setListener(new MeOnItemClickListener() {
            @Override
            public void onParentItemClick(View v, int position, DataBean dataBean) {
                boolean isExpandable = dataBean.getIsExpandable();
                int childAdapterPosition = recycler.getChildAdapterPosition(v);
                boolean havaInsertChild = dataBean.isHavaInsertChild();
//                Toast.makeText(MainActivity.this, "Position  =  " + childAdapterPosition, Toast.LENGTH_SHORT).show();

                v.findViewById(R.id.item_linear).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"点击", Toast.LENGTH_SHORT).show();
                    }
                });
                if (isExpandable) {
                    //点击收起
                    dataBean.setExpandable(false);
                    if (havaInsertChild) {
                        dataBean.setHavaInsertChild(false);
                        List<DataBean> dataBeanList = dataBean.getDataBeanList();
                        expandableAdapter.removeDataByPosition(childAdapterPosition + 1,dataBeanList);
                        v.findViewById(R.id.item_linear).setVisibility(View.GONE);
                        v.findViewById(R.id.view_line).setVisibility(View.GONE);
                        v.findViewById(R.id.item_img_switch).setBackgroundResource(R.mipmap.down_sign);
                    }
                } else {
                    if (!havaInsertChild) {
                        dataBean.setExpandable(true);
                        dataBean.setHavaInsertChild(true);
                        List<DataBean> dataBeanList = dataBean.getDataBeanList();
                        //点击展开
                        expandableAdapter.insertDataByPosition(childAdapterPosition + 1, dataBeanList);
                        v.findViewById(R.id.item_linear).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.view_line).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.item_img_switch).setBackgroundResource(R.mipmap.up_sign);
                    }
                }
            }

            @Override
            public void onChildItemClick(View v, int position, DataBean dataBean) {
//                Toast.makeText(MainActivity.this, "Child Position " + position, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
