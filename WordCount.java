package com.xiaoniu.beans;




import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
/*
    使用到的正则方法:

(1) compile
    public static Pattern compile(String regex)
        将给定的正则表达式编译到模式中。
        参数：
            regex - 要编译的表达式
        抛出：
            PatternSyntaxException - 如果表达式的语法无效

(2) matcher
    public Matcher matcher(CharSequence input)
        创建匹配给定输入与此模式的匹配器。
        参数：
            input - 要匹配的字符序列
        返回：
            此模式的新匹配器


(3)  replaceAll
     public String replaceAll(String replacement)
       替换模式与给定替换字符串相匹配的输入序列的每个子序列。
       此方法首先重置匹配器。然后，它将扫描输入序列以查找该模式的匹配项。不属于任何匹配的字符被直接添加到结果字符串；在结果中每个匹配都将被替换字符串所替换。替换字符串可能包含到已捕获子序列的引用，如在 appendReplacement 方法中一样。
注意，在替换字符串中使用反斜线 (\) 和美元符号 ($) 可能导致与作为字面值替换字符串时所产生的结果不同。美元符号可视为到如上所述已捕获子序列的引用，反斜线可用于转义替换字符串中的字面值字符。
在给定正则表达式 a*b、输入 "aabfooaabfooabfoob" 和替换字符串 "-" 的情况下，为该表达式针对匹配器调用此方法将产生字符串 "-foo-foo-foo-"。
调用此方法将更改此匹配器的状态。如果在将来的匹配操作中使用该匹配器，则应该首先重置它。
        参数：
                replacement - 替换字符串。
        返回：
                通过使用替换字符串替换每个匹配子序列，并在需要时取代已捕获子序列所构造的字符串。


*/

public class WordCount {

    public static void  main(String [] args) throws Exception {
        //定义文件的路径
        String fileName="/Users/caozhan/Downloads/word.txt";
        //调用这个方法，来得到一共有多少个单词
        int wordNum = getWordNum(fileName);
        System.out.println("word.txt中一共有---"+wordNum+"--个英文单词");

        //定义要查找的单词
        String word="another";
        wordNum=getWordNUm(fileName,word);
        System.out.println("word.txt中--"+word+"单词-共出现了"+wordNum+"次");
    }


    /**
     *
     * @param fileName 文件的路径
     * @return 返回有多少个单词
     * @throws Exception 文件不存在的异常
     */
    //判断一个文件中有多少个单词
    public static int getWordNum(String fileName) throws Exception {
        //定义字符串初始化个数
        int wordNum=0;
        //用字符流缓冲流就可以拿出，看题，抛出异常所以不用进行try cath
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //定义接受每一行的字符串的变量
        String line;
        //定义正则，主要用于替换特殊字符，因为单词和单词之间的结合方式不一样，所以将那些， 。 。 等都替换成 空格，然后再对其进行切分
        Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
        //开始循环拿取每一行的字符串。
        while((line=br.readLine())!=null){
            //执行每一行的特殊字符替换成空格
            line=p.matcher(line).replaceAll(" ");
            //将替换完成的字符串以空格切分
            String []  strs = line.split(" ");
            //遍历每一个字符串
            for(String str:strs){
                //需要对每一个字符串进行判断，非空字符串就不记录个数,
                if(!str.trim().equals("")){
                    //符合条件的就+1
                    wordNum++;
                }
            }
        }
        //关闭流
        br.close();
        //将统计好的单词个数返回
        return wordNum;
    }


    /**
     *
     * @param fileName 文件的路径
     * @param word 要统计个数的单词
     * @return 返回单词的个数
     * @throws Exception 文件不在的异常
     */
    //统计word.txt文件中含有指定英文单词的个数
    public static int getWordNUm(String fileName,String word) throws Exception {
        //定义字符串初始化个数
        int wordNum=0;
        //用字符流缓冲流就可以拿出，看题，抛出异常所以不用进行try cath
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //定义接受每一行的字符串的变量
        String line;
        //定义正则，主要用于替换特殊字符，因为单词和单词之间的结合方式不一样，所以将那些， 。 。 等都替换成 空格，然后再对其进行切分
        Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
        //开始循环拿取每一行的字符串。
        while((line=br.readLine())!=null){
            //执行每一行的特殊字符替换成空格
            line=p.matcher(line).replaceAll(" ");
            //将替换完成的字符串以空格切分
            String []  strs = line.split(" ");
            //遍历每一个字符串
            for(String str:strs){
                //需要对每一个字符串进行判断，一样的单词就计数,应该大小写都算，所以使用equalsIgnoreCase这个方法
                if(str.trim().equalsIgnoreCase(word)){
                    //符合条件的就+1
                    wordNum++;
                }
            }
        }
        //将统计好的单词个数返回
        return wordNum;
    }



}

