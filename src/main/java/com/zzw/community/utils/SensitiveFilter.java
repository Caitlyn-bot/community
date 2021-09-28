package com.zzw.community.utils;

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

/**
 * 敏感词过滤器
 * @author 张志伟
 * @version v1.0
 */
@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);
    /**
     * 替换符
     */
    private static final String REPLACEMENT = "***";
    /**
     * 初始化根结点
     */
    private TrieNode rootNode = new TrieNode();

    /**
     * 在这个容器被初始化后，这个被@PostConstruct修饰的方法就会被调用
     */
    @PostConstruct
    public void init(){
        try(
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            ){
            String keyword;
            while ((keyword = reader.readLine()) != null){
                //添加到前缀树
                this.addKeyword(keyword);
            }
        }catch (IOException e){
            logger.error("加载敏感词文件失败："+e.getMessage());
        }
    }

    /**
     * 添加敏感词到前缀树
     * @param keyword
     */
    private void addKeyword(String keyword){
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null){
                //初始化子结点
                subNode = new TrieNode();
                tempNode.addSubNode(c,subNode);
            }
            //让子结点作为tempNode进入下一层
            tempNode = subNode;
            if (i == keyword.length() - 1){
                //设置结束标识
                tempNode.setKeywordEnd(true);
            }
        }
    }

    /**
     * 打印前缀树
     */
    public void print(){
        System.out.println(rootNode.subNodes);
    }

    /**
     * 过滤敏感词
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text){
        if (StringUtils.isBlank(text)){
            return null;
        }
        // 指针1
        TrieNode tempNode = rootNode;
        // 指针2
        int begin = 0;
        // 指针3
        int position = 0;
        // 结果
        StringBuilder sb = new StringBuilder();

        while(begin < text.length()){
            if(position < text.length()) {
                Character c = text.charAt(position);
                // 跳过符号
                if (isSymbol(c)) {
                    if (tempNode == rootNode) {
                        begin++;
                        sb.append(c);
                    }
                    position++;
                    continue;
                }

                // 检查下级节点
                tempNode = tempNode.getSubNode(c);
                if (tempNode == null) {
                    // 以begin开头的字符串不是敏感词
                    sb.append(text.charAt(begin));
                    // 进入下一个位置
                    position = ++begin;
                    // 重新指向根节点
                    tempNode = rootNode;
                }
                // 发现敏感词
                else if (tempNode.isKeywordEnd()) {
                    sb.append(REPLACEMENT);
                    begin = ++position;
                }
                // 检查下一个字符
                else {
                    position++;
                }
            }
            // position遍历越界仍未匹配到敏感词
            else{
                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            }
        }
        return sb.toString();
    }

    /**
     * 判断c是否是普通字符
     * 是特殊符号返回true
     * @param c
     * @return
     */
    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    /**
     * 定义前缀树
     */
    private class TrieNode{
        /**
         * 描述关键词结束的标识
         */
        private boolean isKeywordEnd = false;

        /**
         * 子结点(Map的key是下级结点的字符，value是下级结点)
         */
        private Map<Character,TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        /**
         * 添加子结点
         * @param c
         * @param node
         */
        public void addSubNode(Character c,TrieNode node){
            subNodes.put(c,node);
        }

        /**
         * 获取子结点
         * @param c
         * @return
         */
        public TrieNode getSubNode(Character c){
            return subNodes.get(c);
        }
    }
}
