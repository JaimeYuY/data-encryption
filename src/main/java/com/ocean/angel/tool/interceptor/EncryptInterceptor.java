package com.ocean.angel.tool.interceptor;

import com.ocean.angel.tool.annotation.SensitiveBean;
import com.ocean.angel.tool.annotation.SensitiveField;
import com.ocean.angel.tool.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
})
public class EncryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // @Signature指定了type=parameterHandler后，这里的invocation.getTarget()便是parameterHandler
        // 若指定ResultSetHandler，这里则能强转为ResultSetHandler
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();

        // 获取参数对像，即mapper中paramsType的实例
        Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
        parameterField.setAccessible(true);

        // 取出实例
        Object parameterObject = parameterField.get(parameterHandler);
        if (parameterObject != null) {
            Class<?> parameterObjectClass = parameterObject.getClass();

            // 校验该实例的类是否被@SensitiveData所注解
            SensitiveBean sensitiveData = AnnotationUtils.findAnnotation(parameterObjectClass, SensitiveBean.class);

            if (Objects.nonNull(sensitiveData)) {
                // 取出当前当前类所有字段，传入加密方法
                Field[] declaredFields = parameterObjectClass.getDeclaredFields();
                encrypt(declaredFields, parameterObject);
            }
        }
        return invocation.proceed();
    }

    /**
     * 切记配置，否则当前拦截器不会加入拦截器链
     */
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    // 自定义配置写入，没有自定义配置的可以直接置空此方法
    @Override
    public void setProperties(Properties properties) {
    }

    public <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException {
        for (Field field : declaredFields) {

            // 取出所有被EncryptDecryptField注解的字段
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);

            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(paramsObject);

                // 暂时只实现String类型的加密
                if (object instanceof String) {
                    String value = (String) object;

                    // 加密, 这里我使用自定义的AES加密工具
                    try {
                        field.set(paramsObject, AESUtil.encrypt(value));
                    } catch (IllegalArgumentException e) {
                        log.error("AES加密失败");
                    }
                }
            }
        }
        return paramsObject;
    }
}
