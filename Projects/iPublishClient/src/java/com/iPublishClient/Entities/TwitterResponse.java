/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zipv5_000
 */
public class TwitterResponse implements Serializable{
    
    private List<TwitterItem> statuses;

    /**
     * Get the value of statuses
     *
     * @return the value of statuses
     */
    public List<TwitterItem> getStatuses() {
        return statuses;
    }

    /**
     * Set the value of statuses
     *
     * @param statuses new value of statuses
     */
    public void setStatuses(List<TwitterItem> statuses) {
        this.statuses = statuses;
    }

}
