# Word页码获取

## 开发环境

#### 1.Windows 64bit 环境
#### 2.安装Office Word
#### 3.安装SaveAsPDFandXPS插件
#### 4.将jacob-1.18-x64.dll放到jre的bin目录下
#### 5.将jacob.jar文件拷贝一份放到C:/windows/system32下
#### 6.将jacob.jar安装到本地maven仓库
```
mvn install:install-file -Dfile=jacob.jar -DgroupId=com.jacob -DartifactId=jacob -Dversion=1.8 -Dpackaging=jar
```
###### 提示Success即可

##### 服务默认目录:
```
#部署目录
c:\Application\app\word2pdf-0.1.jar

#log
c:\Application\app\log

#临时文件目录
c:\Application\app\tmp

```

## 业务处理过程

#### 下载文件到本地 --->> 转化成PDF --->> 计算PDF页数