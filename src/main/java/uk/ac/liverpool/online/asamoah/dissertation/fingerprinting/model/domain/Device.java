/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Entity
@Table(name = "device")
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d"),
    @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id"),
    @NamedQuery(name = "Device.findByName", query = "SELECT d FROM Device d WHERE d.name = :name"),
    @NamedQuery(name = "Device.findByIp", query = "SELECT d FROM Device d WHERE d.ip = :ip"),
    @NamedQuery(name = "Device.findByPlatform", query = "SELECT d FROM Device d WHERE d.platform = :platform"),
    @NamedQuery(name = "Device.findByFonts", query = "SELECT d FROM Device d WHERE d.fonts = :fonts"),
    @NamedQuery(name = "Device.findByTimezone", query = "SELECT d FROM Device d WHERE d.timezone = :timezone"),
    @NamedQuery(name = "Device.findByLanguage", query = "SELECT d FROM Device d WHERE d.language = :language"),
    @NamedQuery(name = "Device.findByJava", query = "SELECT d FROM Device d WHERE d.java = :java"),
    @NamedQuery(name = "Device.findByDisplayHeight", query = "SELECT d FROM Device d WHERE d.displayHeight = :displayHeight"),
    @NamedQuery(name = "Device.findByDisplayWidth", query = "SELECT d FROM Device d WHERE d.displayWidth = :displayWidth"),
    @NamedQuery(name = "Device.findByColourDepth", query = "SELECT d FROM Device d WHERE d.colourDepth = :colourDepth"),
    @NamedQuery(name = "Device.findByLocation", query = "SELECT d FROM Device d WHERE d.location = :location"),
    @NamedQuery(name = "Device.findByLocales", query = "SELECT d FROM Device d WHERE d.locales = :locales")})
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "platform")
    private String platform;
    @Column(name = "fonts")
    private String fonts;
    @Basic(optional = false)
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @Column(name = "java")
    private boolean java;
    @Column(name = "display_height")
    private Short displayHeight;
    @Column(name = "display_width")
    private Short displayWidth;
    @Column(name = "colour_depth")
    private Integer colourDepth;
    @Column(name = "location")
    private String location;
    @Column(name = "locales")
    private String locales;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    private Set<Session> sessions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostDevice")
    private Set<Browser> browsers;
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User owner;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Device(Integer id, String name, String ip, String platform, String timezone, boolean java) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.platform = platform;
        this.timezone = timezone;
        this.java = java;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFonts() {
        return fonts;
    }

    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getJava() {
        return java;
    }

    public void setJava(boolean java) {
        this.java = java;
    }

    public Short getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(Short displayHeight) {
        this.displayHeight = displayHeight;
    }

    public Short getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(Short displayWidth) {
        this.displayWidth = displayWidth;
    }

    public Integer getColourDepth() {
        return colourDepth;
    }

    public void setColourDepth(Integer colourDepth) {
        this.colourDepth = colourDepth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocales() {
        return locales;
    }

    public void setLocales(String locales) {
        this.locales = locales;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessionCollection) {
        this.sessions = sessionCollection;
    }

    public Set<Browser> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(Set<Browser> browserCollection) {
        this.browsers = browserCollection;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Device[ id=" + id + " ]";
    }
    
}
