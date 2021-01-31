package test;

/* 
 * 该功能实现读取字幕文件中的外语，调取百度API批量翻译成中文
 * 并生成中文文件
 * 快速实现外语字幕文件翻译
 * */

public class fastBatchTranslation {

	public static void main(String[] args) {

		try {

			String iniPath = "D:/test/eng.srt";// 操作文件路径
            
			
			boolean resultMsg = fileTranslateOperation.batchTranslationOperation(iniPath);

			if (resultMsg == true) {

				System.out.println("批量翻译成功");

			} else {

				System.out.println("批量翻译失败");

			}

		} catch (Exception e) {

			System.out.println("执行文件出错");

			e.printStackTrace();
		}

	}

}
