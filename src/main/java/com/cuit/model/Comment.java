package com.cuit.model;

import java.io.Serializable;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    private Integer id;

    private String content;

    private Integer type;

    private static final long serialVersionUID = 1L;
}