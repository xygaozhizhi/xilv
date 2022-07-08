package com.gzf.xilv.ui.device;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.HttpUtils;
import com.ejlchina.okhttps.OkHttps;
import com.gzf.xilv.R;
import com.gzf.xilv.adapter.DeviceBannerAdapter;
import com.gzf.xilv.anim.RotateYTransformer;
import com.gzf.xilv.base.mvp.BaseMvpActivity;
import com.gzf.xilv.databinding.ActivityDeviceBinding;
import com.gzf.xilv.model.LEDInfo;
import com.gzf.xilv.ui.main.Config;
import com.gzf.xilv.ui.main.MainActivity;
import com.youth.banner.indicator.CircleIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends BaseMvpActivity<DevicePresenter> implements DeviceContract.View {
    private DeviceBannerAdapter bannerAdapter;
    private Config config=new Config();
    private String oboxSerialId="";
    JSONArray scene_arr=new JSONArray();

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
                //进入场景
            } else if (ledInfo.getType() == LEDInfo.TYPE_ADD_NEW) {
                //添加新场景
                add_Scene();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        bannerAdapter.setDatas(presenter.getLEDInfos());


        get_scene_list();

        String value=getIntent().getStringExtra("OBOX_Device");
        Log.e("TAG", "initData: "+value );

    }

    @Override
    protected DevicePresenter createPresenter() {
        return new DevicePresenter();
    }
    protected void get_scene_list()
    {
        SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        HttpUtils.async(config.url_api1+"/api/scene")
                .addHeader("Authorization",sp1.getString("token", null) )
                .addUrlPara("status",1)
                .setOnResponse((HttpResult result1) -> {
                    String str=result1.getBody().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(str);
                        if (jsonObject.getInt("code")==200) {

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            SharedPreferences sp = getSharedPreferences("scene", MODE_PRIVATE);//保存场景数据
                            sp1.edit().putString("scene_list",jsonArray.toString()).apply();
                            scene_arr=jsonArray;

                            if (jsonArray.length()>3) {
                                List<LEDInfo> ledInfos = new ArrayList<>();
                                LEDInfo succulents = new LEDInfo(R.color.teal_200, "水草专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
                                LEDInfo coral = new LEDInfo(R.color.teal_700, "珊瑚专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
                                LEDInfo aquatic = new LEDInfo(R.color.purple_700, "多肉植物专用", "Spectrum for coral", "20小时", "620mm", "96%", "16.5", "26.5", "1000");
                                ledInfos.add(succulents);
                                ledInfos.add(coral);
                                ledInfos.add(aquatic);

                                for (int i = 0; i < jsonArray.length()-3; i++) {
                                    Log.e("baidu", "123:" + jsonArray.getJSONObject(i).getJSONArray("scene").getJSONObject(0).getString("sceneName"));
                                    LEDInfo custom = new LEDInfo(R.color.purple_700,
                                            jsonArray.getJSONObject(i).getJSONArray("scene").getJSONObject(0).getString("sceneName"),
                                            " ", " ", " ", " ", " ", "  ", " ");
                                    ledInfos.add(custom);
                                }

                                LEDInfo addNew = new LEDInfo(LEDInfo.TYPE_ADD_NEW);
                                ledInfos.add(addNew);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        bannerAdapter.setDatas(ledInfos);
                                    }
                                });

                                //bannerAdapter.setDatas(ledInfos);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                })
                .setOnException((IOException e) -> {
                    Log.e("baidu", "onCreate:" + e.toString());
                    //网络错误

                })
                .get();
    }
    public void add_Scene()
    {
        String string="[{\"sceneName\":\"自定义\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":0,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":80,\"groupNum\":1,\"isAll\":true,\"red\":100,\"blue\":80,\"white\":80}}]},{\"sceneName\":\"123\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":1,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":10,\"groupNum\":1,\"isAll\":true,\"red\":30,\"blue\":80,\"white\":20}}]},{\"sceneName\":\"自定义\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":2,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":80,\"groupNum\":1,\"isAll\":true,\"red\":0,\"blue\":20,\"white\":80}}]},{\"sceneName\":\"自定义\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":3,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":30,\"groupNum\":1,\"isAll\":true,\"red\":80,\"blue\":0,\"white\":50}}]},{\"sceneName\":\"自定义\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":4,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":60,\"groupNum\":1,\"isAll\":true,\"red\":90,\"blue\":40,\"white\":10}}]},{\"sceneName\":\"自定义\",\"sceneType\":\"cloud\",\"conditions\":[[{\"condJson\":{\"minute\":5,\"hour\":12,\"periods\":{\"saturday\":true,\"thursday\":true,\"sunday\":true,\"tuesday\":true,\"friday\":true,\"wednesday\":true,\"monday\":true}},\"conditionType\":\"simpleTime\"}]],\"actions\":[{\"actionTarget\":\"\",\"actionType\":\"controlOboxDevice\",\"actionParamJson\":{\"green\":0,\"groupNum\":1,\"isAll\":true,\"red\":0,\"blue\":0,\"white\":0}}]}]";
        SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        HttpUtils.async(config.url_api1+"/api/scene")
                .bodyType(OkHttps.JSON)
                .setBodyPara(string)
                .addHeader("Authorization",sp1.getString("token", null) )
                .setOnResponse((HttpResult result1) -> {
                    Log.e("baidu", "onCreate:" + result1.getBody().toString());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    get_scene_list();
                })
                .setOnException((IOException e) -> {
                    Log.e("baidu", "onCreate:" + e.toString());
                    //网络错误

                })
                .post();


        Log.e("123", "add_Scene: "+string);

    }
}
