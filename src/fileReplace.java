package test;

/*
 * ʵ�ֹ��ܣ��������ļ��е����������滻
 */

public class fileReplace {

	public static void main(String[] args) {

		try {

			String sourceFilePath = "D:/test/test/chi.srt";// Դ�ļ�·��

			String iniPath = "D:/test/test/eng.srt";// �����ļ�·��

			boolean resultMsg = fileTranslateOperation.fileReplacementOperation(sourceFilePath, iniPath);

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
