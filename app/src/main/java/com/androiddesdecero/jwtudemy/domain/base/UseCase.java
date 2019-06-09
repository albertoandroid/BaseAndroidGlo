package com.androiddesdecero.jwtudemy.domain.base;

import com.androiddesdecero.jwtudemy.domain.model.DomainError;

/* Use cases are the entry points to the domain layer.
*
* @param <Input> the request type
* @param <Output> the response type
* @param <ErrorOutput> the error response type
*/
@SuppressWarnings({ "CanBeFinal", "UnusedReturnValue", "WeakerAccess"})
public abstract class UseCase <Input extends UseCase.RequestValues, Output extends UseCase.ResponseValue, ErrorOutput extends UseCase.ErrorValue> {

    public interface UseCaseCallback<Output, ErrorOutput> {

        void onSuccess(Output response);

        void onError(ErrorOutput errorResponse);

    }

    /**
     * Data passed to a request.
     */
    public interface RequestValues { }

    /**
     * Data received from a request.
     */
    public interface ResponseValue { }


    public static class ErrorValue {

        private DomainError errorData;

        public ErrorValue(DomainError errorData) {
            this.errorData = errorData;
        }

        public DomainError getErrorData() {
            return errorData;
        }

    }

    private Input requestValues;

    private UseCaseCallback<Output, ErrorOutput> useCaseCallback;

    public Input getRequestValues() {
        return requestValues;
    }

    public UseCaseCallback<Output, ErrorOutput> getUseCaseCallback() {
        return useCaseCallback;
    }

    public UseCase<Input, Output, ErrorOutput> setRequestValues(Input requestValues) {
        this.requestValues = requestValues;
        return this;
    }

    public UseCase<Input, Output, ErrorOutput> setUseCaseCallback(UseCaseCallback<Output, ErrorOutput> useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
        return this;
    }

    void run() {
        executeUseCase(requestValues);
    }

    protected abstract void executeUseCase(Input requestValues);
}

