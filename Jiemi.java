package Cipher;

import java.io.BufferedReader;
import java.util.ArrayList;//集合包
import java.util.Arrays;
import java.util.Scanner;
class Jiemi {

	public String Fuc(int num,String pwd,String K) {
		String answer ="";
		switch (num)
        {
            //查看库存清单
            case 1:
            	answer=CSCipher(pwd,K);
              break;//跳出switch，继续输入序号判断
           case 2:
        	   answer=LDCipher(pwd);
                break;//跳出switch判断，继续循环
           case 3:
        	   answer=STCipher(pwd,K);
        	   break;//跳出switch判断，继续循环
           case 4:
        	   answer=VCipher(pwd,K);
        	   break;//跳出switch判断，继续循环
           case 5:
        	   answer=CCipherNum(pwd,K,2);
        	   break;//跳出switch判断，继续循环
           case 6:
        	   answer=CCipherNum(pwd,K,1);
        	   break;//跳出switch判断，继续循环
           case 7:
        	   answer=CCipher(pwd,K);
        	   break;//跳出switch判断，继续循环
            default :
                System.out.println("您的输入不合法~");//当输入其他情况时显示
                break;
        }
		return answer;
	};
	 String CSCipher(String password,String outkey) {//凯撒加密	输入生成字符串，取串中字符-密钥（超出循环）输出
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
	 
	 String LDCipher(String password) {//字母倒排加密 输入生成字符串，取串中字符（循环），提取ascll编码，并用155减去生成新编码，利用编码得到新字符输出
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
	 String STCipher(String password,String key2) {//单表置换加密 将输入密钥去重并与剩余字母重新组合，还原加密字母序列，利用查找函数找到密文中字母位置，列出一份正确的排列，根据它将密文置换回正确字母
		 	String answers="";
			password= password.toUpperCase();
			key2 = removeRepeatChar(key2.toUpperCase());
			String key3="abcdefghijklmnopqrstuvwxyz";
			if(key2.length()>26)return "超长输入";
			for(int i=0;i<password.length();i++){
				char ch = password.charAt(i);
				if(ch>=65&&ch<91) ch=key3.charAt(key2.indexOf(ch));			
				answers+=ch;
				} 
			return answers.toUpperCase();
	 }
	 public String removeRepeatChar(String str) { //字符串处理函数		
		 	char Alpha[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i ++) {//字符串去重函数		
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
	 String VCipher(String password,String key2) {//维吉尼亚加密 根据密钥中字母ascll码-65生成需移位数组，将明文中每个字母减移位数组值进行置换（超出循环）
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
	 String CCipher(String password,String key2) {//字符转换加密
//		 根据输入密钥生成还原时用到的排列顺序数组，将密文字符串视为一个转置过后的矩阵，按列抽取为字符串数组
//并按照排列顺序数组进行还原，最后将所获列顺序还原的字符串套入转换（行列）：函数中进行还原。
		 
		 	String answers="";
			password= password.toUpperCase();
			key2 = key2.toUpperCase();
			int key=key2.length();
			int[] numsz=ArFuc(key2);//生成密钥顺序的函数
			String[] Ccp=new String[key];
			int RowNum=password.length()/key;
			int ColumnsNum=password.length()%key;
			int mark=0;
			System.out.println("输入："+password);
			System.out.println("密钥顺序数组："+Arrays.toString(numsz));
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
			System.out.println("answers："+answers);
			String answers2=CCipherNum(answers,String.valueOf(key),1);
			System.out.println("结果："+answers2.toString());
			return answers2;
	 }		 
	 int[] ArFuc(String key) {//生成密钥顺序的函数
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
	 String CCipherNum(String password,String outkey,int k1) {//转换加密(数字)
			String answers="";
			int key =Integer.parseInt(outkey);
			password= password.toUpperCase();		
			if(k1==1) {//数字行列 将字符串按数字切分并视为转置矩阵，按列顺序还原加总输出
				char[] Ccp=password.toCharArray();
				char[] Ccp2=new char[password.length()];
				int RowNum=password.length()/key;
				int ColumnsNum=password.length()%key;
				System.out.println(ColumnsNum+" "+password.length()+" "+RowNum);
				for(int i=0;i<password.length();i++) {
					if(i<(ColumnsNum+1)*(RowNum+1)-1&&ColumnsNum!=0) {
						System.out.println("进入1循环"+i);
						Ccp2[i/(RowNum+1)+key*(i%(RowNum+1))] = Ccp[i];
					}else if(ColumnsNum!=0&&i>=(ColumnsNum)*(RowNum+1)-1) {
						int k =i-ColumnsNum*(RowNum+1);
						System.out.println("进入二循环"+i);
						System.out.println("进入二循环"+(k/(RowNum)+key*(k%(RowNum))));
						Ccp2[ColumnsNum+k/RowNum+key*(k%RowNum)] = Ccp[i];
					}else if(ColumnsNum==0){ 
						Ccp2[i/(RowNum)+key*(i%(RowNum))] = Ccp[i];
					}else System.out.println("出错啦"+Arrays.toString(Ccp2));
					System.out.println("恢复数组状态："+Arrays.toString(Ccp2));
				}
				answers = String.valueOf(Ccp2);
			}else if(k1==2) {//数字倒排 将字符串按数字切分，倒排后还原并输出。
				char Ccp[][] = new char[1+password.length()/key][key];
				for(int k=0;k<password.length()/key;k++) {
					Ccp[k]= password.substring(k*key, k*key+key).toCharArray();
				}
				Ccp[password.length()/key] = password.substring(password.length()-password.length()%key).toCharArray();	
				for(int j=0;j<1+password.length()/key;j++) {//字母倒排	
					String dpst=new StringBuffer(String.copyValueOf(Ccp[j])).reverse().toString();
					answers += dpst;
				}
			}			 
			return answers;
	 }
	 
	 
	 
	 
	 
}































