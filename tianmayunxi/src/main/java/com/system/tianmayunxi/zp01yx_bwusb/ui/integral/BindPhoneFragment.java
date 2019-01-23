package com.system.tianmayunxi.zp01yx_bwusb.ui.integral;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.uilibrary.views.titlebar.TitleBarView;

import butterknife.BindView;

@Route(path = TmyxRouterConfig.LQJF_BINDPHONE)
public class BindPhoneFragment extends MVPBaseFragment {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;

    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bindphone_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        titleBar.setTitleMainText("绑定手机")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
    }
}
