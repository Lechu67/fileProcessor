package com.demo.spain.app.strategy.formatting;

import com.demo.spain.app.Formatting;
import org.springframework.stereotype.Component;

@Component
public interface FormatDecoderStrategy {
    boolean isApplicable(String line);

    Formatting setCurrentProcessing();
}
