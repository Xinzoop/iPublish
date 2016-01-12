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
public class TwitterItem implements Serializable{
    
    private long id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    private String text;

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    private String created_at;

    /**
     * Get the value of created_at
     *
     * @return the value of created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Set the value of created_at
     *
     * @param created_at new value of created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private TwitterUser user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public TwitterUser getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(TwitterUser user) {
        this.user = user;
    }

}
