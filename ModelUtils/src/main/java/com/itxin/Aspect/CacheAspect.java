package com.itxin.Aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.itxin.AnnotateModel.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@ComponentScan
public class CacheAspect {
    /*配置jedis*/
    @Autowired
    private Jedis jedis;

    public Object RroundMethod(ProceedingJoinPoint point){
        //获取增强的对象
        Object target = point.getTarget();
        //获取连接点上的签名。此接口并行
        Signature signature = point.getSignature();
        //获取方法名
        String name = signature.getName();
        //获取方法参数
        Object[] args = point.getArgs();
        //创建一个空的Object对象
        Object res=null;
        //创建一个类的数据，长度为方法参数的长度
        Class[] classes = new Class[args.length];
        //构造参数类型
        for (int i = 0; i < args.length; i++) {
            classes[i]=args[i].getClass();
        }
        //将增强类的对象转成字节码加载进内存
        Class<?> targetClass = target.getClass();

        try {
            //获取完成的方法
            Method method = targetClass.getMethod(name, classes);
            //建立存入缓存key的名字
           String key =targetClass.getName()+"."+ name;
           //判断方法是否有相应的注解修饰
            if(method.isAnnotationPresent(Cache.class)){
                //判断该方法是否存在该缓存的key名
                if(!jedis.exists(key)){
                    System.out.println("没有缓存");
                    //执行方法
                    res = method.invoke(target, args);
                    //将缓存存入redis中
                    jedis.set(key, JSON.toJSONString(res));
                }else {
                    System.out.println("缓存");
                    //从缓存中获取未解码的json数据
                    String resStr = jedis.get(key);
                    //判断方法返回值类型是否是list集合
                    if(method.getReturnType().getCanonicalName().equals(List.class.getCanonicalName())){
                        //获取方法的返回值泛型
                        Type genericReturnType = method.getGenericReturnType();
                        //判断genericReturnType是否属于ParameterizedType（参数化类型）
                        if(genericReturnType instanceof ParameterizedType){
                            //强转为参数类型;且返回表示实际类型的{@CodeType}对象数组
                            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                            //获取出参数的对象数组类型名并转为字节码存入(此法只能用在jdk8版本之后)
                            Class<?> aClass = Class.forName(actualTypeArguments[0].getTypeName());
                            //根据所获得的参数类型的直接嘛以及读出来的未解码的json数据使用json的静态方法parseArray()解析出真确数据
                            res= JSON.parseArray(resStr, aClass);
                        }
                    }else {
                        //将获取出的数据解码
                        res=JSON.parseObject(resStr,method.getReturnType());
                    }
                }
            }else {
                //由于是不是修饰的方法直接执行返回数据
                System.out.println("不是修饰的方法");
                res= point.proceed();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("..............结束..............");
        return res;
    }
}
