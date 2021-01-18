package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class fileReplace {
	
	static boolean  fileReplacementOperation(String sourceFilePath,String iniPath) {

		File sourceFile = new File(sourceFilePath);//Դ�ļ�·��
		File _file = new File(iniPath);//�滻�ļ�·��

		byte[] sourceFileContext = new byte[(int) sourceFile.length()];
		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		FileInputStream sourceFileIn = null;
		PrintWriter out = null;

		try {
			//Դ�ļ���ȡ����
			sourceFileIn = new FileInputStream(sourceFile);
			sourceFileIn.read(sourceFileContext);
			String sourceFileStr = new String(sourceFileContext,"utf-8");
			String[] sourceFileContent = sourceFileStr.split("(\r\n|\r|\n|\n\r)");//���ݻ��з��ָ��ַ���



			//�滻�ļ�����
			in = new FileInputStream(_file);
			in.read(fileContext);
			String str = new String(fileContext,"utf-8");
			String[] content = str.split("(\r\n|\r|\n|\n\r)");//���ݻ��з��ָ��ַ���
			String newStr = new String();

			for(int i=0;i<content.length;i++) {

				if(!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");//�ж�ÿ���Ƿ������ĸ

					if (str2){//����ַ����Ƿ�Ϊ��ĸ

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

			String sourceFilePath = "D:/test/test/chi.srt";//Դ�ļ�·��
			
			String iniPath = "D:/test/test/eng.srt";//�����ļ�·��

			boolean resultMsg = fileReplacementOperation(sourceFilePath,iniPath);

			if(resultMsg == true) {
				
				System.out.println("�滻�ɹ�");
				
			}else {
				
				System.out.println("�滻ʧ��");
				
			}

		}catch(Exception e) {
			
			System.out.println("ִ���ļ�����");
			
			e.printStackTrace();
			
		}



	}

}
