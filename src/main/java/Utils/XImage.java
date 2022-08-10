/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/images/fpt.png");
        return new ImageIcon(url).getImage();
    }

    /**
     * Sao chép file logo chuyên đề vào thư mục logo
     *
     * @param src là đối tượng file ảnh
     */
    public static void save(File src) {
        File dst = new File("images", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
