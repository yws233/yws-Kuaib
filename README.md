# yws-Kuaib

![](https://github.com/yws233/yws-Kuaib/tree/master/images/kuaib_logo.png)
# kuaib
## 一、简介

### 让编写变得纯粹实用，高效简单，也让查询更加友好;同时也是一款去除文档格式神器，便于将各种文档格式转换为无格式文档，方便应用他出。

![](https://github.com/yws233/yws-Kuaib/blob/master/images/head1.png)&nbsp;![](https://github.com/yws233/yws-Kuaib/blob/master/images/head03.png)&nbsp;![](https://github.com/yws233/yws-Kuaib/blob/master/images/head1.png)&nbsp;![](https://github.com/yws233/yws-Kuaib/blob/master/images/head03.png)

<strong>快编（kuaib）</strong>是一款专门为解决修改编写对而开发的文本对比编辑软件，其最大特点就是所有功能都可在一个页面完成，完全省去了编辑查询等多窗口编辑模式。快编将参照、编写、个性化查重、对比、pdf转换等放在同一窗口显示，<strong style="font-size:24px">PDF等格式编辑神器，可以直接复制为无格式文字，方便应用于任何文档，再也不用麻烦转化格式问题</strong>。可以支持上传word/pdf/excel/rtf/txt/java/py/c等十多种格式文件上传，并可同步显示在文件上传区域内，并可直接进行编辑。在整体的页面即可实现实时查询与发送，提供谷歌、维基、知网、高校官网等直接查询，另外还可连接qq/微信/git等社交软件，方便随时编写发送与沟通。此外，在编辑区域不仅可以实现实时统计字数，还提供富裕文本编辑，方便拓展编辑，可上传图片/视频等，字体/排版等都可个性化设置。此外，在历史记录区域，可以实现一键对比，可以将编辑过的文字直接显示在历史，方便对比。
快编系统省去了目前Word等编辑软件在修改比对切换的麻烦，快编就是为了让编写、比对、修改同显示，更直观、更纯粹。项目基于SSM框架，更新于6.0版本。

## 项目地址:http://yws233.cn:8080/kuaib/

![](https://github.com/yws233/yws-Kuaib/blob/master/images/head02.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head04.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head06.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head07.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head08.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head09.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/head11.png)

前端：HTML5 + CSS3 + JS + jQuery + ajax + fastjson + xhEditer + pdf.js

后端：SpringMVC + Spring + Mybatis + POIXMLDocument + SimHash

数据库：MySQL + Redis(缓存后期)

服务器：Tomcat9.02 + aliyun + jdk1.8

文件处理：POI + PDFBOX + JDOM + itextpdf

## 二、设计原图

![view](https://github.com/yws233/yws-Kuaib/blob/master/images/kuaib.png)

## 三、登录界面

![](https://github.com/yws233/yws-Kuaib/blob/master/images/login.png)

## 四、4.0版本效果

![](https://github.com/yws233/kuaib/blob/master/images/main.png)

## 五、注册界面

![](https://github.com/yws233/kuaib/blob/master/reg2.0.png)

## 六、密码找回

![](https://github.com/yws233/kuaib/blob/master/pwd2.0.png)

## 七、操作指南


### 一、登录注册
1.登录网址：http://yws233.cn:8080/kuaib/ （推荐使用谷歌或火狐浏览器）

2.登录页面

3.点击表单右侧的Register进行注册，进入注册页面，按照提示的要求逐一填写注册,注册成功后自动跳转至登录页面，否则为注册信息填写有误。

4.登录后进入首页
   
注:在任何可编辑的区域内拉动右下角的区域可实现自由改变编辑框大小，若被文字遮住也可随意调整大小，上下左右都可进行拉动，可根据自身喜好随意调整（文件上传后的双击编辑区域也可调整大小）。
拉动后效果： 

### 二、页面操作

1.头部区域，点击左上方的“Kuaib.com”可以查看系统的API文档，中间区域的图标点击可直接进入相应的登录页面，依次为github/微信/qq/推特/谷歌搜索/facebook，右侧显示登录名、登录时间以及点击可注销。

2.中间编辑区域，左侧参考部分为文件上传区域，可以上传各类文本格式，如pdf/word/txt/js/html/rtf等，中间的主编辑区域为编辑区域，上半部分提供带格式的编辑，如字体大小、颜色、插入表格等等，点击上方的 可实现全屏编辑。下方区域提供计数和对比功能，可以将所写的文字粘贴或写入下方区域，可以进行计数，此外还可显示在右侧历史记录区域，历史记录的文字也可进行编辑。

3.下方按钮操作

①删除：文件上传之后，可进行删除显示。

②上传及保存、下载文件：点击上传按钮可上传文档，随后点击保存按钮，即可看到文档在参考部分进行显示，双击文字区域后，会自动将文档内容转换为可编辑或复制内容，此后你可在中间或其他任何的编辑区域内进行参照编辑。点击下载文件则可将之前上传的文件进行下载查看。例如：传入word文档：
双击下方文字区域前（超出部分不要紧，双击自动收入编辑框内）：

双击之后，可以看到超出部分已完全收入编辑框内：

③pdf文件：提供pdf格式文件在线阅览及格式转换等功能。如果想对pdf格式文件进行去除复制，可点击此按钮，点击之后跳转页面，其中显示的英文文档为示例图，点击按钮可上传本地pdf文档，点击按钮可在右侧显示文档目录，点击右上方可以返回主页面，此外此页还提供打印、搜索、直达页首页尾等功能，可自行探索，另外，如果想要复制pdf文本，直接选择要复制的文本后，点击返回主页面，可在编辑区域或者历史记录的文本区域内进行粘贴，即可去除所有格式，仅留文本内容也可进行编辑，效果见图3，再进行复制即可粘贴到word/wps等任何文档，适应任何格式!

④清除，点击后可以清除编辑区域下方文本框的内容，重新新计数。

⑤字数统计，可以显示编辑区域下方文本框内的字数。

⑥查重，可以实现与任何数据库实现查重，目前还在测试中...，效果如下：

⑦对比，可以实现下方编辑区域与历史同步显示，在下方编辑区域编辑时，右侧历史记录会保留，不随之改变，直到再次点击对比，再次同步替换：

⑧右下侧的图标按钮，可以直达知网、维基百科、百度、华师官网、中农数据库、以及国外注明的学术论文库ProQues。

## 八、功能特点

1.单一窗口可显示参考文档、编辑区、编辑历史显示，直观展现。

2.可一键对比修改历史。

3.计算增加字数，知晓修改字数。

4.计算重复率。

5.修改内容与原文档实时对比。

6.提供文档上传、显示、编辑。

## 九、后期规划

1.不断增加新旧文档对比准确性。

2.不断扩充重复率准确度，利用爬虫、机器学习增加网上重复率比对。

3.引入好友在线协同编写，可用社交网络登入，提供在线交流，省去发来发去的麻烦。

4.提供线下编辑,引入更多论文相关接口及数据库。

## 版权所有：Copyright © 2017-2018  All Rights Reserved  yws 快编系统。此系统供交流学习免费使用，但不可用于转载、复用、商用以及其他未经本人允许的操作。
## 支持作者
![](https://github.com/yws233/yws-Kuaib/blob/master/images/alplay.png)
![](https://github.com/yws233/yws-Kuaib/blob/master/images/weixin.png)


















