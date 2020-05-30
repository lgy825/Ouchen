package com.yunmu.back.service.index;

import java.util.List;
import java.util.Map;

public interface IndexService {

    Map<String,Object> getSellData(String startTime, List<String> projectIds,String time);
}
