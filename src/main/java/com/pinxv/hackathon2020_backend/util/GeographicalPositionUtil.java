package com.pinxv.hackathon2020_backend.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pinxv.hackathon2020_backend.vo.PositionInfoVO;

import java.text.DecimalFormat;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class GeographicalPositionUtil {

    private static double EARTH_RADIUS = 6378.137;

    /**
     * 获取地理位置详细信息
     *
     * @param locationName 地理位置全称 例：北京市朝阳区阜通东大街6号
     * @return PositionInfoVO对象
     */
    public static PositionInfoVO getPositionInfo(String locationName) {
        Float longitude;
        Float latitude;
        String adCode;
        //完成请求
        try {
            String url = "https://restapi.amap.com/v3/geocode/geo";
            String key = "9fbf6778861a6699c10613e1d0769486";
            String locationInfo = HttpUtil.get(url + "?key=" + key + "&address=" + locationName);

            //处理返回数据
            JSONArray array = JSONUtil.parseObj(locationInfo).getJSONArray("geocodes");
            JSONObject queryInfoObj = array.getJSONObject(0);
            String[] loc = queryInfoObj.getStr("location").split(",");
            longitude = Float.parseFloat(loc[0]);
            latitude = Float.parseFloat(loc[1]);
            adCode = queryInfoObj.getStr("adcode");
        } catch (Exception e) {
            return new PositionInfoVO(locationName, null, null, null);
        }
        return new PositionInfoVO(locationName, longitude, latitude, adCode);
    }

    public static double getDistance(String location1, String location2) {
        PositionInfoVO pos1 = getPositionInfo(location1);
        PositionInfoVO pos2 = getPositionInfo(location2);
        double distance = Double.NaN;
        try{
            distance = getDistance(pos1.getLatitude(), pos1.getLongitude(), pos2.getLatitude(), pos2.getLongitude());
        }
        catch (Exception e){

        }
        return distance;
    }


    /**
     * 计算两个经纬度之间的距离
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return km
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        DecimalFormat df = new DecimalFormat("#.000");
        s = Double.parseDouble(df.format(s));
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
