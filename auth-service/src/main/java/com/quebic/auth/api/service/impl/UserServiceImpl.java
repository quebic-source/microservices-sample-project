package com.quebic.auth.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.quebic.auth.api.dao.RoleDao;
import com.quebic.auth.api.dao.UserDao;
import com.quebic.auth.api.dto.UserImageDto;
import com.quebic.auth.api.model.Role;
import com.quebic.auth.api.model.User;
import com.quebic.auth.api.security.SecurityUtil;
import com.quebic.auth.api.service.UserService;
import com.quebic.common.constants.ConfigConstants;
import com.quebic.common.exception.DataAccessException;
import com.quebic.common.exception.DuplicateEmailRegisteredException;
import com.quebic.common.exception.OldPasswordNotMatch;
import com.quebic.common.exception.RequiredFieldMissingException;
import com.quebic.common.exception.ServiceException;
import com.quebic.common.messages.ErrorMessage;
import com.quebic.common.messages.InfoMessage;
import com.quebic.common.model.Permission;
import com.quebic.common.model.image.Image;
import com.quebic.common.service.impl.GenericServiceImpl;
import com.quebic.common.util.image.ImageUtil;

import rx.Single;
import rx.exceptions.Exceptions;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	@Value(ConfigConstants.IMAGE_UPLOAD_LOCATION)
	private String IMAGE_LOCATION;
	
	@PostConstruct
	void init() {
        init(User.class, userDao);
    }

	@Override
	public Single<User> findByEmail(String email){
		try{
			return Single.just(userDao.findByEmail(email));
		}catch (DataAccessException e) {
			return Single.error(e);
        }
	}
	
	@Override
	public Single<User> findByUsernameOrEmail(String username, String email){
		try{
			return Single.just(userDao.findByUsernameOrEmail(username, email));
		}catch (DataAccessException e) {
			return Single.error(e);
        }
	}
	
	@Override
    public Single<User> add(User user){
		
		registerValidation(user);
    	
    	return findByEmail(user.getEmail()).flatMap(checkUser->{
    		
    		//check email already registered
    		if(checkUser != null)
				throw Exceptions.propagate(new DuplicateEmailRegisteredException()); 
    		
    		//setting default username, if username is empty
        	if(StringUtils.isEmpty(user.getUsername())){
        		String[] splictedEmail = user.getEmail().split("@");
        		if(splictedEmail.length >= 2) user.setUsername(splictedEmail[0]);
        	}
        		
            //encode password
        	user.setPassword(passwordEncoder.encode(user.getPassword()));
        	user.setRegisteredOn(new Date());
        	user.setLastPasswordResetDate(new Date());
            
        	for(Role r : user.getRoles()){
        	
        		Role role;
				try {
					role = roleDao.getById(r.getId());
					 //set user_permission
    	            List<Permission> permissions = role.getPermissions();
    	            for(Permission permission : permissions){
    	            	user.getPermissions().add(permission);
    	            }
    	            
				} catch (DataAccessException e) {
					throw Exceptions.propagate(e);
				}
        		
        	}
        	
        	user.setStatus(User.STATUS_ACTIVE);
            
            return super.add(user);
            
    	});
    	
    }
	
	@Override
    public Single<User> edit(User user, UserImageDto imageDto){
    	
		User loggedUser = SecurityUtil.getLoggedDbUser();
		
		ImageUtil imageUtil = ImageUtil.createDropBoxStorageImageUtil();
		
    	//Prepare Profile Picture File Names
        List<Image> tmpProfilePictures = new ArrayList<>();
    	for(MultipartFile file : imageDto.getProfileImagesFiles()){
    		String fileName = imageUtil.genarateFileName(file);
    		tmpProfilePictures.add(new Image(fileName));
    	}
    	//Prepare Profile Picture File Names
    	
    	//Prepare Cover Picture File Names
    	List<Image> tmpCoverPictures = new ArrayList<>();
    	for(MultipartFile file : imageDto.getCoverImagesFiles()){
    		String fileName = imageUtil.genarateFileName(file);
    		tmpCoverPictures.add(new Image(fileName));
    	}
    	//Prepare Cover Picture File Names
    	
    	return getById(loggedUser.getId()).flatMap(savedUser->{
    		if(savedUser != null){
				
    			/**
    			 * email, username, password, can't update by common edit function
    			 */
    			user.setId(savedUser.getId());
    			user.setEmail(savedUser.getEmail());
    			user.setUsername(savedUser.getUsername());
    			user.setPassword(savedUser.getPassword());
    			user.setRoles(savedUser.getRoles());
    			user.setPermissions(savedUser.getPermissions());
    			user.setRegisteredOn(savedUser.getRegisteredOn());
    			user.setLastPasswordResetDate(savedUser.getLastPasswordResetDate());
    			user.setLastLoggedOn(savedUser.getLastLoggedOn());
    			user.setAttempts(savedUser.getAttempts());
    			user.setStatus(savedUser.getStatus());
    			
    			//add images with existing images
    			user.setProfileImageContainer(savedUser.getProfileImageContainer());
    			user.getProfileImageContainer().getImages().addAll(tmpProfilePictures);
    			
    			user.setCoverImageContainer(savedUser.getCoverImageContainer());
    			user.getCoverImageContainer().getImages().addAll(tmpCoverPictures);
				
    			if(Role.checkUserIs_Seller_Admin(savedUser) || Role.checkUserIs_Seller(savedUser)){
    				user.setSellerProfile(savedUser.getSellerProfile());
    				user.setBuyerProfile(null);
    			}
    			else if(Role.checkUserIs_Buyer(savedUser)){
    				user.setBuyerProfile(savedUser.getBuyerProfile());
    				user.setSellerProfile(null);
    			}
    			
				return super.edit(user);
				
			}else{
				throw Exceptions.propagate(new ServiceException(ErrorMessage.USER_NOT_FOUND));
			}	
    	}).map(u->{
    		
    		IntStream.range(0, imageDto.getProfileImagesFiles().size()).forEach(idx->{
				
				String id = u.getId();
				String fileName = tmpProfilePictures.get(idx).getFileName();
				MultipartFile file = imageDto.getProfileImagesFiles().get(idx);
				
				try{
					imageUtil.storeFile(String.valueOf(id), fileName, IMAGE_LOCATION, ConfigConstants.IMAGE_PROFILE_UPLOAD_LOCATION, file).subscribe(s->{
						logger.info(InfoMessage.USER_PROFILE_IMAGE_SAVED, s);
					});
				}catch(Exception e){
					throw Exceptions.propagate(e);
				}
			});
    		
    		IntStream.range(0, imageDto.getCoverImagesFiles().size()).forEach(idx->{
				
    			String id = u.getId();
				String fileName = tmpCoverPictures.get(idx).getFileName();
				MultipartFile file = imageDto.getCoverImagesFiles().get(idx);
				
				try{
					imageUtil.storeFile(String.valueOf(id), fileName, IMAGE_LOCATION, ConfigConstants.IMAGE_COVER_UPLOAD_LOCATION, file).subscribe(s->{
						logger.info(InfoMessage.USER_COVER_IMAGE_SAVED, s);
					});
				}catch(Exception e){
					throw Exceptions.propagate(e);
				}
			});
    		
    		return u;
    	});
    	
    }
	
	@Override
    public Single<Boolean> changePassword(String oldPassword, String newPassword){

		User loggedUser = SecurityUtil.getLoggedDbUser();
		
       if(passwordEncoder.matches(oldPassword, loggedUser.getPassword())){
    	   
    	   return getById(loggedUser.getId()).flatMap(savedUser->{
			   
			   savedUser.setPassword(passwordEncoder.encode(newPassword));
    		   savedUser.setLastPasswordResetDate(new Date());
    		   super.edit(savedUser);
    		   return Single.just(true);
			   
		   });
    	   
       }else
    	   return Single.error(new OldPasswordNotMatch());
    }

	@Override
	public Single<Boolean> delete(String id){
		User user= new User();
		user.setId(id);
		super.delete(user);
		return Single.just(true);
	}

	@Override
	public Single<Boolean> delete(List<String> idList){
		for(String id : idList){
			this.delete(id);
		}
		return Single.just(true);
	}

	@Override
	public Single<User> findUserByToken(String token){
		try{
			return Single.just(userDao.findUserByToken(token));
		}catch (Exception e) {
			return Single.error(e);
		}
	}
	
	private boolean registerValidation(User user){
		
		List<String> missingFields = new ArrayList<>();
		
		if(StringUtils.isEmpty(user.getEmail()))
			missingFields.add("user.email");
		
		if(StringUtils.isEmpty(user.getPassword()))
			missingFields.add("user.password");
		
		if(!missingFields.isEmpty())
			throw new RequiredFieldMissingException(missingFields.toArray());
		
		return true;
		
	}
	
}
