package com.rewards.api.Link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorStoreService {
    @Autowired
    private VendorStoreRepository vendorStoreRepository;

    public List<VendorStoreEntity> getAllVendorShopLinks() {
        return vendorStoreRepository.findAll();
    }

    public Optional<VendorStoreEntity> getVendorShopLinkById(Long id) {
        return vendorStoreRepository.findById(id);
    }

    public VendorStoreEntity saveVendorShopLink(VendorStoreEntity vendorShopLink) {
        return vendorStoreRepository.save(vendorShopLink);
    }

    public void deleteVendorShopLink(Long id) {
        vendorStoreRepository.deleteById(id);
    }
}
