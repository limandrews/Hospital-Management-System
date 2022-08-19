package com.yeshua.clinicapp.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yeshua.clinicapp.models.User;
import com.yeshua.clinicapp.repositories.RoleRepository;
import com.yeshua.clinicapp.repositories.UserRepository;
 
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	public List<User> allDoctors() {
		ArrayList<User> allUsers = new ArrayList<User>();
		allUsers.addAll(userRepository.findAll());
		ArrayList<User> allDoctors = new ArrayList<User>();
		for (User oneUser: allUsers) {
			if (oneUser.getRoles().get(0).getName().contains("ROLE_DOCTOR")) {
				allDoctors.add(oneUser);
			}
		}
		return allDoctors;
	}
	
	public List<User> allAdmins() {
		ArrayList<User> allUsers = new ArrayList<User>();
		allUsers.addAll(userRepository.findAll());
		ArrayList<User> allAdmins = new ArrayList<User>();
		for (User oneUser: allUsers) {
			if (oneUser.getRoles().get(0).getName().contains("ROLE_ADMIN")) {
				allAdmins.add(oneUser);
			}
		}
		return allAdmins;
	}
	
	public List<User> allPatients() {
		ArrayList<User> allUsers = new ArrayList<User>();
		allUsers.addAll(userRepository.findAll());
		ArrayList<User> allPatients = new ArrayList<User>();
		for (User oneUser: allUsers) {
			if (oneUser.getRoles().get(0).getName().contains("ROLE_PATIENT")) {
				allPatients.add(oneUser);
			}
		}
		return allPatients;
	}
	
    // 1
    public void saveUserWithPatientRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_PATIENT"));
        userRepository.save(user);
    }
    
    public void saveUserWithDoctorRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_DOCTOR"));
        userRepository.save(user);
    }    
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
}