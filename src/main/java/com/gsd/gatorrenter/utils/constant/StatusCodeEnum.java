package com.gsd.gatorrenter.utils.constant;

/**
 * Created by Intesar on 3/5/2017.
 */
public interface StatusCodeEnum<E extends Enum<E>> extends IntegerEnumType<E> {

    String getStatusCodeStr();
    String getStatusMessage();
}
