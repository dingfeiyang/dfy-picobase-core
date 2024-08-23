package io.github.dingfeiyang.interceptor;

@FunctionalInterface
public interface InterceptorNextFunc<T, R> {

    R run(T t);

}
