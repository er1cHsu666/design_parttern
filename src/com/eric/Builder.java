package com.eric;

/**
 * @Author: Eric
 * @Date: 2020/8/24 10:05 下午
 * @title: 建造者模式
 * @Description: 对于多参数的构建，参数联合校验比较友好。
 * 用于构造函数参数过多，并且构造后对象不可变，避免对象处于无效状态（只set了几个参数，还有必要的参数没有set完）
 */
class ResourcePoolConfig {
    private String name;
    private int maxTotal;
    private int minTotal;

    private ResourcePoolConfig(Builder builder){
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.minTotal = builder.minTotal;
    }

    public static class Builder{
        private static final int DEFAULT_MAX_TOTAL = 8;
        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int minTotal = 0;

        public ResourcePoolConfig Builder() throws Exception {
            //参数校验
            if (minTotal > maxTotal){
                throw new Exception("error");
            }
            return new ResourcePoolConfig(this);
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) throws Exception {
            if (maxTotal < 0){
                throw new Exception("error");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMinTotal(int minTotal){
            this.minTotal = minTotal;
            return this;
        }
    }
}
