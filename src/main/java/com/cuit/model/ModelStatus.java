package com.cuit.model;

import java.io.Serializable;

import lombok.Data;

/**
 * model_status
 *
 * @author
 */
@Data
public class ModelStatus implements Serializable {
    private Integer id;

    private String tName;

    /**
     * 0/不可用
     * 1/可用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}