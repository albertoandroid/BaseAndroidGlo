package com.androiddesdecero.jwtudemy.domain.base;

public class UseCaseHandler {

    private static UseCaseHandler INSTANCE;

    private final UseCaseScheduler mUseCaseScheduler;

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public <Input extends UseCase.RequestValues, Output extends UseCase.ResponseValue, ErrorOutput extends UseCase.ErrorValue> void execute(
            final UseCase<Input, Output, ErrorOutput> useCase) {

        if (useCase.getRequestValues() != null){

            useCase.setUseCaseCallback(new UiCallbackWrapper<>(useCase.getUseCaseCallback(), this));

            mUseCaseScheduler.execute(useCase::run);

        } else {
            throw new NullPointerException(useCase.getClass().getCanonicalName() + ": GetProfesorUseCase.RequestValues cannot be NULL!");
        }


    }

    public <Output extends UseCase.ResponseValue, ErrorOutput extends UseCase.ErrorValue> void notifyResponse(
            final Output response,
            final UseCase.UseCaseCallback<Output, ErrorOutput> useCaseCallback) {

        mUseCaseScheduler.notifyResponse(response, useCaseCallback);

    }

    private <Output extends UseCase.ResponseValue, ErrorOutput extends UseCase.ErrorValue> void notifyError(
            final ErrorOutput errorResponse,
            final UseCase.UseCaseCallback<Output, ErrorOutput> useCaseCallback) {

        mUseCaseScheduler.onError(errorResponse, useCaseCallback);

    }

    private static final class UiCallbackWrapper<Output extends UseCase.ResponseValue, ErrorOutput extends UseCase.ErrorValue> implements
            UseCase.UseCaseCallback<Output, ErrorOutput> {

        private final UseCase.UseCaseCallback<Output, ErrorOutput> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UiCallbackWrapper(UseCase.UseCaseCallback<Output, ErrorOutput> callback,
                                 UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(Output response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError(ErrorOutput errorResponse) {
            mUseCaseHandler.notifyError(errorResponse, mCallback);
        }

    }
}
