package com.cuit.service;

import com.cuit.mapper.WcMapper;
import com.cuit.mr.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Jwei
 * @Date 2020/6/3 17:50
 */
@Service
public class DisposeService {
    private WcMapper wcMapper;
    private WordCount wordCount;

    public boolean wordCount() {
        wcMapper.truncate();
        try {
            wordCount.run();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Autowired
    public void setWcMapper(WcMapper wcMapper) {
        this.wcMapper = wcMapper;
    }

    @Autowired
    public void setWordCount(WordCount wordCount) {
        this.wordCount = wordCount;
    }
}
