package com.example.ngelesalpha.model;

/**
 * Created by Timothy on 7/3/2016.
 */
public class Search_model {


    String imageId;
    String id_color;
    String title;
    String class_days;
    String class_shift;
    String class_charge;
    String id_money;
    String charge_per_blank;
    String address;
    String learning_category;
    String branch;
    String study_duration;
    String study_period;
    String payment_description;
    String description;
    String learning_method;
    String age_min;
    String age_max;
    String background_image;
    String id_color2;
    String logo;
    String status_recommended;
    String address_state_name;
    String count_registrant;
    String registration_start;
    String registration_end;
    String contact_email_address;
    String contact_facebook;
    String contact_phone;
    String contact_web_page;


    public String getStatus_recommended() {
        return status_recommended;
    }

    public void setStatus_recommended(String status_recommended) {
        this.status_recommended = status_recommended;
    }

    public String getAddress_state_name() {
        return address_state_name;
    }

    public void setAddress_state_name(String address_state_name) {
        this.address_state_name = address_state_name;
    }

    public String getCount_registrant() {
        return count_registrant;
    }

    public void setCount_registrant(String count_registrant) {
        this.count_registrant = count_registrant;
    }

    public String getRegistration_start() {
        return registration_start;
    }

    public void setRegistration_start(String registration_start) {
        this.registration_start = registration_start;
    }

    public String getRegistration_end() {
        return registration_end;
    }

    public void setRegistration_end(String registration_end) {
        this.registration_end = registration_end;
    }

    public String getContact_email_address() {
        return contact_email_address;
    }

    public void setContact_email_address(String contact_email_address) {
        this.contact_email_address = contact_email_address;
    }

    public String getContact_facebook() {
        return contact_facebook;
    }

    public void setContact_facebook(String contact_facebook) {
        this.contact_facebook = contact_facebook;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_web_page() {
        return contact_web_page;
    }

    public void setContact_web_page(String contact_web_page) {
        this.contact_web_page = contact_web_page;
    }


    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLearning_category() {
        return learning_category;
    }
    public void setLearning_category(String learning_category) {
        this.learning_category = learning_category;
    }
    public String getStudy_duration() {
        return study_duration;
    }
    public void setStudy_duration(String study_duration) {
        this.study_duration = study_duration;
    }
    public String getStudy_period() {
        return study_period;
    }
    public void setStudy_period(String study_period) {
        this.study_period = study_period;
    }
    public String getPayment_description() {
        return payment_description;
    }
    public void setPayment_description(String payment_description) {
        this.payment_description = payment_description;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLearning_method() {
        return learning_method;
    }
    public void setLearning_method(String learning_method) {
        this.learning_method = learning_method;
    }
    public String getAge_min() {
        return age_min;
    }
    public void setAge_min(String age_min) {
        this.age_min = age_min;
    }
    public String getAge_max() {
        return age_max;
    }
    public void setAge_max(String age_max) {
        this.age_max = age_max;
    }
    public String getBackground_image() {
        return background_image;
    }
    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }
    public String getId_color2() {
        return id_color2;
    }
    public void setId_color2(String id_color2) {
        this.id_color2 = id_color2;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Search_model(){

    }

    //SETTER


    public void setId_color(String id_color) {
        this.id_color = id_color;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setClass_days(String class_days){
        this.class_days=class_days;
    }

    public void setClass_shift(String class_shift)
    {
        this.class_shift=class_shift;
    }

    public void setClass_charge(String class_charge)
    {
        this.class_charge=class_charge;
    }

    public void setId_money(String id_money)
    {
        this.id_money=id_money;
    }

    public void setCharge_per_blank(String charge_per_blank)
    {
        this.charge_per_blank=charge_per_blank;
    }

    //GETTER


    public String getId_color() {
        return id_color;
    }

    public String getTitle()
    {
        return title;
    }

    public String getClass_days()
    {
        return class_days;
    }

    public String getClass_shift()
    {
        return class_shift;
    }

    public String getClass_charge()
    {
        return class_charge;
    }

    public String getId_money()
    {
        return id_money;
    }

    public String getCharge_per_blank()
    {
        return charge_per_blank;
    }
}
