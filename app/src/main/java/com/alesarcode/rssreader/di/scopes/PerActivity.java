package com.alesarcode.rssreader.di.scopes;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * @author Sarai Díaz García
 * @version %I%
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
