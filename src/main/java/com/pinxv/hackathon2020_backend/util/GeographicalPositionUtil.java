package com.pinxv.hackathon2020_backend.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pinxv.hackathon2020_backend.vo.PositionInfoVO;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class GeographicalPositionUtil {

    /**
     * 获取地理位置详细信息
     * @param locationName 地理位置全称 例：北京市朝阳区阜通东大街6号
     * @return PositionInfoVO对象
     */
    public static PositionInfoVO getLatitudeAndLongitude(String locationName){
        Float longitude;
        Float latitude;
        //完成请求
        try{
            String url = "https://restapi.amap.com/v3/geocode/geo";
            String key = "9fbf6778861a6699c10613e1d0769486";
            String locationInfo = HttpUtil.get(url + "?key=" + key + "&address=" + locationName);

            //处理返回数据
            JSONArray array = JSONUtil.parseObj(locationInfo).getJSONArray("geocodes");
            JSONObject qureydInfoObj = array.getJSONObject(0);
            String[] loc = qureydInfoObj.getStr("location").split(",");
            longitude = Float.parseFloat(loc[0]);
            latitude = Float.parseFloat(loc[1]);
        }
        catch (Exception e){
            return null;
        }
        return new PositionInfoVO(locationName, longitude,latitude);
    }

    public static void main(String[] args) {
        System.out.println(getLatitudeAndLongitude("河北省衡水市"));
    }

}
