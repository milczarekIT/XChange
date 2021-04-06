package org.knowm.xchange.bitbay.v3.dto.trade;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class BitbayBalancesHistoryQuery {

  private String limit;
  private String offset;
  private List<String> types;
  private Long fromTime;
  private Long toTime;
  private List<Map<String, String>> sort =
      ImmutableList.of(ImmutableMap.of("by", "time", "order", "ASC"));
}
