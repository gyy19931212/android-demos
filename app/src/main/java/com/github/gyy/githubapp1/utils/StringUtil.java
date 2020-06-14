package com.github.gyy.githubapp1.utils;

import android.text.TextUtils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 000286 on 2017/2/28. Description
 */
public final class StringUtil {
  /**
   * 字符串是否为空
   *
   * @param s the s
   * @return 为空时true ，不为空时false
   */
  public static boolean isEmpty(String s) {
    return s == null || s.trim().isEmpty();
  }

  /**
   * 字符串是否为空
   *
   * @param sequence the s
   * @return 为空时true ，不为空时false
   */
  public static boolean isEmpty(CharSequence sequence) {
    return sequence == null || sequence.toString().trim().isEmpty();
  }

  /**
   * String to map map.
   *
   * @param s the s
   * @return map
   */
  public static Map<String, String> stringToMap(String s) {
    Map<String, String> map = new HashMap<>();
    if (isEmpty(s)) {
      return map;
    }
    String[] splits = s.split("&");
    for (String split : splits) {
      String[] values = split.split("=");
      if (values.length > 1) {
        map.put(values[0], values[1]);
      } else {
        map.put("", values[0]);
      }
    }
    return map;
  }

  /**
   * 得到string类型的值
   *
   * @param value 参数
   * @return null或 ""则返回"",否则返回字符串本身
   */
  public static String getStringValue(String value) {
    if (isEmpty(value)) {
      return "";
    } else {
      return value;
    }
  }

  /**
   * Double转String,0的时候返回"".
   *
   * @param value 传入的Double值
   * @return 返回的String string value
   */
  public static String getStringValue(Double value) {
    if (value == null) {
      return "";
    } else {
      return String.valueOf(value);
    }
  }

  /**
   * Integer转String
   *
   * @param value 传入的Integer值
   * @return 返回的String string value
   */
  public static String getStringValue(Integer value) {
    if (value == null) {
      return "";
    } else {
      return String.valueOf(value);
    }
  }

  /**
   * BigDecimal转String
   *
   * @param value 传入的BigDecimal值
   * @return 返回的String string value
   */
  public static String getStringValue(BigDecimal value) {
    if (value == null) {
      return "";
    } else {
      return String.valueOf(value);
    }
  }

  /**
   * Long转String
   *
   * @param value 传入的Long值
   * @return 返回的String string value
   */
  public static String getStringValue(Long value) {
    if (value == null) {
      return "";
    } else {
      return String.valueOf(value);
    }
  }

  /**
   * Double转int
   *
   * @param value 传入的Double值
   * @return 返回的int int value
   */
  public static Integer getIntValue(Double value) {
    if (value == null) {
      return 0;
    } else {
      return value.intValue();
    }
  }

  /**
   * String转Double
   *
   * @param value 传入的String值
   * @return 返回的Double double value
   */
  public static Double getDoubleValue(String value) {
    Double reValue = 0.0;
    try {
      if (!isEmpty(value)) {
        reValue = Double.parseDouble(value);
      }
    } catch (Exception e) {
      return reValue;
    }
    return reValue;
  }

  /**
   * String转int
   *
   * @param value 传入的String值
   * @return 返回的int int value
   */
  public static int getIntValue(String value) {
    int reValue = 0;
    try {
      reValue = Integer.parseInt(value);
    } catch (Exception e) {
      return reValue;
    }
    return reValue;
  }

  /**
   *  运单号验证       
   *
   * @param str the str
   * @return the boolean
   * @param  str       
   * @return 验证通过返回true       
   */
  public static boolean isWayBillNo(String str) {
    Pattern p;
    Matcher m;
    boolean b;
    // 验证运单号 
    p = Pattern.compile("^[1-9]{1}[0-9]{11}|^[1-9]{1}[0-9]{14}$");
    m = p.matcher(str);
    b = m.matches();
    return b;
  }

  /**
   *  运单号验证       
   *
   * @param str the str
   * @return the boolean
   * @param  str       
   * @return 验证通过返回true       
   */
  public static boolean isMainWayBillNo(String str) {
    Pattern p;
    Matcher m;
    boolean b;
    // 验证运单号 
    p = Pattern.compile("^[1-9]{1}[0-9]{11}$");
    m = p.matcher(str);
    b = m.matches();
    return b;
  }

  /**
   * 大于5位数的 纯数字
   * @param phoneNumber phoneNumber
   * @return
   */
  public static boolean isPhoneNumber(String phoneNumber) {
    if (StringUtil.isEmpty(phoneNumber)) {
      return false;
    } else {
      return phoneNumber.matches("^\\d+$") && phoneNumber.length() > 5;
    }

  }



  /**
   *  封签号验证       
   *
   * @param str the str
   * @return the boolean
   * @param  str       
   * @return 验证通过返回true       
   */
  public static boolean isSealNo(String str) {
    Pattern p;
    Matcher m;
    boolean b;
    // 验证运单号 
    p = Pattern.compile("^[0-9]{8,10}$");
    m = p.matcher(str);
    b = m.matches();
    return b;
  }


  /**
   *  身份证号验证       
   *
   * @param str the str
   * @return the boolean
   * @param  str       
   * @return 验证通过返回true       
   */
  public static boolean isIDCard(String str) {
    Pattern p1;
    Pattern p2;
    Matcher m1;
    Matcher m2;
    boolean b1;
    boolean b2;
//    p1 = Pattern.compile(
//        "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");// 身份证正则表达式(15位)
    p1 = Pattern.compile("\\d{15}");// 身份证正则表达式(15位)

    m1 = p1.matcher(str);
    b1 = m1.matches();
//    p2 = Pattern.compile(
//        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");// 身份证正则表达式(18位)
    p2 = Pattern.compile("\\d{17}([0-9]|X|x)");// 身份证正则表达式(18位)
    m2 = p2.matcher(str);
    b2 = m2.matches();
    return b1 || b2;
  }

