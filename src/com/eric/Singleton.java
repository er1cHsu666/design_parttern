package com.eric;

/**
 * @Author: Eric
 * @Date: 2020/8/19 9:56 下午
 * @title: 单例模式, 双重检测
 * @Description: 用于处理资源访问冲突，表示全局唯一类。
 * 例如logger，Id生成器，配置类，数据库连接池等
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
