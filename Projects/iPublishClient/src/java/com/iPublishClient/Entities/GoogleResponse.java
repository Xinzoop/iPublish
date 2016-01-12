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
public class GoogleResponse implements Serializable{
    
    private List<GoogleItem> items;

    /**
     * Get the value of items
     *
     * @return the value of items
     */
    public List<GoogleItem> getItems() {
        return items;
    }

    /**
     * Set the value of items
     *
     * @param items new value of items
     */
    public void setItems(List<GoogleItem> items) {
        this.items = items;
    }

}
