package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.CanNotCancellableStateException;

public enum State {
    CREATED {
        @Override
        void checkCancellable() {
        }
    },
    SHIPPING,
    COMPLETED,
    CANCELED;

    void checkCancellable() {
        throw new CanNotCancellableStateException("이미 취소 혹은 취소 불가");
    }
}
