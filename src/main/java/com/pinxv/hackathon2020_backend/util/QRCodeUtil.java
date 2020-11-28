package com.pinxv.hackathon2020_backend.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class QRCodeUtil {

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/QRCode/";

    /**
     * @param base64 base64
     * @return 返回图片所在的路径
     */
    public static String base64ToPic(String base64) {
        BASE64Decoder decoder = new BASE64Decoder();
        String filePath = "";
        try {
            String[] sourceSplit = base64.split(",");
            if (sourceSplit.length != 2) {
                return null;
            }
            String type = "";
            if (sourceSplit[0].contains("jpeg")) {
                type = ".jpeg";
            } else if (sourceSplit[0].contains("png")) {
                type = ".png";
            } else {
                return null;
            }
            byte[] decoderBytes = decoder.decodeBuffer(base64.split(",")[1]);
            filePath = BASE_FILE_PATH + System.currentTimeMillis() + (int) (Math.random() * 10000) + type;
            FileOutputStream write = new FileOutputStream(new File(filePath));
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            return null;
        }
        return filePath;
    }

    /**
     * @param base64 base64
     * @return 解码信息
     */
    public static String decodeQRCode(String base64) {
        String filePath = base64ToPic(base64);
        String decode = QrCodeUtil.decode(FileUtil.file(filePath));
        FileUtil.del(new File(filePath));
        return decode;
    }

    /**
     * @param source 要编码的内容
     * @return base64
     */
    public static String encodeQRCode(String source) {
        String filePath = BASE_FILE_PATH + System.currentTimeMillis() + (int) (Math.random() * 10000) + ".jpeg";
        QrCodeUtil.generate(source, 300, 300, FileUtil.file(filePath));
        String base64 = picToBase64(filePath);
        FileUtil.del(new File(filePath));
        return base64;
    }

    public static String picToBase64(String filePath) {
        String picBase = "data:image/png;base64,";
        InputStream inputStream;
        byte[] data;
        try {
            File file;
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return picBase + encoder.encode(data);
    }

}
