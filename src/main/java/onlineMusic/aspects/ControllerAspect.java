package onlineMusic.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("within(onlineMusic.controllers.*Controller)")
    public void isController(){}

    @Before("isController() && target(controller) && args(arguments)")
    public void addLogging(JoinPoint joinPoint, Object controller, Object arguments){
        log.info("Controller:" + controller + " arguments:"  + arguments.toString() + " method: " + joinPoint.toString());
    }

    @Before("isController() && target(controller)")
    public void addLogging1(JoinPoint joinPoint, Object controller){
        log.info("Controller:" + controller + " method: " + joinPoint.toString());
    }
}
