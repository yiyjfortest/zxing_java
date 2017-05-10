package ean_13;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.Paths;

/**
 * Created by yijea on 2017/5/9 下午6:03.
 * 条形码生成
 */
public class ZxingEAN13EncoderHandler {

    /**
     *
     * @param contents
     * @param width
     * @param height
     * @param imagePath
     */
    public void encode(String contents, int width, int height, String imagePath) {
        int codeWidth = 3 +
                7 * 6 +
                5 +
                7 * 6 +
                3;
        codeWidth = Math.max(codeWidth, width);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, //生成条形码内容长度
                    codeWidth, height, null);
            MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //生成图片路径
        String imagePath = "/Users/yijea/Desktop/2.png";
        //益达口香糖
        String contents = "6923450657713";
        int width = 105, height = 50;
        ZxingEAN13EncoderHandler zxingEAN13EncoderHandler = new ZxingEAN13EncoderHandler();
        zxingEAN13EncoderHandler.encode(contents, width, height, imagePath);
    }

}
