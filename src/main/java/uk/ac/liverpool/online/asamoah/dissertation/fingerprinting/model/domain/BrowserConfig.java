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
@Table(name = "browser_config")
@NamedQueries({
    @NamedQuery(name = "BrowserConfig.findAll", query = "SELECT b FROM BrowserConfig b"),
    @NamedQuery(name = "BrowserConfig.findById", query = "SELECT b FROM BrowserConfig b WHERE b.id = :id"),
    @NamedQuery(name = "BrowserConfig.findByUserAgent", query = "SELECT b FROM BrowserConfig b WHERE b.userAgent = :userAgent"),
    @NamedQuery(name = "BrowserConfig.findByCanvas", query = "SELECT b FROM BrowserConfig b WHERE b.canvas = :canvas"),
    @NamedQuery(name = "BrowserConfig.findByJavascriptHeapSize", query = "SELECT b FROM BrowserConfig b WHERE b.javascriptHeapSize = :javascriptHeapSize"),
    @NamedQuery(name = "BrowserConfig.findByName", query = "SELECT b FROM BrowserConfig b WHERE b.name = :name"),
    @NamedQuery(name = "BrowserConfig.findByCodeName", query = "SELECT b FROM BrowserConfig b WHERE b.codeName = :codeName"),
    @NamedQuery(name = "BrowserConfig.findByVersion", query = "SELECT b FROM BrowserConfig b WHERE b.version = :version"),
    @NamedQuery(name = "BrowserConfig.findByCookiesEnabled", query = "SELECT b FROM BrowserConfig b WHERE b.cookiesEnabled = :cookiesEnabled"),
    @NamedQuery(name = "BrowserConfig.findByDnt", query = "SELECT b FROM BrowserConfig b WHERE b.dnt = :dnt"),
    @NamedQuery(name = "BrowserConfig.findByPlugins", query = "SELECT b FROM BrowserConfig b WHERE b.plugins = :plugins"),
    @NamedQuery(name = "BrowserConfig.findByMimeTypes", query = "SELECT b FROM BrowserConfig b WHERE b.mimeTypes = :mimeTypes"),
    @NamedQuery(name = "BrowserConfig.findByHardwareConcurrency", query = "SELECT b FROM BrowserConfig b WHERE b.hardwareConcurrency = :hardwareConcurrency"),
    @NamedQuery(name = "BrowserConfig.findByVendor", query = "SELECT b FROM BrowserConfig b WHERE b.vendor = :vendor"),
    @NamedQuery(name = "BrowserConfig.findByProduct", query = "SELECT b FROM BrowserConfig b WHERE b.product = :product"),
    @NamedQuery(name = "BrowserConfig.findByProductSub", query = "SELECT b FROM BrowserConfig b WHERE b.productSub = :productSub"),
    @NamedQuery(name = "BrowserConfig.findByPlatform", query = "SELECT b FROM BrowserConfig b WHERE b.platform = :platform"),
    @NamedQuery(name = "BrowserConfig.findByNameAndHostDeviceId", query = "SELECT b FROM BrowserConfig b WHERE b.name = :name and b.hostDevice.id = :deviceId"),
    @NamedQuery(name = "BrowserConfig.findByNameCodeNamePlatformAndHostDeviceId", query = "SELECT b FROM BrowserConfig b WHERE b.name = :name and b.codeName = :codeName and b.platform = :platform and b.hostDevice.id = :deviceId")})
public class BrowserConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "canvas", columnDefinition = "TEXT")
    private String canvas;
    @Column(name = "canvas_digest")
    private String canvasDigest;
    @Column(name = "javascript_heap_size")
    private Integer javascriptHeapSize;
    @Column(name = "name")
    private String name;
    @Column(name = "code_name")
    private String codeName;
    @Column(name = "version")
    private String version;
    @Column(name = "cookies_enabled")
    private Boolean cookiesEnabled;
    @Column(name = "dnt")
    private Boolean dnt;
    @Column(name = "plugins", columnDefinition = "TEXT")
    private String plugins;
    @Column(name = "plugins_digest")
    private String pluginsDigest;
    @Column(name = "mime_types", columnDefinition = "TEXT")
    private String mimeTypes;
    @Column(name = "mime_types_digest")
    private String mimeTypesDigest;
    @Column(name = "hardware_concurrency")
    private String hardwareConcurrency;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "product")
    private String product;
    @Column(name = "product_sub")
    private String productSub;
    @Column(name = "platform")
    private String platform;
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "browser")
    private Set<Session> sessions = new HashSet<Session>();
    @JoinColumn(name = "host_device", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DeviceConfig hostDevice;

    public BrowserConfig() {
    }

    public BrowserConfig(Integer id) {
        this.id = id;
    }

    public BrowserConfig(Integer id, String userAgent) {
        this.id = id;
        this.userAgent = userAgent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the canvas
     */
    public String getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(String canvas) {
        this.canvas = canvas;
    }

    public String getCanvasDigest() {
        return canvasDigest;
    }

    public void setCanvasDigest(String canvas) {
        this.canvasDigest = canvas;
    }

    public Integer getJavascriptHeapSize() {
        return javascriptHeapSize;
    }

    public void setJavascriptHeapSize(Integer javascriptHeapSize) {
        this.javascriptHeapSize = javascriptHeapSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getCookiesEnabled() {
        return cookiesEnabled;
    }

    public void setCookiesEnabled(Boolean cookiesEnabled) {
        this.cookiesEnabled = cookiesEnabled;
    }

    public Boolean getDnt() {
        return dnt;
    }

    public void setDnt(Boolean dnt) {
        this.dnt = dnt;
    }

    /**
     * @return the plugins
     */
    public String getPlugins() {
        return plugins;
    }

    /**
     * @param plugins the plugins to set
     */
    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getPluginsDigest() {
        return pluginsDigest;
    }

    public void setPluginsDigest(String plugins) {
        this.pluginsDigest = plugins;
    }

    /**
     * @return the mimeTypes
     */
    public String getMimeTypes() {
        return mimeTypes;
    }

    /**
     * @param mimeTypes the mimeTypes to set
     */
    public void setMimeTypes(String mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public String getMimeTypesDigest() {
        return mimeTypesDigest;
    }

    public void setMimeTypesDigest(String mimeTypes) {
        this.mimeTypesDigest = mimeTypes;
    }

    public String getHardwareConcurrency() {
        return hardwareConcurrency;
    }

    public void setHardwareConcurrency(String hardwareConcurrency) {
        this.hardwareConcurrency = hardwareConcurrency;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductSub() {
        return productSub;
    }

    public void setProductSub(String productSub) {
        this.productSub = productSub;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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
        session.setBrowser(this);
        this.sessions.add(session);
    }

    public DeviceConfig getHostDevice() {
        return hostDevice;
    }

    public void setHostDevice(DeviceConfig hostDevice) {
        this.hostDevice = hostDevice;
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
        if (!(object instanceof BrowserConfig)) {
            return false;
        }
        BrowserConfig other = (BrowserConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Browser[ id=" + id + " ]";
    }
    
}
