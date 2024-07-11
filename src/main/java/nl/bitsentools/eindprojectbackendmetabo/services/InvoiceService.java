package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final WarrantyRepository warrantyRepository;
    private final UserRepository userRepository;
    private final WarrantyService warrantyService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, WarrantyRepository warrantyRepository, UserRepository userRepository, WarrantyService warrantyService, ProductRepository productRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.warrantyRepository = warrantyRepository;
        this.userRepository = userRepository;
        this.warrantyService = warrantyService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    //  GET-ALL

    public List<InvoiceOutputDto> getAllInvoices() {
        List<InvoiceModel> invoiceList = invoiceRepository.findAll();
        List<InvoiceOutputDto> invoiceOutputDtoList = new ArrayList<>();
        for (InvoiceModel invoiceModel : invoiceList) {
            invoiceOutputDtoList.add(transferToDto(invoiceModel));
        }
        return invoiceOutputDtoList;
    }

    //GET-BYID

    public InvoiceOutputDto getOneInvoiceById(Long id) {
        Optional<InvoiceModel> invoiceModelOptional = invoiceRepository.findById(id);
        if (invoiceModelOptional.isPresent()) {
            return transferToDto(invoiceModelOptional.get());
        } else {
            throw new RecordNotFoundException("Invoice with id :" + id + "was not found.");
        }
    }

    //POST

    public InvoiceOutputDto createInvoice(InvoiceInputDto createInvoiceDto) {
        InvoiceModel invoiceModel = new InvoiceModel();
        InvoiceModel invoice = transferToInvoice(invoiceModel, createInvoiceDto);


        // Berekening BTW
        double vatRate = createInvoiceDto.getVatRate();
        double totalNetPriceWithoutVat = 0.0;

        // Controleer of de orders lijst niet null is
        List<OrderInputDto> orders = createInvoiceDto.getOrders();
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("De orders lijst mag niet null of leeg zijn.");
        }


        // Bereken de totale nettoprijs zonder BTW over alle producten op de factuur
        for (OrderInputDto orderDto : orders) {
            ProductModel product = productRepository.findByProductNumber(orderDto.getProductNumber())
                    .orElseThrow(() -> new RecordNotFoundException("Product met productnummer: " + orderDto.getProductNumber() + " niet gevonden."));

            double productNetPriceWithoutVat = product.getPrice() * orderDto.getQuantity();
            totalNetPriceWithoutVat += productNetPriceWithoutVat;
        }


        //berekening btw bedrag
        double vatAmount = invoice.getNetPriceWithoutVat() * (vatRate / 100);

        if (vatRate == 9) {
            invoice.setVat9ProductPrice(vatAmount);
            invoice.setVat21ProductPrice(0.0);
        } else {
            invoice.setVat21ProductPrice(vatAmount);
            invoice.setVat9ProductPrice(0.0);
        }

//       instellen nettoprijs en totale prijs zonder btw

         invoice.setNetPriceWithoutVat(totalNetPriceWithoutVat);
        double totalPrice = invoice.getNetPriceWithoutVat() + vatAmount;
         invoice.setTotalPrice(totalPrice);

        invoice.setTotalPrice(totalPrice);


        invoiceRepository.save(invoice);
        return transferToDto(invoice);
    }

    //PUT
    public InvoiceOutputDto updateInvoice(Long id, InvoiceInputDto updateDto) {
        InvoiceModel existingInvoice
                = invoiceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Invoice with id: " + id + " not found"));

        transferToInvoice(existingInvoice, updateDto);

        invoiceRepository.save(existingInvoice);
        return transferToDto(existingInvoice);
    }


    //DELETE

    public void deleteInvoice(Long id) {
        try {

            Optional<InvoiceModel> i = invoiceRepository.findById(id);
            if (i.isPresent()){
                InvoiceModel imodel = i.get();
                imodel.setOrderModel(null);
            }
            invoiceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Invoice with id :" + id + "is not found");
        }
    }


    public InvoiceModel transferToInvoice(InvoiceModel invoice, InvoiceInputDto dto) {

        invoice.setInvoiceId(dto.invoiceId);
        invoice.setTotalPrice(dto.totalPrice);
        invoice.setVat21ProductPrice(dto.vat21ProductPrice);
        invoice.setVat9ProductPrice(dto.vat9ProductPrice);
        invoice.setNetPriceWithoutVat(dto.netPriceWithoutVat);
        invoice.setVatRate(dto.vatRate);
        invoice.setProductWarranty(dto.productWarranty);
        invoice.setDateOfPurchase(dto.dateOfPurchase);

        return invoice;
    }

    public InvoiceOutputDto transferToDto(InvoiceModel invoiceModel) {
        InvoiceOutputDto dto = new InvoiceOutputDto();

        dto.setId(invoiceModel.getId());
        dto.setInvoiceId(invoiceModel.getInvoiceId());
        dto.setTotalPrice(invoiceModel.getTotalPrice());
        dto.setVat21ProductPrice(invoiceModel.getVat21ProductPrice());
        dto.setVat9ProductPrice(invoiceModel.getVat9ProductPrice());
        dto.setNetPriceWithoutVat(invoiceModel.getNetPriceWithoutVat());
        dto.setVatRate(invoiceModel.getVatRate());
        dto.setUserId(invoiceModel.getUserId());
        dto.setUserAddress(invoiceModel.getUserAddress());
        dto.setProductWarranty(invoiceModel.isProductWarranty());
        dto.setDateOfPurchase(invoiceModel.getDateOfPurchase());
        dto.setTotalPrice(invoiceModel.getTotalPrice());

        return dto;
    }

    public void assignWarrantyToInvoice(Long id, Long warrantyId) {
        var optionalInvoice = invoiceRepository.findById(id);
        var optionalWarranty = warrantyRepository.findById(warrantyId);

        if (optionalInvoice.isPresent() && optionalWarranty.isPresent()) {
            var invoice = optionalInvoice.get();
            var warranty = optionalWarranty.get();

            invoiceRepository.save(invoice);
        } else {
            throw new RecordNotFoundException("Warranty or invoice is not found.");
        }
    }
}
