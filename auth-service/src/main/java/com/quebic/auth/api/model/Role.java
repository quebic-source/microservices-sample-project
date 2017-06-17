package com.quebic.auth.api.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quebic.common.model.EntityBase;
import com.quebic.common.model.Permission;

@Document(collection="Role")
public class Role extends EntityBase{
	
    public static final String ROLE_ADMIN = "role.admin";
    public static final String ROLE_SELLER_ADMIN = "role.seller.admin";
    public static final String ROLE_SELLER = "role.seller";
    public static final String ROLE_BUYER = "role.buyer";

    private String code;

    @DBRef
    private List<Permission> permissions = new ArrayList<>();

    public Role() {
	}
    
    public Role(String id){
    	setId(id);
    }
    
    public String getCode() {
        return this.code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    @JsonIgnore
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public static Role Create_Admin(){
		return new Role(Role.ROLE_ADMIN);
	}
	
	public static Role Create_Seller_Admin(){
		return new Role(Role.ROLE_SELLER_ADMIN);
	}
	
	public static Role Create_Seller(){
		return new Role(Role.ROLE_SELLER);
	}
	
	public static Role Create_Buyer(){
		return new Role(Role.ROLE_BUYER);
	}
	
	public static boolean checkUserIs_Admin(User user){
		return checkUserType(user, Role.Create_Admin());
	}
	
	public static boolean checkUserIs_Seller_Admin(User user){
		return checkUserType(user, Role.Create_Seller_Admin());
	}
	
	public static boolean checkUserIs_Seller(User user){
		return checkUserType(user, Role.Create_Seller());
	}
	
	public static boolean checkUserIs_Buyer(User user){
		return checkUserType(user, Role.Create_Buyer());
	}
	
	public static boolean checkUserType(User user, Role... roles){
		
		for(Role checkRole : roles){
			
			if(user.getRoles().contains(checkRole))
				return true;
				
		}

		return false;
		
		
	}
	
	
	
}
