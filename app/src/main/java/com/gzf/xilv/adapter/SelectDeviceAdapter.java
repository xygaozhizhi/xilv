package com.gzf.xilv.adapter;

import com.contrarywind.adapter.WheelAdapter;

import java.util.List;

public class SelectDeviceAdapter implements WheelAdapter<String> {
    private final List<String> items;

    public SelectDeviceAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    @Override
    public String getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    @Override
    public int indexOf(String o) {
        return 0;
    }

    public List<String> getItems() {
        return items;
    }
}
