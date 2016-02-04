package com.chen.network;

import java.io.Serializable;

public class WeatherBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String city; // 城市
	String cityid;// 城市id
	int temp;// 温度
	String WD;// 风向
	String WS;// 风等级
	String SD; // 湿度
	int WSE;
	String time;
	int isRadar;
	String Radar;
	String njd;
	String qy;

	@Override
	public String toString() {
		return "[city=" + this.city + ",cityid=" + this.cityid + ",temp="
				+ this.temp + ",WD=" + this.WD + ",WS=" + this.WS + ",SD="
				+ this.SD + ",WSE=" + this.WSE + ",time=" + this.time
				+ ",isRadar" + this.isRadar + ",Radar=" + this.Radar + ",njd="
				+ this.njd + ",qy=" + this.qy + "]";
	}
}
