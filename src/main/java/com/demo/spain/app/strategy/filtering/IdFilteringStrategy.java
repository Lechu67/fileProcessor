package com.demo.spain.app.strategy.filtering;

import com.demo.spain.app.DataHolder;
import org.springframework.stereotype.Component;

@Component
public class IdFilteringStrategy implements FilteringStrategy{

    @Override
    public boolean isApplicable(String searchType, String searchString, DataHolder output) {
        return searchType.equals(FilteringType.ID.toString()) && output.getId().equals(searchString);
    }

    @Override
    public String filter(DataHolder output) {
        return output.getCity().toUpperCase();
    }
}
