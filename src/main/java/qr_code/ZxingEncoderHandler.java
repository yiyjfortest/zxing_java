package qr_code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Created by yijea on 2017/5/9 下午2:55.
 * 二维码生成
 */
public class ZxingEncoderHandler {

    /**
     *
     * @param contents
     * @param width
     * @param height
     * @param imagePath
     */
    public void encode(String contents, int width, int height, String imagePath) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //生成图片路径
        String imagePath = "/Users/yijea/Desktop/1.png";
        String contents = "Jea so cute";
        int width = 300, height = 300;
        ZxingEncoderHandler zxingEncoderHandler = new ZxingEncoderHandler();
        zxingEncoderHandler.encode(contents, width, height, imagePath);
//        System.getProperties().list(System.out);
//        System.out.println(System.getProperty("file.separator"));
    }
}
