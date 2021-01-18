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
			String[] sourceFileContent = sourceFileStr.split("(\r\n|\r|\n|\n\r)");//根据换行符分割字符串



			//替换文件操作
			in = new FileInputStream(_file);
			in.read(fileContext);
			String str = new String(fileContext,"utf-8");
			String[] content = str.split("(\r\n|\r|\n|\n\r)");//根据换行符分割字符串
			String newStr = new String();

			for(int i=0;i<content.length;i++) {

				if(!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");//判断每行是否包含字母

					if (str2){//检查字符串是否为字母

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

			String sourceFilePath = "D:/test/test/chi.srt";//源文件路径
			
			String iniPath = "D:/test/test/eng.srt";//操作文件路径

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
