package com.cuit.service;

import com.cuit.mapper.ModelStatusMapper;
import com.cuit.mapper.WcMapper;
import com.cuit.model.ModelStatus;
import com.cuit.mr.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author Jwei
 * @Date 2020/6/3 17:50
 */
@Service
public class DisposeService {
    private WcMapper wcMapper;
    private WordCount wordCount;
    private ModelStatusMapper modelStatusMapper;

    public boolean canRun() {
        ModelStatus modelStatus = modelStatusMapper.selectByPrimaryKey(0);
        if (modelStatus.getStatus() == 1) {
            backup();
            return true;
        } else {
            return false;
        }
    }

    @Async
    public void wordCount() {

        ModelStatus modelStatus = modelStatusMapper.selectByPrimaryKey(0);
        modelStatus.setStatus(0);
        modelStatusMapper.updateByPrimaryKey(modelStatus);
        wcMapper.truncate0();
        try {
            wordCount.run();
            modelStatus.setStatus(1);
            modelStatusMapper.updateByPrimaryKey(modelStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    private void backup() {
        //备份
        ModelStatus modelStatus1 = modelStatusMapper.selectByPrimaryKey(1);
        modelStatus1.setStatus(0);
        modelStatusMapper.updateByPrimaryKey(modelStatus1);
        wcMapper.truncate1();
        wcMapper.backup();
        modelStatus1.setStatus(1);
        modelStatusMapper.updateByPrimaryKey(modelStatus1);
    }

    @Autowired
    public void setModelStatusMapper(ModelStatusMapper modelStatusMapper) {
        this.modelStatusMapper = modelStatusMapper;
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
