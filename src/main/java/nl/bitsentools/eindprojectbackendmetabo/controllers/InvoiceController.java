package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/invoices")

public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceOutputDto>> getAllInvoices(){
        List<InvoiceOutputDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("{id}")
    public ResponseEntity<InvoiceOutputDto>getOneInvoice(@PathVariable("id") Long id){
        InvoiceOutputDto invoiceOutputDto = invoiceService.getOneInvoiceById(id);
        return ResponseEntity.ok(invoiceOutputDto);
    }



    @PostMapping
    public ResponseEntity<InvoiceOutputDto>createInvoice(@Valid @RequestBody InvoiceInputDto invoiceInputDto, @AuthenticationPrincipal UserDetails userDetails) {

        InvoiceOutputDto savedInvoice = invoiceService.createInvoice(invoiceInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedInvoice.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedInvoice);
    }


    @PutMapping("/{id}")
    public ResponseEntity<InvoiceOutputDto>updateInvoice(@PathVariable("id") Long id, @Valid @RequestBody InvoiceInputDto updateInvoice){
        InvoiceOutputDto dto = invoiceService.updateInvoice(id, updateInvoice);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteInvoice(@PathVariable("id")Long id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

}
