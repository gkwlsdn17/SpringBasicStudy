package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
@Component("engine") class Engine{} // <bean id="engine" class="com.fastcampus.ch3.Engine"/>
@Component class SuperEngine extends Engine{}
@Component class TurboEngine extends Engine{}
@Component class Door{}
@Component
class Car{
    @Value("red") String color;
    @Value("100") int oil;

//    @Autowired
//    @Qualifier("superEngine")
    @Resource(name="superEngine")
    Engine engine; // Autowired는 byType이라 타입으로 검색, 하지만 타입이 여러개면 이름으로 검색 - engine, superEngine, turboEngine
    @Autowired Door[] door;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoor(Door[] door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", door=" + Arrays.toString(door) +
                '}';
    }
}


public class SpringDITest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
        Car car = ac.getBean("car", Car.class);
        Engine engine = (Engine) ac.getBean("superEngine"); //byName
        //Engine engine = (Engine) ac.getBean(Engine.class); //byType
        Door door = (Door) ac.getBean("door");

//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoor(new Door[]{ac.getBean("door",Door.class),(Door) ac.getBean("door")});
        System.out.println("car = " + car);
        System.out.println("engine = "+ engine);

    }
}
