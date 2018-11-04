package com.meiya.nettypackage1;

public interface FetcherCallback {

    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
