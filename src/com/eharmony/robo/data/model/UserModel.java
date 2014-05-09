package com.eharmony.robo.data.model;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lastActivity;
    private String email;
    private String firstName;
    private boolean gender;
    private boolean genderPreference;
    private long id;
    private String lbid;
    private String locale;
    private String name;
    private List<String> roles;
    private String status;

    private String displayCriminalWarning;
    private int countryId;

    public void setStatus( String status ) {

        this.status = status;
    }

    public String getStatus() {

        return status;
    }

    public void setRoles( List<String> roles ) {

        this.roles = roles;
    }

    public List<String> getRoles() {

        return roles;
    }

    public void setName( String name ) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setLocale( String locale ) {

        this.locale = locale;
    }

    public String getLocale() {

        return locale;
    }

    public void setCountryId( int countryId ) {

        this.countryId = countryId;
    }

    public int getCountryId() {

        return countryId;
    }

    public void setLbid( String lbid ) {

        this.lbid = lbid;
    }

    public String getLbid() {

        return lbid;
    }

    public void setId( long id ) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setGender( boolean gender ) {

        this.gender = gender;
    }

    public boolean getGender() {

        return gender;
    }

    public void setFirstName( String firstName ) {

        this.firstName = firstName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setEmail( String email ) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setLastActivity( long lastActivity ) {

        this.lastActivity = lastActivity;
    }

    public long getLastActivity() {

        return lastActivity;
    }

    public void setGenderPreference( boolean genderPreference ) {

        this.genderPreference = genderPreference;
    }

    public boolean getGenderPreference() {

        return genderPreference;
    }

    public void setDisplayCriminalWarning( String displayCriminalWarning ) {

        this.displayCriminalWarning = displayCriminalWarning;
    }

    public String getDisplayCriminalWarning() {

        return displayCriminalWarning;
    }

}

