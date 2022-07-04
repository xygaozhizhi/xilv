package com.gzf.xilv.base.mvp;

import com.gzf.xilv.base.BaseActivity;

@SuppressWarnings("rawtypes")
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView {
    public P presenter;

    @Override
    protected void initData() {
        presenter = createPresenter();
        if (presenter != null) {
            //noinspection unchecked
            presenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }
}
