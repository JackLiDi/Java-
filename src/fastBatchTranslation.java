package test;

/* 
 * �ù���ʵ�ֶ�ȡ��Ļ�ļ��е������ȡ�ٶ�API�������������
 * �����������ļ�
 * ����ʵ��������Ļ�ļ�����
 * */

public class fastBatchTranslation {

	public static void main(String[] args) {

		try {

			String iniPath = "D:/test/eng.srt";// �����ļ�·��
            
			
			boolean resultMsg = fileTranslateOperation.batchTranslationOperation(iniPath);

			if (resultMsg == true) {

				System.out.println("��������ɹ�");

			} else {

				System.out.println("��������ʧ��");

			}

		} catch (Exception e) {

			System.out.println("ִ���ļ�����");

			e.printStackTrace();
		}

	}

}
