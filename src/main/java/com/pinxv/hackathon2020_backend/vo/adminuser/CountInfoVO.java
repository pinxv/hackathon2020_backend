package com.pinxv.hackathon2020_backend.vo.adminuser;

import lombok.Data;

/**
 * <p>description: 前台统计数据VO</p>
 *
 * @date 2020/11/28
 */
@Data
public class CountInfoVO {
    /**
     * 批次总数
     */
    Integer batchNum;

    /**
     * 安全批次数
     */
    Integer safeNum;

    /**
     * 危险批次数
     */
    Integer unsafeNum;
}
