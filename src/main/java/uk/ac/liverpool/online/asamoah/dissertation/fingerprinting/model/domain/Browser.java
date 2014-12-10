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
@Table(name = "browser")
@NamedQueries({
    @NamedQuery(name = "Browser.findAll", query = "SELECT b FROM Browser b"),
    @NamedQuery(name = "Browser.findById", query = "SELECT b FROM Browser b WHERE b.id = :id"),
    @NamedQuery(name = "Browser.findByUserAgent", query = "SELECT b FROM Browser b WHERE b.userAgent = :userAgent"),
    @NamedQuery(name = "Browser.findByCanvas", query = "SELECT b FROM Browser b WHERE b.canvas = :canvas"),
    @NamedQuery(name = "Browser.findByJavascriptHeapSize", query = "SELECT b FROM Browser b WHERE b.javascriptHeapSize = :javascriptHeapSize"),
    @NamedQuery(name = "Browser.findByBrowsercol", query = "SELECT b FROM Browser b WHERE b.browsercol = :browsercol"),
    @NamedQuery(name = "Browser.findByName", query = "SELECT b FROM Browser b WHERE b.name = :name"),
    @NamedQuery(name = "Browser.findByCodeName", query = "SELECT b FROM Browser b WHERE b.codeName = :codeName"),
    @NamedQuery(name = "Browser.findByVersion", query = "SELECT b FROM Browser b WHERE b.version = :version"),
    @NamedQuery(name = "Browser.findByCookiesEnabled", query = "SELECT b FROM Browser b WHERE b.cookiesEnabled = :cookiesEnabled"),
    @NamedQuery(name = "Browser.findByDnt", query = "SELECT b FROM Browser b WHERE b.dnt = :dnt"),
    @NamedQuery(name = "Browser.findByPlugins", query = "SELECT b FROM Browser b WHERE b.plugins = :plugins"),
    @NamedQuery(name = "Browser.findByMimeTypes", query = "SELECT b FROM Browser b WHERE b.mimeTypes = :mimeTypes"),
    @NamedQuery(name = "Browser.findByHardwareConcurrency", query = "SELECT b FROM Browser b WHERE b.hardwareConcurrency = :hardwareConcurrency"),
    @NamedQuery(name = "Browser.findByVendor", query = "SELECT b FROM Browser b WHERE b.vendor = :vendor"),
    @NamedQuery(name = "Browser.findByProduct", query = "SELECT b FROM Browser b WHERE b.product = :product"),
    @NamedQuery(name = "Browser.findByProductSub", query = "SELECT b FROM Browser b WHERE b.productSub = :productSub"),
    @NamedQuery(name = "Browser.findByPlatform", query = "SELECT b FROM Browser b WHERE b.platform = :platform")})
public class Browser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "canvas")
    private String canvas;
    @Column(name = "javascript_heap_size")
    private Integer javascriptHeapSize;
    @Column(name = "browsercol")
    private String browsercol;
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
    @Column(name = "plugins")
    private String plugins;
    @Column(name = "mime_types")
    private String mimeTypes;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "browser")
    private Set<Session> sessions;
    @JoinColumn(name = "host_device", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Device hostDevice;

    public Browser() {
    }

    public Browser(Integer id) {
        this.id = id;
    }

    public Browser(Integer id, String userAgent) {
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

    public String getCanvas() {
        return canvas;
    }

    public void setCanvas(String canvas) {
        this.canvas = canvas;
    }

    public Integer getJavascriptHeapSize() {
        return javascriptHeapSize;
    }

    public void setJavascriptHeapSize(Integer javascriptHeapSize) {
        this.javascriptHeapSize = javascriptHeapSize;
    }

    public String getBrowsercol() {
        return browsercol;
    }

    public void setBrowsercol(String browsercol) {
        this.browsercol = browsercol;
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

    public String getPlugins() {
        return plugins;
    }

    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(String mimeTypes) {
        this.mimeTypes = mimeTypes;
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

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessionCollection) {
        this.sessions = sessionCollection;
    }

    public Device getHostDevice() {
        return hostDevice;
    }

    public void setHostDevice(Device hostDevice) {
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
        if (!(object instanceof Browser)) {
            return false;
        }
        Browser other = (Browser) object;
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
