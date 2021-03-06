### 使用说明

#### 方法一：命令行方式

```
java -jar 周报合并工具的jar路径 周报目录
```

例如

```
java -jar weekly-report-merge-tool-1.1-SNAPSHOT.jar D:/周报
```

**生成的汇总周报路径在周报合并工具的 jar 路径下**



#### 方法二（推荐）：拖到周报文件夹到 bat 脚本即可

将 script 目录下的 bat 脚本拷贝出来，与合并工具的 jar 放在同一目录下（或根据个人进行修改 bat 脚本文件）

**生成的汇总周报，与周报文件夹目录平级**

例如：周报文件夹为：```D:/周报```，生成的汇总周报路径为：```D:/xxxx.xlsx```



### 功能介绍

* 将大家的周报合并成一份新的汇总周报（路径根据方法的不同有所不同）
* 样式已设置



### 已知存在的问题

* 隐藏行的数据也会被读取到



### 版本说明

#### v  1.1

>时间：2020-03-22
>
>版本：v 1.1 
>
>作者：HochenChong
>
>介绍：
>
>1. 在 Excel 的第一列新增一列：项目
>2. 根据项目进行分组排列显示，并将相同的项目名合并单元格
>3. 使用了 EasyExcel 的 WriteTable 来实现分组合并单元格的功能

#### v 1.0

>时间：2020-01-13
>
>版本：v 1.0 
>
>作者：HochenChong
>
>介绍：创建周报合并工具项目，用于团队收集周报后汇总成一份，减少手动的合并工具