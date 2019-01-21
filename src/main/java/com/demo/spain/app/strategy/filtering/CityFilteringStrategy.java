package com.demo.spain.app.strategy.filtering;

import com.demo.spain.app.model.DataHolder;
import org.springframework.stereotype.Component;

@Component
public class CityFilteringStrategy implements FilteringStrategy{

    @Override
    public boolean isApplicable(String searchType, String searchString, DataHolder output) {
        return searchType.equals(FilteringType.CITY.toString()) && output.getCity().equalsIgnoreCase(searchString.toLowerCase());
    }

    @Override
    public String filter(DataHolder output) {
        return output.getName() + " " + output.getId();
    }
}
