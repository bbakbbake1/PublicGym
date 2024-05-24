package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.PublicGym;

public class APIUtill {
  public static ArrayList<PublicGym> getToilets(String pIndex) {
    if (pIndex.equals("0")) {
      pIndex = "1";
    }
    ArrayList<PublicGym> list = new ArrayList<>();
    String filePath = "key.properties";
    StringBuilder urlBuilder = new StringBuilder(
        "https://openapi.gg.go.kr/Publtolt");
    try {
      Properties properties = new Properties();
      properties.load(new FileReader(filePath));
      String key = properties.getProperty("key");
      urlBuilder.append("?" + URLEncoder.encode("KEY=", "UTF-8")
          + key);
      urlBuilder.append("&" + URLEncoder.encode("Type=", "UTF-8")
          + URLEncoder.encode("XML", "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("pIndex=", "UTF-8")
          + URLEncoder.encode(pIndex, "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("pSize=", "UTF-8")
          + URLEncoder.encode("300", "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    URL url = null;
    HttpURLConnection conn = null;
    try {
      url = new URL(urlBuilder.toString());
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/xml");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedReader br = null;
    try {
      int statusCode = conn.getResponseCode();
      if (statusCode >= 200 && statusCode <= 300) {
        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      Document doc = parseXML(conn.getInputStream());
      NodeList descNodes = doc.getElementsByTagName("row");
      for (int i = 0; i < descNodes.getLength(); i++) {
        Node item = descNodes.item(i);
        PublicGym PublicGym = new PublicGym();
        for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
          switch (node.getNodeName()) {
            case "FACLT_NM":
              PublicGym.setgName(node.getTextContent());
              break;
            case "POSESN_INST_NM":
              PublicGym.setgArea(node.getTextContent());
              break;
            case "TOT_AR":
              PublicGym.setgSize(Integer.parseInt(node.getTextContent()));
              break;
            case "REFINE_LOTNO_ADDR>":
              PublicGym.setgAddr(node.getTextContent());
              break;
            case "COMPLTN_YY":
              try {
                String data = node.getTextContent();
                if (!data.isEmpty()) {
                  PublicGym.setgDate(new SimpleDateFormat("yyyy-MM-dd").parse(data));
                }
              } catch (DOMException | ParseException e) {
                e.printStackTrace();
              }
              break;
          }
        }
        list.add(PublicGym);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  public static Document parseXML(InputStream inputStream) {
    DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder objDocumentBuilder = null;
    Document doc = null;
    try {
      objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
      doc = objDocumentBuilder.parse(inputStream);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }
}
