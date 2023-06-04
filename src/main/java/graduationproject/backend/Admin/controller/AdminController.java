package graduationproject.backend.Admin.controller;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PutMapping("/sellers/{id}")
    public ResponseEntity<?> updateSeller(@PathVariable Long id, @RequestBody SellerDTO sellerDTO) throws Exception {
        return adminService.updateSeller(id, sellerDTO);
    }

    @DeleteMapping("/sellers/{id}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long id) {
        return adminService.deleteSeller(id);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return adminService.getUsers(pageSize,sortBy,direction,page);
    }

}
