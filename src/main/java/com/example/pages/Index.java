package com.example.pages;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import java.time.ZonedDateTime;

/**
 * Start page of application tapestry-sample.
 */
public class Index {

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectPage
    private About about;

    @Inject
    private Block block;

    // Handle call with an unwanted context
    Object onActivate(EventContext eventContext) {
        return eventContext.getCount() > 0 ?
                new HttpError(404, "Resource not found") :
                null;
    }

    Object onActionFromLearnMore() {
        about.setLearn("LearnMore");

        return about;
    }

    void onComplete() {

    }

    void onAjax() {
        ajaxResponseRenderer.addRender("middlezone", block);
    }

    public ZonedDateTime getCurrentTime() {
        return ZonedDateTime.now();
    }
}
