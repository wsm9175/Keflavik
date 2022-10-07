package com.example.keflavik.datas;

public class UserData {
    String id;
    String pw;
    String name;
    String phoneNumber;
    String email;
    boolean active;//유저 활성화 여부/사용하는 유저냐?아니냐?
    boolean accept;//개인정보 동의인데 지금은 false로
    boolean agreeTerm;
    int ageGroup;
    int career;
    boolean ceo;//개인/사업자
    boolean certification;//본인인증 여부//핸드폰



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isAgreeTerm() {
        return agreeTerm;
    }

    public void setAgreeTerm(boolean agreeTerm) {
        this.agreeTerm = agreeTerm;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public boolean isCeo() {
        return ceo;
    }

    public void setCeo(boolean ceo) {
        this.ceo = ceo;
    }

    public boolean isCertification() {
        return certification;
    }

    public void setCertification(boolean certification) {
        this.certification = certification;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", pw:" + pw +
                ", name:" + name +
                ", phoneNumber:" + phoneNumber +
                ", email:" + email +
                ", active:" + active +
                ", accept:" + accept +
                ", agreeTerm:" + agreeTerm +
                ", ageGroup:" + ageGroup +
                ", career:" + career +
                ", ceo:" + ceo +
                ", certification:" + certification +
                '}';
    }
}
