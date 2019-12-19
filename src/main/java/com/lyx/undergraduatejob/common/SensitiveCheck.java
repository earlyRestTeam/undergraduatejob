package com.lyx.undergraduatejob.common;


import org.apache.commons.lang.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王志坚
 * @createTime 2019.04.10.15:19
 */
@Component
public class SensitiveCheck implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(SensitiveCheck.class);

    private  TreeNode rootNode = new TreeNode();
    private final String repaceString = "***";

    private List<String> sensitiveStrings = new ArrayList<String>();

    public void afterPropertiesSet() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedInputStream = null;
        try{
//            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("classpath:/sensitiveWords.txt");
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sensitiveWords.txt");
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            bufferedInputStream = new BufferedReader(inputStreamReader);
            String lineText;

            while((lineText=bufferedInputStream.readLine())!=null){
                addWord(lineText);
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            if(bufferedInputStream!=null){
                bufferedInputStream.close();
            }
            if(inputStreamReader!=null){
                inputStreamReader.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
    }

    public String check(String s){
        if(StringUtils.isEmpty(s)){
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int j = 0;
        TreeNode tempNode = null;
        Character nuderCharacter = null;
        Character floatCharacter = null;
        for(int i=0;i<s.length();){
             j = i;
            floatCharacter = s.charAt(i);
            nuderCharacter = floatCharacter;
            tempNode = rootNode;

            if(isSymbol(floatCharacter)){
                stringBuilder.append(floatCharacter);
                ++i;
                continue;
            }

            //System.out.println(tempNode);


            while(tempNode.hasKey(floatCharacter) && !tempNode.getEnd()){

                //当i为最后一个时

                if(j+1>=s.length()){
                    break;
                }
                ++j;

                tempNode = tempNode.getNode(floatCharacter);
                //获取下一个字符
                floatCharacter  = s.charAt(j);
                while(isSymbol(floatCharacter) && j<s.length()){
                    ++j;
                    floatCharacter = s.charAt(j);
                }
            }

            if(tempNode.isEnd){
                stringBuilder.append(repaceString);
                sensitiveStrings.add(s.substring(i,j+1));
                i=j;
            }else{
                stringBuilder.append(nuderCharacter);
                ++i;
            }
        }
        return stringBuilder.toString();
    }

    private Boolean isSymbol(char c){
        int ic = (int)c;
        // 0x2E80~0x9FFF 东亚文字
        return !CharUtils.isAsciiAlphanumeric(c) && (ic<0x2E80 ||ic>0x9FFF);
    }



    private void addWord(String LineText){
        String word = LineText.trim();
        TreeNode tempNode = rootNode;
        for(int i=0;i<word.length();++i){
            Character character = word.charAt(i);
            if(!tempNode.hasKey(character)){
                TreeNode treeNode = new TreeNode();
                tempNode.put(character,treeNode);
            }
            tempNode = tempNode.getNode(character);
            if(i==word.length()-1){
                tempNode.setEnd(true);
            }
        }
    }

    public List<String> getSensitiveStrings() {
        return sensitiveStrings;
    }

    public void setSensitiveStrings(List<String> sensitiveStrings) {
        this.sensitiveStrings = sensitiveStrings;
    }

    //内部类
    private class TreeNode{
        private Boolean isEnd = false;
        private Map<Character,TreeNode> map = new HashMap<Character,TreeNode>();

        public TreeNode(Map map) {
            this.map = map;
            isEnd = false;
        }

        public TreeNode( Boolean isEnd, Map map) {
            this.isEnd = isEnd;
            this.map = map;
        }

        public Boolean put(Character key,TreeNode treeNode){
            if(!isNull(treeNode)){
                map.put(key,treeNode);
            }
            return false;
        }

        public Boolean isNull(TreeNode treeNode){
            return treeNode==null;
        }

        public Boolean hasKey(Character key){
            if(key!=null&&key!=' '){
                return map.containsKey(key);
            }
            return null;
        }

        public TreeNode getNode(Character character){
            if(character!=null&&character!=' '){
                return map.get(character);
            }
            return null;
        }

        public TreeNode() {
        }

        public Boolean getEnd() {
            return isEnd;
        }

        public void setEnd(Boolean end) {
            isEnd = end;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

    }

    public static void main(String[] args) {
        SensitiveCheck sensitiveCheck = new SensitiveCheck();

        sensitiveCheck.addWord("色情");
        sensitiveCheck.addWord("赌博");
        sensitiveCheck.addWord("骚话");
        sensitiveCheck.addWord("fuck");
        sensitiveCheck.addWord("狗屎");

        System.out.println(sensitiveCheck.check("hi     你——好——色情,赌博也参与。最近还开始说骚话了，怕是飘了皮痒了，我都无fuck说了，只有一句狗屎给你了,好色"));

    }

}
