package com.ocean.angel.tool.interceptor;

import cn.hutool.core.collection.CollUtil;
import com.ocean.angel.tool.annotation.SensitiveBean;
import com.ocean.angel.tool.annotation.SensitiveField;
import com.ocean.angel.tool.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class DecryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 取出查询的结果
        Object resultObject = invocation.proceed();
        if (Objects.isNull(resultObject)) {
            return null;
        }

        // 基于selectList
        if (resultObject instanceof ArrayList) {
            ArrayList resultList = (ArrayList) resultObject;
            if (!CollUtil.isEmpty(resultList) && needToDecrypt(resultList.get(0))) {
                for (Object result : resultList) {
                    // 逐一解密
                    decrypt(result);
                }
            }
            // 基于selectOne
        } else {
            if (needToDecrypt(resultObject)) {
                decrypt(resultObject);
            }
        }
        return resultObject;
    }

    private boolean needToDecrypt(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return false;
        }
        Class<?> objectClass = object.getClass();
        SensitiveBean sensitiveDean = AnnotationUtils.findAnnotation(objectClass, SensitiveBean.class);
        return Objects.nonNull(sensitiveDean);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public <T> T decrypt(T result) throws IllegalAccessException {

        // 取出resultType的类
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();

        for (Field field : declaredFields) {

            // 取出所有被EncryptDecryptField注解的字段
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(result);

                // 只支持String的解密
                if (object instanceof String) {
                    String value = (String) object;

                    // 对注解的字段进行逐一解密
                    try {
                        field.set(result, AESUtil.decrypt(value));
                    } catch (Exception e) {
                        log.error("AES解密失败");
                    }
                }
            }
        }
        return result;
    }

}
