package graduationproject.backend.Admin.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.repository.UserRepository;
import graduationproject.backend.User.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final SellerService sellerService;

    @Autowired
    private final UserRepository userRepository;

    public ResponseEntity<?> createSeller(SellerDTO user) throws ResourceNotFoundException {
        Seller sellerDb = sellerService.createSeller(user);
        if (sellerDb != null)
            sellerService.saveSeller(sellerDb);
        return ResponseEntity.ok("Successfully Added");
    }

    public ResponseEntity<?> getSellers() {
        return ResponseEntity.ok(sellerService.getSellers());
    }

    public ResponseEntity<?> updateSeller(Long id, SellerDTO sellerDTO) throws ResourceNotFoundException {
        ResponseEntity<?> seller = sellerService.updateSeller(id, sellerDTO);
        if (seller.getBody() != null && seller.getStatusCode().is2xxSuccessful()){
            sellerService.saveSeller((Seller) seller.getBody());
        }
        Seller sellerDb = (Seller) seller.getBody();
        SellerDTO sellerDTOResponse = new SellerDTO();
        sellerDTOResponse.setAddress(sellerDb.getAddress());
        sellerDTOResponse.setCity(sellerDb.getCity());
        sellerDTOResponse.setCompanyName(sellerDb.getCompanyName());
        sellerDTOResponse.setPhone(sellerDb.getPhone());
        sellerDTOResponse.setId(sellerDb.getId());
        return ResponseEntity.ok(sellerDTOResponse);
    }

    public ResponseEntity<?> deleteSeller(Long id) throws ResourceNotFoundException {
        sellerService.deleteSeller(id);
        return ResponseEntity.ok("Successfully Deleted");
    }

    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
