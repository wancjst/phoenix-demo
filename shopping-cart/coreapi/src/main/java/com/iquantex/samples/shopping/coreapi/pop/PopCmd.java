package com.iquantex.samples.shopping.coreapi.pop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author quail
 */
@Getter
@AllArgsConstructor
public class PopCmd implements Serializable {
    private String popCode;
}
