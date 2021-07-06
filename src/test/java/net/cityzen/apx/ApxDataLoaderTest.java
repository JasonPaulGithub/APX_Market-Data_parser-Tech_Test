package net.cityzen.apx;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ApxDataLoaderTest {

  private static final ZoneId TIME_ZONE = ZoneId.of("Europe/Amsterdam");

  @Test
  public void load() throws Exception {
    ApxDataLoader sut = new ApxDataLoader();
    sut.url = getClass().getResource("apx-data.json");
    QuoteResponse response = sut.load();

    assertNotNull(response);
    assertNotNull(response.quote);

    Iterator<Quote> quoteIterator = response.quote.iterator();

    assertTrue(quoteIterator.hasNext());
    Quote quote1 = quoteIterator.next();
    assertEquals("APX Power NL Hourly", quote1.market);
    assertEquals(1498514400000L, quote1.dateApplied);
    assertEquals(
      LocalDate.of(2017, 6, 27),
      quote1.getDate(TIME_ZONE));

    Iterator<QuoteValue> quoteValueIterator1 = quote1.quoteValues.iterator();
    assertQuoteValue(quoteValueIterator1, "Order", "1", 1);
    assertQuoteValue(quoteValueIterator1, "Hour", "01", 1);
    assertQuoteValue(quoteValueIterator1, "Net Volume", "3916.10", 3916.10);
    assertQuoteValue(quoteValueIterator1, "Price", "28", 28.0);
    assertFalse(quoteValueIterator1.hasNext());

    assertTrue(quoteIterator.hasNext());
    Quote quote2 = quoteIterator.next();
    assertEquals("APX Power NL Hourly", quote2.market);
    assertEquals(1498428000000L, quote2.dateApplied);
    assertEquals(
      LocalDate.of(2017, 6, 26),
      quote2.getDate(TIME_ZONE));

    Iterator<QuoteValue> quoteValueIterator2 = quote2.quoteValues.iterator();
    assertQuoteValue(quoteValueIterator2, "Order", "2", 2);
    assertQuoteValue(quoteValueIterator2, "Hour", "02", 2);
    assertQuoteValue(quoteValueIterator2, "Net Volume", "3874", 3874.0);
    assertQuoteValue(quoteValueIterator2, "Price", "29.82", 29.82);
    assertFalse(quoteValueIterator2.hasNext());

    assertFalse(quoteIterator.hasNext());
  }

  private void assertQuoteValue(Iterator<QuoteValue> quoteValueIterator, String expectedTLabel, String expectedValue, int expectedIntValue) {
    QuoteValue quoteValue = assertQuoteValue(quoteValueIterator, expectedTLabel, expectedValue);
    assertEquals(expectedIntValue, quoteValue.getValueAsInt());
  }

  private void assertQuoteValue(Iterator<QuoteValue> quoteValueIterator, String expectedTLabel, String expectedValue, double expectedDoubleValue) {
    QuoteValue quoteValue = assertQuoteValue(quoteValueIterator, expectedTLabel, expectedValue);
    assertEquals(expectedDoubleValue, quoteValue.getValueAsDouble(), 0);
  }

  private QuoteValue assertQuoteValue(Iterator<QuoteValue> quoteValueIterator, String expectedTLabel, String expectedValue) {
    assertTrue(quoteValueIterator.hasNext());
    QuoteValue quoteValue = quoteValueIterator.next();
    assertEquals(expectedTLabel, quoteValue.tLabel);
    assertEquals(expectedValue, quoteValue.value);
    return quoteValue;
  }
}