package com.androiddesdecero.jwtudemy.domain.base;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue, E extends UseCase.ErrorValue> void notifyResponse(final V response,
                                                                                        final UseCase.UseCaseCallback<V, E> useCaseCallback);

    <V extends UseCase.ResponseValue, E extends UseCase.ErrorValue> void onError(final E errorResponse,
                                                                                 final UseCase.UseCaseCallback<V, E> useCaseCallback);
}
