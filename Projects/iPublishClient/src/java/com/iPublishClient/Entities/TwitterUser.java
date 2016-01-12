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
public class TwitterUser implements Serializable{
    
    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    private String screen_name;

    /**
     * Get the value of screen_name
     *
     * @return the value of screen_name
     */
    public String getScreen_name() {
        return screen_name;
    }

    /**
     * Set the value of screen_name
     *
     * @param screen_name new value of screen_name
     */
    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    private String profile_image_url_https;

    /**
     * Get the value of profile_image_url_https
     *
     * @return the value of profile_image_url_https
     */
    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    /**
     * Set the value of profile_image_url_https
     *
     * @param profile_image_url_https new value of profile_image_url_https
     */
    public void setProfile_image_url_https(String profile_image_url_https) {
        this.profile_image_url_https = profile_image_url_https;
    }

}
