package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.mysubscription.adapter.MySubAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.TieIvAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.TieZiBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class ThemeDetailAdapter extends BaseQuickAdapter<TieZiBean, BaseViewHolder> {
    public ThemeDetailAdapter(@Nullable List<TieZiBean> data) {
        super(R.layout.fragment_themedetail_item_zp01yx_bwusb2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TieZiBean item) {

        RecyclerView mgrid=helper.getView(R.id.mlist);
        mgrid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    OnItemClickListener onItemClickListener = getOnItemClickListener();
                    onItemClickListener.onItemClick(ThemeDetailAdapter.this,null,helper.getLayoutPosition());
                }

                return false;
            }
        });
        mgrid.setLayoutManager(new GridLayoutManager(mContext,3));
    //    mgrid.addItemDecoration(new com.library.flowlayout.SpaceItemDecoration(UEMethod.dp2px(mContext,5)));
        TieIvAdapter adapter = new TieIvAdapter(item.getImage());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OnItemChildClickListener onItemChildClickListener = getOnItemChildClickListener();
                int layoutPosition = helper.getLayoutPosition();
                onItemChildClickListener.onItemChildClick(ThemeDetailAdapter.this,view,layoutPosition);
            }
        });
        mgrid.setAdapter(adapter);
        SimpleDraweeView iv_head=helper.getView(R.id.iv_head);
        helper.addOnClickListener(R.id.mlist);
        helper.addOnClickListener(R.id.tv_share);
        helper.addOnClickListener(R.id.tv_up);

        iv_head.setImageURI(item.getTheme_image());
        SimpleDraweeView user_head=helper.getView(R.id.user_head);

        String head_pic = item.getHead_pic();
        if(!TextUtils.isEmpty(head_pic)){
            if(!head_pic.contains("http")){
                String domain = TMSharedPUtil.getTMBaseConfig(mContext).getDomain();

                head_pic=domain+head_pic;
            }
            user_head.setImageURI(head_pic);
        }else {
            user_head.setBackgroundResource(R.mipmap.default_head);
        }

        if(!TextUtils.isEmpty(item.getTheme_title())){
            helper.setText(R.id.tv_theme,"#"+item.getTheme_title()+"#");
        }
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        if(!TextUtils.isEmpty(item.getCreate_at())){
            helper.setText(R.id.tv_time,item.getCreate_at());
        }
        if(!TextUtils.isEmpty(item.getContent())){
            helper.setText(R.id.tv_content,item.getContent());
        }
        if(!TextUtils.isEmpty(item.getMember_nickname())){
            helper.setText(R.id.tv_username,item.getMember_nickname());
        }
        if(!TextUtils.isEmpty(item.getLike_num()+"")){
            helper.setText(R.id.tv_up,"("+item.getLike_num()+")");
        }
        if(!TextUtils.isEmpty(item.getMsg_num()+"")){
            helper.setText(R.id.tv_comments,"("+item.getMsg_num()+")");
        }

    }

}
