package com.ericsson.shoppingcart_service.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlRootElement(name="root")
public class ShoppingCartXmlWrapper {
    List<ShoppingCartResponse> shoppingCartResponses;
}
