package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.baidu.translate.demo.TransApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/* 
 * �ù���ʵ�ֶ�ȡ��Ļ�ļ��е������ȡ�ٶ�API�������������
 * �����������ļ�
 * ����ʵ��������Ļ�ļ�����
 * */

public class fastBatchTranslation {
	
	private static final String APP_ID = "20210114000671426"; //�ٶ�AI app_id
	private static final String SECURITY_KEY = "Oq1u7G2gki8IcnPDAMFF"; //�ٶ�AI SECURITY_KEY
	
	
	/*���ðٶ�Api�����ı�����
	 * @param sourceTranslation ��Ҫ������ı�����
	 * @return ��������
	 * @date  2021-1-17
	 */
	static String translationOperation(String sourceTranslation) {
		
		try {
			
			TransApi api = new TransApi(APP_ID, SECURITY_KEY);

	        String query = sourceTranslation;
	        
	        String resultMsg = api.getTransResult(query, "auto", "zh"); //�õ��ٶ�API���ص�Json�ַ���
	        
	        //�����ַ����õ���������
	        if(resultMsg !=  null && !resultMsg.equals("")) {
	        	
	        	JsonObject  jsonObj = (JsonObject)new JsonParser().parse(resultMsg);    //����json�ֶ�
	        	
		        String res = jsonObj.get("trans_result").toString();    //��ȡjson�ֶ��е� result�ֶΣ���Ϊresult�ֶα�����һ��json�����ֶΣ�����Ҫ��һ������
		        
		        JsonArray  js = new JsonParser().parse(res).getAsJsonArray();    //����json�����ֶ�
		        
		        jsonObj = (JsonObject)js.get(0);    //result������ֻ��һ��Ԫ�أ�����ֱ��ȡ��һ��Ԫ��

		        String translation = jsonObj.get("dst").getAsString();  //�õ�dst�ֶΣ������ģ������
				
		        return translation;
				
	        }else {
	        	
	        	System.out.println("����Ϊ�գ�");
	        	
	        	return null;
	        	
	        }
	        
	        
			
		}catch(Exception e) {
			
			System.out.println("����ʧ��");
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}
    
	/*
	 * ���ı����ݶ���Buffer��
	 * @param buffer buffer
	 * @param filepath �ļ�·��
	 * @throws IOException
	 * @date  2021-1-17
	 */
	static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {

		BufferedReader reader = null;
		FileInputStream is = null;

		is = new FileInputStream(filePath);
		String line = null; // ����ÿ������

		reader = new BufferedReader(new InputStreamReader(is,"utf-8"));

		line = reader.readLine(); // ��ȡ��һ��

		while (line != null) {// lineΪ��˵��������

			buffer.append(line);
			buffer.append("\r\n");
			line = reader.readLine();

		}

		reader.close();
		is.close();

	}
	
	/*
	 * �xȡ�ı��ļ�����
	 * @param filePath �ļ�����·��
	 * @return �ı�����
	 * @throws IOException
	 * @date 2021-1-17
	 */
	static String readFile(String filePath) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		
		readToBuffer(sb,filePath);
		
		return sb.toString();
		
	}
	
	/*
	 * ��ȡ��Ļ�ļ������룬��д���������ļ�
	 * @param iniPath  �ļ�·��
	 * @return ��������   true:ִ�гɹ�   false:ִ��ʧ��
	 * @date 2021-1-17
	 */
	static boolean batchTranslationOperation(String iniPath) {


		File _file = new File(iniPath);//�滻�ļ�·��

		PrintWriter out = null;

		try {

			//�滻�ļ�����

			String recMsg = readFile(iniPath);

			String str = new String(recMsg.getBytes("utf-8"),"utf-8");

			String[] content = str.split("(\r\n|\r|\n|\n\r)"); //�����з������ַ���

			String newStr = new String();

			for(int i=0;i<content.length;i++) {

				if(!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");//�ж�ÿ���Ƿ������ĸ


					if (str2){//��������ĸ����ðٶȷ���     ����������ֱ��д���ļ�

						String translationMsg = translationOperation(content[i]);

						content[i] = content[i].replace(content[i], translationMsg);

					}

					newStr += content[i]+"\r\n";

					Thread.sleep(800);

					System.out.println(i);

				}else {

					newStr += content[i]+"\r\n";

					continue;
				}

			}


			out = new PrintWriter(_file,"utf-8");

			out.write(newStr);

			return true;

		} catch (IOException | InterruptedException e ) {

			return false;

		} finally{

			out.flush();

			out.close();

		}


	}

	
	public static void main(String[] args) {
		
		try {
			
			
			  	String iniPath = "D:/test/eng.srt";//�����ļ�·��
			
			  	boolean resultMsg = batchTranslationOperation(iniPath);
			
			  	if(resultMsg == true) {
			  		
			  		System.out.println("��������ɹ�");
			  		
			  	}else {
			  		
			  		System.out.println("��������ʧ��");
			  		
			  	}
			
			}catch(Exception e) {
				
				System.out.println("ִ���ļ�����");
				
				e.printStackTrace();
			}

	}

}
