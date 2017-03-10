package NameEntityRecognize;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;




public class TrieDictionary
{
	  private static TrieDictionary dict = null;
	 
	  //����ԭ�ʹʸ��ڵ�
	  private TrieNode root = new TrieNode();
	  
	  //���������ʸ��ڵ�
	  private TrieNode root_derivative = new TrieNode();
	  
	  //����importDict���������ֵ�
	  private TrieDictionary(String fn) {
	    importDict(fn);
	  }
	  
	  //����TrieDictionary���͵ĺ�����ʵ����һ���ļ���
	  public static TrieDictionary getInstance(String fileName) {
		System.out.println("nigedasb");
	    if (dict == null)
	      dict = new TrieDictionary(fileName);
	      
	    return dict;
	  }
	
	  //�����ֵ��ļ��е�����
	  private boolean importDict(String fileName)
	  
	  {
	    try
	    {
	      System.out.println("fileName:"+fileName);
	      //����ʵ��е�����
	      Scanner in = new Scanner(new File(fileName),"UTF-8"); 
	      
	      String word = "";
	      int i = 0;
	      while (in.hasNextLine()) {
              word = in.nextLine();
              //System.out.println(i+":"+word);
  	    	  i ++;
  	    	  
  	    	  //����ֵ䵽�ڴ�
  	          add(word);
//  	          System.out.println("i: " + i);
          } 
          in.close();
          System.out.println("i: " + i);
//          System.out.println("word: " + word);
          System.out.println("Construct Dict Finish!");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return true;
	  }
	  
	  //�����ֵ���
	  public void add(String term)
	  {
	    TrieNode p = this.root;
	    TrieNode p_derivative = this.root_derivative;
	    int i;
	    String word = "";
	    String word_derivative = "";
	    WordNetHelper getstem = new WordNetHelper();
	    for (i = 0; i < term.length(); ++i) {
		      char c = term.charAt(i);
		      if(c == ' '||i == (term.length()-1)||NonChiSplit.isCharSeperator(c)){
		    	 if(word.length() > 0){
			    	  //���c�ǿո����Ѿ��õ�һ��word�������ֵ����ķ��������ֵ����Ĺ���
			    	  if(c == ' '){
			    		  word_derivative = getstem.findStem(word);
			    		  word_derivative = word_derivative.toLowerCase();//�����д�дת����Сд��ĸ
			    		  TrieNode pChild =  null;
			    		  TrieNode pChild_derivative = null;
//			    		  TrieNode pChild = new TrieNode();
//			    		  TrieNode pChild_derivative = new TrieNode();
			    		  //�����word�����ڣ��򽫸�word���뵽�ֵ�����
			    	      if((pChild = (TrieNode)p.childs.get(word)) == null) {
			    	          pChild = new TrieNode(word);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p.childs.put(word, pChild);
			    	      }
			    	      
			    	      //��������ʲ����ڣ��������ʼ��뵽�������ֵ���
			    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
			    	          pChild_derivative = new TrieNode(word_derivative);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p_derivative.childs.put(word_derivative, pChild_derivative);
			    	      }
			    	      
			    	      //ָ��ָ���word
			    	      p = pChild;
			    	      p_derivative = pChild_derivative;
			    	  }
			    	  //����Ǳ�����
			    	  else if(NonChiSplit.isCharSeperator(c)){
			    		  word_derivative = getstem.findStem(word);
			    		  word_derivative = word_derivative.toLowerCase();//�����д�дת����Сд��ĸ
			    		  TrieNode pChild = null;
			    		  TrieNode pChild_derivative = null;
//			    		  TrieNode pChild = new TrieNode();
//			    		  TrieNode pChild_derivative = new TrieNode();
			    		  //�����word�����ڣ��򽫸�word���뵽�ֵ�����
			    	      if((pChild = (TrieNode)p.childs.get(word)) == null) {
			    	          pChild = new TrieNode(word);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p.childs.put(word, pChild);
			    	      }
			    	      
			    	      //��������ʲ����ڣ��������ʼ��뵽�������ֵ���
			    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
			    	          pChild_derivative = new TrieNode(word_derivative);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p_derivative.childs.put(word_derivative, pChild_derivative);
			    	      }
			    	      
			    	      //ָ��ָ���word
			    	      p = pChild;
			    	      p_derivative = pChild_derivative;
			    	  }
			    	  //����Ǹö�������һ����ĸ���������һ������
			    	  else if(i == (term.length()-1)){
			    		  if(!NonChiSplit.isCharSeperator(c)){
			    				word += c;
			    		  }
			    		  //word += c; 
			    		  word_derivative = getstem.findStem(word);
			    		  word_derivative = word_derivative.toLowerCase();//�����д�дת����Сд��ĸ
			    		  
			    		  TrieNode pChild = null;
			    		  TrieNode pChild_derivative = null;
//			    		  TrieNode pChild = new TrieNode();
//			    		  TrieNode pChild_derivative = new TrieNode();
			    		  
			    	      if ((pChild = (TrieNode)p.childs.get(word)) == null) {
			    	          pChild = new TrieNode(word);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p.childs.put(word, pChild);
			    	      }
			    	      
			    	      //��������ʲ����ڣ��������ʼ��뵽�������ֵ���
			    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
			    	          pChild_derivative = new TrieNode(word_derivative);
			    	          //System.out.println("pChild:" + pChild.key);
			    	          p_derivative.childs.put(word_derivative, pChild_derivative);
			    	      }
			    	      
			    	      p = pChild;
			    	      p_derivative = pChild_derivative;
			    	  }
		    	  }
		    	  //System.out.println(word);
		    	  word = "";
		      }
		      else{
		    	  word += c; 
		      }
	    }
	    p.bound = true;
	    p_derivative.bound = true;
	   // System.out.println("pChild:" + p.key +" "+"p.bound:"+" "+p.bound);
	   // System.out.println("pChild_derivative:" + p_derivative.key +" "+"p_derivative.bound:"+" "+p_derivative.bound);
	  }
	
