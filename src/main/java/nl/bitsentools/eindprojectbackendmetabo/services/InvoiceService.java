package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.InvoiceRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
        InvoiceModel invoiceModel = new InvoiceModel();
        InvoiceModel invoice = transferToInvoice(invoiceModel, createInvoiceDto);

//        //Berekening BTW
//
//        double vatAmount;
//        if (createInvoiceDto.getVatRate() == 9){
//            vatAmount = invoice.getNetPriceWithoutVat() * 0.09;
//        } else {
//            vatAmount = invoice.getNetPriceWithoutVat() * 0.21;
//        }
//        double totalPriceWithoutVat = invoiceModel.getNetPriceWithoutVat();
//        double totalPrice = invoice.getNetPriceWithoutVat() + vatAmount;
//
//        //aantal producten moet uit order komen icm type en prductnummerzxx
//
////        double totalAmountWithoutVat = totalPriceWithoutVat * quantity;
////        double totalAmountWithVat = totalPriceWithVat * quantity;
//
//        // Zet de totale prijzen inclusief en exclusief BTW in het factuurmodel
////        invoice.setTotalPrice(totalAmountWithVat);
////        invoice.setNetPriceWithoutVat(totalAmountWithoutVat);


        // Berekening BTW
        double vatRate = createInvoiceDto.getVatRate();
        double vatAmount = invoice.getNetPriceWithoutVat() * (vatRate / 100);

        // Store the VAT amount separately
        if (vatRate == 9) {
            invoice.setVat9ProductPrice(vatAmount);
            invoice.setVat21ProductPrice(0.0); // Set 0 for 21% VAT if it's 9% VAT
        } else {
            invoice.setVat21ProductPrice(vatAmount);
            invoice.setVat9ProductPrice(0.0); // Set 0 for 9% VAT if it's 21% VAT
        }

        double totalPriceWithoutVat = invoiceModel.getNetPriceWithoutVat();
        double totalPrice = invoice.getNetPriceWithoutVat() + vatAmount;



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
            invoiceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new RecordNotFoundException("Invoice with id :" + id + "is not found");
        }
    }

    //2 METHODE VAN INVOICE NAAR DTO

    public InvoiceModel transferToInvoice(InvoiceModel invoice, InvoiceInputDto dto){
//        var invoice = new InvoiceModel();

//        product.setId(dto.id);
       invoice.setInvoiceId(dto.invoiceId);
       invoice.setProductName(dto.productName);
       invoice.setTotalPrice(dto.totalPrice);
       invoice.setVat21ProductPrice(dto.vat21ProductPrice);
       invoice.setVat9ProductPrice(dto.vat9ProductPrice);
       invoice.setNetPriceWithoutVat(dto.netPriceWithoutVat);
       invoice.setVatRate(dto.vatRate);
       invoice.setUserId(dto.userId);
       invoice.setUserAddress(dto.userAddress);
       invoice.setProductWarranty(dto.productWarranty);
       invoice.setWarrantyInMonths(dto.warrantyInMonths);
       invoice.setDateOfPurchase(dto.dateOfPurchase);

       //berekening VAT

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
        dto.setNetPriceWithoutVat(invoiceModel.getNetPriceWithoutVat());
        dto.setVatRate(invoiceModel.getVatRate());
        dto.setUserId(invoiceModel.getUserId());
        dto.setUserAddress(invoiceModel.getUserAddress());
        dto.setWarrantyInMonths(invoiceModel.getWarrantyInMonths());
        dto.setDateOfPurchase(invoiceModel.getDateOfPurchase());
        dto.setTotalPrice(invoiceModel.getTotalPrice());

        //deze omzetten naar product

//HIER VATBEREKENING TOEVOEGEN.

        return dto;
    }

}
