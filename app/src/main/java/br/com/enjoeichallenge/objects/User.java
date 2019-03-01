package br.com.enjoeichallenge.objects;

public class User {

    private long         id;
    private String      name;
    private Photo       avatar;
    private long        id_photo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public long getId_photo() {
        return id_photo;
    }

    public void setId_photo(long id_photo) {
        this.id_photo = id_photo;
    }
}
