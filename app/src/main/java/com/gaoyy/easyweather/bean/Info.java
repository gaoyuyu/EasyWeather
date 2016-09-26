package com.gaoyy.easyweather.bean;

import java.util.List;

/**
 * Created by gaoyy on 2016/9/22 0022.
 */
public class Info
{
    private List<String> chuanyi;
    private List<String> ganmao;
    private List<String> kongtiao;
    private List<String> wuran;
    private List<String> xiche;
    private List<String> yundong;
    private List<String> ziwaixian;

    public List<String> getChuanyi()
    {
        return chuanyi;
    }

    public void setChuanyi(List<String> chuanyi)
    {
        this.chuanyi = chuanyi;
    }

    public List<String> getGanmao()
    {
        return ganmao;
    }

    public void setGanmao(List<String> ganmao)
    {
        this.ganmao = ganmao;
    }

    public List<String> getKongtiao()
    {
        return kongtiao;
    }

    public void setKongtiao(List<String> kongtiao)
    {
        this.kongtiao = kongtiao;
    }

    public List<String> getWuran()
    {
        return wuran;
    }

    public void setWuran(List<String> wuran)
    {
        this.wuran = wuran;
    }

    public List<String> getXiche()
    {
        return xiche;
    }

    public void setXiche(List<String> xiche)
    {
        this.xiche = xiche;
    }

    public List<String> getYundong()
    {
        return yundong;
    }

    public void setYundong(List<String> yundong)
    {
        this.yundong = yundong;
    }

    public List<String> getZiwaixian()
    {
        return ziwaixian;
    }

    public void setZiwaixian(List<String> ziwaixian)
    {
        this.ziwaixian = ziwaixian;
    }

    @Override
    public String toString()
    {
        return "Info{" +
                "chuanyi=" + chuanyi.toString() +
                ", ganmao=" + ganmao.toString() +
                ", kongtiao=" + kongtiao.toString() +
                ", wuran=" + wuran.toString() +
                ", xiche=" + xiche.toString() +
                ", yundong=" + yundong.toString() +
                ", ziwaixian=" + ziwaixian.toString() +
                '}';
    }
}
