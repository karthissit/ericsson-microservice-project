package com.ericsson.shoppingcart_service.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "root")
public class ShoppingCartXmlRequest {
    @XmlElement(name="customerId")
    private long customerId;
}
