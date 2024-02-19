package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            invoiceOutputDtoList.add(transferToDto(invoice));
        }
        return invoiceOutputDtoList;
    }

    //GET-BYID

    //POST

    //PUT

    //DELETE

    //2 METHODE VAN INVOICE NAAR DTO
    //VAN DTO NAAR INVOICE
}
