package com.gzf.xilv.model;

import com.contrarywind.interfaces.IPickerViewData;

public class DeviceInfo implements IPickerViewData {
    private String name;

    public DeviceInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
