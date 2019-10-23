package ru.mihassu.mycar.base;

public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
