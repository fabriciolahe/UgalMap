package eu.crystalsystem.ugalmap.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.models.User;
import eu.crystalsystem.ugalmap.repositories.RoleRepository;
import eu.crystalsystem.ugalmap.repositories.UserRepository;
import eu.crystalsystem.ugalmap.rest.request.UserAddRequest;
import eu.crystalsystem.ugalmap.rest.request.UserRequest;
import eu.crystalsystem.ugalmap.rest.response.RoleResponse;
import eu.crystalsystem.ugalmap.rest.response.UserResponse;
import eu.crystalsystem.ugalmap.utils.UserUtil;


@RestController
@RequestMapping(path = "/admin")
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserUtil userUtil;

	/*
	@PostMapping(path = "/user/add")
	public UserAddResponse addUser(@RequestBody UserAddRequest userAddRequest) {
		UserAddResponse userAddResponse;
		List<User> listUser= new ArrayList<>();
		if (userAddRequest.getRequestUser().getRole().getRoleName().equals(Role.ADMIN)) {
		 
			if (userRepository.findAllByUserEmail(userAddRequest.getInsertUser().getUserEmail()) == null) {
			
				userAddResponse = new UserAddResponse(
						new GenericHeader(true,
								"User : " + userAddRequest.getInsertUser().getUserEmail() + " created."),
						userRepository.save(userAddRequest.getInsertUser()));
			}else {
				userAddResponse = new UserAddResponse(new GenericHeader(false, "User already exists"));
				
			}
			
		}else{
			userAddResponse = new UserAddResponse(new GenericHeader(false, "Access denied"));
		}

		return userAddResponse;
	} */

	@PostMapping(path = "/add")
	public UserResponse add(@RequestBody UserRequest userRequest) {
		UserResponse userResponse;
		List<User> listUsera = new ArrayList<>();
		
		try {
			if(userRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)) {
				User user = new User(userRequest.getUser().getUserFirstname(),userRequest.getUser().getUserLastname(),
						userRequest.getUser().getUserEmail(),userRequest.getUser().getUserPasswd(),userRequest.getUser().getUserActive(),
						roleRepository.findByRoleId(userRequest.getRole().getRoleId()));
				listUsera.add(userRepository.save(user));
				
				userResponse= new UserResponse(new GenericHeader(true,"User Inserted"),listUsera);
			} else {
				userResponse = new UserResponse( new GenericHeader(false , "Access Denied"));
			}
		}
			catch ( Exception e) {
				userResponse = new UserResponse ( new GenericHeader(false, "Exception: " + e.getMessage()));
				e.printStackTrace();
			}
			
		return userResponse;
	}
	
	
	
	
	@GetMapping("/testPayload")
	public UserAddRequest testPayload() {
		return new UserAddRequest(new User("first", "last", "mail", "pass", "1", roleRepository.findByRoleId(1)),
				userRepository.findByUserId(1));
	}
	
	@GetMapping("/all")
	public UserResponse showAll(){
		List<User> listUser= userRepository.findAll();
		return new UserResponse(new GenericHeader(true,"Users founded"),listUser);
	}
	
	@GetMapping("/{id}")
	public UserResponse getById(@PathVariable int id) {
		UserResponse userResponse;
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			List<User> listUser= new ArrayList<>();
			listUser.add(user.get());
			userResponse = new UserResponse(new GenericHeader(true,"User founded"),listUser);
		} else {
			userResponse = new UserResponse(new GenericHeader(false,"User not founded"));
		}
		return userResponse;
	}
	
			
	@DeleteMapping("/delete/{id}")
	public UserResponse deleteUser(UserRequest userRequest, @PathVariable int id) {
		Optional<User> userOptional=userRepository.findById(id);
		UserResponse userResponse;
		List<User> listUser=new ArrayList<>();
		try {
			if(userOptional.isPresent()) {
						userOptional.get().setUserActive("0");
						listUser.add(userRepository.save(userOptional.get()));
						userResponse = new UserResponse(new GenericHeader(true,"User updated"),listUser);
					} else {
						userResponse = new UserResponse(new GenericHeader(false,"User not found"));
					}
				
			} catch (Exception e) {
				userResponse = new UserResponse(new GenericHeader(false,"Exception: " + e.getMessage()));
				e.printStackTrace();
			}
			
			return userResponse;
	}
	@PutMapping("/update/{id}")
	public UserResponse updateUser(@RequestBody UserRequest userRequest,@PathVariable int id) {
		Optional<User> userOptional=userRepository.findById(id);
		UserResponse userResponse;
		List<User> listUser=new ArrayList<>();
		try {
		if(userRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)) {
				if(userOptional.isPresent()) {
					userOptional.get().setUserFirstname(userRequest.getUser().getUserFirstname());
					userOptional.get().setUserLastname(userRequest.getUser().getUserLastname());
					userOptional.get().setUserEmail(userRequest.getUser().getUserEmail());
					userOptional.get().setUserPasswd(userRequest.getUser().getUserPasswd());
					userOptional.get().setUserActive(userRequest.getUser().getUserActive());
					userOptional.get().setRole(roleRepository.findByRoleId(userRequest.getRole().getRoleId()));
					listUser.add(userRepository.save(userOptional.get()));
					userResponse = new UserResponse(new GenericHeader(true,"User updated"),listUser);
				} else {
					userResponse = new UserResponse(new GenericHeader(false,"User not found"));
				}
			} else {
				userResponse = new UserResponse(new GenericHeader(false,"Access Denied"));
			}
		} catch (Exception e) {
			userResponse = new UserResponse(new GenericHeader(false,"Exception: " + e.getMessage()));
			e.printStackTrace();
		}
		
		return userResponse;
	}
	
	@GetMapping("/allRoles")
	public RoleResponse showAllRoles(){
		List<Role> listRole= roleRepository.findAll();
		return new RoleResponse(new GenericHeader(true,"Roles founded"),listRole);
	}
	

	@PostMapping(value="/login")
	@ResponseBody
	public User userLogin(@RequestBody User user) {
		return userUtil.getLoginResponse(user);
	}
	
}