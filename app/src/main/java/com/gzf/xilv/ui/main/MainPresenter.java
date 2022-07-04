package com.gzf.xilv.ui.main;

import com.gzf.xilv.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    public List<String> getDevicesInfo() {
        List<String> deviceInfoList = new ArrayList<>();
        deviceInfoList.add("item0");
        deviceInfoList.add("item1");
        deviceInfoList.add("item2");
        deviceInfoList.add("item3");
        deviceInfoList.add("item4");
        deviceInfoList.add("item5");
        return deviceInfoList;
    }
}
