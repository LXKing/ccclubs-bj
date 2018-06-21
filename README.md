# 工程运行说明

## 工程混合为一，修改配置文件变更应用
- applicationContext-import.xml
- struts.xml

## 工程运行环境

- tomcat 版本要求 6.0.18
    - window=>preferences=>server=>Runtime Environments=>add=>apache tomcat v6.0=>指定tomcat根目录，创建成功 
- jdk 1.7
    - window=>preferences=>java=>installed jres=>add=>standard vm=>选择jdk目录
    - window=>preferences=>java=>compiler=>errors/warnings=>deprecated and restricted api=>全部调成warning
- Eclipse

## 运行
- 工程右键run as=>run on server,通过控制台旁边的servers可以看到相关的信息
- tomcat服务器运行超时问题，在servers下面，双击服务器，修改timeout=>start值3600即可

## Eclipse工程报错问题，修改指定web.xml版本
- \.settings\org.eclipse.wst.common.project.facet.core.xml

```
  <installed facet="jst.web" version="2.4"/>
```