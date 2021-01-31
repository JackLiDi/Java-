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

public class fileTranslateOperation {

	private static final String APP_ID = "20210114000671426"; // �ٶ�AI app_id
	private static final String SECURITY_KEY = "Oq1u7G2gki8IcnPDAMFF"; // �ٶ�AI SECURITY_KEY

	/*
	 * ��ȡ�ļ�ǰ�ж��ļ�����
	 * 
	 * @param newFile ��Ҫ�жϵ��ļ�
	 * 
	 * @return �ļ������ʽ
	 * 
	 * @date 2021-1-31
	 */
	static String fileJudgingCode(File newFile) {

		EncodingDetect s = new EncodingDetect();
		return EncodingDetect.javaname[s.detectEncoding(newFile)];

	}

	/*
	 * ���ļ���Ӣ���滻Ϊ����
	 * 
	 * @param iniPath �������ļ�·��
	 * 
	 * @return �������� true������ɹ� false������ʧ��
	 * 
	 * @date 2021-1-31
	 */
	static boolean fileReplaceLetterOperation(String iniPath) {

		File _file = new File(iniPath);// �滻�ļ�·��

		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		PrintWriter out = null;

		try {

			// �滻�ļ�����
			in = new FileInputStream(_file);
			in.read(fileContext);

			String fileCode = fileJudgingCode(_file); // �ж��ļ������ʽ

			String str = new String(fileContext, fileCode);
			String[] content = str.split("(\r\n|\r|\n|\n\r)");// ���ݻ��з��ָ��ַ���
			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// �ж�ÿ���Ƿ������ĸ

					if (str2) {// ����ַ����Ƿ�Ϊ��ĸ

						content[i] = content[i].replace(content[i], " ");

					}

					newStr += content[i] + "\r\n";
					// Thread.sleep(100);
					System.out.println(i);

				} else {
					newStr += content[i] + "\r\n";
					continue;
				}

			}

			out = new PrintWriter(_file, "utf-8");
			out.write(newStr);

