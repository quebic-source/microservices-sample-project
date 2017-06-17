package com.quebic.auth.api.security.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quebic.auth.api.security.JwtTokenUtil;
import com.quebic.auth.api.security.dto.JwtAuthenticationDto;
import com.quebic.auth.api.security.dto.JwtResponse;
import com.quebic.common.exception.UnAuthorizedAccessException;
import com.quebic.common.util.ControllerBase;

@RestController
@RequestMapping("/auth")
public class AuthController extends ControllerBase {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(
			@ModelAttribute JwtAuthenticationDto jwtAuthenticationDto, Device device)
					throws Exception {

		// Checking username|email and password
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				jwtAuthenticationDto.getUsername(), jwtAuthenticationDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationDto.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails, device);
		
		Date expiration = jwtTokenUtil.getExpirationDateFromToken(token);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		return makeResponse(new JwtResponse(token, dateFormat.format(expiration)));

	}

	@RequestMapping("/current")
	public ResponseEntity<UserDetails> getCurrent() throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String authenticatedUserName = authentication.getName();
		if(authenticatedUserName.equals("anonymousUser"))
			throw new UnAuthorizedAccessException(authenticatedUserName);
		else
			return makeResponse((UserDetails)authentication.getPrincipal());
	}
	
}
