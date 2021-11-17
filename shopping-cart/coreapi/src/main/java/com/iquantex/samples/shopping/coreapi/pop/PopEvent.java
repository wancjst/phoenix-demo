package com.iquantex.samples.shopping.coreapi.pop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * @author quail
 */
@Getter
@AllArgsConstructor
public class PopEvent implements Serializable {

    private static final long serialVersionUID = -6527202043741732580L;
    private String popCode;
    private Map<String, Integer> popList;
}
