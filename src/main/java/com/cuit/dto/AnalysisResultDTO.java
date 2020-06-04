package com.cuit.dto;

import lombok.Data;

import java.text.DecimalFormat;

/**
 * @Author Jwei
 * @Date 2020/6/4 8:39
 */
@Data
public class AnalysisResultDTO {
    private String result;
    private double[] rate;
    private String[] percent;

    public AnalysisResultDTO(double[] p) {
        initResult(p);
        initRate(p);
    }

    private void initRate(double[] p) {
        double sum = 0;
        for (double v : p) {
            sum += v;
        }
        this.rate = new double[3];
        this.percent = new String[3];
        DecimalFormat df = new DecimalFormat("0.00%");
        for (int i = 0; i < p.length; i++) {
            rate[i] = p[i] / sum;
            percent[i] = df.format(rate[i]);
        }
    }

    private void initResult(double p[]) {
        double max = 0;
        int mIndex = -1;
        for (int i = 0; i < p.length; i++) {
            if (p[i] > max) {
                max = p[i];
                mIndex = i;
            }
        }
        switch (mIndex) {
            case 0:
                this.result = "差评";
                break;
            case 1:
                this.result = "中评";
                break;
            case 2:
                this.result = "好评";
                break;
            default:
                this.result = "UNKNOW";
        }
    }
}
