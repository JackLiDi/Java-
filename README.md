昨天想看部奥斯卡老片，结果发现居然没有中文版。 为了更方便理解电影内容，下载了罗马尼亚语srt字幕。

但是，碍于水平有限，只能用谷歌翻译快速翻译完。在压制视频时，却发现时间戳由于谷歌翻译的影响。变得不可用。

                                                                                                                                               
于是，只能把原始srt的时间戳复制到新翻译文件中。但是字幕对话多达几千条，一条条复制真的太

费时间。由于之前做过一段时间程序员，让我想起了Java文件批量操作。好了，步入正题，直接上代码。

                                                                                                                                               

package test;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
 
public class fileReplace {
	
	static boolean  fileReplacementOperation(String sourceFilePath,String iniPath) {
		
		File sourceFile = new File(sourceFilePath);//源文件路径
		File _file = new File(iniPath);//替换文件路径
		
		byte[] sourceFileContext = new byte[(int) sourceFile.length()];
		byte[] fileContext = new byte[(int) _file.length()];
		
		FileInputStream in = null;
		FileInputStream sourceFileIn = null;
		PrintWriter out = null;
		
		try {
			//源文件读取操作
			sourceFileIn = new FileInputStream(sourceFile);
			sourceFileIn.read(sourceFileContext);
			String sourceFileStr = new String(sourceFileContext,"utf-8");
			String[] sourceFileContent = sourceFileStr.split("\r\n");
			
			
			
			//替换文件操作
		    in = new FileInputStream(_file);
		    in.read(fileContext);
		    String str = new String(fileContext,"utf-8");
		    String[] content = str.split("\r\n");
		    String newStr = new String();
		    
		    for(int i=0;i<content.length;i++) {
		    	
		    	if(!content[i].equals("")) {
		    		
		    		 if (!Character.isDigit(content[i].charAt(0))){//检查字符串首字符是否为数字
		    			 content[i] = content[i].replace(content[i], sourceFileContent[i]);
		    			 
		    		 }
		    		 
		    		 newStr += content[i]+"\r\n";
//	    		     Thread.sleep(100);
		    		 System.out.println(i);
		    		
		    	}else {
		    		newStr += content[i]+"\r\n";
		    		continue;
		    	}
		    	
		    }
		    
 
		    out = new PrintWriter(_file,"utf-8");
		    out.write(newStr);
		    
		    return true;
		    
		} catch (IOException e ) {
		    return false;
		} finally{
		    try {
			out.flush();
			out.close();
			in.close();
			sourceFileIn.close();
			
		    } catch (IOException e) {
			return false;
		    }
		    
		}
		
		
	}
    
	
	
	public static void main(String[] args) {
		
		try {
			
			String sourceFilePath = "D:/test/chi.srt";//源文件路径
			String iniPath = "D:/test/eng.srt";//操作文件路径
			
			boolean resultMsg = fileReplacementOperation(sourceFilePath,iniPath);
			
			if(resultMsg == true) {
				System.out.println("替换成功");
			}else {
				System.out.println("替换失败");
			}
			
		}catch(Exception e) {
			System.out.println("执行文件出错");
			e.printStackTrace();
		}
		
		
 
	}
 
}
由于要换时间戳，所以只需要通过代码将旧srt中的罗马尼亚语，逐行替换为中文即可。

 运行程序注意需要将两个字幕文件放到同一文件夹下,我的是放在D盘test文件夹下，


 如果你不是在这个路径下，需要修改代码中对应的路径

                                                                                                                                    
由于是Java程序，需要安装Java虚拟机，如有需要可以在以下链接下载（Java虚拟机，代码文件都在里面）

链接：https://pan.baidu.com/s/1KOyavFDUSOo_BvYF2ZZH1A 
  提取码：eg9u 
                                                                                                                                    

java虚拟机下载后只需要打开，一直点确定就能完成安装。

安装完成虚拟机后，就可以运行Java文件了。

如何在命令行运行Java文件可以自行百度一下。

虽然谷歌翻译效果差强人意，但是理解电影的意思还是足够了。而且效率高，几千条字幕几分钟翻译完毕。然后用以上代码更换时间戳，然后等压制完成，就可以完成一部老电影的字幕翻译工作。

然后就可以美滋滋的欣赏老电影了，再也不用因为有限的外语水平影响观影的体验了
