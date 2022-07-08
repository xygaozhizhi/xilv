package com.gzf.xilv.ui.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.contrarywind.view.WheelView;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.HttpUtils;
import com.ejlchina.okhttps.OkHttps;
import com.gzf.xilv.R;
import com.gzf.xilv.adapter.SelectDeviceAdapter;
import com.gzf.xilv.base.mvp.BaseMvpActivity;
import com.gzf.xilv.databinding.ActivityMainBinding;
import com.gzf.xilv.model.DeviceInfo;
import com.gzf.xilv.ui.device.DeviceActivity;
import com.gzf.xilv.ui.personal.PersonalActivity;
import com.gzf.xilv.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ob.bright.gz.oblibrary.OBAddSDK;
import ob.bright.gz.oblibrary.StateChangeListener;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    private ActivityMainBinding binding;
    private DeviceInfo selectDevice;
    private SelectDeviceAdapter selectDeviceAdapter;


    private Config config=new Config();

    private String[] OBOX_Device_Data=new  String[1];
    private String OBOX_Device="";
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
        binding.wheelDevices.setItemsVisibleCount(3);
        binding.wheelDevices.setAlphaGradient(true);
        binding.wheelDevices.setCyclic(false);
    }

    @Override
    protected void initData() {
        super.initData();
        selectDeviceAdapter = new SelectDeviceAdapter(presenter.getDevicesInfo());

        binding.wheelDevices.setAdapter(selectDeviceAdapter);

        OBOX_Device_Data = new String[1];
        selectDevice = selectDeviceAdapter.getItem(0);
        OBOX_Device =" ";


        binding.wheelDevices.setOnItemSelectedListener(index -> {
            selectDevice = selectDeviceAdapter.getItem(index);
            OBOX_Device =OBOX_Device_Data[index];
            //ToastUtil.show(MainActivity.this,selectDevice.getName());
        });




        // 获取定位权限
        if (Build.VERSION.SDK_INT >= 23) {
            int checkAccessFinePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkAccessFinePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Log.e(getPackageName(), "没有权限，请求权限");
                //return;
            }

        }

        login();

        thread.start();

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void onMainClick(View view) {
        if (view == binding.ivRefresh) {
            //TODO 刷新点击处理
            getOboxList();
        } else if (view == binding.btnConnect) {

            Intent intent=new Intent(MainActivity.this,DeviceActivity.class);
            intent.putExtra("OBOX_Device",OBOX_Device);
            startActivity(intent);

            //navigation(DeviceActivity.class);
        } else if (view == binding.btnAddDevice) {
            //TODO 添加设备点击处理
            addobox();
        }else if (view == binding.ivPersonal) {
            navigation(PersonalActivity.class);
        }else if (view == binding.btnDeteteDevice){
            Log.e("TAG", "onMainClick: "+OBOX_Device );

            try {
                JSONObject jsonObject = new JSONObject(OBOX_Device);
                new AlertDialog.Builder(MainActivity.this).setTitle("信息提示")//设置对话框标题

                        .setMessage("是否删除"+jsonObject.getString("oboxName"))
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加确定按钮

                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件，点击事件没写，自己添加
                                OBOX_delete_Device(OBOX_Device);
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮

                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件，点击事件没写，自己添加

                            }

                        }).show();//在按键响应事件中显示此对话框
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    public void login()
    {
        SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        String token = sp1.getString("token", null);

        if (token==null)
        {
            sp1.edit().putString("phone","18834355845").apply();
            sp1.edit().putString("password","123123").apply();
            String phone = sp1.getString("phone", null);
            String password= sp1.getString("password",null);
            HttpUtils.async(config.url_api1+"/api/login")
                    .addUrlPara("code",password)
                    .addUrlPara("phone",phone)
                    .setOnResponse((HttpResult result) -> {
                        String temp = result.getBody().toString();

                        try {
                            JSONObject jsonObject=new JSONObject(temp);
                            if (jsonObject.getInt("code") == 200) {
                                jsonObject=jsonObject.getJSONObject("data");
                                temp = jsonObject.getString("token");
                                sp1.edit().putString("token", temp).apply();
                                Log.e("TAG", "login: "+temp );
                            }
                            else
                            {
                                //验证码错误账号错误

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
        else{
            getOboxList();
        }
    }

    //返回设备列表
    public void getOboxList()
    {
        selectDeviceAdapter = new SelectDeviceAdapter(presenter.getDevicesInfo());
        binding.wheelDevices.setAdapter(selectDeviceAdapter);
        SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
        HttpUtils.async(config.url_api1+"/api/gateway")
                .addHeader("Authorization",sp1.getString("token", null) )
                .setOnResponse((HttpResult result1) -> {
                    String string = result1.getBody().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(string);
                        if (jsonObject.getInt("code")==200)
                        {
                            JSONArray jsonArray =jsonObject.getJSONArray("data");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<DeviceInfo> deviceInfoList = new ArrayList<>();
                                    int num=jsonArray.length();
                                    if (num!=0) {
                                        OBOX_Device_Data = new String[num];
                                        for (int i=0;i<num;i++)
                                        {

                                            try {
                                                deviceInfoList.add(new DeviceInfo(jsonArray.getJSONObject(i).getString("oboxName")));
                                                OBOX_Device_Data[i]=jsonArray.getJSONObject(i).toString();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        selectDeviceAdapter = new SelectDeviceAdapter(deviceInfoList);
                                        binding.wheelDevices.setAdapter(selectDeviceAdapter);

                                        selectDevice = selectDeviceAdapter.getItem(0);

                                        OBOX_Device =OBOX_Device_Data[0];
                                    }




                                }
                            });


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

    public void addobox()
    {

        String timestamp = OBAddSDK.getTimestamp();
        // 生成token  getAuthToken(时间戳，config.OB_APPSecret)
        String token = OBAddSDK.getAuthToken(timestamp , config.OB_APPSecret);
        HTTP http= HTTP.builder().build();
        http.async(config.url_api+"/middleground/registerDeviceToken")
                .addHeader("token",token)
                .addHeader("timestamp",timestamp)
                .addHeader("appId",config.OB_APPID)
                .setOnResponse((HttpResult result)->{
                    String string=null;
                    String temp=result.getBody().toString();
                    try {
                        JSONObject jsonObject = new JSONObject(temp);
                        jsonObject=jsonObject.getJSONObject("result");
                        string=jsonObject.getString("token");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String registerDeviceToken = string;
                    String registerDeviceTokenUrl = config.url_api+"/middleground/registerDeviceByToken?token="+registerDeviceToken;
                    OBAddSDK.getHttpUtils().setOnChangeListener(MainActivity.this,registerDeviceToken,registerDeviceTokenUrl ,new StateChangeListener() {
                        @Override
                        public void StateChange(String oboxninfo, String token, Activity act) {

                            Toast.makeText(MainActivity.this, "配网完成", Toast.LENGTH_SHORT).show();

                            SharedPreferences sp1 = getSharedPreferences("http", MODE_PRIVATE);
                            sp1.edit().putString("obox_data",oboxninfo).apply();


                            // 配网SDK工作已经完成， 第三方通过用token刷接口， 轮询设备令牌配网结果，/middleground/queryRegisterDeviceStateByToken， 获取配网的状态
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(act, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            },4000);





                        }
                    });

                    //String token = result.getString("token");
                }).setOnException((IOException e)->{
                    Log.e("baidu", "onCreate:"+e.toString());
                    //网络错误
                })
                .get();

    }
    public Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {

            SharedPreferences sp2 = getSharedPreferences("login", MODE_PRIVATE);

            try {
                while(true)
                {
                    SharedPreferences sp1 = getSharedPreferences("http", MODE_PRIVATE);
                    String box_data= sp1.getString("obox_data",null);
                    if (box_data!=null)
                    {
                        JSONObject jsonObject=new JSONObject(box_data);
                        JSONObject jsonObject1=new JSONObject();
                        jsonObject1.put("id","");
                        jsonObject1.put("isOnline",0);
                        jsonObject1.put("oboxName",jsonObject.getString("oboxName"));
                        jsonObject1.put("oboxSerialId",jsonObject.getString("oboxSerialId"));
                        jsonObject1.put("oboxVersion",jsonObject.getString("oboxVersion"));
                        Log.e("TAG", "run: "+jsonObject1.toString() );
                        HttpUtils.async("https://lsjyy0527.com:7877/api/gateway")
                                .bodyType(OkHttps.JSON)
                                .addHeader("Authorization",sp2.getString("token", null) )
                                .setBodyPara(jsonObject1.toString())
                                .setOnResponse((HttpResult result)->{
                                    Log.e("TAG", "run: "+result.getBody().toString() );

                                    sp1.edit().clear().commit();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    getOboxList();

                                })
                                .setOnException((IOException e)->{
                                    Log.e("baidu", "onCreate:"+e.toString());
                                    //网络错误
                                })
                                .post();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    });
    private void OBOX_delete_Device(String str)
    {
        try {
            SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
            JSONObject jsonObject =new JSONObject(str);
            String str_ID=jsonObject.getString("oboxSerialId");
            Log.e("TAG", "OBOX_delete_Device: "+str_ID );
            HttpUtils.async(config.url_api1+"/api")
                    .bodyType(OkHttps.JSON)
                    .addUrlPara("oboxSerialId",str_ID)
                    .addHeader("Authorization",sp1.getString("token", null) )
                    .setOnResponse((HttpResult result1)->{
                        Log.e("r", "OBOX_delete_Device: "+result1.getBody().toString() );
                        getOboxList();
                    }).setOnException((IOException e)->{

                    }).delete();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}