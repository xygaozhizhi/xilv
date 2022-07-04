package com.gzf.xilv.base.mvp;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V view;

    public BasePresenter() {
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public V getView() {
        return view;
    }
}
