package com.gzf.xilv.ui.main;

import com.gzf.xilv.base.mvp.BasePresenter;
import com.gzf.xilv.model.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public List<DeviceInfo> getDevicesInfo() {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        deviceInfoList.add(new DeviceInfo("    "));
        return deviceInfoList;
    }
}
