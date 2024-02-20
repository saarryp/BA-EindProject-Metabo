package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    //  GET-ALL

    public List<InvoiceOutputDto>getAllInvoices(){
        List<InvoiceModel> invoiceList = invoiceRepository.findAll();
        List<InvoiceOutputDto> invoiceOutputDtoList = new ArrayList<>();
        for(InvoiceModel invoiceModel : invoiceList) {
            invoiceOutputDtoList.add(transferToDto(invoiceModel));
        }
        return invoiceOutputDtoList;
    }

    //GET-BYID

    public InvoiceOutputDto getOneInvoiceById(Long id){
        Optional<InvoiceModel> invoiceModelOptional = invoiceRepository.findById(id);
        if (invoiceModelOptional.isPresent()){
            return  transferToDto(invoiceModelOptional.get());
        } else {
            throw new RecordNotFoundException("Invoice with id :" + id + "was not found.");
        }
    }

    //POST

    public InvoiceOutputDto createInvoice(InvoiceInputDto createInvoiceDto) {
        InvoiceModel invoice = transferToInvoice(createInvoiceDto);
        invoiceRepository.save(invoice);
        return transferToDto(invoice);
    }

    //PUT
//    public InvoiceOutputDto updateStock(Long id, InvoiceInputDto invoiceInputDto) {
//        InvoiceModel.existingInvoice
//                = invoiceRepository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("Invoice with id: " + id + " not found"));
//
//        transferToInvoice(existingInvoice, invoiceInputDto);
//
//        invoiceRepository.save(existingInvoice);
//        return transferToDto(existingInvoice);
//    }

    //PUT

    //DELETE

    //2 METHODE VAN INVOICE NAAR DTO

    public InvoiceModel transferToInvoice( InvoiceInputDto dto){
        var invoice = new InvoiceModel();

//        product.setId(dto.id);
       invoice.setInvoiceId(dto.invoiceId);
       invoice.setProductName(dto.productName);
       invoice.setTotalPrice(dto.totalPrice);
       invoice.setVat21ProductPrice(dto.vat21ProductPrice);
       invoice.setVat9ProductPrice(dto.vat9ProductPrice);
       invoice.setUserId(dto.userId);
       invoice.setUserAddress(dto.userAddress);
       invoice.setProductWarranty(dto.productWarranty);
       invoice.setWarrantyInMonths(dto.warrantyInMonths);
       invoice.setDateOfPurchase(dto.dateOfPurchase);

        return invoice;
    }

    public InvoiceOutputDto transferToDto(InvoiceModel invoiceModel){
        InvoiceOutputDto dto = new InvoiceOutputDto();

        dto.setId(invoiceModel.getId());
        dto.setInvoiceId(invoiceModel.getInvoiceId());
        dto.setProductName(invoiceModel.getProductName());
        dto.setTotalPrice(invoiceModel.getTotalPrice());
        dto.setVat21ProductPrice(invoiceModel.getVat21ProductPrice());
        dto.setVat9ProductPrice(invoiceModel.getVat9ProductPrice());
        dto.setUserId(invoiceModel.getUserId());
        dto.setUserAddress(invoiceModel.getUserAddress());
        dto.setWarrantyInMonths(invoiceModel.getWarrantyInMonths());
        dto.setDateOfPurchase(invoiceModel.getDateOfPurchase());

        //deze omzetten naar product



        return dto;
    }

    //VAN DTO NAAR INVOICE
}
