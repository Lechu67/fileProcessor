package com.demo.spain.app.strategy.filtering;

import com.demo.spain.app.model.DataHolder;

public interface FilteringStrategy {
    boolean isApplicable(String searchType, String searchString, DataHolder output);

    String filter(DataHolder output);
}
