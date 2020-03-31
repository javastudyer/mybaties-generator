/**
 * Copyright (C), 2015-2019
 * 文件名: Main
 * 作者:   gaojing
 * 创建时间:     2019/6/19 12:56
 * 描述:
 * 历史修改:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com;

import cn.hutool.core.date.DateUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * @author gaojing
 * @create 2019/7/19
 * @version 1.0
 */
public class Main {
    public static void generator(JTextField outfilepath_textfield,JTextField outxml_package_textfield,JTextField outlogpath_textfield,
    JTextField daopackage_textfield,JTextField outdo_textfield,JTextField dopackage_textfield,
                          JTextField jdbc_textfield,JTextField name_textfield,JTextField pass_textfield,
                          JTextField table_textfield,JTextField do_name_textfield,JTextArea outtext_textarea) throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException {
        try {
            List<String> list = new ArrayList<String>();
            Boolean overwrite = true;
            Configuration configuration = new Configuration();
           // configuration.addClasspathEntry("../mysql-connector-java-5.1.22.jar");
            Context context = new Context(null);
            context.setId("context1");
            //配置jdbc连接
            JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
            jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
            jdbcConnectionConfiguration.setConnectionURL(jdbc_textfield.getText());
            jdbcConnectionConfiguration.setPassword(pass_textfield.getText());
            jdbcConnectionConfiguration.setUserId(name_textfield.getText());

            //配置xml
            SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
            sqlMapGeneratorConfiguration.setTargetPackage(outxml_package_textfield.getText());
            sqlMapGeneratorConfiguration.setTargetProject(outfilepath_textfield.getText());

            //配置model
            JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
            javaModelGeneratorConfiguration.setTargetPackage(dopackage_textfield.getText());
            javaModelGeneratorConfiguration.setTargetProject(outdo_textfield.getText());

            //配置dao
            JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
            javaClientGeneratorConfiguration.setTargetProject(outlogpath_textfield.getText());
            javaClientGeneratorConfiguration.setTargetPackage(daopackage_textfield.getText());
            javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
            javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");

            CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
            commentGeneratorConfiguration.setConfigurationType("org.mybatis.generator.internal.HairyCommentGenerator");
            commentGeneratorConfiguration.addProperty("javaFileEncoding", "UTF-8");
            commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
            commentGeneratorConfiguration.addProperty("suppressDate", "true");

            JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
            javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");


            context.setTargetRuntime("MyBatis3");
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(table_textfield.getText());
            tableConfiguration.setDomainObjectName(do_name_textfield.getText());
            tableConfiguration.setCountByExampleStatementEnabled(false);
            tableConfiguration.setSelectByExampleStatementEnabled(false);
            tableConfiguration.setDeleteByExampleStatementEnabled(false);
            tableConfiguration.setUpdateByExampleStatementEnabled(false);

            context.addTableConfiguration(tableConfiguration);

            context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
            context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
            context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

            configuration.addContext(context);

            DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,defaultShellCallback,list);
            myBatisGenerator.generate(null);
            for(String war:list){
                outtext_textarea.append(war+"\n");
                System.out.println(war);
            }
            outtext_textarea.append("***************转换结束****************"+DateUtil.now()+"\n");
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            outtext_textarea.append(e+"\n");
        } catch (SQLException e) {
            e.printStackTrace();
            outtext_textarea.append(e+"\n");
        } catch (IOException e) {
            e.printStackTrace();
            outtext_textarea.append(e+"\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
            outtext_textarea.append(e+"\n");
        } catch (Exception e){
            e.printStackTrace();
            outtext_textarea.append(e+"\n");
        }
    }

    public void generator() throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException {
        List<String> list = new ArrayList<String>();
        Boolean overwrite = true;
        File config = new File("D:\\企业QQ文件存储\\2851507555\\FileRecv\\mall-mybaties\\src\\main\\resources\\mybaties.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(list);
        Configuration configuration = configurationParser.parseConfiguration(config);
        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,defaultShellCallback,list);
        myBatisGenerator.generate(null);
        for(String war:list){
            System.out.println(war);
        }
    }


    public static void main(String[] args) {
//        Main main = new Main();
//        try {
//            main.generator();
//        } catch (InvalidConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XMLParserException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(System.getProperty("user.dir"));
        MsgLoadFrame m = new MsgLoadFrame();

        m.show();
    }

}
