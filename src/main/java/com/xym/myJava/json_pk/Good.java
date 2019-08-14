package com.xym.myJava.json_pk;

public class Good {
  private String id;
  private String name;
  private double price;
  private String barCode;
  private String desc;
  private int count;
  public Good() {
  }
  public Good(String id, String name, double price, String barCode, String desc, int count) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.barCode = barCode;
      this.desc = desc;
      this.count = count;
  }
  public String getId() {
      return id;
  }
  public void setId(String id) {
      this.id = id;
  }
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public double getPrice() {
      return price;
  }
  public void setPrice(double price) {
      this.price = price;
  }
  public String getBarCode() {
      return barCode;
  }
  public void setBarCode(String barCode) {
      this.barCode = barCode;
  }
  public String getDesc() {
      return desc;
  }
  public void setDesc(String desc) {
      this.desc = desc;
  }
  public int getCount() {
      return count;
  }
  public void setCount(int count) {
      this.count = count;
  }
  @Override
  public String toString() {
      return "Good{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", price=" + price +
              ", barCode='" + barCode + '\'' +
              ", desc='" + desc + '\'' +
              ", count=" + count +
              '}';
  }
}