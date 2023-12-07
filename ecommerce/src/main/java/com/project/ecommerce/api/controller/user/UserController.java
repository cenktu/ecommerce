package com.project.ecommerce.api.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Address;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.model.dao.AddressDAO;

@RestController
@RequestMapping("/user")
public class UserController {

	private AddressDAO addressDAO;

	public UserController(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	
	@GetMapping("/{userId}/address")
	public ResponseEntity<List<Address>> getAddress(@AuthenticationPrincipal ModelUser user, @PathVariable Long userId){
		
		if(!userHasPermission(user, userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(addressDAO.findByUserId(userId));
	}
	
	private boolean userHasPermission(ModelUser user,Long id) {
		return user.getId().equals(id);
	}
	@GetMapping("/{userId}/address/{addressId}")
	public ResponseEntity<Address> getOneAddress(@AuthenticationPrincipal ModelUser user, @PathVariable Long userId, @PathVariable Long addressId){
		if(!userHasPermission(user, userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Optional<Address> optionalAddress = addressDAO.findById(addressId);
		if (optionalAddress.isPresent()) {
		      Address address = optionalAddress.get();
		      if (address.getUser().getId().equals(userId)) {
		        return ResponseEntity.ok(address);
		      }
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{userId}/address")
	public ResponseEntity<Address> putAddress(@AuthenticationPrincipal ModelUser user, @PathVariable Long userId, @RequestBody Address address){
		if(!userHasPermission(user, userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		address.setId(null);
		ModelUser refUser = new ModelUser();
		refUser.setId(userId);
		address.setUser(refUser);
		return ResponseEntity.ok(addressDAO.save(address));
	}
	
	@PatchMapping("/{userId}/address/{addressId}")
	public ResponseEntity<Address> updateAddress(@AuthenticationPrincipal ModelUser user, @PathVariable Long userId, @PathVariable Long addressId, @RequestBody Address address){
		if(!userHasPermission(user, userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		if(address.getId().equals(addressId)) {
			Optional<Address> optUserAddress = addressDAO.findById(addressId);
			if(optUserAddress.isPresent()) {
				ModelUser orgUser= optUserAddress.get().getUser();
				if(orgUser.getId()==userId) {
					address.setUser(orgUser);
					return ResponseEntity.ok(addressDAO.save(address));
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}
	@DeleteMapping("/{userId}/address/{addressId}")
	public ResponseEntity<String> deleteAddress(@AuthenticationPrincipal ModelUser user,@PathVariable Long userId, @PathVariable Long addressId){
		if(!userHasPermission(user, userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Optional<Address> addressToDelete = addressDAO.findById(addressId);
		if(addressToDelete.isPresent()) {
			ModelUser addressUser = addressToDelete.get().getUser();
			if(addressUser.getId().equals(userId)) {
				addressDAO.deleteById(addressId);
				 return ResponseEntity.ok("Address deleted successfully!.");
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
}
