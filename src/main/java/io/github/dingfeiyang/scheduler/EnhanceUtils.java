package io.github.dingfeiyang.scheduler;

import javassist.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.hutool.core.text.CharSequenceUtil.upperFirst;


public abstract class EnhanceUtils {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    static {
        // 适配Tomcat，因为Tomcat不是用的默认的类加载器，而Javassist用的是默认的加载器
        var classArray = new Class<?>[]{
                IScheduler.class
        };

        var classPool = ClassPool.getDefault();

        for (var clazz : classArray) {
            if (classPool.find(clazz.getName()) == null) {
                ClassClassPath classPath = new ClassClassPath(clazz);
                classPool.insertClassPath(classPath);
            }
        }
    }

    public static IScheduler createScheduler(ReflectScheduler reflectScheduler) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var classPool = ClassPool.getDefault();

        Object bean = reflectScheduler.getBean();
        Method method = reflectScheduler.getMethod();

        // 定义类名称
        CtClass enhanceClazz = classPool.makeClass(EnhanceUtils.class.getName() + "$" + upperFirst(bean.getClass().getSimpleName()) + ATOMIC_INTEGER.incrementAndGet());
        enhanceClazz.addInterface(classPool.get(IScheduler.class.getName()));

        // 定义类中的一个成员
        CtField field = new CtField(classPool.get(bean.getClass().getName()), "bean", enhanceClazz);
        field.setModifiers(Modifier.PRIVATE);
        enhanceClazz.addField(field);

        // 定义类的构造器
        CtConstructor constructor = new CtConstructor(classPool.get(new String[]{bean.getClass().getName()}), enhanceClazz);
        constructor.setBody("{this.bean=$1;}");
        constructor.setModifiers(Modifier.PUBLIC);
        enhanceClazz.addConstructor(constructor);

        // 定义类实现的接口方法
        CtMethod invokeMethod = new CtMethod(classPool.get(void.class.getName()), "invoke", null, enhanceClazz);
        invokeMethod.setModifiers(Modifier.PUBLIC + Modifier.FINAL);
        String invokeMethodBody = "{this.bean." + method.getName() + "();}";
        invokeMethod.setBody(invokeMethodBody);
        enhanceClazz.addMethod(invokeMethod);

        // 释放缓存
        enhanceClazz.detach();

        Class<?> resultClazz = enhanceClazz.toClass(IScheduler.class);
        Constructor<?> resultConstructor = resultClazz.getConstructor(bean.getClass());
        return (IScheduler) resultConstructor.newInstance(bean);
    }

}
