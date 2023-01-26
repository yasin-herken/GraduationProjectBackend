package graduationproject.backend.Admin.controller;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @PostMapping("/sellers/add")
    public ResponseEntity<?> createSeller(@RequestBody SellerDTO user) throws Exception {
        return adminService.createSeller(user);
    }

    @GetMapping("/sellers")
    public ResponseEntity<?> getSellers() {
        return adminService.getSellers();
    }

    @PutMapping("/sellers/{id}")
    public ResponseEntity<?> updateSeller(@PathVariable Long id, @RequestBody SellerDTO sellerDTO) throws Exception {
        return adminService.updateSeller(id, sellerDTO);
    }

    @DeleteMapping("/sellers/{id}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long id) throws Exception {
        return adminService.deleteSeller(id);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return adminService.getUsers();
    }

}
