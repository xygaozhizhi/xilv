package com.gzf.xilv.model;

public class LEDInfo {
    private int imageRes;
    private String name;
    private String englishName;
    private String irradiationTime;
    private String wavelengthPeak;
    private String purity;
    private String spRatio;
    private String gai;
    private String ppft;

    public LEDInfo(int imageRes, String name, String englishName, String irradiationTime, String wavelengthPeak, String purity, String spRatio, String gai, String ppft) {
        this.imageRes = imageRes;
        this.name = name;
        this.englishName = englishName;
        this.irradiationTime = irradiationTime;
        this.wavelengthPeak = wavelengthPeak;
        this.purity = purity;
        this.spRatio = spRatio;
        this.gai = gai;
        this.ppft = ppft;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getIrradiationTime() {
        return irradiationTime;
    }

    public void setIrradiationTime(String irradiationTime) {
        this.irradiationTime = irradiationTime;
    }

    public String getWavelengthPeak() {
        return wavelengthPeak;
    }

    public void setWavelengthPeak(String wavelengthPeak) {
        this.wavelengthPeak = wavelengthPeak;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getSpRatio() {
        return spRatio;
    }

    public void setSpRatio(String spRatio) {
        this.spRatio = spRatio;
    }

    public String getGai() {
        return gai;
    }

    public void setGai(String gai) {
        this.gai = gai;
    }

    public String getPpft() {
        return ppft;
    }

    public void setPpft(String ppft) {
        this.ppft = ppft;
    }
}
