package com.gzf.xilv.base.mvp;

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    V getView();
}
