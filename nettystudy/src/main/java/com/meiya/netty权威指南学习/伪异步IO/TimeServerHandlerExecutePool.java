package com.meiya.netty权威指南学习.伪异步IO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

    private ExecutorService executorService;

    public TimeServerHandlerExecutePool(int maxPoolSize, int dequeLength) {

        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(dequeLength));

    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
