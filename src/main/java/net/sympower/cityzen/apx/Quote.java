package net.sympower.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Quote {

  public String market;

  @JsonProperty("date_applied")
  public long dateApplied;

  @JsonProperty("values")
  public List<QuoteValue> quoteValues;

  @JsonIgnore
  public LocalDate getDate(ZoneId timeZone) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateApplied), timeZone).toLocalDate();
  }

  @Override
  public String toString() {
    return "Quote{" +
      "market='" + market + '\'' +
      ", dateApplied='" + dateApplied + '\'' +
      ", quoteValues=" + quoteValues +
      '}';
  }

}
