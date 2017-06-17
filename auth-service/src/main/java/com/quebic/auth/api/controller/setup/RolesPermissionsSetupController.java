package com.quebic.auth.api.controller.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quebic.auth.api.model.Role;
import com.quebic.auth.api.service.PermissionService;
import com.quebic.auth.api.service.RoleService;
import com.quebic.common.model.Permission;
import com.quebic.common.util.ControllerBase;

/**
 * Only for development
 */
@RestController
@RequestMapping("/roles-permissions-setup")
public class RolesPermissionsSetupController extends ControllerBase{

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/init", method=RequestMethod.POST)
	public ResponseEntity<String> init(){
		
		//Permission
		Permission permission_USER = new Permission();
		permission_USER.setId(Permission.USER);
		permission_USER.setCode(Permission.USER);
		permission_USER.setStatus(1);
		
		Permission permission_ADMIN = new Permission();
		permission_ADMIN.setId(Permission.USER_ADMIN);
		permission_ADMIN.setCode(Permission.USER_ADMIN);
		permission_ADMIN.setStatus(1);
		
		Permission permission_SELLER_ADMIN = new Permission();
		permission_SELLER_ADMIN.setId(Permission.USER_SELLER_ADMIN);
		permission_SELLER_ADMIN.setCode(Permission.USER_SELLER_ADMIN);
		permission_SELLER_ADMIN.setStatus(1);
		
		Permission permission_SELLER = new Permission();
		permission_SELLER.setId(Permission.USER_SELLER);
		permission_SELLER.setCode(Permission.USER_SELLER);
		permission_SELLER.setStatus(1);
		
		Permission permission_BUYER = new Permission();
		permission_BUYER.setId(Permission.USER_BUYER);
		permission_BUYER.setCode(Permission.USER_BUYER);
		permission_BUYER.setStatus(1);
		
		permissionService.add(permission_USER);
		permissionService.add(permission_ADMIN);
		permissionService.add(permission_SELLER_ADMIN);
		permissionService.add(permission_SELLER);
		permissionService.add(permission_BUYER);
		//Permission
		
		//Role ROLE_ADMIN
		Role role_ADMIN = new Role();
		role_ADMIN.setId(Role.ROLE_ADMIN);
		role_ADMIN.setCode(Role.ROLE_ADMIN);
		role_ADMIN.setStatus(1);
		role_ADMIN.getPermissions().add(permission_USER);
		role_ADMIN.getPermissions().add(permission_ADMIN);
		
		//Role ROLE_SELLER_ADMIN
		Role role_SELLER_ADMIN = new Role();
		role_SELLER_ADMIN.setId(Role.ROLE_SELLER_ADMIN);
		role_SELLER_ADMIN.setCode(Role.ROLE_SELLER_ADMIN);
		role_SELLER_ADMIN.setStatus(1);
		role_SELLER_ADMIN.getPermissions().add(permission_USER);
		role_SELLER_ADMIN.getPermissions().add(permission_SELLER_ADMIN);
		role_SELLER_ADMIN.getPermissions().add(permission_SELLER);
		
		//Role ROLE_SELLER
		Role role_SELLER = new Role();
		role_SELLER.setId(Role.ROLE_SELLER);
		role_SELLER.setCode(Role.ROLE_SELLER);
		role_SELLER.setStatus(1);
		role_SELLER.getPermissions().add(permission_USER);
		role_SELLER.getPermissions().add(permission_SELLER);
		
		//Role ROLE_BUYER
		Role role_BUYER = new Role();
		role_BUYER.setId(Role.ROLE_BUYER);
		role_BUYER.setCode(Role.ROLE_BUYER);
		role_BUYER.setStatus(1);
		role_BUYER.getPermissions().add(permission_USER);
		role_BUYER.getPermissions().add(permission_BUYER);
		
		roleService.add(role_ADMIN);
		roleService.add(role_SELLER_ADMIN);
		roleService.add(role_SELLER);
		roleService.add(role_BUYER);
		
		return makeResponse("roles-permissions : init");
		
	}
	
	
}
