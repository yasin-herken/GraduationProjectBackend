package graduationproject.backend.Admin.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.Role.entity.ERole;
import graduationproject.backend.Role.entity.Role;
import graduationproject.backend.Role.repository.RoleRepository;
import graduationproject.backend.User.entity.CustomUser;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.repository.SellerRepository;
import graduationproject.backend.User.repository.UserRepository;
import graduationproject.backend.User.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final SellerService sellerService;

    public ResponseEntity<?> createSeller(SellerDTO user) throws ResourceNotFoundException {
        Seller sellerDb = sellerService.createSeller(user);
        if(sellerDb != null)
            sellerService.saveSeller(sellerDb);
        return ResponseEntity.ok("Successfully Added");
    }
}
