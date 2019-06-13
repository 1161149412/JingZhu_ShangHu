package com.xiaomai.yyshanghu.indexfragment.storepage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.indexfragment.storepage.method.ExpandableHelpMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WANG on 17/12/8.
 */

public abstract class ExpandableBaseAdapter<T> extends RecyclerView.Adapter<ExpandableBaseAdapter.ExpandableViewHolder> implements ExpandableHelpMethod<T> {

   protected List<T> baseData;

    public ExpandableBaseAdapter() {
        if(baseData == null){
            baseData = new ArrayList<>();
        }
    }

    @Override
    public void setRefreshData(List<T> data) {
        baseData.clear();
        baseData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void setLoadMoveData(List<T> data) {
        baseData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void insertDataByPosition(int insertPosition, List<T> dataBean) {
        baseData.addAll(insertPosition,dataBean);
        for (int i = 0; i < dataBean.size() ; i++) {
            notifyItemInserted(insertPosition+i);
        }
        // notifyItemRangeChanged(position,dataBean.size());
    }

    @Override
    public void removeDataByPosition(int insertPosition, List<T> dataBean) {
        for (int i = 0; i <dataBean.size() ; i++) {
            T dataBean1 = baseData.get(insertPosition);
            if(dataBean1 != null) {
                baseData.remove(insertPosition);
                notifyItemRemoved(insertPosition);
            }
        }
    }


    abstract ExpandableViewHolder baseOnCreateViewHolder(ViewGroup parent, int viewType);

    abstract void baseOnBindViewHolder(ExpandableViewHolder holder, int position);


    abstract int baseGetItemCount();

    @Override
    public ExpandableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return baseOnCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(ExpandableViewHolder holder, int position) {
        baseOnBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return baseGetItemCount();
    }


    public static class ExpandableViewHolder extends RecyclerView.ViewHolder {
        public TextView item_tv_id;
        public TextView item_item_device_id ,item_item_yesterday_order,item_item_total_order,item_item_type;
        public ImageView item_img_switch;
        public ExpandableViewHolder(View itemView , int ViewType) {
            super(itemView);
            if(ViewType ==1 ){
                //TODO  控件初始化
                item_item_device_id = itemView.findViewById(R.id.item_item_device_id);
                item_item_yesterday_order = itemView.findViewById(R.id.item_item_yesterday_order);
                item_item_total_order = itemView.findViewById(R.id.item_item_total_order);
                item_item_type = itemView.findViewById(R.id.item_item_type);
            }else {
                item_tv_id = itemView.findViewById(R.id.item_tv_id);
                item_img_switch = itemView.findViewById(R.id.item_img_switch);
            }
        }

    }
}
