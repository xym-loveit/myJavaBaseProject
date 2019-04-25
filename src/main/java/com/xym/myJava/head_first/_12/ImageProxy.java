package com.xym.myJava.head_first._12;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 图标代理类
 *
 * @author xym
 * @create 2019-04-17 11:31
 */
public class ImageProxy implements Icon {

    private URL imageUrl;
    private Thread retrievalThread;
    private boolean retrieving = false;
    private ImageIcon imageIcon;
    private int c = 0;

    public ImageProxy(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        System.out.println("-----------------------" + (++this.c));
        if (imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("图片加载中，请等待！", x + 300, y + 190);
            if (!retrieving) {
                retrieving = true;
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        imageIcon = new ImageIcon(imageUrl, "加载图片");
                        c.repaint();
                    }
                });
                retrievalThread.start();
            }
        }
    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null) {
            return imageIcon.getIconWidth();
        } else {
            return 800;
        }
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        } else {
            return 600;
        }
    }
}
