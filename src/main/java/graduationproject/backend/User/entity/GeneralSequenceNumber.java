package graduationproject.backend.User.entity;

import graduationproject.backend.Admin.payload.request.SellerDTO;

import java.lang.reflect.Field;

public class GeneralSequenceNumber {
    public static void main(String[] args) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setAddress("asdasd");
        sellerDTO.setCity("asdasd");
        sellerDTO.setCompanyName("asdasd");
        sellerDTO.setPhone("asdasd");
        System.out.println(sellerDTO.toString());
        Seller seller = new Seller();

    }
}