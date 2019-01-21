package com.demo.spain.app;


import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;

//@Configuration
//@EnableStateMachine
public class StateMachineConfiguration extends EnumStateMachineConfigurerAdapter<Formatting, Events> {

//    @Override
//    @SneakyThrows
//    public void configure(StateMachineConfigurationConfigurer<Formatting, Events> config) {
//        config.withConfiguration()
//            .autoStartup(true);
//    }
//
//    @Override
//    @SneakyThrows
//    public void configure(StateMachineStateConfigurer<Formatting, Events> states) {
//        states
//            .withStates()
//            .initial(Formatting.START)
//            .states(EnumSet.allOf(Formatting.class))
//            .end(Formatting.END);
//
//    }
//
//    @Override
//    @SneakyThrows
//    public void configure(StateMachineTransitionConfigurer<Formatting, Events> transitions) {
//        transitions
//            .withExternal()
//                .source(Formatting.START).target(Formatting.TYPE_1)
//                .event(Events.F1)//.action(initAction())
//                .and()
//            .withExternal()
//                .source(Formatting.START).target(Formatting.TYPE_2)
//                .event(Events.F2)
//                .and()
//            .withExternal()
//                .source(Formatting.TYPE_1).target(Formatting.TYPE_2)
//                .event(Events.F2)
//                .and()
//            .withExternal()
//                .source(Formatting.TYPE_1).target(Formatting.END)
//                .event(Events.NO_LINES);
//    }

}