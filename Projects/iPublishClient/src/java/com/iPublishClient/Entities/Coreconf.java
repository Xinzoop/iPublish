/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iPublishClient.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zipv5_000
 */
@Entity
@Table(name = "CORECONF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coreconf.findAll", query = "SELECT c FROM Coreconf c"),
    @NamedQuery(name = "Coreconf.findByConfId", query = "SELECT c FROM Coreconf c WHERE c.confId = :confId"),
    @NamedQuery(name = "Coreconf.findByConfTitle", query = "SELECT c FROM Coreconf c WHERE c.confTitle = :confTitle"),
    @NamedQuery(name = "Coreconf.findByAcronym", query = "SELECT c FROM Coreconf c WHERE c.acronym = :acronym"),
    @NamedQuery(name = "Coreconf.findByRank", query = "SELECT c FROM Coreconf c WHERE c.rank = :rank"),
    @NamedQuery(name = "Coreconf.findByChanged", query = "SELECT c FROM Coreconf c WHERE c.changed = :changed"),
    @NamedQuery(name = "Coreconf.findByForcode", query = "SELECT c FROM Coreconf c WHERE c.forcode = :forcode")})
public class Coreconf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONF_ID")
    private Integer confId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "CONF_TITLE")
    private String confTitle;
    @Size(max = 25)
    @Column(name = "ACRONYM")
    private String acronym;
    @Size(max = 15)
    @Column(name = "RANK")
    private String rank;
    @Column(name = "CHANGED")
    private Boolean changed;
    @Size(max = 4)
    @Column(name = "FORCODE")
    
    private String query;
    private String year;
    private String forcode;
    private String venue;
    private String confPeriod;
    private String confDescription;
    private String confLink;
    private final List<TwitterItem> positiveTwitter;
    private final List<TwitterItem> neutralTwitter;
    private final List<TwitterItem> negativeTwitter;
    
    private final HashMap<String, String> confProperties;

    public Coreconf() {
        this.confProperties = new HashMap<>();
        this.negativeTwitter = new ArrayList<>();
        this.positiveTwitter = new ArrayList<>();
        this.neutralTwitter = new ArrayList<>();
    }

    public Coreconf(Integer confId) {
        this.confProperties = new HashMap<>();
        this.negativeTwitter = new ArrayList<>();
        this.positiveTwitter = new ArrayList<>();
        this.neutralTwitter = new ArrayList<>();
        this.confId = confId;
    }

    public Coreconf(Integer confId, String confTitle) {
        this.confProperties = new HashMap<>();
        this.negativeTwitter = new ArrayList<>();
        this.positiveTwitter = new ArrayList<>();
        this.neutralTwitter = new ArrayList<>();
        this.confId = confId;
        this.confTitle = confTitle;
    }
    /**
     * Get the value of confProperties
     *
     * @return the value of confProperties
     */
    public HashMap<String, String> getConfProperties() {
        return confProperties;
    }
    /**
     * Get the value of query
     *
     * @return the value of query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Set the value of query
     *
     * @param query new value of query
     */
    public void setQuery(String query) {
        this.query = query;
    }
     /**
     * Get the value of year
     *
     * @return the value of year
     */
    public String getYear() {
        return year;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Get the value of confLink
     *
     * @return the value of confLink
     */
    public String getConfLink() {
        return confLink;
    }

    /**
     * Set the value of confLink
     *
     * @param confLink new value of confLink
     */
    public void setConfLink(String confLink) {
        this.confLink = confLink;
    }

    /**
     * Get the value of confDescription
     *
     * @return the value of confDescription
     */
    public String getConfDescription() {
        return confDescription;
    }

    /**
     * Set the value of confDescription
     *
     * @param confDescription new value of confDescription
     */
    public void setConfDescription(String confDescription) {
        this.confDescription = confDescription;
    }

    /**
     * Get the value of negativeTwitter
     *
     * @return the value of negativeTwitter
     */
    public List<TwitterItem> getNegativeTwitter() {
        return negativeTwitter;
    }

    /**
     * Get the value of neutralTwitter
     *
     * @return the value of neutralTwitter
     */
    public List<TwitterItem> getNeutralTwitter() {
        return neutralTwitter;
    }

    /**
     * Get the value of positiveTwitter
     *
     * @return the value of positiveTwitter
     */
    public List<TwitterItem> getPositiveTwitter() {
        return positiveTwitter;
    }

    /**
     * Get the value of confPeriod
     *
     * @return the value of confPeriod
     */
    public String getConfPeriod() {
        return confPeriod;
    }

    /**
     * Set the value of confPeriod
     *
     * @param confPeriod new value of confPeriod
     */
    public void setConfPeriod(String confPeriod) {
        this.confPeriod = confPeriod;
    }

    /**
     * Get the value of venue
     *
     * @return the value of venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Set the value of venue
     *
     * @param venue new value of venue
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getConfId() {
        return confId;
    }

    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    public String getConfTitle() {
        return confTitle;
    }

    public void setConfTitle(String confTitle) {
        this.confTitle = confTitle;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public String getForcode() {
        return forcode;
    }

    public void setForcode(String forcode) {
        this.forcode = forcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confId != null ? confId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coreconf)) {
            return false;
        }
        Coreconf other = (Coreconf) object;
        return (this.confId != null || other.confId == null) && (this.confId == null || this.confId.equals(other.confId));
    }

    @Override
    public String toString() {
        return "com.iPublishClient.Entities.Coreconf[ confId=" + confId + " ]";
    }
}
