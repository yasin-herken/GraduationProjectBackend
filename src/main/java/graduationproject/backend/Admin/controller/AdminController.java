package graduationproject.backend.Admin.controller;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Admin.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("BASdas");
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSeller(@RequestBody SellerDTO user) throws Exception {
        return adminService.createSeller(user);
    }

}
