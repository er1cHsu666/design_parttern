package com.eric;

/**
 * @Author: Eric
 * @Date: 2020/8/19 9:56 下午
 * @title: 单例模式, 双重检测
 * @Description: 用于处理资源访问冲突，表示全局唯一类。
 * 例如logger，Id生成器，配置类等
 *
 * 缺点：单例对OOP特性支持不友好
 * 单例会隐藏类之间的依赖关系（由于不需要显示创建单例类实例，所以单例类的调用可能会出现在代码的任意一个角落，难以一眼看出类与哪些类依赖）
 * 扩展性不好
 * 可测试性不好
 * 不支持有参数的构造函数
 *
 * 替代方案：可以使用IOC容器注入单例模式
 */
public class Singleton {
    private Singleton() {
    }

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 通过静态内部类实现
 */
class SingletonStaticInnerClass {
    private SingletonStaticInnerClass() {
    }

    private static class SingletonHolder {
        private static final SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

    public SingletonStaticInnerClass getInstance() {
        return SingletonHolder.instance;
    }
}

/**
 * 通过内部枚举实现（最推崇的方式）
 * 其他方式可以通过反射，序列化与反序列化进行破坏，枚举模式绝对安全
 */
class SingletonEnum {
    private SingletonEnum() {
    }

    private enum SingletonHoler {
        INSTANCE;
        private final SingletonEnum instace;

        SingletonHoler() {
            instace = new SingletonEnum();
        }

        private SingletonEnum getInstace() {
            return instace;
        }
    }

    public SingletonEnum getInstance() {
        return SingletonHoler.INSTANCE.getInstace();
    }
}
