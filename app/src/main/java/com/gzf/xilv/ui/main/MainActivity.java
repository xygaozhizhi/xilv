package com.gzf.xilv.ui.main;

import android.view.View;

import com.contrarywind.view.WheelView;
import com.gzf.xilv.R;
import com.gzf.xilv.adapter.SelectDeviceAdapter;
import com.gzf.xilv.base.mvp.BaseMvpActivity;
import com.gzf.xilv.databinding.ActivityMainBinding;
import com.gzf.xilv.utils.ToastUtil;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    private ActivityMainBinding binding;
    private String selectDevice;
    private SelectDeviceAdapter selectDeviceAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        binding = (ActivityMainBinding) dataBinding;
        binding.wheelDevices.setDividerColor(getColor(R.color.white));
        binding.wheelDevices.setDividerType(WheelView.DividerType.WRAP);
        binding.wheelDevices.setTextColorCenter(getColor(R.color.color_5C64CE));
        binding.wheelDevices.setTextColorOut(getColor(R.color.color_88CCCCCC));
        binding.wheelDevices.setCyclic(false);
    }

    @Override
    protected void initData() {
        super.initData();
        selectDeviceAdapter = new SelectDeviceAdapter(presenter.getDevicesInfo());
        binding.wheelDevices.setAdapter(selectDeviceAdapter);
        binding.wheelDevices.setOnItemSelectedListener(index -> {
            selectDevice = selectDeviceAdapter.getItem(index);
            ToastUtil.show(selectDevice);
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void onMainClick(View view) {
        if (view == binding.ivRefresh) {
            //TODO 刷新点击处理
        } else if (view == binding.btnConnect) {
            //TODO 连接点击处理
        } else if (view == binding.btnAddDevice) {
            //TODO 添加设备点击处理
        }
    }
}