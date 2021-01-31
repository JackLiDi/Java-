package test;

/*
 * 实现功能：将两个文件中的内容逐行替换
 */

public class fileReplace {

	public static void main(String[] args) {

		try {

			String sourceFilePath = "D:/test/test/chi.srt";// 源文件路径

			String iniPath = "D:/test/test/eng.srt";// 操作文件路径

			boolean resultMsg = fileTranslateOperation.fileReplacementOperation(sourceFilePath, iniPath);

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
