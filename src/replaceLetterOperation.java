package test;

/*
 * 实现功能：将文件中的带字母行替换为空行
 */

public class replaceLetterOperation {

	public static void main(String[] args) {

		try {

			String iniPath = "D:/test/eng.srt";// 操作文件路径

			boolean resultMsg = fileTranslateOperation.fileReplaceLetterOperation(iniPath);

			if (resultMsg == true) {

				System.out.println("替换成功");

			} else {

				System.out.println("替换失败");

			}

		} catch (Exception e) {

			System.out.println("执行文件出错");

			e.printStackTrace();

		}

	}
}
