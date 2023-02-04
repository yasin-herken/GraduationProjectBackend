package graduationproject.backend.Admin.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.Page.payload.response.PageResponse;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.mapper.SellerMapper;
import graduationproject.backend.User.payload.response.UsersResponse;
import graduationproject.backend.User.repository.SellerRepository;
import graduationproject.backend.User.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static graduationproject.backend.User.entity.specification.UserSpecification.getAllUsers;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final SellerService sellerService;
    @Autowired
    private final SellerRepository sellerRepository;

    public ResponseEntity<?> createSeller(SellerDTO user) throws ResourceNotFoundException {
        Seller sellerDb = sellerService.createSeller(user);
        if (sellerDb != null)
            sellerService.saveSeller(sellerDb);
        return ResponseEntity.ok("Successfully Added");
    }

    public ResponseEntity<?> updateSeller(Long id, SellerDTO sellerDTO) throws ResourceNotFoundException {
        ResponseEntity<?> seller = sellerService.updateSeller(id, sellerDTO);
        if (seller.getBody() != null && seller.getStatusCode().is2xxSuccessful()) {
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
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sellerRepository.delete(seller);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getUsers(Integer pageSize, String sortBy, String direction, Integer page) {
        Pageable paging;
        if (direction.equalsIgnoreCase("ASC")) {
            paging = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        }

        List<UsersResponse> usersResponseList = new ArrayList<>();
        sellerRepository.findAll(getAllUsers(), paging).stream().forEach(
                child -> {
                    UsersResponse users = SellerMapper.INSTANCE.userToResponse(child);
                    usersResponseList.add(users);
                }
        );

        PageResponse<UsersResponse> pageResponse = new PageResponse();
        pageResponse.setTotalRecords(sellerRepository.count());
        pageResponse.setData(usersResponseList);
        return ResponseEntity.ok(pageResponse);
    }
}
