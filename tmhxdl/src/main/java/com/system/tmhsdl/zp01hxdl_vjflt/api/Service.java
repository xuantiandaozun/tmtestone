package com.system.tmhsdl.zp01hxdl_vjflt.api;


import com.system.tmhsdl.zp01hxdl_vjflt.bean.TMBaseResoultEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {
    /**
     *图片上传接口
     * @return
     */
    @Multipart
    @POST("zp01yx_bwusb/api/imgUpload")
    Observable<TMBaseResoultEntity<Object>> uploadFile(@Part List<MultipartBody.Part> partList);
}
