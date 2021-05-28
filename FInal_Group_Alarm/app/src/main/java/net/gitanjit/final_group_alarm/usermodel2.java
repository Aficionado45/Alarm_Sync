package net.gitanjit.final_group_alarm;

public class usermodel2 {

    public String fullName, phone, email ;
    //public ArrayList< UserHelperClass >


    public usermodel2(String fullName , String phone, String email )
    {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }
    public usermodel2() {}



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public  String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}