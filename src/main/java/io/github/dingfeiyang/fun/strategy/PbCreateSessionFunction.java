package io.github.dingfeiyang.fun.strategy;


import io.github.dingfeiyang.session.PbSession;

import java.util.function.Function;

/**
 * 函数式接口：创建 PbSession 的策略
 *
 * <p>  参数：SessionId  </p>
 * <p>  返回：PbSession对象  </p>
 */
@FunctionalInterface
public interface PbCreateSessionFunction extends Function<String, PbSession> {

}