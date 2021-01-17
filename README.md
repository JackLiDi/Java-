项目主要功能：

1.实现两种语言字幕文件内容的批量替换。即将外语srt字幕文件中的外文批量替换为翻译好的中文。
  实现代码在src目录下的fileReplace.java
  

2.实现代码自动调用百度通用翻译api，批量翻译外语srt字幕文件并自动生成中文srt字幕文件
 实现代码在src目录下的fastBatchTranslation.java
 
注意：以上两个功能不同点在于：功能1需先手动将外语字幕翻译成一份中文字幕，然后调用程序
即可实现两份文件的内容互换。文件格式如下：

![image](https://github.com/JackLiDi/Java-/blob/main/img/1.PNG)

![image](https://github.com/JackLiDi/Java-/blob/main/img/2.PNG)
