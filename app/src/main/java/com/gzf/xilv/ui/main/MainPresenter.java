package com.gzf.xilv.ui.main;

import com.gzf.xilv.base.mvp.BasePresenter;
import com.gzf.xilv.model.DeviceInfo;

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
    public List<DeviceInfo> getDevicesInfo2() {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        deviceInfoList.add(new DeviceInfo("item0"));
        deviceInfoList.add(new DeviceInfo("item1"));
        deviceInfoList.add(new DeviceInfo("item2"));
        deviceInfoList.add(new DeviceInfo("item3"));
        deviceInfoList.add(new DeviceInfo("item4"));
        deviceInfoList.add(new DeviceInfo("item5"));
        return deviceInfoList;
    }
}
