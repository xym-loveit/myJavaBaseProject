package com.xym.myJava.head_first._012;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 图片代理类测试
 *
 * @author xym
 * @create 2019-04-17 11:39
 */
public class ImageProxyTest {
    ImageComponent imageComponent;
    JFrame jFrame = new JFrame("Image Viewer");
    JMenuBar jMenuBar;
    JMenu jMenu;
    Hashtable<String, String> hashtable = new Hashtable();

    public static void main(String[] args) throws Exception {
        ImageProxyTest imageProxyTest = new ImageProxyTest();
    }

    public ImageProxyTest() throws Exception {
        hashtable.put("A-A", "http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        hashtable.put("B-B", "http://pic75.nipic.com/file/20150821/9448607_145742365000_2.jpg");
        hashtable.put("C-C", "http://pic69.nipic.com/file/20150608/9252150_134415115986_2.jpg");
        hashtable.put("D-D", "http://pic41.nipic.com/20140429/12728082_192158998000_2.jpg");
        hashtable.put("E-E", "http://pic49.nipic.com/file/20140923/12106414_110747139072_2.jpg");
        hashtable.put("F-F", "http://pic31.nipic.com/20130720/5793914_122325176000_2.jpg");
        hashtable.put("G-G", "http://pic37.nipic.com/20140113/8800276_184927469000_2.png");
        URL initUrl = new URL(hashtable.get("A-A"));
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("喜欢的图");
        jMenuBar.add(jMenu);
        jFrame.setJMenuBar(jMenuBar);

        Set<Map.Entry<String, String>> entries = hashtable.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            JMenuItem jMenuItem = new JMenuItem(next.getKey());
            jMenu.add(jMenuItem);
            jMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        imageComponent.setIcon(new ImageProxy(new URL(next.getValue())));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    jFrame.repaint();
                }
            });
        }

        Icon icon = new ImageProxy(initUrl);
        imageComponent = new ImageComponent(icon);
        jFrame.getContentPane().add(imageComponent);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setVisible(true);
    }
}
