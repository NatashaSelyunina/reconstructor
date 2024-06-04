package reConstructor.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* reConstructor.services..*.*(..))")
    public void logServices() {}

    @Before("logServices()")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(String.format("Called method %s() of class %s with parameters" + args, methodName, className));
    }

    @After("logServices()")
    public void after(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(String.format("The %s() method of the %s class has finished", methodName, className));
    }

    @AfterReturning(
            pointcut = "logServices()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(String.format("The %s() method of class %s successfully returned the result - %s",
                methodName, className, result));
    }

    @AfterThrowing(
            pointcut = "logServices()",
            throwing = "e"
    )
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(String.format("Method %s() of class %s caused an error - threw the exception '%s'",
                methodName, className, e.getMessage()));
    }
}
