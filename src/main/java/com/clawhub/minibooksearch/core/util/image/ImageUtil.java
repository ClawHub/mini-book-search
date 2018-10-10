package com.clawhub.minibooksearch.core.util.image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * <Description> 图片工具<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/28 <br>
 */
public class ImageUtil {
    /**
     * Byte 2 image.
     *
     * @param data the data
     * @param path the path
     * @throws IOException the io exception
     */
    public static void byte2image(byte[] data, String path) throws IOException {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }

    /**
     * /**
     * 更改图片像素
     * 按图片的原比例进行修改
     *
     * @param bytes     源图片字节
     * @param desPath   修改大小后图片路径
     * @param scaleSize 图片的修改比例，目标宽度
     * @return ImageBean
     * @throws IOException the io exception
     */
    public static ImageBean resizeImage(byte[] bytes, String desPath, int scaleSize) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
        float width = bi.getWidth(); // 像素
        float height = bi.getHeight(); // 像素
        float scale = width / scaleSize;
        BufferedImage buffImg = new BufferedImage(scaleSize, (int) (height / scale), BufferedImage.TYPE_INT_RGB);
        //使用TYPE_INT_RGB修改的图片会变色
        buffImg.getGraphics().drawImage(
                bi.getScaledInstance(scaleSize, (int) (height / scale), Image.SCALE_SMOOTH), 0, 0, null);
//        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(desPath));
        ImageIO.write(buffImg, "JPEG", new File(desPath));
//        imageOutput.close();
        return new ImageBean(width, height, desPath);
    }
}
