package NameEntityRecognize;

import java.util.HashMap;

/** 
 * �����ڴ�ʵ��Trie����� 
 *  
 * @author single(����) 
 * @version 1.01, 10/11/2009 
 */  

public class TrieNode {
	/**���ؼ��֣���ֵΪӢ�Ĵ��е�һ��word*/  
    public String key = "";  
    /**�����word�ڴ����ĩβ����bound=true*/  
    public boolean bound=false;  
    /**ָ����һ������ָ��ṹ��������ŵ�ǰword���ڴ��е���һ��word��λ��*/  
    public HashMap<String,TrieNode> childs=new HashMap<String,TrieNode>();  
     
    //��ʼ�����캯��
    public TrieNode(){  
    }  
    //��ʼ�����캯��  
    public TrieNode(String k){  
        this.key=k;  
    }  
}
