package com.iktpreobuka.project.controllers;

// 1.2 u paketu com.iktpreobuka.project.controllers napraviti
//klasu UserController sa metodom getDB() koja vraća listu
//svih korisnika aplikacije

/*
• Implementirati autentifikaciju u projektu
• Dozvoliti pristup endpoint-ima na osnovu odgovarajuce uloge korisnika
• Napomena: Po potrebi kreirati dodatne entitete
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
// import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.iktpreobuka.project.entities.EUserRole;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repositories.UserRepository;


@RestController
@RequestMapping("/project/users")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public UserEntity createUser(@RequestParam String firstName,
			@RequestParam String email) {
		UserEntity user = new UserEntity();
		user.setEmail(email);
		user.setFirstName(firstName);
		userRepository.save(user);
		return user;
	}
	
//	// Veljko
//	@GetMapping("/{id}")
//	public List<UserEntity> findAllUsers(){
//		return getDB();
//	}

	
	// TODO 1.2: REST endpoint koji vraća listu SVIH korisnika aplikacije
	// putanja: /admin
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	private List<UserEntity> getDB() {
		List<UserEntity> users = new ArrayList<>();
			UserEntity u1 = new UserEntity(1, "Mira", "Mirić", "mira.miric@ff.uns.ac.rs", "mira", "mirka", EUserRole.ROLE_CUSTOMER, null, null, null, null);
			UserEntity u2 = new UserEntity(2, "Dragam", "Dragić", "dragicd@uns.ac.rs",	"dejan", "debe", EUserRole.ROLE_CUSTOMER, null, null, null, null);
			UserEntity u3 = new UserEntity(3, "Bojan", "Bojić", "bb@gmail.com",	"nikola", "niko", EUserRole.ROLE_CUSTOMER, null, null, null, null);
			users.add(u1);
			users.add(u2);
			users.add(u3);
	return users;
	}
	
//	// Veljko
//	@GetMapping("/{id}")
//	public Optional<UserEntity> findUserById(@PathVariable Integer id){
//		return users.stream().filter(x -> x.getId().equals(id).findFirst();
//	}
	
	
	
	// TODO 1.3: REST endpoint koji vraća listu korisnika aplikacije
	// putanja: /project/users
	@RequestMapping(method = RequestMethod.GET, path = "/getAll")
	public List<UserEntity> getAll(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
//	// Veljko
//	@GetMapping("/id")
//	public Optional<UserEntity> findUserById(@PathVariable Integer id){
//		return users.stream().filter(x -> x.getId().equals(id).findFirst());
//	}
	
	
	
	// TODO 1.4: REST endpoint koji vraća listu korisnika po vrednosti prosleđenog ID
	// putanja: /project/users/{id}
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public List<UserEntity> getOne (@PathVariable Integer id) {
		for (UserEntity ue : getDB()) {
			if(ue.getId().equals(id))
				return (List<UserEntity>) ue;
		}
		return null;
	}
	
	
	
	// TODO 1.5: REST endpoint koji omogućava dodavanje novog korisnika
	// putanja: /project/users
	// user role: ROLE_CUSTOMER
	// vraća dodatog korisnika
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public UserEntity createNewUser(@RequestBody UserEntity newUser) {
		List<UserEntity> users = getDB();
		newUser.setId(new Random().nextInt());
		newUser.setEuserRole(EUserRole.ROLE_CUSTOMER);
		users.add(newUser);
		return newUser;
	}
	
//	// Veljko
//	@PostMapping("/")
//	public String addUser(@RequestBody UserEntity user) {
//		Integer id = Math.abs(new Random().nextInt());
//		UserEntity newUser = new UserEntity(id, user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),  user.getEmail(), user.getEuserRole());
//		if(!newUser.equals(null)) {
//			users.add(newUser);
//			return "Successfully added new user" + newUser.toString();
//		}
//		return "Something went wrong!";
//	}
	
	
	
	// TODO 1.6: REST endpoint koji omogućava izmenu postojećeg korisnika
	// putanja: /project/users/{id}
	// vraća NULL ako se prosledi ID nepostojećeg korisnika
	// vraća korisnika s izmenjenim vrednostima
	// ne menjati USER ROLE i PASSWORD
	// ne dozvoljavamo promenu: USERNAME
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserEntity modifyUser(@RequestBody UserEntity modifiedUser, 
			@PathVariable Integer id) {
		for (UserEntity ue : getDB()) {
			if(!userRepository.existsById(id)) 
				return null;
			if(ue.getId().equals(id)) {
				if(modifiedUser.getEmail() != null) {
					ue.setEmail(modifiedUser.getEmail());
				}
				if(modifiedUser.getFirstName() != null) {
					ue.setFirstName(modifiedUser.getFirstName());
				}
				if(modifiedUser.getLastName() != null) {
					ue.setLastName(modifiedUser.getLastName());
				}
			return modifiedUser;
			}
		}
		return null;
	}
	
	
	
	// TODO 1.7: REST endpoint koji omogućava izmenu atributa USER_ROLE postojećeg korisnika
	// putanja: /project/users/change/{id}/role/{role}
	// vraća NULL ako se prosledi ID nepostojećeg korisnika
	// vraća korisnika s izmenjenom vrednošću USER_ROLE
	@RequestMapping(method = RequestMethod.PUT, value = "/change/{id}/role/{role}")
	public UserEntity modifyUserRole (@PathVariable Integer id, @PathVariable EUserRole role) {
		for (UserEntity ue :getDB()) {
			if (ue.getId().equals(id)) {
				ue.setEuserRole(role);
			}
			return ue;
		}
		return null;
	}
	
	// Stefan:
//	@RequestMapping(value = "/change/{id}/role/{role}")
//	public UserEntity newUserRole(@PathVariable Integer id, @PathVariable EUserRole role) {
//		if(!userRepository.existsById(id))
//			return null;
//		UserEntity user = userRepository.findById(id).get();
//		user.setEuserRole(role);
//		return userRepository.save(user);
//	}
	
//	// Veljko:
//	@PutMapping("/change/{id}/role/{role}")
//	public UserEntity updateUserRole (@PathVariable Integer id, @PathVariable Integer role) {
//		UserEntity userDB = findUserById(id).get();
//		if(userDB.equals(null))
//			return null;
//		EUserRole newRole = EUserRole.getRoleByInt(role);
//		if(role.equals(null)) {
//			throw new IllegalArgumentException("Role not found!");
//		}
//		userDB.setEuserRole(newRole);
//		return userDB;
//	}
	
	
	
	// TODO 1.8: REST endpoint koji omogućava izmenu PASSWORD postojećeg korisnika
	// putanja: /project/users/changePassword/{id}
	// kao RequestParam proslediti vrednosti stare i nove lozinke
	// vraća NULL ako se prosledi ID nepostojećeg korisnika
	// vraća korisnika s izmenjenom vrednošću PASSWORD
	// da bi se stara lozinka mogla zameniti novom, vrednost stare lozinke mora da se poklapa sa starom lozinkom prosleđenom kroz RequestParam
	@RequestMapping(method = RequestMethod.PUT, value = "/changePassword/{id}")
	public UserEntity modifyPassword(@PathVariable Integer id,
									@RequestParam String oldPassword,
									@RequestParam String newPassword) {
		for (UserEntity ue : getDB()) {
			if(ue.getId().equals(id)) {
				if(ue.getPassword().equals(oldPassword)) {
					ue.setPassword(newPassword);
				}
			return ue;
			}
		}
		return null;
	}
	
	
	
	// TODO 1.9: REST endpoint koji omogućava brisanje postojećeg korisnika
	// putanja: /project/users/{id}
	// vraća NULL ako se prosledi ID nepostojećeg korisnika
	// vraća podatke o korisniku koji je obrisan
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public UserEntity deleteUser(@PathVariable Integer id) {
		List<UserEntity> users = getDB();
		Iterator<UserEntity> it = users.iterator();
		while (it.hasNext()) {
			UserEntity ue = it.next();
			if(ue.getId().equals(id)) {
				it.remove();
				return ue;
			}
		}
		return null;
	}
	
//	// Veljko:
//	@DeleteMapping("/id")
//	public String updateUserPswd (@PathVariable Integer id) {
//		UserEntity userDB = findUserById(id).get();
//		if(userDB.equals(null))
//			return null;
//		users.remove(userDB);
//		return "Successully deleted user " + userDB.toString();
//	}
	
	
	
	// TODO 1.10: REST endpoint koji vraća korisnika po vrednosti prosleđenog USERNAME
	// putanja: /project/users/by-username/{username}
	// vraća NULL ako se prosledi USERNAME nepostojećeg korisnika
	@RequestMapping(method = RequestMethod.GET, path = "/by-username/{username}")
	public UserEntity getOne (@PathVariable String username) {
		for (UserEntity ue : getDB()) {
			if(ue.getUsername().equals(username))
				return ue;
		}
		return null;
	}
	
//	// Veljko
//	@GetMapping("/by-username/{username}")
//	public Optional<UserEntity> findByUsername (@PathVariable String username){
//		return users.stream().filter(x -> x.getUsername().equals(username)).findFirst();
//	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/public")
	public List<UserEntity> getPublic(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/private")
	public List<UserEntity> getPrivate(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public List<UserEntity> getAdmin(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
	
}