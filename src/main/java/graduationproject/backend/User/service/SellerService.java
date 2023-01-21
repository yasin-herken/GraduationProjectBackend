package graduationproject.backend.User.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Role.entity.ERole;
import graduationproject.backend.Role.entity.Role;
import graduationproject.backend.User.entity.CustomUser;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.repository.SellerRepository;
import graduationproject.backend.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SellerService {

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final UserRepository userRepository;

    public Seller  createSeller(SellerDTO sellerDTO){
        CustomUser user =userRepository.findById(sellerDTO.getId()).orElse(null);
        if(user == null){
            return null;
        }

        return new Seller(user, sellerDTO.getCompanyName(),sellerDTO.getAddress(),sellerDTO.getCity(),sellerDTO.getPhone());
    }

    public void saveSeller(Seller seller){
        sellerRepository.save(seller);
    }
}
