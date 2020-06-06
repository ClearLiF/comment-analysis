package com.cuit.service.impl;

import com.cuit.dto.AnalysisResultDTO;
import com.cuit.mapper.WordCountBkMapper;
import com.cuit.model.WordCountBk;
import com.cuit.model.WordCountBkExample;
import com.cuit.service.AnalysisService;
import com.cuit.util.JieBaUtil;
import com.cuit.util.NaiveBayesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Jwei
 * @Date 2020/6/6 14:00
 */
@Service
public class AnalysisServiceImpl2  implements AnalysisService {
    private WordCountBkMapper wordCountBkMapper;

    public AnalysisResultDTO analysis(String text) {
        double[] p = analysisByBayes(text);
        return new AnalysisResultDTO(p);
    }

    private double[] analysisByBayes(String text) {
        WordCountBkExample wordCountExample = new WordCountBkExample();
        Long[] ci = new Long[3];
        long sumCi = 0L;
        for (int i = 0; i < ci.length; i++) {
            wordCountExample.clear();
            wordCountExample.createCriteria()
                    .andWordEqualTo("typeCounter")
                    .andTypeEqualTo(i + 1);
            List<WordCountBk> wordCounts = wordCountBkMapper.selectByExample(wordCountExample);
            ci[i] = wordCounts.get(0).getCount();
            sumCi += ci[i];
        }
        double[] pCi = new double[3];
        for (int i = 0; i < pCi.length; i++) {
            pCi[i] = NaiveBayesUtil.pCi(ci[i], sumCi);
        }
        double[] p = getPs(text, ci, pCi);
        return p;
    }

    private double[] getPs(String text, Long[] ci, double[] pCi) {
        List<String> list = JieBaUtil.testCutForSearch(text);
        double[] p = new double[3];
        double[] temp = new double[3];
        for (int i = 0; i < 3; i++) {
            p[i] = pCi[i];
            temp[i] = pCi[i];
        }
        boolean isOverflow = false;
        WordCountBkExample wordCountExample = new WordCountBkExample();
        for (String s : list) {
            for (int i = 0; i < 3; i++) {
                wordCountExample.clear();
                wordCountExample.createCriteria()
                        .andTypeEqualTo(i + 1)
                        .andWordEqualTo(s);
                List<WordCountBk> wordCounts = wordCountBkMapper.selectByExample(wordCountExample);
                Long count;
                if (wordCounts.size() == 0) {
                    count = 0L;
                } else {
                    count = wordCounts.get(0).getCount();
                }
                temp[i] *= NaiveBayesUtil.pXiCi(count, ci[i]);
                if (temp[i] == 0) {
                    isOverflow = true;
                    break;
                }
            }
            if (isOverflow) {
                break;
            }
            for (int i = 0; i < 3; i++) {
                p[i] = temp[i];
            }
        }
        return p;
    }

    @Autowired
    public void setWordCountBkMapper(WordCountBkMapper wordCountBkMapper) {
        this.wordCountBkMapper = wordCountBkMapper;
    }
}
