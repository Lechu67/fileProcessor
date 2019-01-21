package com.demo.spain.app.strategy.formatting;

import com.demo.spain.app.Formatting;
import org.springframework.stereotype.Component;

@Component
public class FormatOneStrategy implements FormatDecoderStrategy {

    public boolean isApplicable(String line) {
        return line.equalsIgnoreCase(FormatType.F1.toString());
    }

    public Formatting setCurrentProcessing(){
        return Formatting.TYPE_1;
    }

}
