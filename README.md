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

# git 分支管理

## 约定

### 分支名称

#### 需求编码名称
第一期需求：stage1
第N期需求: stageN
北京出行开发分支命名为需求编号，请自行按照分支编号创建分支开发
#### 开发分支名称
```
需求编号：PRD-180003
互不干扰分支名称：bj_180003
功能相同需求合并，则分支名称：bj_180003_180001
```
#### 测试发布分支名称
```
需求第一期：stage1
开发分支合并到测试分支：bj_test_stage1
```
#### 预发布分支名称
```
需求第一期：stage1
测试分支合并到预发布分支：bj_pre_stage1
```
#### 正式发布分支名称
```
需求第一期：stage1
预发布分支合并到正式发布分支：bj_release_stage1
```
#### 标签分支名称
```
需求第一期：stage1
总结需求内容，写入readme文件中
正式发布分支合并到标签发布分支：bj_stage1
```
#### bug修复分支名称
```
需求第一期：stage1
需求编号：PRD-180003
bug修复分支：bj_bug_{第几期}_{版本，可选}_{需求编号，可选}
例如：
bj_test_stage1：bj_bug_stage1_test_180003

```

### 代码发布流程

开发分支=>测试分支=>预发布分支=>正式分支=>标签分支(tag)=>删除（正式、预发布、测试、开发分支）

### bug修复流程

由需要修复的分支，创建出bug修复分支（命名参考以上“bug修复分支名称”），修复后合并到所修复的分支
例如：
bj_release_stage1=>bj_bug_stage1_release=>bj_release_stage1

### 代码风格

#### eclipse编码风格
```
http://120.26.220.241/BurningGroup/CodeReview.git
```
编码规范文件在StyleGuide目录VcEclipseStyle.xml

Window=>Preferences=>Java=>Code Style=>Formatter

