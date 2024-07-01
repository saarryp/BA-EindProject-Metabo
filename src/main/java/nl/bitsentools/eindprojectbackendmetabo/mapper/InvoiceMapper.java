package nl.bitsentools.eindprojectbackendmetabo.mapper;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;

public class InvoiceMapper {

    public static InvoiceModel transferToInvoiceModel(InvoiceInputDto dto) {
        InvoiceModel invoiceModel = new InvoiceModel();
        invoiceModel.setInvoiceId(dto.getInvoiceId());
        invoiceModel.setTotalPrice(dto.getTotalPrice());
        invoiceModel.setVat21ProductPrice(dto.getVat21ProductPrice());
        invoiceModel.setVat9ProductPrice(dto.getVat9ProductPrice());
        invoiceModel.setNetPriceWithoutVat(dto.getNetPriceWithoutVat());
        invoiceModel.setVatRate(dto.getVatRate());
        invoiceModel.setUserId(dto.getUserId());
        invoiceModel.setUserAddress(dto.getUserAddress());
        invoiceModel.setProductWarranty(dto.isProductWarranty());
        invoiceModel.setDateOfPurchase(dto.getDateOfPurchase());



        return invoiceModel;
    }
}
