package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car{}
@Component class SportsCar extends Car{}
@Component class Truck extends Car{}
@Component class Engine{}

class AppContext{
    Map map = new HashMap();

    AppContext(){
     map = new HashMap();
     doComponentScan();
    }

    private void doComponentScan() {
        try {
            // 1.패키지내의 클래스 목록을 가져온다.
            // 2.반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
            // 3.@Component가 붙어있으면 객체를 생성해서 map에 저장

            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);
    }

}

public class Main3 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car)ac.getBean("car");
        Engine engine = (Engine)ac.getBean("engine");
        System.out.println("car = "+car);
        System.out.println("engine = " + engine);
    }

}
