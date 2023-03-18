package graduationproject.backend.PurchasingProduct.service;

import graduationproject.backend.Order.repository.OrderRepository;
import graduationproject.backend.Page.payload.response.PageResponse;
import graduationproject.backend.PurchasingProduct.dto.OrderResponseDTO;
import graduationproject.backend.PurchasingProduct.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasingProductService {

    private final OrderRepository orderRepository;

    public PageResponse<OrderResponseDTO> getOrders(Integer pageSize, String sortBy, String direction, Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(direction), sortBy);
        List<OrderResponseDTO> orderResponses = new ArrayList<>();
        orderRepository.findAll(pageable).stream().forEach(
                child -> {
                    OrderResponseDTO orderResponse = OrderMapper.INSTANCE.toOrderResponseDTO(child);
                    orderResponses.add(orderResponse);
                }
        );
        PageResponse<OrderResponseDTO> pageResponse = new PageResponse<>();
        pageResponse.setTotalRecords(orderRepository.count());
        pageResponse.setData(orderResponses);
        return pageResponse;
    }
}
