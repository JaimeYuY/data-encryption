package com.ocean.angel.tool;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CodeAutoGenerate {

    // 数据库连接URL
    static String URL = "jdbc:mysql://192.168.88.100:3306/db-ocean?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";

    // 数据库连接账号
    static String USERNAME = "root";

    // 数据库连接密码
    static String PASSWORD = "F@yuan5015";

    // 包名
    static String PACKAGE = "com.ocean.angel.tool";

    // 表名
    static String TABLE_NAME = "t_user_info";

    // 剔除表的前缀
    static String TABLE_PREFIX = "t_";

    @Test
    void codeAutoGeneration() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Jaime.yu");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(false);//开启Swagger2模式
        gc.setBaseResultMap(true);  //启用通用查询映射结果
        gc.setBaseColumnList(true);  //启用通用查询结果列

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null); //模块名
        pc.setParent(PACKAGE);
        pc.setController("controller");
        pc.setEntity("domain.entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 配置模板引擎
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig abc = new InjectionConfig() {
            @Override
            public void initMap() {}
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<FileOutConfig>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        abc.setFileOutConfigList(fileOutList);
        mpg.setCfg(abc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 对那一张表生成代码
        strategy.setInclude(TABLE_NAME);
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 生成实体时去掉表前缀
        strategy.setTablePrefix(TABLE_PREFIX);
        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }

}
