package com.xym.myJava.head_first._012;

import javax.swing.*;
import java.awt.*;

/**
 * 图片组件
 *
 * @author xym
 * @create 2019-04-17 11:26
 */
public class ImageComponent extends JComponent {
    private Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        int x = (800 - width) / 2;
        int y = (600 - height) / 2;
        icon.paintIcon(this, graphics, x, y);
    }
}