  /**
   * 去掉营业部，分公司,T,Z等特殊字符的字段.
   *
   * @param name 传入的字符串
   * @return 去掉营业部 ，分公司后的字段
   */
  public static String trimSpecialString(String name) {
    String mResult = getStringValue(name);
    if (!"".equals(mResult)) {
      if (mResult.contains("分公司")) {
        mResult = mResult.replaceAll("分公司", "");
      }
      if (mResult.contains("营业部")) {
        mResult = mResult.replaceAll("营业部", "");
      }
      if (mResult.contains("分拨中心")) {
        mResult = mResult.replaceAll("分拨中心", "");
      }
      if (mResult.contains("分拨")) {
        mResult = mResult.replaceAll("分拨", "");
      }
      if (mResult.contains("Z")) {
        mResult = mResult.replaceAll("Z", " ");
      }
      if (mResult.contains("T")) {
        mResult = mResult.replaceAll("T", " ");
      }
    }
    return mResult;
  }

  /**
   * 替换字符串中所有指定的字符
   *
   * @param value 原字符串
   * @param target 被替换的字符串
   * @param replacement 替换的字符串
   * @return 替换后的新字符串，如果字符串为null或者空，将直接返回不处理
   */
  public static String replace(String value, CharSequence target, CharSequence replacement) {
    if (isEmpty(value)) {
      return value;
    }
    return value.replace(target, replacement);
  }

  /**
   * 截取特定的长度.
   *
   * @param string the string
   * @param index 长度
   * @return 截取特定的长度后的字符串 string
   */
  public static String subString(String string, int index) {
    String mResult = string;
    if (string.length() > index) {
      mResult = string.substring(0, index);
    }
    return mResult;
  }

  /**
   * 将字符串组合
   *
   * @param msgs 字符串数组
   * @param symbol 分割符号
   * @return 字符串
   */
  public static String join(String[] msgs, String symbol) {
    if (msgs == null || msgs.length <= 0) {
      return "";
    }
    StringBuffer mBuffer = new StringBuffer();
    for (String msg : msgs) {
      mBuffer.append(msg).append(symbol);
    }
    return mBuffer.substring(0, mBuffer.length() - 1);
  }

  /**
   * 将bigdecimal转换为string
   *
   * @param value bigdecimal 至
   * @return 当bigdecimal为null时返回空字符串
   */
  public static String bigDecimalToString(BigDecimal value) {
    if (value == null) {
      return "";
    }
    return value.toString();
  }

  /**
   * 取出子单号的序列号
   *
   * @param wayBill 子单号
   * @return 返回序列号
   */
  public static String getWaybillIndex(String wayBill) {
    int index;
    if (wayBill == null || wayBill.length() != 15) {
      return "";
    }
    String indexValue = wayBill.substring(12, 15);
    try {
      index = Integer.parseInt(indexValue);
    } catch (Exception e) {
      return "";
    }
    return String.valueOf(index);
  }

  /**
   * 将map数据整理成URL需要的字符串，
   *
   * @param params 参数
   * @return url的字符串key1=value1&key2=value2&……
   */
  public static String bindParamsIfNeed(Map<String, String> params) {
    if (params == null || params.isEmpty()) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    for (Map.Entry entity : params.entrySet()) {
      builder.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
    }
    return builder.deleteCharAt(builder.lastIndexOf("&")).toString();
  }


  /**
   * 判断输入的是否是配载单号
   *
   * @return true-是，false-不是
   */
  public static boolean isCarCodeNo(String result) {
    if (TextUtils.isEmpty(result)){ return false;}
    if (result.length() != 12 || (!result.startsWith("P") && !result.startsWith("p"))) { return false;};
    return true;


  }

  /**
   * 获得数字订单渠道码
   * @param result 源字符
   * @return order channel
   */
  public static String matchFigureWaybillOrderChannelOrNull(String result) {
    Matcher matcher = Pattern.compile("orderChannel=([0-9]+)").matcher(result);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
  }

  /**
   * 获得数字订单Key
   * @param result 源字符
   * @return key
   */
  public static String matchFigureWaybillKeyOrNull(String result) {
    Matcher matcher = Pattern.compile("key=([^&]+)").matcher(result);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
  }

  /**
   * 是否数字订单
   * @param result 源字符串
   * @return 是 or 否
   */
  public static boolean isFigureWaybill(String result) {
    Matcher matcher = Pattern.compile("^http([\\S]+)/figureWaybill?").matcher(result);
    return matcher.find();
  }

  public static String getStowageNo(String result) {
    return "P" + result;
  }

  /**
   *分割后的数组
   * @param string 指定字符串
   * @param tag 指定tag
   * @return 分割后的数组
   */
  public static String[] cutString(String string , String tag) {
    String[] split = string.split(tag);
    return split;
  }

  /**
   * 判断值是否是0， 或者0.0
   * null ," ",返回true
   *(传入的字符串，需可转换为 数字)
   * @return true-是，false-不是
   */
  public static boolean valueIsZone(String result) {
    if (isEmpty(result)) {
      return true;
    }
    BigDecimal value = new BigDecimal(result);
    if (value.compareTo(BigDecimal.ZERO) == 0) {
      return true;
    } else {
      return false;
    }
  }



}
