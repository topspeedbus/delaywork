package com.chan.delaywork.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chen
 * @date: 2020/5/12 - 21:40
 * @describe:
 */
@Data
@Builder
public class Order implements Serializable {

    private String id;
    private String orderName;
    private Date createDate;
}
