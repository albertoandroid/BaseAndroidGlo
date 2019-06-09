package com.androiddesdecero.jwtudemy.domain.base;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also  Executor for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    private final Handler mHandler = new Handler();

    public static final int POOL_SIZE = 2;

    public static final int MAX_POOL_SIZE = 4;

    public static final int TIMEOUT = 30;

    ThreadPoolExecutor threadPoolExecutor;

    public UseCaseThreadPoolScheduler() {
        threadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(POOL_SIZE));
    }

    @Override
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue, E extends UseCase.ErrorValue> void notifyResponse(V response, UseCase.UseCaseCallback<V, E> useCaseCallback) {

        mHandler.post(() -> useCaseCallback.onSuccess(response));

    }

    @Override
    public <V extends UseCase.ResponseValue, E extends UseCase.ErrorValue> void onError(E errorResponse, UseCase.UseCaseCallback<V, E> useCaseCallback) {

        mHandler.post(() -> useCaseCallback.onError(errorResponse));

    }

}


