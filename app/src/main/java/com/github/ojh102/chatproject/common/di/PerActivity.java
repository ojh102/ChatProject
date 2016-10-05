package com.github.ojh102.chatproject.common.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Activity Scope
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerActivity
{
}