package com.lf.commons;

import lombok.Data;

/**
 * 提供返回前台信息
 */
@Data
public class ResultVo {
    private Integer code;
    private String  info;
    private String url;

    public ResultVo() {
    }
    public ResultVo(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
