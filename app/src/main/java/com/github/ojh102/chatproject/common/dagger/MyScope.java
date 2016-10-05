package com.github.ojh102.chatproject.common.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface MyScope
{
}