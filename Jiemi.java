package Cipher;

import java.io.BufferedReader;
import java.util.ArrayList;//���ϰ�
import java.util.Arrays;
import java.util.Scanner;
class Jiemi {

	public String Fuc(int num,String pwd,String K) {
		String answer ="";
		switch (num)
        {
            //�鿴����嵥
            case 1:
            	answer=CSCipher(pwd,K);
              break;//����switch��������������ж�
           case 2:
        	   answer=LDCipher(pwd);
                break;//����switch�жϣ�����ѭ��
           case 3:
        	   answer=STCipher(pwd,K);
        	   break;//����switch�жϣ�����ѭ��
           case 4:
        	   answer=VCipher(pwd,K);
        	   break;//����switch�жϣ�����ѭ��
           case 5:
        	   answer=CCipherNum(pwd,K,2);
        	   break;//����switch�жϣ�����ѭ��
           case 6:
        	   answer=CCipherNum(pwd,K,1);
        	   break;//����switch�жϣ�����ѭ��
           case 7:
        	   answer=CCipher(pwd,K);
        	   break;//����switch�жϣ�����ѭ��
            default :
                System.out.println("�������벻�Ϸ�~");//�������������ʱ��ʾ
                break;
        }
		return answer;
	};
	 String CSCipher(String password,String outkey) {//��������	���������ַ�����ȡ�����ַ�-��Կ������ѭ�������
		 String answers="";
		int key =Integer.parseInt(outkey);
		password= password.toUpperCase();
		for(int i=0;i<password.length();i++){
			char ch = password.charAt(i);
			if(ch>=65&&ch<=90) {
				ch-=key;
				while(ch<65) {
					ch+=26;
				}
			}
			
			answers+=ch;
			} 
		return answers;
	}	 
	 
