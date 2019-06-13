package com.xiaomai.yyshanghu.indexfragment.storepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.indexfragment.storepage.DataBean;
import com.xiaomai.yyshanghu.indexfragment.storepage.method.MeOnItemClickListener;


/**
 * Created by WANG on 17/12/5.
 */

public class ExpandableAdapter extends ExpandableBaseAdapter<DataBean>{
    private Context context;
    private LayoutInflater inflater;
    private MeOnItemClickListener meOnitemClickListener;
    public ExpandableAdapter(Context context) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void setListener(Object o) {
        MeOnItemClickListener lister = (MeOnItemClickListener) o;
        this.meOnitemClickListener = lister;
    }

    @Override
    public int getItemViewType(int position) {
        if(baseData.size() == 0) {
            return super.getItemViewType(position);
        }else {
            boolean isHavaChild = baseData.get(position).isHavaChild();
            if(!isHavaChild){
                //子列表
                return 1;
            }else {
                //父列表
                return 2;
            }
        }
    }

    @Override
    ExpandableViewHolder baseOnCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate;
        if(viewType == 1){
            inflate = inflater.inflate(R.layout.item_item_store, parent, false);
        }else {
            inflate = inflater.inflate(R.layout.item_store, parent, false);
        }
        return new ExpandableViewHolder(inflate,viewType);
    }

    @Override
    void baseOnBindViewHolder(ExpandableViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        final DataBean dataBean = baseData.get(position);
        if(itemViewType == 1){
            final DataBean.ChildBean childBean = dataBean.getChildBean();
            if(childBean == null){
                return;
            }
            String title = childBean.getTitle();
            holder.item_item_device_id.setText(title);
            holder.item_item_yesterday_order.setText(childBean.getYesterday());
            holder.item_item_total_order.setText(childBean.getAll());
            holder.item_item_type.setText(childBean.getType());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(meOnitemClickListener != null){
                        meOnitemClickListener.onChildItemClick(v,childBean.getPodition(),dataBean);
                    }
                }
            });
        }else {
            String title = dataBean.getTitle();
            holder.item_tv_id.setText(title);
            holder.item_img_switch.setBackgroundResource(R.mipmap.down_sign);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(meOnitemClickListener != null){
                        meOnitemClickListener.onParentItemClick(v,position,dataBean);
                    }
                }
            });
        }
    }

    @Override
    int baseGetItemCount() {
        return baseData.size();
    }

}
