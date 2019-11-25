package jbc.oct21.jindanupajit.jobposting.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Entity
public class CloudinaryImage {

    @Id
    @SequenceGenerator(name = "CloudinaryImage", sequenceName = "CloudinaryImageId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "CloudinaryImage")
    private long id;

    private String public_id;
    private long version;
    private int width;
    private int height;
    private String format;
    private Timestamp created_at;
    private String resource_type;
    private int bytes;
    private String type;
    private String etag;
    private String url;
    private String secure_url;
    private String signature;
    private String original_filename;

    public CloudinaryImage() {
        populate(new HashMap<String, Object>());
    }

    public CloudinaryImage(Map<String, Object> infoMap) {
        populate(infoMap);
    }

    private CloudinaryImage populate(Map<String, Object> infoMap) {
        public_id = infoMap.getOrDefault("public_id", "").toString();
        version = Long.parseLong(infoMap.getOrDefault("version", "0").toString());
        width = Integer.parseInt(infoMap.getOrDefault("width", "0").toString());
        height = Integer.parseInt(infoMap.getOrDefault("height", "0").toString());
        format = infoMap.getOrDefault("format", "").toString();
        java.util.Date create;
        try {
            create = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(infoMap.getOrDefault("created_at", "").toString());
        } catch (ParseException e) {
            create = new java.util.Date();
        }
        created_at = new Timestamp(create.getTime());
        resource_type = infoMap.getOrDefault("resource_type", "").toString();
        bytes = Integer.parseInt(infoMap.getOrDefault("bytes", "0").toString());
        type = infoMap.getOrDefault("type", "").toString();
        etag = infoMap.getOrDefault("etag", "").toString();
        url = infoMap.getOrDefault("url", "").toString();
        secure_url = infoMap.getOrDefault("secure_url", "").toString();
        signature = infoMap.getOrDefault("signature", "").toString();
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecure_url() {
        return secure_url;
    }

    public void setSecure_url(String secure_url) {
        this.secure_url = secure_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }
}
