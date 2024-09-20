package com.nowcoder.community.util;


import com.nowcoder.community.controller.LoginController;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    TrieNode root = new TrieNode();

    @PostConstruct
    public void init(){
        try(
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        ){
            String keyword;
            while ((keyword = bufferedReader.readLine())!=null){
                this.addKeyword(keyword);
            }
        }catch (IOException e){
            logger.error("加载敏感词失败"+e.getMessage());
        }
    }
    private void addKeyword(String keyword){
        TrieNode tempNode = root;
        for(int i=0;i<keyword.length();i++){
            char c = keyword.charAt(i);
            TrieNode subNodes = tempNode.getSubNodes(c);
            if(subNodes == null){
                subNodes = new TrieNode();
                tempNode.addSubNodes(c,subNodes);
            }
            tempNode = subNodes;
            if(i == keyword.length() - 1){
                tempNode.setKeywordEnd(true);
            }
        }
    }

    /**
     *
     * @param text:需要过滤的文本
     * @return 过滤之后的文本
     */
    public String filter(String text){
        if(StringUtils.isBlank(text)){
            return null;
        }
        TrieNode tempNode = new TrieNode();
        int begin = 0;
        int position = 0;
        StringBuilder sb = new StringBuilder();
        while (position<text.length()){
            char c = text.charAt(position);
            if(isSymbol(c)){
                if(tempNode==root){
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            tempNode = tempNode.getSubNodes(c);
            if(tempNode == null){
                //以begin开头的不是敏感词
                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = root;

            }else if(tempNode.isKeywordEnd()){
                sb.append(REPLACEMENT);
                begin = ++position;
                tempNode = root;
            }else {
                position++;
            }
        }
        sb.append(text.substring(begin));
        return sb.toString();
    }

    private boolean isSymbol(Character c){
        //东亚文字范畴
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    public class TrieNode{
        private boolean isKeywordEnd = false;



        private Map<Character,TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd(){
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        public void addSubNodes(Character c,TrieNode node){
            subNodes.put(c,node);
        }
        public TrieNode getSubNodes(Character c){
            return subNodes.get(c);
        }
    }

}
