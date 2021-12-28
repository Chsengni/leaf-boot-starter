package com.chsengni.leaf.interfaces;

import com.chsengni.leaf.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