//	  //��ѯ�ֵ���
//	  public boolean contains(String term)
//	  {
//		    TrieNode p = this.root;
//		    int i;
//		    String word = "";
//		    for (i = 0; i < term.length(); ++i) {
//			      char c = term.charAt(i);
////			      TrieNode pChild = null;
////			      if ((pChild = (TrieNode)p.childs.get(Character.valueOf(c))) == null) break;
////			      p = pChild;
//			      if(c == ' '||i == (term.length()-1)){
//			    	  //���c�ǿո����Ѿ��õ�һ��word�������ֵ����ķ��������ֵ����Ĺ���
//			    	  if(c== ' '){
//			    		  TrieNode pChild = null;
//			    		  //�����word�����ڣ��򽫸�word���뵽�ֵ�����
//			    	      if((pChild = (TrieNode)p.childs.get(word)) == null) {
//			    	          break;
//			    	      }
//			    	      //ָ��ָ���word
//			    	      p = pChild;
//			    	  }
//			    	  //����Ǹö�������һ����ĸ���������һ������
//			    	  else if(i == (term.length()-1)){
//			    		  word += c; 
//			    		  TrieNode pChild = null;
//			    	      if ((pChild = (TrieNode)p.childs.get(word)) == null) {
//			    	         break;
//			    	      }
//			    	      p = pChild;
//			    	  }
//			    	  System.out.println("contains:"+word);
//			    	  word = "";
//			      }
//			      else{
//			    	  word += c; 
//			      }
//		    }
//		
//		    return ((i == word.length()) && (p.bound));//���ҵ�����ƥ��
//	  }
//	
	  public TrieNode getRoot()
	  {
	     return this.root;
	  }
	  public TrieNode getRootDerivative()
	  {
		  return this.root_derivative;
	  }
}
