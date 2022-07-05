package com.gzf.xilv.adapter;

import com.contrarywind.adapter.WheelAdapter;
import com.gzf.xilv.model.DeviceInfo;

import java.util.List;

public class SelectDeviceAdapter implements WheelAdapter<DeviceInfo> {
    private final List<DeviceInfo> items;

    public SelectDeviceAdapter(List<DeviceInfo> items) {
        this.items = items;
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    @Override
    public DeviceInfo getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    @Override
    public int indexOf(DeviceInfo o) {
        return 0;
    }

    public List<DeviceInfo> getItems() {
        return items;
    }
}
