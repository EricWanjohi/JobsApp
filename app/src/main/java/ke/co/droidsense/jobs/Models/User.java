package ke.co.droidsense.jobs.Models;

public class User {
    //Member Variables.
    private String full_name;
    private String display_name;
    private String email;
    private String phone;
    private String password;
    private String confirm_password;
    private String image_url;
    private String push_id;

    //Empty constructor.
    public User(){
        //Required by Firebase and Parceler...
    }

    //Constructor.
    public User(String full_name, String display_name, String email, String phone, String password, String confirm_password, String image_url, String push_id) {
        this.full_name = full_name;
        this.display_name = display_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirm_password = confirm_password;
        this.image_url = image_url;
        this.push_id = push_id;
    }

    //Getters and Setters.
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }
}
