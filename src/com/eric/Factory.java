package com.eric;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Eric
 * @Date: 2020/8/20 11:00 下午
 * @title: 工厂模式
 * @Description: 把创建同一对象的不同子类封装到工厂方法里，对外屏蔽创建细节，降低耦合
 * 简单工厂与工厂方法的选择：如果创建对象很简单，直接用简单工厂即可，如果每个对象都有其不同的构造逻辑，建议使用工厂方法。
 * 除此之外，如果要创建的对象并不能使用单例模式，又想去掉if else分支，可以选用工厂方法
 */

//region simple factory

/**
 * 简单工厂
 */
class SimpleFactory {
    public Animal createInstance(String type) {
        if ("dog".equals(type.toLowerCase())) {
            return new Dog();
        } else if ("cat".equals(type.toLowerCase())) {
            return new Cat();
        } else {
            //or throw exception
            return null;
        }
    }
}

/**
 * 简单工厂实现对象的单例创建（饿汉式）
 */
class SingletonSimpleFactory {
    private static final Map<String, Animal> animals = new HashMap<>();

    //静态块会在类第一次加载的时候由JVM调用，由JVM保证只调用一次
    static {
        animals.put("dog", new Dog());
        animals.put("cat", new Cat());
    }

    public Animal getInstance(String type) {
        type = type.toLowerCase();
        return animals.containsKey(type) ? animals.get(type) : null;
    }
}
//endregion

//region factory method

/**
 * 工厂方法模式,由于各类Factory不包含状态，完全可以复用，可以设计为单例，避免if else
 */
class FactoryMethod {
    private static final Map<String, AnimalFactory> factoryMap = new HashMap<>();

    static {
        factoryMap.put("dog", new DogFactory());
        factoryMap.put("cat", new CatFactory());
    }

    public static Animal getInstance(String type) throws Exception {
        if (!factoryMap.containsKey(type)) {
            throw new Exception("type not found");
        }
        return factoryMap.get(type).createInstance();
    }
}

interface AnimalFactory {
    Animal createInstance();
}

class DogFactory implements AnimalFactory {

    @Override
    public Animal createInstance() {
        return new Dog();
    }
}

class CatFactory implements AnimalFactory {

    @Override
    public Animal createInstance() {
        return new Cat();
    }
}

//endregion

//region abstract factory

/**
 * 抽象工厂
 *
 * 简单工厂与工厂方法都是一个工厂对应一种类型的create instance方法，抽象方法中每个工厂可以创建不同类型的实例
 * 在此例子中（格式解析器），有xml和json解析器两种类型，同时每种解析器还有分rule自定义规则和System config规则
 */
class ParserFactory{
    private static final Map<ParserFactoryType,IConfigParserFactory> factoryMap = new HashMap<>();

    static {
        factoryMap.put(ParserFactoryType.Json,new JsonConfigParserFactory());
        factoryMap.put(ParserFactoryType.Xml,new XmlConfigParserFactory());
    }

    public static IConfigParserFactory getInstance(ParserFactoryType type){
        return factoryMap.get(type);
    }

    enum ParserFactoryType{
        Json,
        Xml
    }
}

interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}

class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}

class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}

interface ISystemConfigParser{

}

interface IRuleConfigParser{

}

class JsonRuleConfigParser implements IRuleConfigParser{

}

class JsonSystemConfigParser implements ISystemConfigParser{

}

class XmlRuleConfigParser implements IRuleConfigParser{

}

class XmlSystemConfigParser implements ISystemConfigParser{

}

//endregion

abstract class Animal {

}

class Dog extends Animal {

}

class Cat extends Animal {

}
