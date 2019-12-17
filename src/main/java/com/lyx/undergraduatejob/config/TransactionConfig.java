package com.lyx.undergraduatejob.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;


@Configuration
@Aspect
public class TransactionConfig {


    @Autowired
    private PlatformTransactionManager transactionManager;

    //切点 的 配置
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.example.express_dena.services.impl.*.*(..))";

    @Bean
    public TransactionInterceptor txAdvice(){
        //创建事务的 隔离级别
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        // 创建一个 根据名字匹配 进行事务增强的 类
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        //加入 规则
        source.addTransactionalMethod("save",txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("add*", txAttr_REQUIRED);

        //以下 只能为 只读的 数据库连接
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("sear*", txAttr_REQUIRED_READONLY);

        //将增强 配置近事务的 拦截器中
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        //创建 切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        //植入
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
