package com.example.r2dbc_mvc_webflux.controller;

import com.example.r2dbc_mvc_webflux.model.*;
import com.example.r2dbc_mvc_webflux.model.custom_query_return.*;
import com.example.r2dbc_mvc_webflux.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@RestController
@RequestMapping("/itStore")
public class ITStoreController {

    @Autowired
    ProductsRepository productRepository;
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    CustomerEmployeeSupplierRepository customerEmployeeSupplierRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/products")
    public Flux<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Products> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    @GetMapping("/product_max_profit")
    public Flux<CategoryProductProfit> getProductWithMaxProfit() {
        return productRepository.getProductWithGreatestProfitInCategory();
    }
    @GetMapping("/category_profit")
    public Flux<CategoryProfit> getCategoryProfit() {
        return productRepository.findProfitForEachCategory();
    }

    @GetMapping("/characteristics")
    public Flux<Characteristic> getAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    @GetMapping("/characteristics_for_product")
    public Flux<Characteristic> getCharacteristicsForProduct(@RequestParam(name = "productId") Integer productId) {
        return characteristicRepository.findCharacteristicByIdProd(productId);
    }

    @GetMapping("/customer_employee_suppliers")
    public Flux<CustomerEmployeeSupplier> getAllCustomerEmployeeSupplier() {
        return customerEmployeeSupplierRepository.findAll();
    }
    @GetMapping("/employee")
    public Flux<String> getEmployeeWithGreatestNrOfInvoices() {
        return customerEmployeeSupplierRepository.findEmployeeWithGreatestNrOfInvoices();
    }

    @GetMapping("/ces_by_type")
    public Flux<CustomerEmployeeSupplier> getCustomerEmployeeSupplierByTypeCES(@RequestParam(name = "type") char type) {
        return customerEmployeeSupplierRepository.findCustomerEmployeeSupplierByTypeCES(type);
    }

    @GetMapping("/invoices")
    public Flux<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/max_invoice")
    public Mono<Float> getMaxInvoice() {
        return invoiceRepository.findMaxValue();
    }

    @GetMapping("/month_with_greatest_sale")
    public Flux<MonthSale> getMonthWithGreatestSale() {
        return invoiceRepository.getMonthWithGreatestSales();
    }

    @GetMapping("/sales_cost")
    public Flux<SalesCostMonthYear> getSalesCost() {
        return invoiceRepository.findSalesCostMonthYear();
    }

    @GetMapping("/profit_on_each_product")
    public Flux<ProfitOnEachProduct> getProfitOnEachProduct() {
        return invoiceRepository.findProfitOnEachProduct();
    }

    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/items_between_dates")
    public Flux<ProdQuantity1> getItemsBetweenDates() {
        return  itemRepository.findItemsBetweenDates();
    }

}
