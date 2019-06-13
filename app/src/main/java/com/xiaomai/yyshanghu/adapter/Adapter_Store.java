package com.xiaomai.yyshanghu.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.App;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.indexfragment.storepage.activity.StoreDetailsActivity;
import com.xiaomai.yyshanghu.utils.HiddenAnimUtils;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Store extends BaseQuickAdapter<String,BaseViewHolder> {
//    public Adapter_Store(int layoutResId, @Nullable List<GetPageSellerBillBean.DataBean.ListBean> data) {
//        super(layoutResId, data);
//    }

    private List<String> moreList = new ArrayList<>();
    private Adapter_Item adapter_item;

    public Adapter_Store(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        moreList.add("JZCB01542154998");
        moreList.add("JZCB01542154998");
        moreList.add("JZCB01542154998");
        moreList.add("JZCB01542154998");
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tv_title,""+item.getBillMonth()+"账单");//标题
//        helper.setText(R.id.tv_account_period,"账期:"+item.getBillDate());//账期
//        helper.setText(R.id.item_zhangdan_allmonet,item.getTotalPrice());//订单总额
//        helper.setText(R.id.tv_total_income,item.getSellerEarn()+"");//总收益
//        helper.setText(R.id.tv_out_bill,"出账日期:"+item.getCreateTime());//出账日期
        helper.setText(R.id.item_tv_id,item);//出账日期

        RecyclerView recyclerView = helper.getView(R.id.item_recycler);
        LinearLayout layout = helper.getView(R.id.item_linear);
        //recycler 展开
        recyclerView.setLayoutManager(new LinearLayoutManager(App.content, LinearLayoutManager.VERTICAL, false));
        adapter_item = new Adapter_Item(R.layout.item_item_store, moreList);
        recyclerView.setAdapter(adapter_item);
        adapter_item.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                view.getContext().startActivity( new Intent(view.getContext(),StoreDetailsActivity.class));
            }
        });

        LinearLayout linearLayout = helper.getView(R.id.linear_layout);
        ImageView iv = helper.getView(R.id.item_img_switch);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HiddenAnimUtils.newInstance(view.getContext(), recyclerView, iv, (24 * moreList.size())+10).toggle();
                HiddenAnimUtils.newInstance(view.getContext(), layout, iv, 40).toggle();
            }
        });



    }
}
