package com.system.tmhsdl.zp01hxdl_vjflt.api.base;



import com.system.myproject.utils.klog.KLog;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by zbmobi on 2016/9/29.
 */

public class BaseOKHttpInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    public static final MediaType MEDIA_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        // 获得Connection，内部有route、socket、handshake、protocol方法
        Connection connection = chain.connection();
        // 如果Connection为null，返回HTTP_1_1，否则返回connection.protocol()
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        // 比如: --> POST http://121.40.227.8:8088/api http/1.1
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;

        KLog.json("requestStartMessage = " + requestStartMessage);

        // 打印 Response
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodyValue = "NONE";
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        if (contentLength != 0) {
            // 获取Response的body的字符串 并打印
            bodyValue =bodyValue.replaceAll("\\s*", "");// URLDecoder.decode(buffer.clone().readString(UTF8), "UTF-8");

        }
        KLog.json("response = " + bodyValue);
        Response newResponse = response.newBuilder()
                .body(ResponseBody.create(MEDIA_TYPE, bodyValue))
                .build();
        return newResponse;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}