package com.xym.myJava.json_pk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JSONReader处理大json
 *
 * @author xym
 * @create 2019-07-22 18:23
 */
public class Main {
    public static void main(String[] args) {
        //createDataFile();
        jsonReader();
    }

    public static void jsonReader() {
        try {
            JSONReader reader = new JSONReader(new FileReader("d://goods.json"));
            reader.startArray();
            long start = System.currentTimeMillis();
            while (reader.hasNext()) {
                reader.startObject();
                Good good = new Good();
                while (reader.hasNext()) {
                    String key = reader.readString();
                    if ("id".equals(key)) {
                        good.setId(reader.readString());
                    } else if ("name".equals(key)) {
                        good.setName(reader.readString());
                    } else if ("price".equals(key)) {
                        good.setPrice(Double.parseDouble(reader.readString()));
                    } else if ("barCode".equals(key)) {
                        good.setBarCode(reader.readString());
                    } else if ("desc".equals(key)) {
                        good.setDesc(reader.readString());
                    } else if ("count".equals(key)) {
                        good.setCount(Integer.parseInt(reader.readString()));
                    } else {
                        reader.readObject();
                    }
                    //System.out.println(good);
                }
                reader.endObject();
            }
            System.out.println(Instant.now().toEpochMilli() - start + " ms ");
            reader.endArray();
            reader.close();
            reader = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDataFile() {
        List<Good> goodList = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            Good good = new Good(System.currentTimeMillis() + "_" + i,
                    new String("booke") + i, 10.f + i,
                    System.currentTimeMillis() + "",
                    "describe book" + i,
                    i);
            goodList.add(good);
        }
        try {
            byte[] json = JSONArray.toJSONBytes(goodList);
            Path write = Files.write(Paths.get("d://goods.json"), json, StandardOpenOption.APPEND);
            System.out.println(write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
