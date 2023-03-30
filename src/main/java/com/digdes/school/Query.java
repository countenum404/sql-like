package com.digdes.school;

import java.util.List;
import java.util.Map;

public interface Query {
    List<Map<String,Object>> execute(String query);
}
