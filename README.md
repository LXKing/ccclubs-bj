# 工程运行说明

## 工程混合为一，修改配置文件变更应用
- applicationContext-import.xml
- struts.xml

### 项目说明
```
admin    后台配置
wechat   微信服务配置
unit     企业服务配置
thread   通信协议处理
manage   移动端后台服务配置
web      网站前台配置
m        新版微信公众号服务配置
m alipay 新版支付宝服务窗服务配置
api      API服务配置，第三方对接
official 公务用车系统配置
app      app服务配置，APP端
receiver 网关程序系统配置
site     地勤
```

## 工程运行环境

- jdk 1.7
    - window=>preferences=>java=>installed jres=>add=>standard vm=>选择jdk目录
    - window=>preferences=>java=>compiler=>errors/warnings=>deprecated and restricted api=>全部调成warning
- tomcat 版本要求 6.0.18
    - window=>preferences=>server=>Runtime Environments=>add=>apache tomcat v6.0=>指定tomcat根目录，指定JDK7，创建成功 
- Eclipse
	- Eclipse Java EE IDE for Web Developers.

## 运行
- 工程右键run as=>run on server,通过控制台旁边的servers可以看到相关的信息
- tomcat服务器运行超时问题，在servers下面，双击服务器，修改timeout=>start值3600即可
- 在eclipse工作空间下面的“servers”修改server.xml配置，添加URIEncoding="UTF-8"这句，防止查询乱码。例如：\<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8"/>

## Eclipse工程报错问题，修改指定web.xml版本
- \.settings\org.eclipse.wst.common.project.facet.core.xml

```
  <installed facet="jst.web" version="2.4"/>
```
# 统一代码风格

## eclipse编码风格
```
http://120.26.220.241/BurningGroup/CodeReview.git
```
编码规范文件在StyleGuide目录VcEclipseStyle.xml

Window=>Preferences=>Java=>Code Style=>Formatter

# git 分支管理

## 分支开发“代码发布流程”和“命名规则”

第一期需求：stage1
第N期需求: stageN
北京出行开发分支命名，请按照以下流程中“括号内”的“命名规则”进行创建：

```
develop分支
↓
开发者分支（命名bj_stageN_{开发者}）
↓
测试分支（bj_test_stageN）
↓
测试hotfix分支（bj_test_stageN_hotfix_{开发者}）
↓
↓合并回
↓
测试分支（bj_test_stageN）
↓
预发布分支（bj_pre_stageN），删除"开发者分支"
↓
预发布hotfix分支（bj_pre_stageN_hotfix_{开发者}）
↓
↓合并回
↓
预发布分支（bj_pre_stageN）
↓
正式分支（bj_release_stageN），删除"预发布hotfix分支"
↓
正式hotfix分支（bj_release_stageN_hotfix_{开发者}）
↓
↓合并回
↓
正式分支（bj_release_stageN）
↓
标签分支（bj_stageN，总结“需求内容”并写入readme文件中），删除"正式hotfix分支"
↓
↓合并回
↓
master分支，develop分支
↓
删除（正式、预发布、测试、开发者分支、hotfix分支）
↓
开启下一个里程碑
```

 



