package com.gzf.xilv.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gzf.xilv.R;
import com.gzf.xilv.model.DeviceInfo;

import java.util.List;

public class SelectDeviceAdapter2 extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {


    public SelectDeviceAdapter2(@Nullable List<DeviceInfo> data) {
        super(R.layout.item_device, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DeviceInfo deviceInfo) {
        baseViewHolder.setText(R.id.tv_device, deviceInfo.getName());
    }
}
