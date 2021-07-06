package net.sympower.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuoteValue {

  public String tLabel;
  public String value;

  @JsonIgnore
  public int getValueAsInt() {
    return Integer.parseInt(value);
  }

  @JsonIgnore
  public double getValueAsDouble() {
    return Double.parseDouble(value);
  }

  @Override
  public String toString() {
    return "QuoteValue{" +
      "tLabel='" + tLabel + '\'' +
      ", value=" + value +
      '}';
  }

}
