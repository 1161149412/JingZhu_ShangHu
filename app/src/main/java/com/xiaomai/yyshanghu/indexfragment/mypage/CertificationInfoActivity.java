package com.xiaomai.yyshanghu.indexfragment.mypage;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.utils.bottompopfragmentmenu.BottomMenuFragment;
import com.xiaomai.yyshanghu.utils.bottompopfragmentmenu.MenuItem;
import com.xiaomai.yyshanghu.utils.bottompopfragmentmenu.MenuItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertificationInfoActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_main_boby_name)
    TextView tv_main_boby_name;
    @BindView(R.id.tv_category)
    TextView tv_category;
    @BindView(R.id.linear_layout_main_boby_category)
    LinearLayout linear_layout_main_boby_category;
    @BindView(R.id.tv_id_card_certification)
    TextView tv_id_card_certification;
    @BindView(R.id.linear_layout_id_card)
    LinearLayout linear_layout_id_card;
    @BindView(R.id.tv_open_license)
    TextView tv_open_license;
    @BindView(R.id.linear_layout_open_license)
    LinearLayout linear_layout_open_license;
    @BindView(R.id.linear_layout_my_contract)
    LinearLayout linear_layout_my_contract;
    @BindView(R.id.btn_submit_review)
    Button btn_submit_review;

    @Override
    public int getLayoutId() {
        return R.layout.activity_certification_info;
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.back, R.id.tv_main_boby_name, R.id.tv_category, R.id.linear_layout_main_boby_category, R.id.tv_id_card_certification, R.id.linear_layout_id_card, R.id.tv_open_license, R.id.linear_layout_open_license, R.id.linear_layout_my_contract, R.id.btn_submit_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_main_boby_name:
                break;
            case R.id.tv_category:
                break;
            case R.id.linear_layout_main_boby_category:
                BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();

                List<MenuItem> menuItemList = new ArrayList<MenuItem>();
                MenuItem menuItem1 = new MenuItem();
                menuItem1.setText("企业");
                menuItem1.setStyle(MenuItem.MenuItemStyle.COMMON);
                MenuItem menuItem2 = new MenuItem();
                menuItem2.setText("个人");
                menuItem2.setStyle(MenuItem.MenuItemStyle.COMMON);
                menuItem1.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem1) {
                    @Override
                    public void onClickMenuItem(View v, MenuItem menuItem) {
                        Log.i("", "onClickMenuItem: "+menuItem.getText().toString());
                        tv_category.setText(menuItem.getText().toString());
                    }
                });
                menuItem2.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem2) {
                    @Override
                    public void onClickMenuItem(View v, MenuItem menuItem) {
                        Log.i("", "onClickMenuItem: "+menuItem.getText().toString());
                        tv_category.setText(menuItem.getText().toString());
                    }
                });
                menuItemList.add(menuItem1);
                menuItemList.add(menuItem2);
                bottomMenuFragment.setMenuItems(menuItemList);
                bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");

                break;
            case R.id.tv_id_card_certification:
                break;
            case R.id.linear_layout_id_card://身份证认证
                toClass(CertificationInfoActivity.this,IDCardProofActivity.class);
                break;
            case R.id.tv_open_license:
                break;
            case R.id.linear_layout_open_license://营业执照
                toClass(CertificationInfoActivity.this,OpenLicenseActivity.class);
                break;
            case R.id.linear_layout_my_contract://我的合同
                break;
            case R.id.btn_submit_review:
                break;
        }
    }

}
