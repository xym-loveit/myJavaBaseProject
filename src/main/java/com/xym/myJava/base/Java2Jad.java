package com.xym.myJava.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-07 23:39
 */
public class Java2Jad {

    public enum FileType {
        TXT("txt", "文本文档"),
        JAVA("java", "java源文件"),
        SQL("sql", "sql文件"),
        AVI("avi", "视频文件"),
        PDF("pdf", "pdf文件");

        private String suffix;
        private String name;

        FileType(String suffix, String name) {
            this.suffix = suffix;
            this.name = name;
        }

        public String getSuffix() {
            return suffix;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        copyFile(FileType.PDF.getSuffix(), "D:\\soft", "d:/pdfs/", "jad");
    }

    private static void copyFile(String fileType, String pathname, String pathname1, String suffix) {
        File file = new File(pathname);
        String[] list = file.list((d, n) -> n.endsWith(fileType));
        File parentDir = new File(pathname1);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        Arrays.stream(list).map(s -> new File(file, s)).forEach(f -> {
            try (FileOutputStream out = new FileOutputStream(new File(parentDir, f.getName().replace(fileType, suffix)))) {
                Files.copy(Paths.get(f.toURI()), out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
