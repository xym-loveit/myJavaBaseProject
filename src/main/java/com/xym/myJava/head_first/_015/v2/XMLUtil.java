package com.xym.myJava.head_first._015.v2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-23 16:32
 */
public class XMLUtil {

    /**
     * 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
     *
     * @return
     */
    public static Object getBean(String path) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document parse = documentBuilder.parse(new File(path));
            NodeList className = parse.getElementsByTagName("className");
            Node firstChild = className.item(0).getFirstChild();
            String cName = firstChild.getNodeValue();
            Class<?> aClass = Class.forName(cName);
            return aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
