package com.algorithm.singleton;

/**
 * 破坏单例的行为
 * 1、反射，可以通过在私有构造函数抛出异常解决
 * 2、如果单例类实现了序列化接口Serializable, 就可以通过反序列化破坏单例，
 * 所以我们可以不实现序列化接口,如果非得实现序列化接口，
 * 可以重写反序列化方法readResolve(), 反序列化时直接返回相关单例对象。
 */
public class Singleton {



    private Singleton() {
        // 避免通过反射创建对象
        if (singleton1 !=null){
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }


    /**
     * 单线程使用-饱汉模式
     */
    private static Singleton singleton1 = null;
    public static Singleton instance1() {
        if (singleton1 == null) {
            singleton1 = new Singleton();
        }
        return singleton1;
    }



    /**
     * 饿汉模式
     *
     * @return
     */
    private static final Singleton singleton2 = new Singleton();
    public static Singleton instance2() {
        return singleton2;
    }

    /**
     * 线程安全-但是效率不高
     */
    private static Singleton singleton3 = null;
    public static synchronized Singleton instance3() {
        if (singleton3 == null) {
            singleton3 = new Singleton();
        }
        return singleton3;
    }


    /**
     * 双重检锁模式-
     *
     * 在不加volatile关键字的情况下，
     * 在多线程的情况下，可能会出现空指针问题，
     * 出现问题的原因是JVM在实例化对象的时候会进行优化和指令重排序操作
     */
    private static volatile Singleton singleton4 = null;

    private static Singleton instance4() {
        if (singleton4 == null) {
            synchronized (Singleton.class) {
                if (singleton4 == null) {
                    singleton4 = new Singleton();
                }
            }
        }
        return singleton4;
    }

    /**
     * 利用内部类加载
     *
     * @return
     */
    public static Singleton instance5() {

        return Test.singleton;
    }

    private static class Test {
        private static final Singleton singleton = new Singleton();
    }

    /**
     * 通过反射创建单例可以避免反射及反序列化
     * @return
     */
    public static Singleton instance6(){
        return InnerEnum.INSTANCE.getInstance();
    }
    enum InnerEnum{
        INSTANCE;

        private Singleton singletonEnum;
        InnerEnum(){
            singletonEnum=new Singleton();
        }

        private  Singleton getInstance(){
            return singletonEnum;
        }
    }


}
