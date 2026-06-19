package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.User;
import com.ecommerce.payload.AddressDTO;

public interface AddressService {

	AddressDTO createAddress(AddressDTO addressDTO, User user);

	List<AddressDTO> getAddresses();

	AddressDTO getAddressesById(Long addressId);

	List<AddressDTO> getUserAddresses(User user);

	AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

	String deleteAddress(Long addressId);

}
