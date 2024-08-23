package io.github.dingfeiyang.fun.strategy;

import io.github.dingfeiyang.event.PbEvent;

import java.util.function.Consumer;

@FunctionalInterface
public interface PbNoEventReceiverFunction extends Consumer<PbEvent> {
}
