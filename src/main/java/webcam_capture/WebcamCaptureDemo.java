package webcam_capture;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by yijea on 2017/5/10 上午11:17.
 * 摄像头调用测试类
 */
public class WebcamCaptureDemo {

    private static int    num    = 0;

    public static void main(String[] args) throws IOException
    {
        final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        final JFrame window = new JFrame("摄像头");
        window.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e)
            {
                webcam.close();
                window.dispose();
            }
        });
        final JButton button = new JButton("截图");
        window.add(panel, BorderLayout.CENTER);
        window.add(button, BorderLayout.SOUTH);
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
        button.addActionListener(e -> {
            button.setEnabled(false);
            String fileName = "/Users/yijea/Desktop/" + num;
            WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "截图成功");
                button.setEnabled(true);
                num++;
            });
        });
    }

}
