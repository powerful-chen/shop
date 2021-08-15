package com.chen.shop.model.buyer.vo.common.slider;

import com.chen.shop.model.buyer.vo.common.VerificationSourceVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName VerificationVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 7:33
 */
@Data
public class VerificationVO implements Serializable {

    //资源
    List<VerificationSourceVO> verificationSources;

    //滑块资源
    List<VerificationSourceVO> verificationSlider;
}
