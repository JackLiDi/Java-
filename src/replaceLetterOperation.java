package test;

/*
 * ʵ�ֹ��ܣ����ļ��еĴ���ĸ���滻Ϊ����
 */

public class replaceLetterOperation {

	public static void main(String[] args) {

		try {

			String iniPath = "D:/test/eng.srt";// �����ļ�·��

			boolean resultMsg = fileTranslateOperation.fileReplaceLetterOperation(iniPath);

			if (resultMsg == true) {

				System.out.println("�滻�ɹ�");

			} else {

				System.out.println("�滻ʧ��");

			}

		} catch (Exception e) {

			System.out.println("ִ���ļ�����");

			e.printStackTrace();

		}

	}
}