	 String LDCipher(String password) {//��ĸ���ż��� ���������ַ�����ȡ�����ַ���ѭ��������ȡascll���룬����155��ȥ�����±��룬���ñ���õ����ַ����
		 	String answers="";
			password= password.toUpperCase();
			for(int i=0;i<password.length();i++){
				char ch = password.charAt(i);
				if(ch>=65&&ch<=90) {
					ch=(char) (155-Integer.valueOf(ch));
				}
				answers+=ch;
				} 
			return answers;
	}	 
	 String STCipher(String password,String key2) {//�����û����� ��������Կȥ�ز���ʣ����ĸ������ϣ���ԭ������ĸ���У����ò��Һ����ҵ���������ĸλ�ã��г�һ����ȷ�����У��������������û�����ȷ��ĸ
		 	String answers="";
			password= password.toUpperCase();
			key2 = removeRepeatChar(key2.toUpperCase());
			String key3="abcdefghijklmnopqrstuvwxyz";
			if(key2.length()>26)return "��������";
			for(int i=0;i<password.length();i++){
				char ch = password.charAt(i);
				if(ch>=65&&ch<91) ch=key3.charAt(key2.indexOf(ch));			
				answers+=ch;
				} 
			return answers.toUpperCase();
	 }
	 public String removeRepeatChar(String str) { //�ַ���������		
		 	char Alpha[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i ++) {//�ַ���ȥ�غ���		
				char charWord = str.charAt(i);					
				int firstPosition = str.indexOf(charWord);			 
		        int lastPosition = str.lastIndexOf(charWord);				
				if (firstPosition  == lastPosition || firstPosition == i) sb.append(charWord);
			}
			str = sb.toString();
			for (int i = 0; i < 26; i++) {
		         if(str.indexOf(Alpha[i])==-1) str+=Alpha[i];
		      }
			return str;
		}	 
	 String VCipher(String password,String key2) {//ά�����Ǽ��� ������Կ����ĸascll��-65��������λ���飬��������ÿ����ĸ����λ����ֵ�����û�������ѭ����
		 	String answers="";
			password= password.toUpperCase();
			key2 = key2.toUpperCase();	
			int Alpha[] = new int[key2.length()];
			for(int j = 0;j<key2.length();j++) {
				Alpha[j]=Integer.valueOf(key2.charAt(j))-65;
			}
			for(int i=0;i<password.length();i++){
				char ch = password.charAt(i);  
				if(ch>=65&&ch<91) {
					ch-=Alpha[i%key2.length()];
					while(ch<65) {
						ch+=26;
					}
				}			
				answers+=ch;
				} 
			return answers;
	 }	 
	 String CCipher(String password,String key2) {//�ַ�ת������
//		 ����������Կ���ɻ�ԭʱ�õ�������˳�����飬�������ַ�����Ϊһ��ת�ù���ľ��󣬰��г�ȡΪ�ַ�������
//����������˳��������л�ԭ�����������˳��ԭ���ַ�������ת�������У��������н��л�ԭ��
		 
		 	String answers="";
			password= password.toUpperCase();
			key2 = key2.toUpperCase();
			int key=key2.length();
			int[] numsz=ArFuc(key2);//������Կ˳��ĺ���
			String[] Ccp=new String[key];
			int RowNum=password.length()/key;
			int ColumnsNum=password.length()%key;
			int mark=0;
			System.out.println("���룺"+password);
			System.out.println("��Կ˳�����飺"+Arrays.toString(numsz));
			for(int i=0;i<key;i++) {
				if(numsz[i]<ColumnsNum&&ColumnsNum!=0) {
					Ccp[numsz[i]]=password.substring(mark, mark+RowNum+1);
					mark+=RowNum+1;
				}else {
					Ccp[numsz[i]]=password.substring(mark,mark+RowNum);	
					mark+=RowNum;
				}
			}
			for(String i:Ccp) answers+=i;			 
			System.out.println("answers��"+answers);
			String answers2=CCipherNum(answers,String.valueOf(key),1);
			System.out.println("�����"+answers2.toString());
			return answers2;
	 }		 
	 int[] ArFuc(String key) {//������Կ˳��ĺ���
		 char[] charsz=key.toCharArray();
		 int[] numsz=new int[key.length()];
		 for(int i=0;i<key.length();i++) numsz[i]= (int)charsz[i]-65;
		 int[] numsz2=new int[key.length()];
		 for(int j=0;j<key.length();j++) {
		 	int minIndex=0;
	        for(int i=0;i<numsz.length;i++){
	            if(numsz[i]<numsz[minIndex])minIndex=i;
	        }	
	        numsz2[minIndex]=j;
			 numsz[minIndex]=26;
		}	 
		 for(int i=0;i<key.length();i++) {
			 numsz[numsz2[i]]=i;
		 }
		 return numsz;
	 }
	 String CCipherNum(String password,String outkey,int k1) {//ת������(����)
			String answers="";
			int key =Integer.parseInt(outkey);
			password= password.toUpperCase();		
			if(k1==1) {//�������� ���ַ����������зֲ���Ϊת�þ��󣬰���˳��ԭ�������
				char[] Ccp=password.toCharArray();
				char[] Ccp2=new char[password.length()];
				int RowNum=password.length()/key;
				int ColumnsNum=password.length()%key;
				System.out.println(ColumnsNum+" "+password.length()+" "+RowNum);
				for(int i=0;i<password.length();i++) {
					if(i<(ColumnsNum+1)*(RowNum+1)-1&&ColumnsNum!=0) {
						System.out.println("����1ѭ��"+i);
						Ccp2[i/(RowNum+1)+key*(i%(RowNum+1))] = Ccp[i];
					}else if(ColumnsNum!=0&&i>=(ColumnsNum)*(RowNum+1)-1) {
						int k =i-ColumnsNum*(RowNum+1);
						System.out.println("�����ѭ��"+i);
						System.out.println("�����ѭ��"+(k/(RowNum)+key*(k%(RowNum))));
						Ccp2[ColumnsNum+k/RowNum+key*(k%RowNum)] = Ccp[i];
					}else if(ColumnsNum==0){ 
						Ccp2[i/(RowNum)+key*(i%(RowNum))] = Ccp[i];
					}else System.out.println("������"+Arrays.toString(Ccp2));
					System.out.println("�ָ�����״̬��"+Arrays.toString(Ccp2));
				}
				answers = String.valueOf(Ccp2);
			}else if(k1==2) {//���ֵ��� ���ַ����������з֣����ź�ԭ�������
				char Ccp[][] = new char[1+password.length()/key][key];
				for(int k=0;k<password.length()/key;k++) {
					Ccp[k]= password.substring(k*key, k*key+key).toCharArray();
				}
				Ccp[password.length()/key] = password.substring(password.length()-password.length()%key).toCharArray();	
				for(int j=0;j<1+password.length()/key;j++) {//��ĸ����	
					String dpst=new StringBuffer(String.copyValueOf(Ccp[j])).reverse().toString();
					answers += dpst;
				}
			}			 
			return answers;
	 }
	 
	 
	 
	 
	 
}































