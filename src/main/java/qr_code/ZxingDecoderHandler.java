package qr_code;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

/**
 * Created by yijea on 2017/5/9 下午4:58.
 * 二维码识别
 */
public class ZxingDecoderHandler {

    /**
     *
     * @param imagePath
     * @return
     */
    public String decode(String imagePath) {
        BufferedImage image;
        Result result;

        try {
            image = ImageIO.read(new File(imagePath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }

            assert image != null;
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(image);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(bufferedImageLuminanceSource));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

            result = new MultiFormatReader().decode(binaryBitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        //图片路径
        String imgPath = "/Users/yijea/Desktop/1.png";
        ZxingDecoderHandler handler = new ZxingDecoderHandler();
        String decodeContent = handler.decode(imgPath);
        System.out.println("解码内容如下：");
        System.out.println(decodeContent);
    }
}
