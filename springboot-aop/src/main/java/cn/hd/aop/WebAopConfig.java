package cn.hd.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面类
 */
@Aspect
@Component
@Slf4j
public class WebAopConfig {

    // 指定切⼊点表达式，拦截哪个类的哪些⽅法
    @Pointcut("execution(* cn.hd.controller.*.*(..))")
    public void pointCut() {
        //切入点表达式说明
        /**
         *       * cn.hd.controller.*.*(..)
         *       第一个  * 表示任意返回类型
         *       cn.hd.controller 表示包名
         *       cn.hd.controller.* 表示包下任意类
         *       cn.hd.controller.*.*  表示任意类下任意方法名
         *       cn.hd.controller.*.*(..)   表示任意类下任意方法名任意参数
         *
         */
    }

    /**
     * 前置通知
     *
     * @param joinPoint 连接点
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("前置通知开始执行了。。。。" + joinPoint);

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
        Object[] object = joinPoint.getArgs();
        log.info("即将执行方法为: {}，属于{}包,参数为:{}", funcName, declaringTypeName, object);

        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);

    }

    /**
     * 后置/最终通知：在执⾏⽬标⽅法之后执⾏ 【⽆论是否出现异常最终都会执⾏】
     *
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("后置通知开始执行了。。。。" + joinPoint);


        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
        Object[] object = joinPoint.getArgs();
        log.info("即将执行方法为: {}，属于{}包,参数为:{}", funcName, declaringTypeName, object);

        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 返回后通知： 在调⽤⽬标⽅法结束后执⾏ 【出现异常不执⾏】
     */
    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        log.info("返回的结果是:{}", ret);
        log.info("返回后通知开始执行了。。。。" + joinPoint);

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
        Object[] object = joinPoint.getArgs();
        log.info("即将执行方法为: {}，属于{}包,参数为:{}", funcName, declaringTypeName, object);

        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);

    }

    /**
     * 异常通知： 当⽬标⽅法执⾏异常时候执⾏此关注点代码
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("异常通知开始执行了。。。。" + joinPoint);
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
        Object[] object = joinPoint.getArgs();
        log.info("即将执行方法为: {}，属于{}包,参数为:{}", funcName, declaringTypeName, object);

        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
        log.error("异常信息:{}", e);


    }

    /**
     * 环绕通知
     */
    @Around(value = "pointCut()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            log.info("环绕通知开始执行了。。。。" + proceedingJoinPoint);
            doBefore(proceedingJoinPoint);
            long start = System.currentTimeMillis();
            Object o = proceedingJoinPoint.proceed();//调用方法，得到其返回值信息
            long end = System.currentTimeMillis();
            doAfterReturning(proceedingJoinPoint, o);


            log.info("环绕通知结束执行了。。。。" + proceedingJoinPoint);
            log.info("方法环绕proceed，结果是:{}", o);
            log.info("方法调用耗时:{}  毫秒", (end - start));
        } catch (Throwable throwable) {
            // throwable.printStackTrace();
            doAfterThrowing(proceedingJoinPoint, (Exception) throwable);
        } finally {
            doAfter(proceedingJoinPoint);
        }
    }


}
