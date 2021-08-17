package com.ieteisf.iete_try1;

public class sponsor {

    private String Logo;
    private String Name;
    private String Weblink;

    sponsor(){}

    public sponsor(String logo, String name, String weblink) {
        this.Logo = logo;
        this.Name = name;
        this.Weblink = weblink;
    }

    public String getLogo() {
        return Logo;
    }

    public String getName() {
        return Name;
    }

    public String getWeblink() {
        return Weblink;
    }

    public void setLogo(String logo) {
        this.Logo = logo;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setWeblink(String weblink) {
        this.Weblink = weblink;
    }
}
