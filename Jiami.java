package Cipher;

import java.io.BufferedReader;
import java.util.ArrayList;//集合包
import java.util.Arrays;
import java.util.Scanner;
class Jiami {

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
	 String CSCipher(String password,String outkey) {//凯撒加密	输入生成字符串，取串中字符+密钥（超出循环）输出
		 String answers="";
		int key =Integer.parseInt(outkey);
		password= password.toUpperCase();
		for(int i=0;i<password.length();i++){
			char ch = password.charAt(i);
			if(ch>=65&&ch<=90) {
				ch+=key;
				while(ch>90) {
					ch-=26;
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
	 String STCipher(String password,String key2) {//单表置换加密 将输入密钥去重并与剩余字母重新组成新的字母序列，然后一一兑换
		 	String answers="";
			password= password.toUpperCase();
			key2 = removeRepeatChar(key2.toUpperCase());	
			for(int i=0;i<password.length();i++){
				if(key2.length()>26) {
					System.out.println("输入异常");
					break;
				}
				char ch = password.charAt(i);
				if(ch>=65&&ch<91) ch=key2.charAt(ch-65);			
				answers+=ch;
				} 
			return answers;
	 }
	 public String removeRepeatChar(String str) { //字符串处理函数		
		 	char Alpha[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i ++) {					
				char charWord = str.charAt(i);					
				int firstPosition = str.indexOf(charWord);			 
		        int lastPosition = str.lastIndexOf(charWord);				
				if (firstPosition  == lastPosition || firstPosition == i) {					
					sb.append(charWord);						
				}
			}
			str = sb.toString();
			for (int i = 0; i < Alpha.length; i++) {
		         if(str.indexOf(Alpha[i])==-1) {
		        	 str+=Alpha[i];
		         }	 
		      }
			return str;
		}	 
	 String VCipher(String password,String key2) {//维吉尼亚加密 根据密钥中字母ascll码-65生成需移位数组，将明文中每个字母加移位数组值进行置换（超出循环）
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
					ch+=Alpha[i%key2.length()];
					while(ch>90) {
						ch-=26;
					}
				}			
				answers+=ch;
				} 
			return answers;
	 }	 
	 String CCipher(String password,String key2) {//字符转换加密 根据输入密钥生成循环时用到的排列顺序数组，将明文按密钥长度划为二维数组，之后根据排列顺序数组抽取明文中对应列并输出为结果
		 	String answers="";
			password= password.toUpperCase();
			key2 = key2.toUpperCase();
			int key=key2.length();
			int[] numsz=ArFuc(key2);//生成密钥顺序的函数
			char Ccp[][] = new char[1+password.length()/key][key];
			for(int k=0;k<password.length()/key;k++) {
				Ccp[k]= password.substring(k*key, k*key+key).toCharArray();
			}
			Ccp[password.length()/key] = password.substring(password.length()-password.length()%key).toCharArray();
			for(int i=0;i<key;i++){//先列后行加密循环
				for(int j=0;j<1+password.length()/key;j++) {
					if(j==password.length()/key&&numsz[i]>=password.length()%key) break;		
					char ch =Ccp[j][numsz[i]];
					answers+=ch;
					}
				} 
			return answers;
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
	 String CCipherNum(String password,String outkey,int k1) {//转换加密(数字) 将明文按照数字切分为二维数组，按每列每行顺序抽取字符加总输出
			String answers="";
			int key =Integer.parseInt(outkey);
			password= password.toUpperCase();
			char Ccp[][] = new char[1+password.length()/key][key];
			for(int k=0;k<password.length()/key;k++) {
				Ccp[k]= password.substring(k*key, k*key+key).toCharArray();
			}
			Ccp[password.length()/key] = password.substring(password.length()-password.length()%key).toCharArray();	
			if(k1==1) {
				for(int i=0;i<key;i++){//先列后行加密循环
					for(int j=0;j<1+password.length()/key;j++) {
						if(j==password.length()/key&&i>=password.length()%key) break;		
						char ch =Ccp[j][i];
						answers+=ch;
						}
					}
			}else if(k1==2) {
				for(int j=0;j<1+password.length()/key;j++) {//字母倒排	将明文按照数字切分为二维数组，每一行倒排后加总输出
					String dpst=new StringBuffer(String.copyValueOf(Ccp[j])).reverse().toString();
					answers += dpst;
				}
			}			
			return answers;
	 }
	 
	 
	 
	 
	 
}































