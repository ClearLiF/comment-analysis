package com.cuit.service;

import com.cuit.mr.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Jwei
 * @Date 2020/6/3 17:50
 */
@Service
public class DisposeService {
    private WordCount wordCount;

    public boolean wordCount() {
        try {
            wordCount.run();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Autowired
    public void setWordCount(WordCount wordCount) {
        this.wordCount = wordCount;
    }
}
