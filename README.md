项目主要功能：

1.实现两种语言字幕文件内容的批量替换。即将外语srt字幕文件中的外文批量替换为翻译好的中文。
  实现代码在src目录下的fileReplace.java
  

2.实现代码自动调用百度通用翻译api，批量翻译外语srt字幕文件并自动生成中文srt字幕文件
 实现代码在src目录下的fastBatchTranslation.java
 
注意：以上两个功能不同点在于：功能1需先手动将外语字幕翻译成一份中文字幕，然后调用程序
即可实现两份文件的内容互换。文件格式如下：

![image](https://github.com/JackLiDi/Java-/blob/main/img/1.PNG)

待翻译文件

![image](https://github.com/JackLiDi/Java-/blob/main/img/2.PNG)

已翻译好文件

还有要注意两个文件的路径，我是放在d盘test文件夹下，
![image](https://github.com/JackLiDi/Java-/blob/main/img/3.PNG)

你们要不是则需要改变代码：
![image](https://github.com/JackLiDi/Java-/blob/main/img/4.PNG)

功能2只需要提供一个外语srt文件即可，但是因为要调用百度通用翻译API接口，所以需要去百度AI开通申请，
并在代码中填写APi_id和key,才能实现此功能。不知道如何申请可自行百度，百度通用翻译API是免费的，但是
速度是有点慢的，但还可以接受。

![image](https://github.com/JackLiDi/Java-/blob/main/img/6.PNG)

功能2文件路径

![image](https://github.com/JackLiDi/Java-/blob/main/img/5.PNG)

填写自己申请的app_id和key

项目所需要的jar包都在resource文件下，下载导入即可

-----------------------------------------------------------------------------------------------
2021-1-31   更新

优化重构代码，新增函数判断文件编码格式
-----------------------------------------------------------------------------------------------


