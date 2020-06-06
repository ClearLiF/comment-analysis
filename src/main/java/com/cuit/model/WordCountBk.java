package com.cuit.model;

import java.io.Serializable;
import lombok.Data;

/**
 * word_count_bk
 * @author 
 */
@Data
public class WordCountBk implements Serializable {
    private String word;

    private Integer type;

    private Long count;

    private static final long serialVersionUID = 1L;
}