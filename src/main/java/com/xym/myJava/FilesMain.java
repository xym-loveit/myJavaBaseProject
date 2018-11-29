package com.xym.myJava;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-28 18:07
 */
public class FilesMain {
    public static void main(String[] args) {

        //遍历一个层级目录
        Path path3 = Paths.get("d:/");
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path3);
            for (Path path : paths) {
                System.out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<Path> paths = new LinkedList<>();
        //递归遍历
        try {
            Files.walkFileTree(Paths.get("d:\\workspace"), new FindJavaVisitor(paths));
            System.out.println(paths.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;

        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (Files.isReadable(file) && file.toString().endsWith(".java")) {
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private static void test1() {
        {
            Path path = Paths.get("abc.txt");
            //表示检测时不包含符号链接文件。
            System.out.println(Files.exists(path, LinkOption.NOFOLLOW_LINKS));
            Path path1 = Paths.get("def.txt");
            if (!Files.exists(path1)) {
                //创建文件
                try {
                    Files.createFile(path1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Path directory = null;
            try {
                Path path2 = Paths.get("d:/a/b/c/d");
                if (Files.notExists(path2)) {
                    //创建一级目录
                    directory = Files.createDirectory(path2);
                }
                System.out.println(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                //创建多级目录
                Path directories = Files.createDirectories(Paths.get("d:/a/b/c/d1"));
                System.out.println(directories);
                System.out.println(Files.deleteIfExists(directories));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //复制文件
            Path source = null;
            try {
                source = Paths.get(".\\src\\main\\java\\com\\xym\\myJava\\FilesMain.java").toRealPath();
                Path desc = Paths.get("d:/FilesMain_copy.java");
                Path copy = Files.copy(source, desc);
                //强制覆盖已经存在的目标文件
                Path copyReplace = Files.copy(source, desc, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(copy);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取文件属性
            try {
                System.out.println(Files.getLastModifiedTime(source));
                System.out.println(Files.size(source));
                System.out.println(Files.isSymbolicLink(source));
                System.out.println(Files.isDirectory(source));
                System.out.println(Files.readAttributes(source, "*"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
