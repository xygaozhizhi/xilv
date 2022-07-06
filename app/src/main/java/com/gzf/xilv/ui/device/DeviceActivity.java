package com.gzf.xilv.ui.device;

import com.gzf.xilv.R;
import com.gzf.xilv.adapter.DeviceBannerAdapter;
import com.gzf.xilv.anim.RotateYTransformer;
import com.gzf.xilv.base.mvp.BaseMvpActivity;
import com.gzf.xilv.databinding.ActivityDeviceBinding;
import com.gzf.xilv.model.LEDInfo;
import com.youth.banner.indicator.CircleIndicator;

public class DeviceActivity extends BaseMvpActivity<DevicePresenter> implements DeviceContract.View {
    private DeviceBannerAdapter bannerAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_device;
    }

    @Override
    protected void initView() {
        ActivityDeviceBinding binding = (ActivityDeviceBinding) dataBinding;
        bannerAdapter = new DeviceBannerAdapter(null);
        binding.banner.setIndicator(new CircleIndicator(this), false);
        binding.banner.setBannerGalleryEffect(50, 12, 0.8f);
        binding.banner.addPageTransformer(new RotateYTransformer());
        binding.banner.setAdapter(bannerAdapter, false);
        binding.banner.setOnBannerListener((data, position) -> {
            LEDInfo ledInfo = (LEDInfo) data;
            if (ledInfo.getType() == LEDInfo.TYPE_LED) {

            } else if (ledInfo.getType() == LEDInfo.TYPE_ADD_NEW) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        bannerAdapter.setDatas(presenter.getLEDInfos());
    }

    @Override
    protected DevicePresenter createPresenter() {
        return new DevicePresenter();
    }
}