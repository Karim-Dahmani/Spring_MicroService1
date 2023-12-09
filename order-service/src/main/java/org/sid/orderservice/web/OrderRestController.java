package org.sid.orderservice.web;

import lombok.AllArgsConstructor;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.repository.OrderRepository;
import org.sid.orderservice.repository.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClientService;
import org.sid.orderservice.service.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order =orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItemList().forEach(p -> p.setProduct(inventoryRestClientService.productById(p.getId())));
        return order;
    }
}