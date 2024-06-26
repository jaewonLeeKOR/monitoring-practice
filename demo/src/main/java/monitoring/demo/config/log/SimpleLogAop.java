package monitoring.demo.config.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class SimpleLogAop {
    private StopWatch stopWatch;
    @AfterThrowing(pointcut = "execution(* monitoring.demo..*(..))", throwing = "exception")
    public void afterThrowingLog(JoinPoint joinPoint, Exception exception) {
        Method method = getMethod(joinPoint);
        log.trace(String.format("AFTER THROWING[%d] %s.%s => %s", Thread.currentThread().getId(), joinPoint.getTarget().getClass().getSimpleName(), method.getName(), exception.getMessage()));
    }

    @Around("execution(* monitoring.demo.controller..*(..))")
    public Object controllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        StringBuilder parameters = new StringBuilder();
        stopWatch = new StopWatch();
        parameters.append(String.format("BEFORE[%d](%f) %s.%s => ", Thread.currentThread().getId(), stopWatch.getTotalTimeSeconds(), joinPoint.getTarget().getClass().getSimpleName(), method.getName()));
        stopWatch.start();
        for (Object arg : joinPoint.getArgs()) {
            parameters.append(arg).append(", ");
        }
        log.trace(parameters.toString());
        Object returnObj = joinPoint.proceed();
        stopWatch.stop();
        log.trace(String.format("AFTER[%d](%f) %s.%s => %s", Thread.currentThread().getId(), stopWatch.getTotalTimeSeconds(), joinPoint.getTarget().getClass().getSimpleName(), method.getName(), returnObj));
        stopWatch = null;
        return returnObj;
    }

    @Around("execution(* monitoring.demo.service..*(..))")
    public Object serviceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        StringBuilder parameters = new StringBuilder();
        parameters.append(String.format("BEFORE[%d](%f) %s.%s => ", Thread.currentThread().getId(), stopWatch.getTotalTimeSeconds(), joinPoint.getTarget().getClass().getSimpleName(), method.getName()));
        for (Object arg : joinPoint.getArgs()) {
            parameters.append(arg).append(", ");
        }
        log.trace(parameters.toString());
        Object returnObj = joinPoint.proceed();
        log.trace(String.format("AFTER[%d](%f) %s.%s => %s", Thread.currentThread().getId(), stopWatch.getTotalTimeSeconds(), joinPoint.getTarget().getClass().getSimpleName(), method.getName(), returnObj));
        return returnObj;
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
