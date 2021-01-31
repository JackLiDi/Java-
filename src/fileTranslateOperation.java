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

	private static final String APP_ID = "20210114000671426"; // 百度AI app_id
	private static final String SECURITY_KEY = "Oq1u7G2gki8IcnPDAMFF"; // 百度AI SECURITY_KEY

	/*
	 * 读取文件前判断文件编码
	 * 
	 * @param newFile 需要判断的文件
	 * 
	 * @return 文件编码格式
	 * 
	 * @date 2021-1-31
	 */
	static String fileJudgingCode(File newFile) {

		EncodingDetect s = new EncodingDetect();
		return EncodingDetect.javaname[s.detectEncoding(newFile)];

	}

	/*
	 * 将文件中英文替换为空行
	 * 
	 * @param iniPath 待处理文件路径
	 * 
	 * @return 布尔类型 true：处理成功 false：处理失败
	 * 
	 * @date 2021-1-31
	 */
	static boolean fileReplaceLetterOperation(String iniPath) {

		File _file = new File(iniPath);// 替换文件路径

		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		PrintWriter out = null;

		try {

			// 替换文件操作
			in = new FileInputStream(_file);
			in.read(fileContext);

			String fileCode = fileJudgingCode(_file); // 判断文件编码格式

			String str = new String(fileContext, fileCode);
			String[] content = str.split("(\r\n|\r|\n|\n\r)");// 根据换行符分割字符串
			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// 判断每行是否包含字母

					if (str2) {// 检查字符串是否为字母

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
	 * 逐行替换两个文件中的内容
	 * 
	 * @param sourceFilePath 源文件路径 iniPath 替换文件路径
	 * 
	 * @return 布尔类型 true：处理成功 false：处理失败
	 * 
	 * @date 2021-1-31
	 */
	static boolean fileReplacementOperation(String sourceFilePath, String iniPath) {

		File sourceFile = new File(sourceFilePath);// 源文件路径
		File _file = new File(iniPath);// 替换文件路径

		byte[] sourceFileContext = new byte[(int) sourceFile.length()];
		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		FileInputStream sourceFileIn = null;
		PrintWriter out = null;

		try {
			// 源文件读取操作
			sourceFileIn = new FileInputStream(sourceFile);
			sourceFileIn.read(sourceFileContext);

			String sourceFileCode = fileJudgingCode(sourceFile); // 判断文件编码格式

			String sourceFileStr = new String(sourceFileContext, sourceFileCode);
			String[] sourceFileContent = sourceFileStr.split("(\r\n|\r|\n|\n\r)");// 根据换行符分割字符串

			// 替换文件操作
			in = new FileInputStream(_file);
			in.read(fileContext);

			String _fileCode = fileJudgingCode(_file); // 判断文件编码格式

			String str = new String(fileContext, _fileCode);
			String[] content = str.split("(\r\n|\r|\n|\n\r)");// 根据换行符分割字符串
			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// 判断每行是否包含字母

					if (str2) {// 检查字符串是否为字母

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
	 * 调用百度Api翻译文本内容
	 * 
	 * @param sourceTranslation 需要翻译的文本内容
	 * 
	 * @return 译文内容
	 * 
	 * @date 2021-1-17
	 */
	static String translationOperation(String sourceTranslation) {

		try {

			TransApi api = new TransApi(APP_ID, SECURITY_KEY);

			String query = sourceTranslation;

			String resultMsg = api.getTransResult(query, "auto", "zh"); // 得到百度API返回的Json字符串

			// 解析字符串得到译文内容
			if (resultMsg != null && !resultMsg.equals("")) {

				JsonObject jsonObj = (JsonObject) new JsonParser().parse(resultMsg); // 解析json字段

				String res = jsonObj.get("trans_result").toString(); // 获取json字段中的
																		// result字段，因为result字段本身即是一个json数组字段，所以要进一步解析

				JsonArray js = new JsonParser().parse(res).getAsJsonArray(); // 解析json数组字段

				jsonObj = (JsonObject) js.get(0); // result数组中只有一个元素，所以直接取第一个元素

				String translation = jsonObj.get("dst").getAsString(); // 得到dst字段，即译文，并输出

				return translation;

			} else {

				System.out.println("翻译为空！");

				return null;

			}

		} catch (Exception e) {

			System.out.println("翻译失败");

			e.printStackTrace();

			return null;
		}

	}

	/*
	 * 将文本内容读入Buffer中
	 * 
	 * @param buffer buffer
	 * 
	 * @param filepath 文件路径
	 * 
	 * @throws IOException
	 * 
	 * @date 2021-1-17
	 */
	static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {

		BufferedReader reader = null;
		FileInputStream is = null;

		is = new FileInputStream(filePath);
		String line = null; // 保存每行内容

		reader = new BufferedReader(new InputStreamReader(is, "utf-8"));

		line = reader.readLine(); // 读取第一行

		while (line != null) {// line为空说明读完了

			buffer.append(line);
			buffer.append("\r\n");
			line = reader.readLine();

		}

		reader.close();
		is.close();

	}

	/*
	 * 讀取文本文件内容
	 * 
	 * @param filePath 文件所在路徑
	 * 
	 * @return 文本内容
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
	 * 读取字幕文件，翻译，并写入生成新文件
	 * 
	 * @param iniPath 文件路径
	 * 
	 * @return 布尔类型 true:执行成功 false:执行失败
	 * 
	 * @date 2021-1-17
	 */
	static boolean batchTranslationOperation(String iniPath) {

		File _file = new File(iniPath);// 替换文件路径

		byte[] fileContext = new byte[(int) _file.length()];

		FileInputStream in = null;
		PrintWriter out = null;

		try {

			// 替换文件操作
			in = new FileInputStream(_file);
			in.read(fileContext);

			String fileCode = fileJudgingCode(_file); // 判断文件编码格式

			String str = new String(fileContext, fileCode);

			String[] content = str.split("(\r\n|\r|\n|\n\r)"); // 按换行符分离字符串

			String newStr = new String();

			for (int i = 0; i < content.length; i++) {

				if (!content[i].equals("")) {

					boolean str2 = content[i].matches(".*[a-zA-z].*");// 判断每行是否包含字母

					if (str2) {// 若包含字母则调用百度翻译 否则则跳出直接写入文件

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
