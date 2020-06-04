package com.cuit.util;

/**
 * 朴素贝叶斯算法
 *
 * @Author Jwei
 * @Date 2020/6/4 8:13
 */
public class NaiveBayesUtil {

    private static final float LAP = (float) 1.0E-20;

    /**
     * TODO 计算条件概率
     *
     * @param ncixi 改词在该类型下出现的次数
     * @param nci   该类型出现的次数
     * @return 条件概率
     */
    public static double pXiCi(Long ncixi, Long nci) {
        double p;
        if (ncixi == null) {
            ncixi = 0L;
        }
        p = ((double) (ncixi + LAP)) / (nci + 3 * LAP); //平滑处理
        return p;
    }

    /**
     * TODO 计算先验概率
     *
     * @param nci 该类型出现的次数
     * @param sum 类型总数
     * @return 先验概率
     */
    public static double pCi(Long nci, Long sum) {
        double p = ((double) (nci + LAP)) / (sum + 3 * LAP); //平滑处理;
        return p;
    }

}
