package com.gzf.xilv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzf.xilv.R;
import com.gzf.xilv.model.DeviceInfo;
import com.gzf.xilv.model.LEDInfo;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class DeviceBannerAdapter extends BannerAdapter<LEDInfo, DeviceBannerAdapter.DeviceViewHolder> {
    public DeviceBannerAdapter(List<LEDInfo> datas) {
        super(datas);
    }

    @Override
    public DeviceViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindView(DeviceViewHolder holder, LEDInfo data, int position, int size) {
        holder.ivBackground.setImageResource(data.getImageRes());
        holder.tvName.setText(data.getName());
        holder.tvEnglishName.setText(data.getEnglishName());
        holder.tvIrradiationTime.setText(data.getIrradiationTime());
        holder.tvWavelengthPeak.setText(data.getWavelengthPeak());
        holder.tvPurity.setText(data.getPurity());
        holder.tvSpRatio.setText(data.getSpRatio());
        holder.tvGAI.setText(data.getGai());
        holder.tvPPFT.setText(data.getPpft());
    }

    static class DeviceViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivBackground;
        public TextView tvName;
        public TextView tvEnglishName;
        public TextView tvIrradiationTime;
        public TextView tvWavelengthPeak;
        public TextView tvPurity;
        public TextView tvSpRatio;
        public TextView tvGAI;
        public TextView tvPPFT;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBackground = itemView.findViewById(R.id.iv_background);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEnglishName = itemView.findViewById(R.id.tv_english_name);
            tvIrradiationTime = itemView.findViewById(R.id.tv_irradiation_time);
            tvWavelengthPeak = itemView.findViewById(R.id.tv_wavelength_peak);
            tvPurity = itemView.findViewById(R.id.tv_purity);
            tvSpRatio = itemView.findViewById(R.id.tv_sp_ratio);
            tvGAI = itemView.findViewById(R.id.tv_gai);
            tvPPFT = itemView.findViewById(R.id.tv_ppft);
        }
    }
}
