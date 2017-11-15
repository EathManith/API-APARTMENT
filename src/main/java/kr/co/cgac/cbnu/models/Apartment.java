package kr.co.cgac.cbnu.models;

public class Apartment {
    private long city_id, gu_id, dong_id;
    private String apart_id, city_name, gu_name, dong_name, apart_name, url_image;

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public long getGu_id() {
        return gu_id;
    }

    public void setGu_id(long gu_id) {
        this.gu_id = gu_id;
    }

    public long getDong_id() {
        return dong_id;
    }

    public void setDong_id(long dong_id) {
        this.dong_id = dong_id;
    }

    public String getApart_id() {
        return apart_id;
    }

    public void setApart_id(String apart_id) {
        this.apart_id = apart_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getGu_name() {
        return gu_name;
    }

    public void setGu_name(String gu_name) {
        this.gu_name = gu_name;
    }

    public String getDong_name() {
        return dong_name;
    }

    public void setDong_name(String dong_name) {
        this.dong_name = dong_name;
    }

    public String getApart_name() {
        return apart_name;
    }

    public void setApart_name(String apart_name) {
        this.apart_name = apart_name;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
