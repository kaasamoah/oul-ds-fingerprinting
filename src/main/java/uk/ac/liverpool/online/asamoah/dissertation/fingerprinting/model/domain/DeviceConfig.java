/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Entity
@Table(name = "device_config")
@NamedQueries({
    @NamedQuery(name = "DeviceConfig.findAll", query = "SELECT d FROM DeviceConfig d"),
    @NamedQuery(name = "DeviceConfig.findById", query = "SELECT d FROM DeviceConfig d WHERE d.id = :id"),
    @NamedQuery(name = "DeviceConfig.findByIp", query = "SELECT d FROM DeviceConfig d WHERE d.ip = :ip"),
    @NamedQuery(name = "DeviceConfig.findByPlatform", query = "SELECT d FROM DeviceConfig d WHERE d.platform = :platform"),
    @NamedQuery(name = "DeviceConfig.findByFonts", query = "SELECT d FROM DeviceConfig d WHERE d.fonts = :fonts"),
    @NamedQuery(name = "DeviceConfig.findByTimezone", query = "SELECT d FROM DeviceConfig d WHERE d.timezone = :timezone"),
    @NamedQuery(name = "DeviceConfig.findByLanguage", query = "SELECT d FROM DeviceConfig d WHERE d.language = :language"),
    @NamedQuery(name = "DeviceConfig.findByJava", query = "SELECT d FROM DeviceConfig d WHERE d.java = :java"),
    @NamedQuery(name = "DeviceConfig.findByDisplayHeight", query = "SELECT d FROM DeviceConfig d WHERE d.displayHeight = :displayHeight"),
    @NamedQuery(name = "DeviceConfig.findByDisplayWidth", query = "SELECT d FROM DeviceConfig d WHERE d.displayWidth = :displayWidth"),
    @NamedQuery(name = "DeviceConfig.findByColourDepth", query = "SELECT d FROM DeviceConfig d WHERE d.colourDepth = :colourDepth"),
    @NamedQuery(name = "DeviceConfig.findByLocales", query = "SELECT d FROM DeviceConfig d WHERE d.locales = :locales"),
    @NamedQuery(name = "DeviceConfig.findByDeviceId", query = "SELECT DISTINCT d FROM DeviceConfig d WHERE d.device.id = :devId"),
    @NamedQuery(name = "DeviceConfig.countByPlatformTimezoneAndDeviceId", query = "SELECT count(d) FROM DeviceConfig d WHERE d.platform = :platform and d.timezone = :tz and d.device.id = :devId")})
public class DeviceConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "platform")
    private String platform;
    @Column(name = "fonts", columnDefinition = "TEXT")
    private String fonts;
    @Column(name = "fonts_digest")
    private String fontsDigest;
    @Basic(optional = false)
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "lang")
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
    @Column(name = "locales")
    private String locales;
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceConfig")
    private Set<Session> sessions = new HashSet<Session>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostDevice")
    private Set<BrowserConfig> browsers = new HashSet<BrowserConfig>();
    @JoinColumn(name = "device", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Device device;

    public DeviceConfig() {
    }

    public DeviceConfig(Integer id) {
        this.id = id;
    }

    public DeviceConfig(Integer id, String ip, String platform, String timezone, boolean java) {
        this.id = id;
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

    /**
     * @return the fonts
     */
    public String getFonts() {
        return fonts;
    }

    /**
     * @param fonts the fonts to set
     */
    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    public String getFontsDigest() {
        return fontsDigest;
    }

    public void setFontsDigest(String fonts) {
        this.fontsDigest = fonts;
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

    public String getLocales() {
        return locales;
    }

    public void setLocales(String locales) {
        this.locales = locales;
    }

    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessionCollection) {
        this.sessions = sessionCollection;
    }
    
    public void addSession(Session session){
        session.setDeviceConfig(this);
        this.sessions.add(session);
    }

    public Set<BrowserConfig> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(Set<BrowserConfig> browserCollection) {
        this.browsers = browserCollection;
    }
    
    public void addBrowser(BrowserConfig browser){
        browser.setHostDevice(this);
        this.browsers.add(browser);
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
        if (!(object instanceof DeviceConfig)) {
            return false;
        }
        DeviceConfig other = (DeviceConfig) object;
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
