package com.jun.plugin.book.app.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wujun
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MultiTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


}
