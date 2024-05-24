package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicGym {
  private String gName;
  private String gArea;
  private int gSize;
  private String gAddr;
  private Date gDate;

  public PublicGym() {
  }

  public PublicGym(String gName, String gArea, int gSize, String gAddr, Date gDate) {
    this.gName = gName;
    this.gArea = gArea;
    this.gSize = gSize;
    this.gAddr = gAddr;
    this.gDate = gDate;
  }

  public String getgName() {
    return gName;
  }

  public void setgName(String gName) {
    this.gName = gName;
  }

  public String getgArea() {
    return gArea;
  }

  public void setgArea(String gArea) {
    this.gArea = gArea;
  }

  public int getgSize() {
    return gSize;
  }

  public void setgSize(int gSize) {
    this.gSize = gSize;
  }

  public String getgAddr() {
    return gAddr;
  }

  public void setgAddr(String gAddr) {
    this.gAddr = gAddr;
  }

  public Date getgDate() {
    return gDate;
  }

  public void setgDate(Date gDate) {
    this.gDate = gDate;
  }

  @Override
  public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return String.format("이름 : %s, 시/군 : %s, 면적 : %d, 주소 : %s, 준공일 : %s\n",
        gName, gArea, gSize, gAddr, dateFormat.format(gDate));
  }

}
