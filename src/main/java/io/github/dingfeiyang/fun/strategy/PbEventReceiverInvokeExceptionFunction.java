package io.github.dingfeiyang.fun.strategy;

import io.github.dingfeiyang.event.IEventReceiver;
import io.github.dingfeiyang.event.PbEvent;

@FunctionalInterface
public interface PbEventReceiverInvokeExceptionFunction  {
    void accept(IEventReceiver receiver, PbEvent event,Throwable e);
}
