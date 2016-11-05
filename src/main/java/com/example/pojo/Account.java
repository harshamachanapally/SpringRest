package com.example.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Account {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@NotNull
	private String userName;
	@Column
	@NotNull
	private String passsword;
	@Column
	@NotNull
	private boolean enabled=true;
	@Column
	@NotNull
	private boolean credentailsExpired = false;
	@Column
	@NotNull
	private boolean expired = false;
	@Column
	@NotNull
	private boolean locked = false;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="AccountRole", 
				joinColumns = @JoinColumn(name = "accoundid", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
	private Set<Role> roles;
	
	Account(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isCredentailsExpired() {
		return credentailsExpired;
	}

	public void setCredentailsExpired(boolean credentailsExpired) {
		this.credentailsExpired = credentailsExpired;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
