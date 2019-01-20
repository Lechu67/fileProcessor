package com.demo.spain.app;


import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfiguration extends EnumStateMachineConfigurerAdapter<State, Events> {

    @Override
    @SneakyThrows
    public void configure(StateMachineConfigurationConfigurer<State, Events> config) {
        config.withConfiguration()
            .autoStartup(true);
    }

    @Override
    @SneakyThrows
    public void configure(StateMachineStateConfigurer<State, Events> states) {
        states
            .withStates()
            .initial(State.START)
            .states(EnumSet.allOf(State.class))
            .end(State.END);

    }

    @Override
    @SneakyThrows
    public void configure(StateMachineTransitionConfigurer<State, Events> transitions) {
        transitions
            .withExternal()
                .source(State.START).target(State.READ_FORMAT1)
                .event(Events.F1)//.action(initAction())
                .and()
            .withExternal()
                .source(State.START).target(State.READ_FORMAT2)
                .event(Events.F2)
                .and()
            .withExternal()
                .source(State.READ_FORMAT1).target(State.READ_FORMAT2)
                .event(Events.F2)
                .and()
            .withExternal()
                .source(State.READ_FORMAT1).target(State.END)
                .event(Events.NO_LINES);
    }

}