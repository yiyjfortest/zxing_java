package ean_13;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by yijea on 2017/5/10 上午10:43.
 * 条形码解码
 */
public class ZxingEAN13DecoderHandler {

    /**
     *
     * @param imagePath 图片路径
     * @return
     */
    public String decode(String imagePath) {

        BufferedImage bufferedImage;
        Result result;

        try {
            bufferedImage = ImageIO.read(new File(imagePath));
            if (bufferedImage == null) {
                System.out.println("the decode image may be not exit.");
            }

            assert bufferedImage != null;
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args) {
        //图片路径
        String imagePath = "/Users/yijea/Desktop/2.png";
        ZxingEAN13DecoderHandler handler = new ZxingEAN13DecoderHandler();
        String decodeContent = handler.decode(imagePath);
        System.out.println("解码内容如下：");
        System.out.println(decodeContent);
    }

}
