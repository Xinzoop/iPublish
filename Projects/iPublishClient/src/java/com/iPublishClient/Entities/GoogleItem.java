/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Entities;

import java.io.Serializable;

/**
 *
 * @author zipv5_000
 */
public class GoogleItem implements Serializable{
    
    private String title;

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    private String link;

    /**
     * Get the value of link
     *
     * @return the value of link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the value of link
     *
     * @param link new value of link
     */
    public void setLink(String link) {
        this.link = link;
    }

    private String snippet;

    /**
     * Get the value of snippet
     *
     * @return the value of snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * Set the value of snippet
     *
     * @param snippet new value of snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}
