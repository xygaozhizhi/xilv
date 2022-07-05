package com.gzf.xilv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzf.xilv.R;
import com.gzf.xilv.model.LEDInfo;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class DeviceBannerAdapter extends BannerAdapter<LEDInfo, RecyclerView.ViewHolder> {

    public DeviceBannerAdapter(List<LEDInfo> datas) {
        super(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LEDInfo.TYPE_LED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_led, parent, false);
                return new LEDViewHolder(view);
            case LEDInfo.TYPE_ADD_NEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_add_new, parent, false);
                return new AddNewViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        switch (getData(position).getType()) {
            case 0:
                type = LEDInfo.TYPE_LED;
                break;
            case 1:
                type = LEDInfo.TYPE_ADD_NEW;
                break;
        }
        return type;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, LEDInfo data, int position, int size) {
        if (data.getType() == LEDInfo.TYPE_LED) {
            ((LEDViewHolder) holder).ivBackground.setImageResource(data.getImageRes());
            ((LEDViewHolder) holder).tvName.setText(data.getName());
            ((LEDViewHolder) holder).tvEnglishName.setText(data.getEnglishName());
            ((LEDViewHolder) holder).tvIrradiationTime.setText(data.getIrradiationTime());
            ((LEDViewHolder) holder).tvWavelengthPeak.setText(data.getWavelengthPeak());
            ((LEDViewHolder) holder).tvPurity.setText(data.getPurity());
            ((LEDViewHolder) holder).tvSpRatio.setText(data.getSpRatio());
            ((LEDViewHolder) holder).tvGAI.setText(data.getGai());
            ((LEDViewHolder) holder).tvPPFT.setText(data.getPpft());
        } else if (data.getType() == LEDInfo.TYPE_ADD_NEW) {
            ((AddNewViewHolder) holder).ivAddNew.setImageResource(R.mipmap.ic_add);
        }
    }

    static class LEDViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivBackground;
        public TextView tvName;
        public TextView tvEnglishName;
        public TextView tvIrradiationTime;
        public TextView tvWavelengthPeak;
        public TextView tvPurity;
        public TextView tvSpRatio;
        public TextView tvGAI;
        public TextView tvPPFT;

        public LEDViewHolder(@NonNull View itemView) {
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

    static class AddNewViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivAddNew;

        public AddNewViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAddNew = itemView.findViewById(R.id.iv_add_new);
        }
    }
}
