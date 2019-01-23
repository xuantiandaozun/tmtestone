package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.BookDetail;
import com.tenma.ventures.GlideApp;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

/**
 * Created by chenxiaoping on 2017/3/28.
 */

public class DzsDetailAdapter extends RecyclerView.Adapter<DzsDetailAdapter.ViewHolder> {

    private Context mContext;
    private List<BookDetail.ImageListBean> image_list;
    private onItemClick clickCb;

    public DzsDetailAdapter(Context c, List<BookDetail.ImageListBean> mdata) {
        mContext = c;
        image_list = mdata;
    }

    public DzsDetailAdapter(Context c, onItemClick cb) {
        mContext = c;
        clickCb = cb;
    }

    public void setOnClickLstn(onItemClick cb) {
        this.clickCb = cb;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.hxdl_layout_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (image_list != null && image_list.size() != 0) {
            String string = TMSharedPUtil.getTMBaseConfig(mContext).getDomain() + image_list.get(position % image_list.size()).getImage();
            GlideApp.with(mContext).asBitmap().load(string).into(new BitmapImageViewTarget(holder.img) {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    holder.img.setImageBitmap(resource);
                }
            });

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "点击了："+position, Toast.LENGTH_SHORT).show();
                if (clickCb != null) {
                    clickCb.clickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return image_list.size();
    }

    public void setNewDatas(List<BookDetail.ImageListBean> mlist) {
        image_list.clear();
        image_list.addAll(mlist);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    interface onItemClick {
        void clickItem(int pos);
    }
}
