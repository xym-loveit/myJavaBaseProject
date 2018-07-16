package com.xym.myJava.lock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * 防止改程序重复启动
 *
 * @author xym
 * @create 2018-07-16 23:24
 */
public class PreventDuplicated {

    public static final String LOCK_PATH = "d:/locks/";
    public static final String LOCK_FILE = ".lock";
    public static final String PERMISSIONS = "rw-------";

    public static void main(String[] args) {

        /*注入钩子，在程序退出时删除lock文件*/
        Runtime.getRuntime().addShutdownHook(new Thread("clear") {
            @Override
            public void run() {
                System.out.println("the program received kill signal");
                getLockFile().toFile().delete();
            }
        });

        /*检查是否存在.lock文件*/
        checkRunning();

        //简单模拟当前程序正在运行
        for (; ; ) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("program is running");
        }

    }

    private static void checkRunning() {
        Path lockFile = getLockFile();
        if (lockFile.toFile().exists()) {
            throw new RuntimeException("the program already running!");
        }

        //Set<PosixFilePermission> posixFilePermissions = PosixFilePermissions.fromString(PERMISSIONS);
        try {
            Files.createFile(lockFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
