package com.chsengni.leaf.annotation;

import com.chsengni.leaf.LeafAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LeafAutoConfigure.class)
@Inherited
public @interface EnableLeafServer {
}
