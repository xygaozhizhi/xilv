package com.gzf.xilv.ui.device;

import com.gzf.xilv.R;
import com.gzf.xilv.base.mvp.BasePresenter;
import com.gzf.xilv.model.LEDInfo;

import java.util.ArrayList;
import java.util.List;

public class DevicePresenter extends BasePresenter<DeviceContract.View> implements DeviceContract.Presenter {

    public List<LEDInfo> getLEDInfos() {
        List<LEDInfo> ledInfos = new ArrayList<>();
        LEDInfo succulents = new LEDInfo(R.color.teal_200, "水草专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
        LEDInfo coral = new LEDInfo(R.color.teal_700, "珊瑚专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
        LEDInfo aquatic = new LEDInfo(R.color.purple_700, "多肉植物专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
        LEDInfo addNew = new LEDInfo(LEDInfo.TYPE_ADD_NEW);
        ledInfos.add(succulents);
        ledInfos.add(coral);
        ledInfos.add(aquatic);
        ledInfos.add(addNew);
        return ledInfos;
    }

}
