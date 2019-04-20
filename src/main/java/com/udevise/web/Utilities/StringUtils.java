package com.udevise.web.Utilities;

public class StringUtils {

  public static char TAB = '\t';
  public static char NEWLINE = '\n';

  public static String replaceIfNull(String originalStr, String replaceStr){
    if (originalStr == null){
      return replaceStr;
    }
    return originalStr;
  }

  public static String replaceIfNull(String originalStr, char replaceStr){
    return replaceIfNull(originalStr,Character.toString(replaceStr));
  }
}