			return true;

		} catch (IOException e) {
			return false;
		} finally {
			try {
				out.flush();
				out.close();
				in.close();

			} catch (IOException e) {
				return false;
			}

		}

	}

	/*
	 * �����滻�����ļ��е�����
	 * 
	 * @param sourceFilePath Դ�ļ�·�� iniPath �滻�ļ�·��
	 * 
	 * @return �������� true������ɹ� false������ʧ��
	 * 
	 * @date 2021-1-31
	 */
	static boolean fileReplacementOperation(String sourceFilePath, String iniPath) {

		File sourceFile = new File(sourceFilePath);// Դ�ļ�·��
		File _file = new File(iniPath);// �滻�ļ�·��

		byte[] sourceFileContext = new byte[(int) sourceFile.length()];
		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		FileInputStream sourceFileIn = null;
		PrintWriter out = null;

		try {
			// Դ�ļ���ȡ����
			sourceFileIn = new FileInputStream(sourceFile);
			sourceFileIn.read(sourceFileContext);

			String sourceFileCode = fileJudgingCode(sourceFile); // �ж��ļ������ʽ

			String sourceFileStr = new String(sourceFileContext, sourceFileCode);
			String[] sourceFileContent = sourceFileStr.split("(\r\n|\r|\n|\n\r)");// ���ݻ��з��ָ��ַ���

			// �滻�ļ�����
			in = new FileInputStream(_file);
			in.read(fileContext);

			String _fileCode = fileJudgingCode(_file); // �ж��ļ������ʽ

			String str = new String(fileContext, _fileCode);
			String[] content = str.split("(\r\n|\r|\n|\n\r)");// ���ݻ��з��ָ��ַ���
			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// �ж�ÿ���Ƿ������ĸ

					if (str2) {// ����ַ����Ƿ�Ϊ��ĸ

						content[i] = content[i].replace(content[i], sourceFileContent[i]);

					}

					newStr += content[i] + "\r\n";
					// Thread.sleep(100);
					System.out.println(i);

				} else {
					newStr += content[i] + "\r\n";
					continue;
				}

			}

			out = new PrintWriter(_file, "utf-8");
			out.write(newStr);

			return true;

		} catch (IOException e) {
			return false;
		} finally {
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

	/*
	 * ���ðٶ�Api�����ı�����
	 * 
	 * @param sourceTranslation ��Ҫ������ı�����
	 * 
	 * @return ��������
	 * 
	 * @date 2021-1-17
	 */
	static String translationOperation(String sourceTranslation) {

		try {

			TransApi api = new TransApi(APP_ID, SECURITY_KEY);

			String query = sourceTranslation;

			String resultMsg = api.getTransResult(query, "auto", "zh"); // �õ��ٶ�API���ص�Json�ַ���

			// �����ַ����õ���������
			if (resultMsg != null && !resultMsg.equals("")) {

				JsonObject jsonObj = (JsonObject) new JsonParser().parse(resultMsg); // ����json�ֶ�

				String res = jsonObj.get("trans_result").toString(); // ��ȡjson�ֶ��е�
																		// result�ֶΣ���Ϊresult�ֶα�����һ��json�����ֶΣ�����Ҫ��һ������

				JsonArray js = new JsonParser().parse(res).getAsJsonArray(); // ����json�����ֶ�

				jsonObj = (JsonObject) js.get(0); // result������ֻ��һ��Ԫ�أ�����ֱ��ȡ��һ��Ԫ��

				String translation = jsonObj.get("dst").getAsString(); // �õ�dst�ֶΣ������ģ������

				return translation;

			} else {

				System.out.println("����Ϊ�գ�");

				return null;

			}

		} catch (Exception e) {

			System.out.println("����ʧ��");

			e.printStackTrace();

			return null;
		}

	}

	/*
	 * ���ı����ݶ���Buffer��
	 * 
	 * @param buffer buffer
	 * 
	 * @param filepath �ļ�·��
	 * 
	 * @throws IOException
	 * 
	 * @date 2021-1-17
	 */
	static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {

		BufferedReader reader = null;
		FileInputStream is = null;

		is = new FileInputStream(filePath);
		String line = null; // ����ÿ������

		reader = new BufferedReader(new InputStreamReader(is, "utf-8"));

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
	 * 
	 * @param filePath �ļ�����·��
	 * 
	 * @return �ı�����
	 * 
	 * @throws IOException
	 * 
	 * @date 2021-1-17
	 */
	static String readFile(String filePath) throws IOException {

		StringBuffer sb = new StringBuffer();

		readToBuffer(sb, filePath);

		return sb.toString();

	}

	/*
	 * ��ȡ��Ļ�ļ������룬��д���������ļ�
	 * 
	 * @param iniPath �ļ�·��
	 * 
	 * @return �������� true:ִ�гɹ� false:ִ��ʧ��
	 * 
	 * @date 2021-1-17
	 */
	static boolean batchTranslationOperation(String iniPath) {

		File _file = new File(iniPath);// �滻�ļ�·��

		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		PrintWriter out = null;

		try {

			// �滻�ļ�����
			in = new FileInputStream(_file);
			in.read(fileContext);

			String fileCode = fileJudgingCode(_file); // �ж��ļ������ʽ

			String str = new String(fileContext, fileCode);

			String[] content = str.split("(\r\n|\r|\n|\n\r)"); // �����з������ַ���

			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// �ж�ÿ���Ƿ������ĸ

					if (str2) {// ��������ĸ����ðٶȷ��� ����������ֱ��д���ļ�

						String translationMsg = translationOperation(content[i]);

						content[i] = content[i].replace(content[i], translationMsg);

					}

					newStr += content[i] + "\r\n";

					Thread.sleep(800);

					System.out.println(i);

				} else {

					newStr += content[i] + "\r\n";

					continue;
				}

			}

			out = new PrintWriter(_file, "utf-8");

			out.write(newStr);

			return true;

		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
			return false;

		} finally {

			try {
				out.flush();
				in.close();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
				return false;
			}

		}

	}

}
